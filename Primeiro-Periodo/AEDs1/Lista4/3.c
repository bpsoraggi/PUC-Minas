int divisao(int num, int deno) {
  if (num / deno == 0) {
    return 0;
  } else {
    return (1 + divisao(num - deno, deno));
  }
}

int main(void) {
  int n, d;
  printf("Digite o numerador e o denominador:\n");
  scanf("%d %d", &n, &d);
  printf("O resultado é: %d", divisao(n, d));
  return 0;
}
