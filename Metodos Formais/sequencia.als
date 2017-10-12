
open util/ordering[Sequencia]

sig Valor {}

sig Sequencia {
   sequencia: Int -> Valor
}

pred init[s: Sequencia] {
  no s.sequencia
}

pred add[s,s': Sequencia, v: Valor] {
   not (v in Int.(s.sequencia))
   s'.sequencia = s.sequencia + ((#s.sequencia) -> v)
}

fact {
   init[first[]]
   all s: Sequencia - last[] | let s' = next[s] | some v: Valor {
         add[s,s',v]
      }
}


run {} for 5
