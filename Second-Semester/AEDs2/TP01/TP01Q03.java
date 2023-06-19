public class TP01Q03 {

  public static void main(String[] args) {
    String[] cifrado = new String[1000];
    String msg = new String();
    String fim = "FIM";
    int a = 0, x = 0;

    do {
      cifrado[x] = "";
      msg = MyIO.readLine();

      if (msg.equals(fim)) {
        a = 1;
      }

      for (int i = 0; i < msg.length(); i++) {
        int c = (int) (msg.charAt(i));
        char cc = (char) ((c + 3));
        cifrado[x] += cc;
      }
      x++;
    } while (a != 1);

    for (int i = 0; i < x - 1; i++) {
      MyIO.println(cifrado[i]);
    }
  }
}