main:
	# fazer loop apenas 3 vezes
	addi s0, zero, 3

    # armazenar valor alto em s1
    addi s1, zero, 20000
	
	loop:
		# se s0 for 0, loop acaba
		beq s0, zero, fim
		
		# decrementa s0
		addi s0, s0, -1
		
		# ler numero do teclado
		addi t0, zero, 4
		ecall
		
		# se valor armazenado for menor
		# ou igual volta pro loop
		ble s1, a0, loop
		
		#se nao
		add s1, zero, a0
		j loop
	
	fim:
		# pega valor de s1
		add a0, s1, zero
	
		# imprimir a soma
		addi t0, zero, 1
		ecall
		ret
		
		
		