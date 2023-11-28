float formula(int n) {
  float s=0, resultado=0;
  for (int x=1; x<=n; x++) {
    s=((x*x)+1.0)/(x+3.0);
    resultado+=s;
  }
  return resultado;
}

int main(void) {
  int n;
  printf("Digite um valor inteiro e positivo para N\n");
  scanf("%d", &n);
  formula(n);
  printf("S=%.4f", formula(n));
  return 0;
}
