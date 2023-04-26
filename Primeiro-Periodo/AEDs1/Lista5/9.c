void calcCircunferencia (float R, float *compr, float *area) {

  *compr=2*3.14*R;
  *area=3.14*(R*R);
}


int main(void) {
  float raio, comprimento, a;
  printf("Digite o valor do raio:\n");
  scanf("%f", &raio);

  calcCircunferencia(raio, &comprimento, &a);
  printf("Comprimento: %f\n", comprimento);
  printf("Area: %f\n", a);
  
  return 0;
}
