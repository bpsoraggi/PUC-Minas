void preenche(int vx[10], int vy[10]) {
  for (int i=0; i<10; i++) {
    vx[i]=rand()%10; //Divide por 10 para os números ficarem menores
    vy[i]=rand()%10;
  }
}

void novoVet(int v1[10], int v2[10], int v3[10]) {
  for (int i=0; i<10; i++) {
    if (v3[i]%2!=0) {
      v3[i]=v1[i];
    } else {
      v3[i]=v2[i];
    }
  }
}

void imprimeVet (int vet[10]) {
  for (int i=0; i<10; i++) {
    printf("%d\n", vet[i]);
  }
}

int main(void) {
  int vetor1[10];
  int vetor2[10];
  int vetor3[10];
  preenche(vetor1, vetor2);
  novoVet(vetor1, vetor2, vetor3);
  imprimeVet(vetor3);
  return 0;
}
