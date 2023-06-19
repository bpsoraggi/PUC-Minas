int main(void) {
  float num, s;
  printf("Digite um número inteiro e positivo:\n");
  scanf("%f",&num);
  for (float x=1; x<=num; x++) {
    s+=1/x;
    printf("S %.0f = %.2f\n", x, s);
  }
  printf ("Valor final de S: %.2f\n", s);
  return 0;
}
