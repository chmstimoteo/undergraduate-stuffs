--tipos polimórficos de data:

{---
#Esses 2 tipos tem q ser identicos ao anterior.
data Par a = Par a a   

ex: 
Par True False::Par Bool Bool
    Par 1 1.1::Fractional a => Par a a
    Par (Par 'a' 'b') (Par 'f' 'e') :: Par (Par Char)

data Par a b = Par a b

ex: Par 1 2 :: (Num a, Num b) => Par b a
    Par 2 2.9 :: (Fractional a, Num b) => Par b a
    Par True False :: Par Bool Bool
---}
f::Num a, b, c => a->b->c
f 1 2 = 3

g::Num a, b :=> a->b->b
g 1 x = x +1


//lista
data Lista a = Cons a (Lista a) | Null

//arvore	  Nó   Filhos
data Tree a = Tree a [Tree a]






