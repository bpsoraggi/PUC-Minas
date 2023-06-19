void main() {
    int velMax=0, velCarro=0;

    //leitura das variaveis
    printf("Digite a velocidade maxima permitida: ");
    scanf("%d", &velMax);
    printf("Digite a velocidade do carro: ");
    scanf("%d", &velCarro);

    //verificacao da velocidade
    if (velCarro <= velMax) {
        printf("Motorista respeitou a lei");
    } else if (velCarro - velMax <= 10) {
        printf("Motorista foi multado em R$ 50,00");
    } else if (velCarro - velMax <= 30) {
        printf("Motorista foi multado em R$ 100,00");
    } else if (velCarro - velMax > 30) {
        printf("Motorista foi multado em R$ 200,00");
    }

}