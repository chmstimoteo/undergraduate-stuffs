module locadora_1

sig Filme { }
sig Cliente { }
sig Locadora {
	locacoes: Cliente -> Filme
}

fact {
	// Em uma mesma locadora, o mesmo filme não pode estar alugado
	// para dois clientes diferentes
	all l: Locadora {
		all disj c,c': Cliente {
			no { l.locacoes[c] & l.locacoes[c'] }
		}
	}
}

pred alugar[l, l': Locadora, c: Cliente, f: Filme] {
	not f in l.locacoes[Cliente]
	l'.locacoes = l.locacoes + c -> f
}
run alugar for 5

pred devolver[l,l': Locadora, c: Cliente, f: Filme] {
	f in l.locacoes[c]
	l'.locacoes = l.locacoes - c -> f
}
run devolver for 5

pred show[] { }
run show for 5
