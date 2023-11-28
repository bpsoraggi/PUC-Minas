main:
    # Le um numero do teclado e armazena em a0
    addi t0, zero, 4   # escolhe a operacao de leitura de inteiro (4)
    ecall              # efetua a operacao de leitura de inteiro

    # se for zero, vai pro final
    beq a0, zero, fim

    # checa se o numero e impar ou par
    andi s0, a0, 1
    beq s0, zero, par # se for par, pula

    # se for impar
    add t1, t1, a0
    j main

par:
    # se for impar
    add t2, t2, a0

    j main

fim:
    sub a0, t1, t2

    # Imprima o valor da subtracao na tela
    addi t0, zero, 1   # escolhe a operacao de escrita de inteiros
    ecall
    ret