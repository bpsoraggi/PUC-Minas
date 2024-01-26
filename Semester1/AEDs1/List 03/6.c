float fatorial(float n) {
  float f=1;
  for (int x = 1; x <= n; x++) {
    f *= x;
  }
  return f;
}
int main(void) {
  int n;
  float s=1;
  printf("Digite o valor de N\n");
  scanf("%d", &n);
  for (float i=1; i<=n; i++) {
    s+=1/fatorial(i);
    printf("Valor de S: %.20f\n", s);
  }
  printf("Valor final de S: %.20f\n", s);
return 0;
}
