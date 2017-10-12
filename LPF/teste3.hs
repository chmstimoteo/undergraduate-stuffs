--Exercicios Lazy Evaluation e Tipos de Dados.

acumula f [] = 0
acumula f (a:[]) = a
acumula f (h:t) = f h (acumula f t) 

soma a b  = a+b
mult a b = a*b

-- para a soma fica 0 e para mult fica 1.
acumula1 f l = acum f l 1
acum f [] p = [] 
acum f (h:t) p= a:(acum f t a)
		 where
		  a = f h p

data Carro = Carro String Int deriving Show

mille = Carro "KHD-0748" 2007
siena = Carro "KLF-2465" 2006

getPlaca (Carro p a) = p
getAno (Carro p a) = a

data Lista = Lista Int Lista deriving Show

l1 = Lista 1 l1