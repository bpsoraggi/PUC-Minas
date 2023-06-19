int main(void) {
  int a, b;

  printf("Digite dois números inteiros:\n");
  scanf("%d %d", &a, &b);

  if (&a>&b) {
    printf("O maior endereço de variável é A: %p\n", &a);
  } else if (&b>&a) {
    printf("O maior endereço de variável é B: %p\n", &b);
  }
  return 0;
}
