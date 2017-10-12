--Exercicios 3

expF f = \x -> f(f(x))

expnF f x 1= f(x)
expnF f x enesima = f(expnF f x (enesima-1))
 

derivada f x d = (f(x+d)-f(x))/d

derivSin = derivada sin
derivCos = derivada cos

integral f a b dx 0 = 0 
integral f a b dx it= f(a+delta)*dx + (integral f a b dx (it-1))
			where
			delta = ((b-a)/it)   
