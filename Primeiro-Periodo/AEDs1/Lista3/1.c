int nota1, nota2, nota3; //variáveis globais

float MedAr(nalunos) { //operação média aritmética
  float MedAr=0;
  MedAr=(nota1+nota2+nota3)/3.0;
  return MedAr;
}

float MedPo(nalunos) { //operação média ponderada
  float MedPo=0;
  MedPo=((nota1*5)+(nota2*3)+(nota3*2))/10.0;
  return MedPo;
}

int main(void) { //principal
  int nalunos;
  char op;
  printf("Digite o número de alunos:\n");
  scanf("%d", &nalunos);
  for (int x=1; x<=nalunos; x++) { //repetição n alunos
  printf("Digite as notas do aluno %d:\n", x);
  scanf("%d %d %d", &nota1, &nota2, &nota3);
  printf("Digite sua opção (A/P):\n");
  scanf(" %c", &op);
  switch (op) {
    case 'A': 
      printf("A média aritmética das notas do aluno %d é: %.3f\n\n", x, MedAr(nalunos));
    break;
    case 'P': 
    printf("A média ponderada das notas do aluno %d é: %.3f\n\n", x, MedPo(nalunos));
    break;
    }
  }
  printf("Fim do programa.\n");
  return 0;
}
