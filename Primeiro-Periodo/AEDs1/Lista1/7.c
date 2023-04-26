void main() {
    float x=0, y=0;

    //leitura das variaveis
    printf("Digite o valor de x: ");
    scanf("%f", &x);

    //calculo de y
    if (x <= 1) {
        y = 1;
    } else if (x > 1 && x <= 2) {
        y = 2;
    } else if (x > 2 && x <= 3) {
        y = x*x;
    } else if (x > 3) {
        y = x*x*x;
    }

    printf("O valor de y e: %.2f", y);
}