//Crie o arquivo p6.asm e resolva na linguagem assembly vista em sala de aula:
//Ache o maior número armazenado na memória no intervalo RAM[0] a RAM[20] e salve em
//RAM[0]

(inicio)
@10
D=M
@0
M=D

@11
D=A
@2
M=D
@11
D=M
@1
M=D
@0
D=D-M
@if
D;JGT

@12
D=A
@2
M=D
@12
D=M
@1
M=D
@0
D=D-M
@if
D;JGT

@13
D=A
@2
M=D
@13
D=M
@1
M=D
@0
D=D-M
@if
D;JGT

@14
D=A
@2
M=D
@14
D=M
@1
M=D
@0
D=D-M
@if
D;JGT

@15
D=A
@2
M=D
@15
D=M
@1
M=D
@0
D=D-M
@if
D;JGT

@16
D=A
@2
M=D
@16
D=M
@1
M=D
@0
D=D-M
@if
D;JGT

@17
D=A
@2
M=D
@17
D=M
@1
M=D
@0
D=D-M
@if
D;JGT

@18
D=A
@2
M=D
@18
D=M
@1
M=D
@0
D=D-M
@if
D;JGT

@19
D=A
@2
M=D
@19
D=M
@1
M=D
@0
D=D-M
@if
D;JGT

@20
D=A
@2
M=D
@20
D=M
@1
M=D
@0
D=D-M
@if
D;JGT
@FIM
D;JMP

(if)
@1
D=M
@0
M=D
@2
D=M
D=D+1
@D
D;JMP

(FIM)
@FIM
D;JMP