main:
    # Le um numero do teclado e armazena em a0
    addi t0, zero, 4   # escolhe a operacao de leitura de inteiro (4)
    ecall              # efetua a operacao de leitura de inteiro

    # Armazena o valor digitado em s0
    add s0, a0, zero

    # Soma 2 ao valor de a0
    addi a0, a0, 2

    # Se o valor lido for igual a zero, return
    beq s0, zero, FIM

    # Imprima o valor de a0 na tela
    addi t0, zero, 1   # escolhe a operacao de escrita de inteiro (1)
    ecall
    j main
FIM:
    ret