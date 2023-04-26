void calculo (int *pa, int *pb, int *pc) {
  int t=0, y=0;
  if ((*pa>*pb)&&(*pb<*pc)&&(*pc<*pa)) {
    t=*pb;
    *pb=*pc;
    *pc=t;
  } else if ((*pb>*pa)&&(*pa>*pc)) {
    t=*pa;
    *pa=*pb;
    *pb=t;
  } else if ((*pb>*pc)&&(*pc>*pa)) {
    t=*pb;
    *pb=*pc;
    *pc=t;
    t=*pc;
    *pc=*pa;
    *pa=t;
  } else if ((*pc>*pb)&&(*pb>*pa)) {
    t=*pa;
    *pa=*pc;
    *pc=t;
  } else if ((*pc>*pa)&&(*pa>*pb)) {
    t=*pc;
    *pc=*pa;
    *pa=t;
    t=*pc;
    *pc=*pb;
    *pb=t;
  }
}

int main(void) {
  int num1, num2, num3;

  printf("Digite três valores inteiros\n");
  scanf("%d %d %d", &num1, &num2, &num3);
  calculo(&num1, &num2, &num3);
  printf("%d %d %d\n",num1, num2, num3);
  return 0;
}
