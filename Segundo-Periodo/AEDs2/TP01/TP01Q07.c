#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

bool isFim(char *str)
{
    bool check = false;
    if (strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M')
    {
        check = true;
    }
    else
    {
        check = false;
    }
    return check;
}

bool isVogal(char *str)
{
    toupper(str);
    bool check = false;
    for (int i = 0; i < strlen(str); i++)
    {
        if (str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U')
        {
            check = true;
        }
        else
        {
            check = false;
        }
    }
    return check;
}

bool isConsoante(char *str)
{
    toupper(str);
    bool check = false;
    for (int i = 0; i < strlen(str); i++)
    {
        if (str[i] == 'B' || str[i] == 'C' || str[i] == 'D' || str[i] == 'F' ||
            str[i] == 'G' || str[i] == 'H' || str[i] == 'J' || str[i] == 'K' ||
            str[i] == 'L' || str[i] == 'M' || str[i] == 'N' || str[i] == 'P' ||
            str[i] == 'Q' || str[i] == 'R' || str[i] == 'S' || str[i] == 'T' ||
            str[i] == 'V' || str[i] == 'W' || str[i] == 'X' || str[i] == 'Y' || str[i] == 'Z')
        {
            check = true;
        }
        else
        {
            check = false;
        }
    }
    return check;
}

/*bool isInt(char *str)
{
    bool check = false;
    for (int i = 0; i < strlen(str); i++)
    {
        if (str[i] == '0' || str[i] == '1' || str[i] == '2' ||
            str[i] == '3' || str[i] == '4' || str[i] == '5' ||
            str[i] == '6' || str[i] == '7' || str[i] == '8' || str[i] == '9')
        {
            check = true;
        }
        else if (str[i] == ',' || str[i] == '.')
        {
            check = false;
        }
        else
        {
            check = false;
        }
    }
    return check;
}*/

bool isInt(char *s)
{

    bool resp = false;

    for (int i = 0; i < strlen(s); i++)
    {

        if (s[i] == '0' || s[i] == '1' || s[i] == '2' || s[i] == '3' || s[i] == '4' ||
            s[i] == '5' || s[i] == '6' || s[i] == '7' || s[i] == '8' || s[i] == '9')
        {

            resp = true;
        }
        else
        {

            return false;
        }
    }

    return resp;
}

/*bool isReal(char *str)
{
    bool check = false;
    for (int i = 0; i < strlen(str); i++)
    {
        if (str[i] == '0' || str[i] == '1' || str[i] == '2' ||
            str[i] == '3' || str[i] == '4' || str[i] == '5' ||
            str[i] == '6' || str[i] == '7' || str[i] == '8' ||
            str[i] == '9' || str[i] == ',' || str[i] == '.')
        {
            check = true;
        }
        else
        {
            check = false;
        }
    }
    return check;
}*/

bool isReal(char *s)
{

    bool resp = false;
    int count = 0;

    for (int i = 0; i < strlen(s); i++)
    {

        if ((s[i] == '0' || s[i] == '1' || s[i] == '2' || s[i] == '3' || s[i] == '4' ||
             s[i] == '5' || s[i] == '6' || s[i] == '7' || s[i] == '8' || s[i] == '9' ||
             s[i] == ',' || s[i] == '.'))
        {

            resp = true;

            if (s[i] == ',' || s[i] == '.')
            {

                count++;
            }
            if (count > 1)
            {

                return false;
            }
        }
        else

            return false;
    }

    return resp;
}

int main(void)
{
    int a = 0; // flag
    char string[1000];
    do
    {
        scanf(" %[^\n]s", string);

        if (isFim(string) == true)
        {
            a = 1;
        }
        else
        {
            // Vogal
            if (isVogal(string) == true)
            {
                printf("SIM ");
            }
            else
            {
                printf("NAO ");
            }

            // Consoantes
            if (isConsoante(string) == true)
            {
                printf("SIM ");
            }
            else
            {
                printf("NAO ");
            }

            // Int
            if (isInt(string) == true)
            {
                printf("SIM ");
            }
            else
            {
                printf("NAO ");
            }

            // Real
            if (isReal(string) == true)
            {
                printf("SIM\n");
            }
            else
            {
                printf("NAO\n");
            }
        }
    } while (a != 1);
}