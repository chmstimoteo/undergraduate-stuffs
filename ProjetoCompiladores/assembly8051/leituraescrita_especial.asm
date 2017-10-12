	org 00h
	mov a, p3
	anl a, #01000011b
	mov p3,a
	ljmp 100h
	org 100h
	mov A,#10010000b
	mov ie, a
	mov A, #10110000b
	mov scon,a
	mov a, pcon
	setb acc.7
	mov pcon,a 
	mov a,tmod
	setb acc.4
	mov tmod,a
	
leia:
	mov A,p2
	cjne A,#0ffh, leia1
	jmp leia
	
leia1:
	cjne A,#11111011b,leia2
	setb p3.2
	jmp grava

leia2: 
	cjne A,#11110111b,leia3
	setb p3.3
	jmp grava

leia3:
	cjne A,#11101111b,leia4
	setb p3.4
	jmp grava

leia4:
	cjne A,#11011111b, leia
	setb p3.5
	jmp grava

grava:
	mov r0,a
	mov tl1, #0fh
	setb tr1
	jnb tf1,$
	clr tr1
	clr tf1
	mov A,p3
	anl a, #0100011b
	mov p3,a
	jmp leia

	org 23h
	setb p3.7
	mov a,sbuf
	cjne a, #0feh,limpa
	mov a,r0
	mov sbuf,a
espera:
	jnb TI,espera
	clr TI
limpa:
	clr RI
	mov a,p1
	reti
	end