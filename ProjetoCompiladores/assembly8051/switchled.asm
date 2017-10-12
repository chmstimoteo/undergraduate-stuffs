	org 0
inicio:
	mov A,P2
	mov R0, #00h
volta: 
	inc R0
	cjne A,00h,volta
	mov P1, R0
	sjmp inicio
	end
