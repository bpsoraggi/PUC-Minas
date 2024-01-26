import java.util.Scanner;

class TP01Q06 {
    static int x = 0;

    public static boolean isVogal(String str, boolean[] check) {
        if (str.toUpperCase().matches("[AEIOU]+")) {
            System.out.print("SIM" + " ");
            check[x] = true;
            return true;
        } else {
            check[x] = false;
            System.out.print("NAO" + " ");
            return false;
        }
    }

    public static boolean isConsoante(String str, boolean[] check) {
        if (str.toUpperCase().matches("[QWRTYPSDFGHJKLZXCVBNM]+")) {
            System.out.print("SIM" + " ");
            check[x] = true;
            return true;
        } else {
            check[x] = false;
            System.out.print("NAO" + " ");
            return false;
        }
    }

    public static boolean isInteiro(String str, boolean[] check) {
        if (str.matches("-" + "[0-9]+") || str.matches("[0-9]+")) {
            System.out.print("SIM" + " ");
            check[x] = true;
            return true;
        } else {
            check[x] = false;
            System.out.print("NAO" + " ");
            return false;
        }
    }

    public static boolean isReal(String str, boolean[] check) {
        if (str.matches("√" + "[0-9]+") || str.matches("-" + "[0-9]+") || str.matches("[0-9]+")
                || str.matches("[0-9,.]+")) {
            System.out.print("SIM" + " ");
            check[x] = true;
            return true;
        } else {
            check[x] = false;
            System.out.print("NAO" + " ");
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean[] check = new boolean[1000];
        String str = "";

        int a = 0;
        // int i = 0;

        do {
            // System.out.println("Digite a string");
            str = scanner.nextLine();

            if (str.equals("FIM")) {
                a = 1;
            } else {
                isVogal(str, check);
                isConsoante(str, check);
                isInteiro(str, check);
                isReal(str, check);
                System.out.println();
                x++;
            }

            // System.out.println(isConsoante(str, check));
            // System.out.println(isVogal(str, check));
            // System.out.println(isInteiro(str, check));
            // System.out.println(isReal(str, check));

        } while (a != 1);

        scanner.close();
    }
}