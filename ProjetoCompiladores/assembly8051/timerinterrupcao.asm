	org 0010h
	mov ie, #00000000b
	mov TMOD, #10h
	mov TH1, #high(45535)
	mov TL1, #low(45535)
	setb TR1
	JNB TF1,$
	CLR TR1
	CLR TF1
	
