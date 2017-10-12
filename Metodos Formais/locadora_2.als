module locadora

abstract sig Status { }
one sig Alugado extends Status { }
one sig Disponivel extends Status { }
sig Filme { }
sig Cliente { }
sig Locadora {
	filmes: Filme -> Status,
	locacoes: Cliente -> Filme
}

fact {
	// Todo filme está em alguma locadora
	all f: Filme | some l: Locadora | f in l.filmes.Status

	// Todo cliente está em alguma locadora
	all c: Cliente | some l: Locadora | c in l.locacoes.Filme
}

pred locOk [l:Locadora] {	
	// Dois clientes de uma mesma locadora não podem estar
	// com o mesmo filme
	all disj c, c': l.locacoes.Filme | no c.(l.locacoes) & c'.(l.locacoes)

	// Se um cliente está com um filme, o estatos desse filme
	// é alugado
	all f: l.filmes.Status | f in Cliente.(l.locacoes)
		implies f.(l.filmes) = Alugado

	// Se um filme não está com nenhum cliente, seu status é Disponível
	all f: l.filmes.Status | not f in Cliente.(l.locacoes)
		implies f.(l.filmes) = Disponivel
}

pred alugar[l, l': Locadora, c: Cliente, f: Filme] {
	f in l.filmes.Disponivel
	l'.filmes = l.filmes - f -> Disponivel + f -> Alugado
	l'.locacoes = l.locacoes + c -> f
}
run alugar for 3

pred devolver[l, l': Locadora, c: Cliente, f: Filme] {
	f in l.filmes.Alugado and c in l.locacoes.f
	l'.filmes = l.filmes - f -> Alugado + f -> Disponivel
	l'.locacoes = l.locacoes - c -> f
}
run devolver for 5

assert testeLocOk {
    all l1,l2:Locadora, c:Cliente, f:Filme {
        locOk[l1] and alugar[l1,l2,c,f] implies locOk[l2]
		locOk[l1] and devolver[l1,l2,c,f] implies locOk[l2]
    }
}
check testeLocOk for 5

pred show[] {
	one Locadora
	#filmes > 1
	#locacoes > 1
}
run show for 15 but 4 Cliente, 5 Filme
