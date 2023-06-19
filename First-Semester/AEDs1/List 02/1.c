int main(void) {
  int total=0, n=0, neg=0, p=0, z=0;
  do {
    printf("Digite um número (digite 123 para parar):\n");
    scanf("%d", &n);
    if (n<0) {
      neg++;
    }
    if (n>0) {
      p++;
    }
    if (n==0) {
      z++;
    }
  } while (n!=123);
  printf("Quantidade de negativos: %d\n", neg);
  printf("Quantidade de positivos: %d\n", p);
  printf("Quantidade de zeros: %d\n", z);
  return 0;
}
