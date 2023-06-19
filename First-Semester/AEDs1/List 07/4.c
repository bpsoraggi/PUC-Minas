int n=1;

typedef struct Registro {
int codigo;
char doacao;
char titulo[20];
char autor[20];
char editora[20];
char area;
}Registro;

void gravardados (Registro r[], char a) {
  for (int i=0; i<n; i++) {
    printf("\nDigite o código do volume %d:\n", i+1);
    scanf("%d", &r[i].codigo);
    printf("O volume foi uma doação? (s/n)\n");
    scanf(" %c", &r[i].doacao);
    printf("Digite o título do volume:\n");
    scanf("%s", r[i].titulo);
    printf("Digite o nome do autor:\n");
    scanf("%s", r[i].autor);
    printf("Digite o nome da editora:\n");
    scanf("%s", r[i].editora);
    r[i].area=a;
    getchar(); //impar buffer do teclado
  }
}

void imprimedados (Registro r) {
  printf("Código do volume: %d\n", r.codigo);
  printf("Doação: %c\n", r.doacao);
  printf("Título do volume: %s\n", r.titulo);
  printf("Autor do volume: %s\n", r.autor);
  printf("Editora do volume: %s\n", r.editora);
  printf("Área do volume: %d\n", r.area);
}

int main(void) {
  Registro e[1], h[1], b[1];
  char a;
  for (int i=0; i<n; i++) {
    printf("Digite a área do volume (e-Exatas/h-Humanas/b-Biológicas):\n");
    scanf(" %c", &a);
    if (a=='e') {
      gravardados(e, 'e');
    }
    if (a=='h') {
      gravardados(h, 'h');
    }
    if (a=='b') {
      gravardados(b, 'b');
    }
  }
  int cdg=0;
  while (cdg!=-1) {
    printf("\nDigite o código para pesquisa:\n");
    scanf("%d", &cdg);
    for (int i=0; i<n; i++) {
      if (cdg==e[i].codigo) {
        imprimedados(e[i]);
      }
      if (cdg==h[i].codigo) {
        imprimedados(h[i]);
      }
      if (cdg==b[i].codigo) {
        imprimedados(b[i]);
      }
    }
  }
  return 0;
} 
