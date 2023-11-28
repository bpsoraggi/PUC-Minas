int i;
int j;
int m = 10;

void matriz_princ(int M[10][10]) {
  for (i = 0; i < m; i++) {
    for (j = 0; j < m; j++) {
      M[i][j] = rand() % 10;
      printf("%d ", M[i][j]);
    }
    printf("\n");
  }
}

//linha 2 com a linha 8
void troca_1(int M[10][10]) {
  int troca;
  for (i = 0; i < m; i++) { //linha
    for (j = 0; j < m; j++) { //coluna
      troca = M[1][j];
      M[1][j] = M[7][j];
      M[7][j] = troca;
    }
  }
}

//coluna 4 com a coluna 10
void troca_2(int M[10][10]) {
  int troca;
  for (i = 0; i < m; i++) { //linha
    for (j = 0; j < m; j++) { //coluna
      troca = M[i][3];
      M[i][3] = M[i][9];
      M[i][9] = troca;
    }
  }
}

//a diagonal principal com a diagonal secundária
void troca_3(int M[10][10]) {
  int troca;
  for (i = 0; i < m; i++) { //linha
    for (j = 0; j < m; j++) { //coluna
      troca = M[i][j];
      M[i][j] = M[j][i];
      M[j][i] = troca;
    }
  }
}

//a linha 5 com a coluna 10
void troca_4(int M[10][10]) {
  int troca;
  for (i = 0; i < m; i++) { //linha
    for (j = 0; j < m; j++) { //coluna
      troca = M[4][j];
      M[4][j] = M[i][9];
      M[i][9] = troca;
    }
  }
}

      
int main(void) {
  int mp[10][10], t1[10][10], t2[10][10], t3[10][10], t4[10][10]; 
  matriz_princ(mp);
  troca_1(t1);
  troca_2(t2);
  troca_3(t3);
  troca_4(t4);
  printf("\nPRIMEIRA TROCA\n");
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < m; j++) {
      printf("%d ", t1[i][j]);
    }
    printf("\n");
  }

  printf("\nSEGUNDA TROCA");
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < m; j++) {
      printf("%d ", t1[i][j]);
    }
    printf("\n");
  }
  
  printf("\nTERCEIRA TROCA");
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < m; j++) {
      printf("%d ", t1[i][j]);
    }
    printf("\n");
  }
  
  printf("\nQUARTA TROCA");
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < m; j++) {
      printf("%d ", t1[i][j]);
    }
    printf("\n");
  }
  printf("\n");
  return 0;
}
