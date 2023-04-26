void mediasal(int n) {
  int soma=0, sal=0, numfi;
  float media=0;
  for (int x=1; x<=n; x++) {
    printf("Digite o valor do salário do cidadão %d\n", x);
    scanf("%d", &sal);
    printf("Digite o número de filhos do cidadão %d\n", x);
    scanf("%d", &numfi);
    soma+=sal;
    media=(float)soma/(float)x;
  }
  printf("A média dos salários é: %.2f\n", media);
}

int main(void) {
  int numpes;
  printf("Digite o número de pessoas:\n");
  scanf("%d", &numpes);
  mediasal(numpes);
  return 0;
}
