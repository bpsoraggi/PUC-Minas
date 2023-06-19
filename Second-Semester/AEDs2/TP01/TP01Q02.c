#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int main(void) {
  bool check[1000];

  int a = 0;
  int i = 0;

  do {
    char str1[1000] = "", str2[1000] = "";
    char strFIM[3] = "FIM";
    int comp;

    scanf(" %[^\n]",str1);

    if (strcmp(str1, strFIM) == 0) {
      a = 1;
    }

    int strLength = strlen(str1);

    int x = 0;
    for (int y = (strLength - 1); y >= 0; --y) {
      str2[x] = str1[y];
      x++;
      if (y == 0) {
        str2[x] = '\0';
      }
    }
    
    if (strcmp(str1, str2) == 0 && a != 1) {
      check[i] = true;
      i++;
    } else if (a!= 1) {
      check[i] = false;
      i++;
    }
  } while (a != 1);

  for (int z = 0; z < i; z++) {
    if (check[z] == true) {
      printf("SIM\n");
    } else {
      printf("NAO\n");
    }
  }

  return 0;
}