int main() {
  int cand1=0, cand2=0, cand3=0, cand4=0, nulo=0, branco=0, rep=1;
  char op;
  while (rep==1) {
    printf("Digite sua opção (1/2/3/4/5/6):\n");
    scanf(" %c", &op);
    switch (op) {
      case '1': cand1++;
      break;
      case '2': cand2++;
      break;
      case '3': cand3++;
      break;
      case '4': cand4++;
      break;
      case '5': nulo++;
      break;
      case '6': branco++;
      break;
      case '0': rep=0;
      break;
      default: 
      printf("Opção inválida\n");
      break;
    }
  }
  printf("Votos para o candidato 1: %d\n", cand1);
  printf("Votos para o candidato 2: %d\n", cand2);
  printf("Votos para o candidato 3: %d\n", cand3);
  printf("Votos para o candidato 4: %d\n", cand4);
  printf("Votos nulos: %d\n", nulo);
  printf("Votos brancos: %d\n", branco);
  return 0;
}
