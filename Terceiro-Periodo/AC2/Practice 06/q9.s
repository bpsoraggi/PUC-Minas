main:
    # armazena o valor 10 em s1 para auxiliar no loop
    addi s1, zero, 10

loop:
    # se s1 for igual a zero, pule para o fim
    beq s1, zero, FIM

    # Le um numero do teclado e armazena em a0
    addi t0, zero, 4   # escolhe a operacao de leitura de inteiro (4)
    ecall              # efetua a operacao de leitura de inteiro

    # Armazena o valor digitado em s0
    add s0, a0, zero

    # Soma 2 ao valor de a0
    addi a0, a0, 2

    # Decrementa em 1 o valor de s1 para o controle do loop
    addi s1, s1, -1

    # Imprima o valor de a0 na tela
    addi t0, zero, 1   # escolhe a operacao de escrita de inteiro (1)
    ecall
    j loop

FIM:
    ret
