//Crie o arquivo p2.asm e resolva na linguagem assembly vista em sala de aula:
//if (R1==0):
//R0 = 0
//else:
//R0 = 1

@1
D=M
@if
D;JEQ
@0
M=1
@FIM
D;JMP
(if)
@0
M=0
(FIM)
@FIM
D;JMP
