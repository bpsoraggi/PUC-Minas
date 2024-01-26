import java.util.Scanner;
import java.util.Random;

class TP01Q04 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
    Random gerador = new Random();
    gerador.setSeed(4);
    
    String frase = ""; //input
    String[] frase2 = new String[1000]; //frases trocada

    int flag = 0, x = 0;

    //System.out.println("a: " + a);
    //System.out.println("b: " + b);

    do {
      char a = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
      char b = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
      
      //System.out.println("digite a string");
      frase = scanner.nextLine();
      frase2[x] = "";
      
      for (int i = 0; i < frase.length(); i++) {
        if (frase.equals("FIM")) {
            flag = 1;
        } else {
          if (frase.charAt(i) == a) {
            frase2[x] += b;
          } else if(frase.charAt(i) == b) {
            frase2[x] += a;
          } else {
            frase2[x] += frase.charAt(i);
          }
        }
      }
      x++;
    } while (flag != 1);

    for (int i = 0; i < x-1; i++) {
      System.out.println(frase2[i]);
    }
    scanner.close();
  }
}