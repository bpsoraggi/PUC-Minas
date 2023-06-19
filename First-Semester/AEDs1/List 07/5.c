struct Cliente {
char nome[20];
char endereco[40];
int telefone;
};

int main(void) {
  struct Cliente cliente1;
  struct Cliente cliente2;

  printf("Digite o nome do cliente 1:\n");
  scanf("%s", cliente1.nome);
  printf("Digite o endereço do cliente 1:\n");
  scanf("%s", cliente1.endereco);
  printf("Digite o telefone do cliente 1:\n");
  scanf("%d", &cliente1.telefone);

  printf("\nDigite o nome do cliente 2:\n");
  scanf("%s", cliente2.nome);
  printf("Digite o endereço do cliente 2:\n");
  scanf("%s", cliente2.endereco);
  printf("Digite o telefone do cliente 2:\n");
  scanf("%d", &cliente2.telefone);

  printf("\nCadastro concluído");
  return 0;
}
