void main() {
    int nota=0;

    //leitura das variaveis
    printf("Digite a nota do aluno: ");
    scanf("%d", &nota);

    //comparacao
    if (nota >= 8 && nota <= 10) {
        printf("Otimo");
    } else if (nota >=7 && nota < 8) {
        printf("Bom");
    } else if (nota >= 5 && nota < 7) {
        printf("Regular");
    } else if (nota < 5) {
        printf("Insatisfatorio");
    }
}