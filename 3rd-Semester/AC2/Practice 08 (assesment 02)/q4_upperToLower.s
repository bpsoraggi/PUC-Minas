main:
	# le um char
	addi t0, zero, 5
	ecall
	
	# deixa minusculo
	addi a0, a0, 32
	
	# imprime char minusculo
	addi t0, zero, 2
	ecall
	ret