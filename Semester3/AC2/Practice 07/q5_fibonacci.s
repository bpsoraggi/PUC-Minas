fib:
    # se zero
    beq a0, zero, retZero

    # subtrai 1 de a0 e armazena em s0
    addi s0, a0, -1

    # subtrai 2 de a0 e armazena em s1
    addi s1, a0, -2

    # se um
    beq s0, zero, um

    # se nao
    j fibRec

    um:
        # armazena 1 em a0
        addi a0, zero, 1
        ret

    # se nao
    fibRec:
        add a0, s0, s1
        ret

    # return zero
    retZero:
        ret


main:
    # Le um numero do teclado e armazena em a0
    addi t0, zero, 4   # escolhe a operacao de leitura de inteiro (4)
    ecall              # efetua a operacao de leitura de inteiro

    addi sp, sp, -4
    sw ra, 0(sp)
    call fib

    # Imprime o valor da adicao na tela
    addi t0, zero, 1   # escolhe a operacao de escrita de inteiros
    ecall

    lw ra, 0(sp)
    addi sp, sp, 4
    ret