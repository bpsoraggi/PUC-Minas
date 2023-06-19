int main(void) {
  int total=0, n=0, neg=0, p=0, z=0, soma=0, pneg=0, pp=0, pz=0;
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
  soma=neg+p+z;
  pneg=(100*neg)/soma;
  pp=(100*p)/soma;
  pz=(100*z)/soma;
  printf("Porcentagem de negativos: %d\n", pneg);
  printf("Porcentagem de positivos: %d\n", pp);
  printf("Porcentagem de zeros: %d\n", pz);
  return 0;
}
