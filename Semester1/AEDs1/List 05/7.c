void calculo (int *pa, int *pb) {
  int t=0;
  if (*pa<*pb) {
    t = *pa;
    *pa = *pb;
    *pb = t;
  }
}

int main(void) {
  int num1, num2;

  printf("Digite dois valores inteiros\n");
  scanf("%d %d", &num1, &num2);
  calculo(&num1, &num2);
  printf("%d %d \n",num1, num2);
  printf("%p %p", &num1, &num2);
  return 0;
}
