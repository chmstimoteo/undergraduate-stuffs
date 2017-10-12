module grafo

abstract sig Grafo { }
sig Node extends Grafo { rel: set Node }

pred show[] {
	#rel > 1
}
run show for 15 but 6 Node
