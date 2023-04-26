public class TP01Q11 {
    public static boolean isFim(String str) {
        // se string for "FIM", retornar true
        if (str.equals("FIM")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPalindromo(String str) {
        // se string tiver vazia ou tiver 1 char, é palíndromo
        if (str.length() == 0 || str.length() == 1) {
            return true;
        }
        // olhar se é palíndromo
        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            return isPalindromo(str.substring(1, str.length() - 1));
        }
        return false;
    }

    public static void main(String[] args) {
        int a = 0; // flag

        do {
            String string = "";
            // System.out.println("Digite a frase");
            string = MyIO.readLine();

            // se for fim, usar o flag pra parar o programa
            if (isFim(string)) {
                a = 1;
            } else {
                if (isPalindromo(string)) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NAO");
                }
            }
        } while (a != 1); // enquanto flag for diferente de 1
    }
}
