//Crie o arquivo p5.asm e resolva na linguagem assembly vista em sala de aula:
//Carregue o array a seguir na memória e faça a transposição do array:

//R0=8
//R1=2
//R2=4
//R3=3
//R4=2
//R5=4

@0
D=M
@10
M=D
@5
D=M
@0
M=D
@10
D=M
@5
M=D

@1
D=M
@10
M=D
@4
D=M
@1
M=D
@10
D=M
@4
M=D

@2
D=M
@10
M=D
@3
D=M
@2
M=D
@10
D=M
@3
M=D

(FIM)
@FIM
D;JMP