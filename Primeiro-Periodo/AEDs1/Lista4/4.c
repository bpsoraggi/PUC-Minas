int resto_divisao(int num, int deno) {
  if (num / deno == 0) {
    return (num);
  } else {
    return (resto_divisao(num - deno, deno));
  }
}

int main(void) {
  int n, d;
  printf("Digite o numerador e o denominador:\n");
  scanf("%d %d", &n, &d);
  printf("D resto da divisão é: %d\n", resto_divisao(n, d));
  return 0;
}
