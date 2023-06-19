int categoria(x) {
  int categoria=0;
  if (5<=x&&x<=7) {
    categoria=1;
  } else if (8<=x&&x<=10) {
    categoria=2;
  } else if (11<=x&&x<=13) {
    categoria=3;
  } else if (14<=x&&x<=15) {
    categoria=4;
  } else if (16<=x&&x<=17) {
    categoria=5;
  } else if (18<=x) {
    categoria=6;
  }
  return categoria;
}
  
int main(void) {
  int idade;
  printf("Digite sua idade:\n");
  scanf("%d", &idade);
  categoria(idade);
  if (categoria(idade)==1) {
    printf("Categoria F\n");
  } else if (categoria(idade)==2) {
    printf("Categoria E\n");
  } else if (categoria(idade)==3) {
    printf("Categoria D\n");
  } else if (categoria(idade)==4) {
    printf("Categoria C\n");
  } else if (categoria(idade)==5) {
    printf("Categoria B\n");
  } else if (categoria(idade)==6) {
    printf("Categoria A\n");
  }
  printf("Fim do programa.\n");
return 0;
}
