module arvore

sig Node {
	nos: set Node
}
sig Tree { root: Node }

fact {
	//Todo nó pertence a uma árvore
	all n: Node | one t: Tree | n in t.root.*nos

	//Evita ciclos na árvore
    all t: Tree | all n: t.root.*nos | not n in n.^nos

	// Dois nós diferentes não possuem filhos em comum
	all t: Tree | all disj n,n': t.root.*nos | no n.nos & n'.nos

   //Toda arvore tem ao menos uma folha e pode ter nos caule ou nao.
   all t:Tree | some n: t.root.*nos | lone n': t.root.*nos {
      no n.nos
      #n'.nos > 0
   }
}

pred show[] {
	one Tree
//	#Tree.root.nos > 1
 }
run show for 5 but 5 Node
