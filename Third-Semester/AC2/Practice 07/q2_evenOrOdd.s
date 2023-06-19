main:
    # Le um numero do teclado e armazena em a0
    addi t0, zero, 4   # escolhe a operacao de leitura de inteiro (4)
    ecall              # efetua a operacao de leitura de inteiro

    # Armazena o valor digitado em s0
    add s0, a0, zero

    # checa se o numero e impar ou par
    andi s1, s0, 1
    beq s1, zero, par # se for par, pula

    # Armazena 73 em t0 (letra I)
    addi a0, zero, 73

    # Imprima o valor de t0 na tela
    addi t0, zero, 2   # escolhe a operacao de escrita de char (2)
    ecall

    ret

par:
    # Armazena 80 em t0 (letra P)
    addi a0, zero, 80

    # Imprima o valor de t0 na tela
    addi t0, zero, 2   # escolhe a operacao de escrita de char (2)
    ecall

    ret
