#include <stdio.h>
#include <string.h>

// método para ver se é palindromo
void isPalindromo(char frase[], int lugar)
{
    int len = strlen(frase) - (lugar + 1);
    if (frase[lugar] == frase[len])
    {
        if (lugar + 1 == len || lugar == len)
        {
            printf("SIM\n");
            return;
        }
        isPalindromo(frase, lugar + 1);
    }
    else
    {
        printf("NAO\n");
    }
}

int main()
{
    char frase[1000];

    // printf("Digite a palavra\n");
    scanf(" %[^\n]", frase);

    // repetir enquanto não receber "FIM"
    while (!(frase[0] == 'F' && frase[1] == 'I' && frase[2] == 'M'))
    {
        isPalindromo(frase, 0);
        scanf(" %[^\n]", frase);
    }

    return 0;
}