int t = 10;
void recebe(int vnotas[10]) {
  for (int n=0; n<t; n++) {
    printf("Digite a nota do aluno %d:\n", n+1);
    scanf("%d", &vnotas[n]);
  }
}

void media(int vnotas2[10]) {
  int soma=0, cont=0;
  float media=0;
  for (int n=0; n<t; n++) {
    soma+=vnotas2[n];
  }
  media=soma/t;
  for (int n=0; n<t; n++) {
    if (vnotas2[n]>media) {
      cont++;
    }
  }
  printf("A média é: %.2f\n", media);
  printf("O número de alunos acima da média é: %d", cont);
}

int main(void) {
  int v[10];
  recebe(v);
  media(v);
  return 0;
}
