void main() {
    int a=0, b=0, soma=0;

    //leitura das variaveis
    printf("Digite um numero: ");
    scanf("%d", &a);
    printf("Digite outro numero: ");
    scanf("%d", &b);

    //soma 
    soma = a + b;

    //comparacao
    if (soma >= 10) {
        soma += 5;
    } else {
        soma+= 7;
    }

    //resultado
    printf("O resultado e: %d", soma);
}