int main(void) {
 float sal=0, numfi=0, maiorsal=0, somafi=0, somasal=0, porsalcont=0, numpes=0, medsal=0, medfi=0, porsal=0;
  while (sal>=0) {
    printf("Digite seu salário:\n");
    scanf("%f", &sal);
    if (sal<0) {
      printf("Resultados:\n");
    } else if (numpes==0) {
      maiorsal=sal;
      somasal+=sal;
      printf("Digite o número de filhos:\n");
      scanf("%f", &numfi);
      somafi+=numfi;
      if (sal<=100) {
        porsalcont++;
      }
    } else {
      if (sal>maiorsal) {
        maiorsal=sal;
      }
      printf("Digite o número de filhos:\n");
      scanf("%f", &numfi);
      somafi+=numfi;
      somasal+=sal;
      if (sal<=100) {
        porsalcont++;
      }
    }
    numpes++;
  }
  numpes=numpes-1;
  printf("Número de cidadãos: %.0f\n", numpes);
  medsal=somasal/numpes;
  printf("A média do salário da população é: %.2f\n", medsal);
  medfi=somafi/numpes;
  printf("A média do número de filhos é: %.2f\n", medfi);
  printf("O maior salário é: %0.2f\n", maiorsal);
  porsal=(100*porsalcont)/numpes;
  printf("Porcentual de pessoas com salário até R$100,00: %.02f\n", porsal);
}
