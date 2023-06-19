ledois:
    # Le um numero do teclado e armazena em a0
    addi t0, zero, 4   # escolhe a operacao de leitura de inteiro (4)
    ecall              # efetua a operacao de leitura de inteiro

    # armazena o valor digitado em s0
    add a1, zero, a0

    # Le um numero do teclado e armazena em a0
    addi t0, zero, 4   # escolhe a operacao de leitura de inteiro (4)
    ecall              # efetua a operacao de leitura de inteiro
    ret

somadois:
    sw ra, 0(sp)
    call ledois
    add a0, a0, a1
    lw ra, 0(sp)
    addi sp, sp, 4
    ret

main:
    addi sp, sp, -8
    sw ra, 4(sp)
    call somadois

    # Imprime o valor da adicao na tela
    addi t0, zero, 1   # escolhe a operacao de escrita de inteiros
    ecall

    lw ra, 0(sp)
    addi sp, sp, 4
    ret
