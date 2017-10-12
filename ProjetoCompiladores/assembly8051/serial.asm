	org 0
	mov scon,#52h
	mov A,pcon
	setb Acc.7
	mov pcon,A
	mov TMOD, #20h
	mov TH1, #0E6h
	setb TR1
	mov A, #7
	mov C,P
	mov TB8,C
	mov SBUF, A
	clr ri

wait2: 
	jnb ti, wait2
	clr ti
	mov sbuf, A

wait:
	jnb ri, wait
	clr ri
	mov P1,sbuf
	end