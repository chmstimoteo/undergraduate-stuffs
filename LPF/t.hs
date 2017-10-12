--1 lista de EXercicios :

c=78

dobralista:: [Integer] -> [Integer]
dobralista [] = []
dobralista (a:t) = 2*a:dobralista t

inverso::[Integer]->[Integer] 
inverso [] = []
inverso (a:t) = (inverso t)++[a]
inverso _ = error "negative argument" 

--ordenacao [] = []
--ordenacao (a:t) |t==[]     =[a]
--		|otherwise if a<=(head t) then (a::t) else ((head t) ++ ordenacao ([a]++(tail t)))