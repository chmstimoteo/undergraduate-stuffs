--Exercicio 10/08
raizQuadrada::Float->Float
raizQuadrada x = buscaBin 0 x x


media::Float->Float->Float
media x y= (x+y)/2


buscaBin::Float->Float->Float->Float
buscaBin i f x=let
			m=media i f
	       in
			busca i f x m


busca::Float->Float->Float->Float->Float
busca i f x m		|m^2==x  = m
			|m^2<x   = buscaBin m f x
			|m^2>x   = buscaBin i m x



sucessor x = x + 1

dobro x = 2*x

aplica10 f = f 10

somaF f g = \x -> (f x) + (g x)

h = somaF sucessor

compose f g = \x -> f(g x)

i= compose sucessor dobro

soma2F f g = \fa fb ga h-> f(fa) + f(fb) + g(ga) + aplica10 h 

j = (soma2F sucessor dobro) 

soma x y = x+y

soma2 x = \y -> x+y