	org 0
inicio:
	mov A,#0
	mov R0, #9	;contador ate 9
	djnz r0,$
	mov A, #0FFh
	sjmp inicio
	end
