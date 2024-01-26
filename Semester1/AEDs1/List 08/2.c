int main(void) {
  FILE *arquivoPtr;
  char mensagem[20];

  arquivoPtr=fopen("arquivo.txt", "w");

  printf("Digite uma mensagem de até 20 caracteres:\n");
//  scanf("%[^\n]", mensagem);
  fgets(mensagem,20, stdin); //onde gravar, número de caracteres, de onde (stdIN-input=teclado)

  fprintf(arquivoPtr, "%s", mensagem);
  return 0;
}
