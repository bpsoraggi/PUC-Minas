#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// flag
bool isFim(char ID[20])
{
    return (ID[0] == 'F' && ID[1] == 'I' && ID[2] == 'M' && strlen(ID) == 3);
}

int main(void)
{
    char ID[20];
    scanf("%s", ID);
    char linha[1000];
    char *result;
    FILE *arq;
    while (isFim(ID) == false)
    {
        arq = fopen("/tmp/games.csv", "r");
        while (!feof(arq))
        {
            int check = 0;
            result = fgets(linha, 1000, arq);
            for (int i = 0; i < strlen(ID); i++)
            {
                if (result[i] != ID[i])
                {
                    check = 1;
                }
            }
            int arq = 0;
            if (check == 0)
            {
                // ascii
                for (int i = 0; i < strlen(result); i++)
                {
                    if (result[i] == 44)
                    {
                        result[i] = ' ';
                    }
                    if (result[i] == 34)
                    {
                        if (arq == 2)
                        {
                            result[i] = 91;
                        }
                        else if (arq == 3)
                        {
                            result[i] = 93;
                        }
                        arq++;
                    }
                }
                printf("%s", result);
            }
        }
        scanf("%s", ID);
    }
}