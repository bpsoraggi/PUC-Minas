float verificador(int n) {
  if (n<0) {
    printf("False: seu número é negativo.\n\n");
  } else if (n>0) {
    printf("True: seu número é positivo.\n\n");
  }
}

int main(void) {
  int num, i=1;
  do {
    printf("Número %d (digite 0 para parar): \n", i);
    scanf("%d", &num);
    verificador(num);
    i++;
  } while (num!=0);
  printf("Fim do programa\n");
  return 0;
}
