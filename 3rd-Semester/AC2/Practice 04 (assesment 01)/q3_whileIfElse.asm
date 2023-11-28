//Crie o arquivo p3.asm e resolva na linguagem assembly vista em sala de aula:
//while (R0 != R1):
//if (R0<R1):
//R0=R0+2
//else:
//R1=R1+1

(inicio)
@0
D=M
@1
D=D-M
@FIM
D;JEQ
@if
D;JLT
@else
D;JGT
(if)
@2
D=A
@0
D=D+M
M=D
@inicio
D;JMP
(else)
@1
D=A
D=D+M
M=D
@inicio
D;JMP
(FIM)
@FIM
D;JMP