int main(void) {
  int in=5; //inteiro
  float re=5.8; //real
  char ca='a'; //caracter

  int *inPtr=&in;
  float *rePtr=&re;
  char *caPtr=&ca;

  printf("Valor de in: %d\n", in);
  printf("Endereço de in: %p\n", &in);
  printf("Valor de inPtr: %d\n", *inPtr);
  printf("Endereço de inPtr: %p\n\n", &inPtr);
  
  printf("Valor de re: %f\n", re);
  printf("Endereço de re: %p\n", &re);
  printf("Valor de rePtr: %f\n", *rePtr);
  printf("Endereço de rePtr: %p\n\n", &rePtr);

  printf("Valor de ca: %c\n", ca);
  printf("Endereço de ca: %p\n", &ca);
  printf("Valor de caPtr: %c\n", *caPtr);
  printf("Endereço de caPtr: %p\n\n", &caPtr);
  
  printf("Digite um novo valor inteiro:\n");
  scanf("%d", inPtr);
  printf("Digite um novo valor real:\n");
  scanf("%f", rePtr);
  printf("Digite um novo caracter:\n");
  scanf(" %c", caPtr);

  printf("\nValor de in: %d\n", in);
  printf("Endereço de in: %p\n", &in);
  printf("Valor de inPtr: %d\n", *inPtr);
  printf("Endereço de inPtr: %p\n\n", &inPtr);
  
  printf("Valor de re: %f\n", re);
  printf("Endereço de re: %p\n", &re);
  printf("Valor de rePtr: %f\n", *rePtr);
  printf("Endereço de rePtr: %p\n\n", &rePtr);

  printf("Valor de ca: %c\n", ca);
  printf("Endereço de ca: %p\n", &ca);
  printf("Valor de caPtr: %c\n", *caPtr);
  printf("Endereço de caPtr: %p\n\n", &caPtr);
}
