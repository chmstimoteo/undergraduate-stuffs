abstract sig Disc {
     prereq : set Disc
}

sig DiscObrig extends Disc {
}

sig DiscElet extends Disc {
}

sig DiscBasico in Disc {
}

fact {
     all d:Disc { not d in d.^prereq }
     all d:DiscObrig { 
          no e:DiscElet | e in d.prereq
     }
     all e:DiscElet { #e.prereq > 0}
}

sig Curso {
    grade : some Disc,
    cargaHoraria : Int
}

fact {
    all c:Curso {
        some o:DiscObrig | o in c.grade
        #c.grade > c.cargaHoraria
        c.cargaHoraria > 0
        c.cargaHoraria > #(c.grade & DiscObrig)
    }
    all d:Disc {
         some c : Curso | d in c.grade
    }
    not preReqEstrangeiro
}

pred preReqEstrangeiro { 
          some d1,d2:Disc, c:Curso {
                d1 in c.grade
                not d2 in c.grade
                d2 in d1.prereq
          }
}

sig Aluno {
    estuda : Curso,
    cursadas,pagando: set Disc
}
fact {
    all a:Aluno {
         a.pagando in a.estuda.grade
         a.cursadas in a.estuda.grade
         a.pagando.prereq in a.cursadas
         a.pagando & a.cursadas = none
    }
}

pred aptoASeFormar[a:Aluno] {
    a.cursadas & DiscObrig = a.estuda.grade & DiscObrig
    #(a.cursadas) >= a.estuda.cargaHoraria
}

pred matricula[a,a':Aluno,  d:Disc]  {
     not d in a.pagando 
     a'.pagando = a.pagando + d
     a'.estuda = a.estuda
     a'.cursadas = a.cursadas
}

pred passou[a,a':Aluno, d:Disc] {
      a'.cursadas = a.cursadas + d
      a.pagando = a'.pagando + d
      a'.estuda = a.estuda
}

run matricula for 3

check {
  no a:Aluno {
         aptoASeFormar[a] && a.cursadas=none 
  }
}for 10

run { some a,a':Aluno, d:DiscElet {
                d in a'.pagando 
                passou[a,a',d]
           }
} for 5

run { some d:DiscElet   | d in DiscBasico
          some d:DiscObrig | d in DiscBasico
          some d:DiscElet   | not d in DiscBasico
          some d:DiscObrig | not d in DiscBasico
            } for 4

run {} for 2 but exactly 4 Disc
