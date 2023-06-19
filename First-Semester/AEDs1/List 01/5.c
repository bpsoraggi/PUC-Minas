void main() {
    float valorDiaria=0;

    //leitura das variaveis
    printf("Digite o valor da diaria: ");
    scanf("%f", &valorDiaria);

    //vezes 0.75 pq tem 25% de desconto
    printf("O valor da diaria promocional e: %.2f\n", valorDiaria*0.75);
    //vezes 60 pq 80% de 75 e 60
    printf("O valor total arrecadado com 80%% de ocupacao e diaria promocional e: %.2f\n", valorDiaria*0.75*60);
    //vezes 37 pq 50% de 75 e 37
    printf("O valor total arrecadado com 50%% de ocupacao e diaria promocional e: %.2f\n", valorDiaria*0.75*37);
    //diferenca entre valores
    printf("A diferenca entre esses dois valores e: %.2f\n", valorDiaria*0.75*60-valorDiaria*0.75*37);
}