void main() {
    float a=0, b=0;

    //leitura das variaveis
    printf("Digite um numero: ");
    scanf("%f", &a);
    printf("Digite outro numero: ");
    scanf("%f", &b);

    //comparacao
    if (a > b) {
        printf("O maior numero e: %.1f", a);
    } else if (a < b){
        printf("O maior numero e: %.1f", b);
    } else {
        printf("Os numeros sao iguais");
    }
}