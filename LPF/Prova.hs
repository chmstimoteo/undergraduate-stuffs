(h:(q:(t:y))) = [1,2,3]

type Ponto = (Int,Int)

f::Integer->Integer
f a = a+2

f1::Integer->Integer
f1 a = a+1

f2::Integer->Integer
f2 a = a+2


g::Integer->(Integer->Integer)
g a = f

w x = x+f x

o = \p-> p 10


--List Comprehension
list::[Integer]
list = [1,2,3,4,5]

leven1 [] = []
leven1 (h:t) = if((mod h 2)==0) then [h]++(leven1 t) else (leven1 t)

l1 l= [[a,a] | a <- l]

iterate1 x f = x:(iterate1 (f x) f)

pot n = [n^y | y <- [0..]]

soma a b = a+b

incrementa = soma 1

iteracao1 f 1 = f
iteracao1 f n = iteracao1 f (n-1).f 

livros [] p = []
livros bd p = aux bd p
		where
		 aux [] p = []
		 aux (h:t) p = if ((fst h)==p) then [(snd h)] ++ (aux t p) else (aux t p) 

livrosC bd p = [(snd l) | l<-bd , ((fst l)==p)]

procura l r = [reg | reg<-l ,(reg==r) ]

existe l r = if ((procura l r)==[]) then False else True

remover l r = [reg | reg<-l ,(reg/=r) ]

aplicafl l f v= [f x v | x<-l]

-----------------------------------------------------------------------------------------------------
--Tipos algebricos

--data Boll = True | False deriving (Show,Eq,Enum)
--data Numero = Int | Integer | Float  deriving (Show,Eq,Enum)
--data Letra = Char deriving (Show,Eq,Enum)
--data Frase = String | [Char] deriving (Show,Eq,Enum)

type Name = String
type Age = Int
data People = Person Name Age deriving Show

data Expr = Lit Int
		| Ad Expr Expr
		| Sub Expr Expr

evaluate (Lit n) = n
evaluate (Ad e1 e2) = (evaluate e1) + (evaluate e2)
evaluate (Sub e1 e2) = (evaluate e1) - (evaluate e2)

data List a = List a [List a]  deriving Show

mostraElem (List a []) = a
mostraElem (List a (h:t)) = a

mostraLst (List a []) = []
mostraLst (List a (h:t)) = (h:t)

--toList   List t   [t]
toList (List a []) = [a]
toList (List a l) = [a]++(aux l)
			where
			  aux [] = []
			  aux (h:t) = (toList (h))++(aux t) 

--fromList [t]   List t
--depth Tree t  Int
--colapse Tree t  [t]
data Tree a = Tree a [Tree a] deriving (Show,Eq)

mostraTip (Tree a []) = a
mostraTip (Tree a (h:t)) = a

mostraList (Tree a l) = l

mostraTipL [] = []
mostraTipL (h:t) = [mostraTip h]++(mostraTipL t)

					--Tree 1 [Tree 2 [],Tree 3 []]
colapse (Tree a []) = [a]
colapse (Tree a l) = [a]++aux l
			where
			 aux [] = []
			 aux (h:t) = (colapse h) ++ aux t  

--aux = avanca lateralmente na arvore.
--colapse = avanca verticalmente na arvore

--mapTree (t->u) Tree t  Tree u

mapTree f (Tree a []) = (Tree (f a) []) 
mapTree f (Tree a l1) = (Tree (f a) (aux f l1))
			where
			 aux f [] = []
			 aux f (h:t) = [(mapTree f h)]++(aux f t)

buscal (Tree a []) = [a]
buscal (Tree a l) = [a] ++ (aux (Tree a l))  

aux (Tree a []) = []
aux (Tree a l) = (mostraTipL l) ++ (aux1 l)

aux1 [] = []
aux1 (h:t) = (aux h) ++ (aux1 t) 

--[a] ++ mostraTipL (aux)