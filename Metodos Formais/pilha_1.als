module pilha

sig Elmt { }
sig Node {
	elmt: Elmt,
	next: lone Node
}
sig Stack { top: Node }

fact {
	//Evita ciclo na pilha
	no n: Node | n in n.^next

	//Todo nó pertence a uma pilha
	all n: Node | one s: Stack | n in s.top.*next

	//Dois nós diferentes não podem ter o mesmo elemento
	//Ok, isso é apenas estética =P
	all disj n,n': Node { n.elmt != n'.elmt }

    //Um no nao pertence a duas pilhas
    all p: Stack, n: p.top.*next | some p': Stack {
       not (n in p'.top.*next)
    }
}

//assert listaCircular {
//	all s: Stack {
//		all n: s.top.*next | n not in n.^next
//	}
//}
//check listaCircular for 5

//Operação de inserir na pilha
pred push[s, s': Stack, n: Node] {
	not (n in s.top.*next)
    n.next = s.top
	s'.top = n
}

//Operação de remover da pilha
pred pop[s, s': Stack] {
    not (s.top in s'.top.*next)
	s'.top = s.top.next
}

run pop for 5
run push for 5
run {} for 5
