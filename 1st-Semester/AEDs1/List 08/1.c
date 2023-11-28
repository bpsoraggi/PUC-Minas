int main(void) {
  FILE *arquivoPtr;
  arquivoPtr=fopen("arquivo.txt", "w");

  for (int i=1; i<=10; i++) {
    fprintf(arquivoPtr, "%d\n", i);
  }
  return 0;
}
