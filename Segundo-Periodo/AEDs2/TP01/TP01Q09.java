public class TP01Q09 {
    public static void main(String[] args) {
        int n = 0;

        // System.out.println("Digite o n");
        n = MyIO.readInt();

        String[] frase2 = new String[n];
        String[] frase = new String[n];

        for (int i = 0; i < n; i++) {
            // System.out.println("Digite o valor");
            frase[i] = MyIO.readLine();
        }

        for (int i = n - 1; i >= 0; i--) {
            frase2[i] = "";
            int x = 0, p = 0;
            if (frase[i].charAt(0) == 46) {
                for (int j = frase[i].length() - 1; j > 0; j--) {
                    if (frase[i].charAt(j) == 48) {
                        x++;
                    } else {
                        j = 0;
                    }
                }
                for (int z = 0; z < frase[i].length() - x; z++) {
                    frase2[i] += frase[i].charAt(z);
                }
                System.out.println("0" + frase2[i]);
            } else {
                for (int y = 0; y < frase[i].length(); y++) {
                    if (frase[i].charAt(y) == 46) {
                        p = 1;
                    }
                }
                for (int j = frase[i].length() - 1; j > 0; j--) {
                    if (frase[i].charAt(j) == 48 && p == 1) {
                        x++;
                    } else {
                        j = 0;
                    }
                }
                for (int z = 0; z < frase[i].length() - x; z++) {
                    frase2[i] += frase[i].charAt(z);
                }
                System.out.println(frase2[i]);
            }
        }
    }
}