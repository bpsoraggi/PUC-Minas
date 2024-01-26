fatorial:
    # caso base
	addi t0, zero, 1
	beq s0, t0, fim
    beq s0, zero, fim

    # calcular n-1
    addi t1, zero, -1
	add s1, s0, t1

    # decrementa s0
    addi s0, s0, -1

    multiplicacao:
        beq s1, zero, swap
        # decrementa s1
        addi s1, s1, -1

        add s2, s2, a0
        j multiplicacao

    swap:
        add a0, s2, zero
        add s2, zero, zero
        j fatorial

main:
	# salvar ra
	addi sp, sp, -4
	sw ra, 0(sp)

	# ler numero do teclado
	addi t0, zero, 4
	ecall

    # armazenar em s0
    add s0, a0, zero
	
	call fatorial

fim:
	# recuperar ra
	lw ra, 0(sp)
	addi sp, sp, 4
	
	# imprimir
	addi t0, zero, 1
	ecall
	ret
	