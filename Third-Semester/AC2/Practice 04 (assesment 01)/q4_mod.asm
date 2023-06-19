//Crie o arquivo p4.asm e resolva na linguagem assembly vista em sala de aula:
//R0 = R1 % R2

(inicio)
@1
D=M
@2
D=D-M
@while
D;JGE
@1
D=M
@0
M=D
D=0
@FIM
D;JLE
(while)
@2
D=M
@1
D=M-D
M=D
@inicio
D;JMP
(FIM)
@FIM
D;JMP