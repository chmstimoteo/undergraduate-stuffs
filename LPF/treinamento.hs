data Tree a  = Null | Node a (Tree a) (Tree a) deriving (Show,Eq)

x = Node 2 (Node 3 (Node 1 Null Null) (Node 7 Null Null)) (Node 4 Null Null)

nosFolha Null = []
nosFolha ( Node a b c) 
    |(  b==Null && c==Null ) =  a:[] 
    |(  b /= Null && c/= Null ) = (nosFolha b )++(nosFolha c )
