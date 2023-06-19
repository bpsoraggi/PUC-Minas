int main(void) {
  int l, fib=0, fib1=0, fib2=1;
  printf("Digite o número de termos desejado:\n");
  scanf("%d", &l);
  printf("Termo 1 é: 0\n");
  printf("Termo 2 é: 1\n");
  for (int x=3; x<=l; x++) {
    fib=fib1+fib2;
    fib1=fib2;
    fib2=fib;
    printf("Termo %d é: %d\n", x, fib);
  }
  return 0;
}
