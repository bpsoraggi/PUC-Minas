float media (int x) {
  int nota=0, soma=0, cont=0;
  float media=0;
  for (int y=1; y<=x; y++) {
     printf("Digite a nota:\n");
    scanf("%d", &nota);
    if (nota>=6.0) {
      soma+=nota;
      cont++;
    }
  }
  media=soma/cont;
  
  return media;
}
  
int main(void) {
  int nalunos=0;

  printf("Digite o número de alunos:\n");
  scanf("%d", &nalunos);

    printf("A média é: %.2f", media(nalunos));
return 0;
}
