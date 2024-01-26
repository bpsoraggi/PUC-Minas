struct Pessoa {
char nome[200];
int dia;
int mes;
};

int main(void) {
  int n=3;
  struct Pessoa p[3];
  for (int i=0; i<n; i++) {
    printf("\nDigite seu nome: \n");
    scanf("%s", p[i].nome);
    printf("Digite seu dia de aniversário: \n");
    scanf("%d", &p[i].dia);
    printf("Digite seu mês de aniversário: \n");
    scanf("%d", &p[i].mes);
  }
  for (int m=1; m<+12; m++) {
    printf("\nAniversariante(s) do mês %d:\n", m);
    for (int i=0; i<n; i++) {
      if (m==p[i].mes) {
        printf("%s no dia %d\n", p[i].nome, p[i].dia);
      }
    }
  }
  return 0;
}
