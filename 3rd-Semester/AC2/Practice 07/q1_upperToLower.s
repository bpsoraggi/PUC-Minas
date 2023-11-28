main:
    addi t0, zero, 5   # escolhe a operacao de leitura de char (5)
    ecall              # efetua a operacao de leitura

    # Armazena o valor digitado em s0
    add s0, a0, zero
    # Subtrai 32 ao valor para deixar char maiusculo
    addi a0, a0, -32

    # Imprima o valor de t0 na tela
    addi t0, zero, 2   # escolhe a operacao de escrita de char (2)
    ecall
    ret