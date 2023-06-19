void main() {
    int ano=0;
    char resp='n';

    //leitura das variaveis
    printf("Digite o ano em que voce nasceu: ");
    scanf("%d", &ano);
    printf("Voce ja fez aniversario este ano? (s/n)");
    scanf(" %c", &resp);

    //comparacao
    if (resp == 's') {
        printf("Voce tem %d anos", 2023-ano);
    } else {
        printf("Voce tem %d anos", 2023-ano-1);
    }
}