--busca::(a->Bool)->[a]->Maybe a
--busca f l 
--	 | (l==[]) = []
--	 | otherwise =





elemento n [] = 0
elemento 0 (h:t) = h
elemento n (h:t) = elemento (n-1) t 

pertence n [] = False
pertence n (h:t) = if (h==n) then True else (pertence n t)

contaocorre n l = aux n l 0
			where
			  aux n [] v = v
			  aux n (h:t) v = if(h==n) then (aux n t (v+1)) else (aux n t v)  

maioresque n [] = []
maioresque n (h:t) = if(h>n) then [h]++(maioresque n t) else (maioresque n t)

remover n [] = []
remover n (h:t) = if (n==h) then (remover n t) else ([h]++(remover n t))

list n = [1..n]
geraltern n = aux (list n)
		where
			aux [] = []
			aux (h:t) = [h]++[-(h)]++(aux t)

existe n l= pertence n l

uniao [] l2 = l2
uniao l1 [] = l1
uniao l1 l2 = aux l1 l2
		where
		 aux [] l2 = l2
		 aux (h1:t1) l2 = if(existe h1 l2) then (aux t1 l2) else (aux t1 l2++[h1])

intersec [] l2 = []
intersec l1 [] = []
intersec (h:t) l2 = if (existe h l2) then ([h]++(intersec t l2)) else (intersec t l2)

