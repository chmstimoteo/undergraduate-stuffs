

abstract sig Boolean {}

one sig True extends Boolean {}
one sig False extends Boolean {}

sig Data {
   dia: Int
}

sig Filme {
   locado: Boolean
}

sig Locacao {
   aluguel: Filme -> Data
}

sig Cliente {
   aluguel: set Locacao
}

fact {
   all c:Cliente | all l: Locacao {
			l in c.aluguel
      }
}

run {} for 3

//Sistema de Locadora de filmes
//Estoque de Loja
//Sequencia
//
