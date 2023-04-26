void main() {
    float sal=0;
    char op='x';

    //leitura das variaveis
    printf("Digite o valor do salario: ");
    scanf("%f", &sal);
    printf("\nDigite a opcao (a/b/c): ");
    scanf(" %c", &op);

    //calculo do salario
    switch(op) {
        case 'a':
            sal += sal*0.08;
        break;
        case 'b':
            sal += sal*0.11;
        break;
        case 'c':
            if (sal <= 1000) {
                sal += 350;
            } else if (sal > 1000) {
                sal += 200;
            }
        break;
        default:
            printf("Opcao invalida!");
        break;
    }

    printf("O valor do salario com aumento de 8%% e: %.2f", sal);
}