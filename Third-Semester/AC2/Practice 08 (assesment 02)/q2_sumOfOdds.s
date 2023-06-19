lerNums:
	# ler numero do teclado
	addi t0, zero, 4
	ecall
	
    # se for zero, fim
	beq a0, zero, fim
	
	# checar se num = par
	andi s1, a0, 1
	bne s1, zero, somaImpares
	
	# se for par e not zero
	call lerNums

somaImpares:
	# armazena em s0
	add s0, s0, a0
	
	call LerNums

main:
	# salvar ra
	addi sp, sp, -4
	sw ra, 0(sp)
	
	call lerNums
	
fim:
	# recuperar ra
	lw ra, 0(sp)
	addi sp, sp, 4
	
	# pega valor de s0
	add a0, s0, zero
	
	# imprimir a soma
	addi t0, zero, 1
	ecall
	ret

	
	
	
	