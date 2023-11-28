int main(void) {
  int termo_n, fib=1, fib1=1, fib2=2;
  printf("Digite o valor de L:\n");
  scanf("%d", &termo_n);
  printf("Termo 1: 0\n");
  printf("Termo 2: 1\n");
  for (int x=3; x<=termo_n; x++) {
    if (fib<termo_n) {
      printf("Termo %d: %d\n", x, fib);
    }
    fib=fib1+fib2;
    fib1=fib2;
    fib2=fib;
  }
  return 0;
}
