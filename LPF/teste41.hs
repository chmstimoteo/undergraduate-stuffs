data Ponto = Qlqcoisa Float Float deriving Show

distancia::Ponto->Ponto->Float
distancia (Qlqcoisa x1 y1) (Qlqcoisa x2 y2) = sqrt(((x1-x2)^2)+((y1-y2)^2))

data Pontou = Qlqc Int
		| Qlqcoi Float

tipo::Pontou->Char
tipo(Qlqc i) = 'i'
tipo(Qlqcoi f) = 'f'

data Avaliacao = NaoFezFinal Prova Prova
		  |FezFinal Prova Prova Prova deriving Show

mediaFinal::Avaliacao-> Float
mediaFinal(NaoFezFinal n1 n2) = ((nota n1)+(nota n2))/ 2
mediaFinal(FezFinal n1 n2 n3) = ((((nota n1)+(nota n2))/2)+(nota n3))/ 2

passou::Avaliacao -> Bool
passou x = (mediaFinal x)>= 5

data Prova = PrimeiraChamada Float
		| SegundaChamada Float
		| Faltou deriving Show

nota::Prova->Float
nota (PrimeiraChamada f) = f
nota (SegundaChamada f) = f
nota Faltou = 0




EXERCISE:

*DEFINA uma estrutura(s) para representar a vida escolar de um aluno.
*Defina uam funcao que classifique um aluno como sendo:
	(a):: Passou por media em todas as disciplinas.
	(b):: Fez algum provas final mas nunca reprovou.
	(c):: Índice de reprovacao menor que 20%.
	(d):: O resto..............