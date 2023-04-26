void media (int x) {
  if (x<=39) {
    printf("Conceito F\n\n");
  } else if (40<=x&&x<=59) {
    printf("Conceito E\n\n");
  } else if (60<=x&&x<=69) {
    printf("Conceito D\n\n");
  } else if (70<=x&&x<=79) {
    printf("Conceito C\n\n");
  } else if (80<=x&&x<=89) {
    printf("Conceito B\n\n");
  } else if (x>=90) {
    printf("Conceito A\n\n");
  }
}

int main(void) {
  int n, rep=1;
  float med;
  
  printf("Digite o número de alunos:\n");
  scanf("%d", &n);
  do {
    printf("Digite sua média:\n");
    scanf("%f", &med);
    media(med);
    rep++;
  } while (rep<=n);
  printf("Fim do programa.\n");
  return 0;
}
