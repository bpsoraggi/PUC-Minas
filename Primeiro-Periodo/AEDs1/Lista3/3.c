void crescente (int a, int b, int c) {
  if (a<b&&b<c) {
    printf("Seus números em ordem crescente:%d %d %d\n\n", a, b, c);
  } else if (a<c&&c<b) {
    printf("Seus números em ordem crescente:%d %d %d\n\n", a, c, b);
  } else if (b<a&&a<c) {
    printf("Seus números em ordem crescente:%d %d %d\n\n", b, a, c);
  } else if (b<c&&c<a) {
    printf("Seus números em ordem crescente:%d %d %d\n\n", b, c, a);
  } else if (c<b&&b<a) {
    printf("Seus números em ordem crescente:%d %d %d\n\n", c, b, a);
  } else if (c<a&&a<b) {
    printf("Seus números em ordem crescente:%d %d %d\n\n", c, a, b);
  } else if (a==b&&a<c) {
    printf("Seus números em ordem crescente:%d %d %d\n\n", a, b, c);
  } else if (a<b&&b==c) {
     printf("Seus números em ordem crescente:%d %d %d\n\n", a, b, c);
  } else if (a==c&&c<b) {
     printf("Seus números em ordem crescente:%d %d %d\n\n", a, c, b);
  } else if (a==b&&a==c) {
     printf("Seus números em ordem crescente:%d %d %d\n\n", a, b, c);
  } else if (b==c&&c<a) {
     printf("Seus números em ordem crescente:%d %d %d\n\n", b, c, a);
  }
}

int main(void) {
  int num1, num2, num3, n, rep=1;
  
  printf("Digite o número desejado de conjuntos:\n");
  scanf("%d", &n);
  do {
    printf("Digite 3 números inteiros:\n");
    scanf("%d %d %d", &num1, &num2, &num3);
    crescente (num1, num2, num3);
    rep++;
  } while (rep<=n);
  printf("Fim do programa.\n");
  return 0;
}
