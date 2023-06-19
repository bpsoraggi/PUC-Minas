void main() {
    char simbolo='x';

    //leitura das variaveis
    printf("Digite o simbolo: ");
    scanf(" %c", &simbolo);

    //verificacao do simbolo
    switch(simbolo) {
        case '<':
            printf("SINAL DE MENOR");
        break;
        case '>':
            printf("SINAL DE MAIOR");
        break;
        case '=':
            printf("SINAL DE IGUAL");
        break;
        default:
            printf("OUTRO SINAL");
        break;
    }
}