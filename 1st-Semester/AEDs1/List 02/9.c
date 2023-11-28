int main () {
int lucro1=0, lucro2=0, lucro3=0, i=1;
float compra,venda,lucro=0,totalL,totalC,totalV;

  while(1){
    printf("Valor de compra da mercadoria %d :\n",i);
    scanf("%f",&compra);
    
    if(compra > 0){
      printf("Valor de venda da mercadoria %d: \n",i);
      scanf("%f",&venda);

      lucro = venda - compra;
      totalV += venda;
      totalC += compra;
      if(lucro <= (0.1)*compra){
        lucro1++;
      } else if ((lucro > (0.1)*compra)&&(lucro <= (0.2)*compra)){
        lucro2++;
      }else if (lucro > (0.2)*compra){
        lucro3++;  
      }
      totalL += lucro;
      printf("Lucro menor que 10%%: %d\nLucro entre 10%% e 20%%: %d\nLucro maior que 20%%: %d\n\n",lucro1,lucro2,lucro3);
    }
    else{
      break;
    }
    i++;
  }
  printf("Gasto total com compras: R$ %.0f,00\nGanho total com vendas: R$ %.0f,00\nLucro total: R$ %.0f,00\n\n",totalC,totalV,totalL);
  return 0;
}
