void main() {
    float a=0, b=0, x=0;

    //leitura das variaveis
    printf("Digite o valor de a: ");
    scanf("%f", &a);
    printf("Digite o valor de b: ");
    scanf("%f", &b);

    //calculo de x
    x = -b/a;

    printf("O valor de x e: %.2f", x);
}