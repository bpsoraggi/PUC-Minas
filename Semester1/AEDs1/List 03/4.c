void triangulo (int x, int y, int z) {
  if ((x<(y+z))&&(y<(x+z))&&(z<(x+y))) {
    if (x==y&&y==z) {
      printf("Seu triângulo é equilátero.\n\n");
    } else if (x==y&&x!=z) {
      printf("Seu triângulo é isósceles.\n\n");
    } else if (x==z&&z!=y) {
      printf("Seu triângulo é isósceles.\n\n");
    } else if (y==z&&z!=x) {
      printf("Seu triângulo é isósceles.\n\n");
    } else if (x!=y&&y!=z&&x!=z) {
      printf("Seu triângulo é escaleno.\n\n");
    }
  } else {
    printf("Seu triângulo é inválido.\n\n");
  }
  }

int main(void) {
  int cat1, cat2, cat3, n, rep=1;
  
  printf("Quantos triângulos você gostaria de conferir?\n");
  scanf("%d", &n);
  do {
    printf("Digite os valores dos catetos:\n");
    scanf("%d %d %d", &cat1, &cat2, &cat3);
    triangulo(cat1, cat2, cat3);
    rep++;
  } while (rep<=n);
  printf("Fim do programa.\n");
  return 0;
}
