struct Cadastro {
int codigo;
char email[40];
int horas;
char pagina;
float pagar;
};

int main(void) {
  int n=3, excedente=0;
  struct Cadastro c[3];

  for (int i=0; i<n; i++) {
    printf("\n\nDigite o código do cliente %d: ", i+1);
    scanf("%d", &c[n].codigo);
    printf("\nDigite o email do cliente: ");
    scanf("%s", c[n].email);
    printf("\nDigite as horas do cliente: ");
    scanf(" %d", &c[n].horas);
    printf("\nTem página? (S ou N): ");
    scanf(" %c", &c[n].pagina);
  }
  for (int i=0; i<n; i++) {
    c[n].pagar = 0;
    if (c[n].horas<=20) {
      c[n].pagar=35;
    } else if (c[n].horas>20) {
      excedente=c[n].horas-20;
      c[n].pagar=35+(excedente*2.5);
    }
    printf("Pagar: %.2f\n", c[n].pagar);
    if (c[n].pagina == 's') {
      printf("tem q pagar\n");
      c[n].pagar+=40;
    }
    printf("Pagar: %.2f\n", c[n].pagar);
    printf("Total a pagar para o cliente %d: %.2f\n", i+1, c[n].pagar);
  }
  return 0;
}
