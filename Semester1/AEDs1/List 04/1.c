int calcula_digito (int n){
  if(n<0){ 
    n*=-1;
  }
  if((n/10)<1){ 
    return (1); 
  }
  else{
    return (1 + calcula_digito(n/10)); 
  }
}

int main() {
  double num;
  printf("Digite um número:\n");
  scanf("%lf", &num);
  printf("O número de digitos de %1.lf é: %d\n", num, calcula_digito(num)); 
  return 0;
