struct Registro {
char nome[20];
int preco;
int telefone;
};

int main(void) {
  int n=3, soma=0;
  float med=0;
  struct Registro r[n];
  for (int i=0; i<n; i++) {
    printf("\nDigite o nome da loja %d:\n", i+1);
    scanf("%s", r[i].nome);
    printf("Digite o preço da loja:\n");
    scanf("%d", &r[i].preco);
    printf("Digite o telefone da loja:\n");
    scanf("%d", &r[i].telefone);
  }
  for (int i=0; i<n; i++) {
    soma+=r[i].preco;
  }
  med=soma/n;
  printf("A média dos preços é: %.2f", med);
  printf("\nLojas com preços abaixo da média:\n");
  for (int i=0; i<n; i++) {
    if (r[i].preco<med) {
      printf("Loja: %s\nTelefone: %d\n\n", r[i].nome, r[i].telefone);
    }
  }
  return 0;
}
