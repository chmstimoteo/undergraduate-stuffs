//Uma Pessoa tem amigos que tambem sao pessoas
sig Pessoa {
   amigos: set Pessoa,
   vizinhos: set Pessoa
}

pred VizinhancaReciproca {
   all p1, p2: Pessoa {
      p1 in p2.vizinhos => p2 in p1.vizinhos
   }
}

pred VizinhoSiMesmo {
   all p:Pessoa {
      not (p in p.vizinhos)
   }
}

pred AmizadeReciproca {
   all p1,p2:Pessoa {
      p1 in p2.amigos => p2 in p1.amigos
   }
}

pred NinguemAmigoSi {
   all p:Pessoa {
      not (p in p.amigos)
   }
}

pred CondicaoAmizade1 {
   // p1 só é amigo de p2, se p2 vizinho de p1 e existe um amigo de p1 que é amigo de p2
   all p1,p2:Pessoa {
      (p2 in p1.vizinhos && some p:p2.amigos { p in p1.amigos} ) =>
         p2 in p1.amigos
      --else
       --not (p2 in p1.amigos)
   }
}

pred CondicaoAmizade2 {
   //Todo vizinho tambem eh um amigo
   all disj p,p': Pessoa {
      p' in p.vizinhos => p' in p.amigos
   }
}

pred fazerAmigo[p, p', pn:Pessoa] {
   not (pn in p.amigos)
   p'.amigos = p.amigos + pn
}

pred perderAmigo[p,p',pn:Pessoa] {
   not (pn in p'.amigos)
   pn in p.vizinhos => (p.vizinhos = p'.vizinhos + pn && not (pn in p'.vizinhos))
   p.amigos = p'.amigos + pn
      
}

pred ehestranho {
   one pe: Pessoa | one p:Pessoa {
      (pe.*amigos &  p.*amigos) = none
   }
}

assert  vizinhonaoamigo {
   some p:Pessoa {
      (p.vizinhos = none) => (p.amigos = none)
   }
}

fact fact1{
   #Pessoa >= 0 =>
         AmizadeReciproca && NinguemAmigoSi 
         && VizinhancaReciproca && VizinhoSiMesmo
	     && CondicaoAmizade1 && CondicaoAmizade2
   all p: Pessoa {
      not (p.amigos = none)
   }
}

run perderAmigo for 5
run ehestranho for 5
check vizinhonaoamigo for 3


