data Ponto = Qlqcoisa Float Float deriving Show

distancia::Ponto->Ponto->Float
distancia (Qlqcoisa x1 y1) (Qlqcoisa x2 y2) = sqrt(((x1-x2)^2)+((y1-y2)^2))

data Pontou = Qlqc Int
		| Qlqcoi Float

tipo::Pontou->Char
tipo(Qlqc i) = 'i'
tipo(Qlqcoi f) = 'f'

data Avaliacao = ApMedia Float Float 
		  |Final Float Float Float deriving Show

mediaFinal::Avaliacao-> Float
mediaFinal(ApMedia n1 n2) = (n1+n2)/ 2
mediaFinal(Final n1 n2 n3) = (((n1+n2)/2)+n3)/ 2

passou::Avaliacao -> Bool
passou x = (mediaFinal x)>= 5
