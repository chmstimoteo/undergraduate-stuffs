ciclo1:
	mov a,p0
	anl a, #00001111b
	cjne a,00H, ciclo2

	sjmp ciclo1
ciclo2:
	mov r0,a
	sjmp caract_0

caract_0:
	cjne a, #0000b,caract_1
	mov p1,#00111111b
	sjmp ciclo1

caract_1:
	cjne a, #0001b,caract_2
	mov p1,#00111110b
	sjmp ciclo1

caract_2:
	cjne a, #0010b,caract_3
	mov p1,#01011011b
	sjmp ciclo1

caract_3:
	cjne a, #0011b,caract_4
	mov p1,#01001111b
	sjmp ciclo1

caract_4:
	cjne a, #0100b,caract_5
	mov p1,#01100110b
	sjmp ciclo1

caract_5:
	cjne a, #0101b,caract_6
	mov p1,#01101101b
	sjmp ciclo1

caract_6:
	cjne a, #0110b,pulo
	mov p1,#01111101b

pulo:
	ljmp ciclo1
	end
