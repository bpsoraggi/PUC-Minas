int main(void) {
  int vtemp[31]={15, 20, 22, 34, 19, 17, 36, 32, 16, 40, 19, 20, 22, 21, 24, 23, 20, 19, 18, 34, 33, 20, 21, 21, 20, 19, 19, 17, 20, 22, 20};
  int maiorTemp=0, menorTemp=40, cont=0, cont2=0;
  float media=0;
  
  for (int i=0; i<31; i++) {
    if (vtemp[i]>maiorTemp) {
      maiorTemp=vtemp[i];
    }
    if (vtemp[i]<menorTemp) {
      menorTemp=vtemp[i];
    }
    cont+=vtemp[i];
  }
  media=cont/31;
  for (int i=0; i<31; i++) {
    if (vtemp[i]<media) {
      cont2++;
    }
  }
  printf("A maior temperatura foi: %d\n", maiorTemp);
  printf("A menor temperatura foi: %d\n", menorTemp);
  printf("A temperatura média foi: %.2f\n", media);
  printf("Número de dias nos quais a temperatura foi inferior a temperatura média: %d\n", cont2);
  return 0;
}
