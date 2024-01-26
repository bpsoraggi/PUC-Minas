public class TP01Q13 {

    public static String cifra(String str, int i) {
        char ch = str.charAt(0);
        StringBuffer output = new StringBuffer();
        ch = (char) ('a' + (ch - 'a' + i));
        output.append(ch);

        if (str.length() > 1) {
            output.append(cifra(str.substring(1), i));
        }

        return output.toString();
    }

    public static void main(String[] args) {
        String str = "";
        int a = 0;

        do {
            // System.out.println("LALALALALLA");
            str = "";
            // System.out.println("Digite a frase");
            str = MyIO.readLine();

            if (str.equals("FIM")) {
                a = 1;
            } else {
                MyIO.println(cifra(str, 3));
            }
        } while (a != 1);

    }

}