class TP01Q15 {

    public static boolean isFim(String s) {

        boolean resp = false;

        if (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M') {

            resp = true;
        }

        return resp;
    }

    public static int isVogal(String s) {

        return isVogal(s, 0);
    }

    // ------vogais------
    public static int isVogal(String s, int i) {

        int resp = 0;

        if (i < s.length()) {

            if (s.charAt(i) == 'a' || s.charAt(i) == 'A' || s.charAt(i) == 'e' || s.charAt(i) == 'E'
                    || s.charAt(i) == 'i' ||
                    s.charAt(i) == 'I' || s.charAt(i) == 'o' || s.charAt(i) == 'O' || s.charAt(i) == 'u'
                    || s.charAt(i) == 'U') {

                resp += 0;
                resp += isVogal(s, i + 1);
            } else {

                resp += 1;
            }
        }

        return resp;
    }

    public static int isConsoante(String s) {

        return isConsoante(s, 0);
    }

    // ------consoantes------
    public static int isConsoante(String s, int i) {

        int resp = 0;

        if (i < s.length()) {

            if (s.charAt(i) == 'b' || s.charAt(i) == 'B' || s.charAt(i) == 'c' || s.charAt(i) == 'C' ||
                    s.charAt(i) == 'd' || s.charAt(i) == 'D' || s.charAt(i) == 'f' || s.charAt(i) == 'F' ||
                    s.charAt(i) == 'g' || s.charAt(i) == 'G' || s.charAt(i) == 'h' || s.charAt(i) == 'H' ||
                    s.charAt(i) == 'j' || s.charAt(i) == 'J' || s.charAt(i) == 'k' || s.charAt(i) == 'K' ||
                    s.charAt(i) == 'l' || s.charAt(i) == 'L' || s.charAt(i) == 'm' || s.charAt(i) == 'M' ||
                    s.charAt(i) == 'n' || s.charAt(i) == 'N' || s.charAt(i) == 'p' || s.charAt(i) == 'P' ||
                    s.charAt(i) == 'q' || s.charAt(i) == 'Q' || s.charAt(i) == 'r' || s.charAt(i) == 'R' ||
                    s.charAt(i) == 's' || s.charAt(i) == 'S' || s.charAt(i) == 't' || s.charAt(i) == 'T' ||
                    s.charAt(i) == 'v' || s.charAt(i) == 'V' || s.charAt(i) == 'w' || s.charAt(i) == 'W' ||
                    s.charAt(i) == 'x' || s.charAt(i) == 'X' || s.charAt(i) == 'y' || s.charAt(i) == 'Y' ||
                    s.charAt(i) == 'z' || s.charAt(i) == 'Z') {

                resp += 0;
                resp += isConsoante(s, i + 1);

            } else {

                resp += 1;
            }

        }

        return resp;
    }

    public static int isInt(String s) {

        return isInt(s, 0);
    }

    // ------int------
    public static int isInt(String s, int i) {

        int resp = 0;

        if (i < s.length()) {

            if (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3'
                    || s.charAt(i) == '4' ||
                    s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8'
                    || s.charAt(i) == '9') {

                resp += 0;
                resp += isInt(s, i + 1);
            } else {

                resp += 1;
            }
        }

        return resp;
    }

    // ------real------
    public static boolean isReal(String s) {

        boolean resp = false;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {

            if ((s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3'
                    || s.charAt(i) == '4' ||
                    s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8'
                    || s.charAt(i) == '9' ||
                    s.charAt(i) == ',' || s.charAt(i) == '.')) {

                resp = true;

                if (s.charAt(i) == ',' || s.charAt(i) == '.') {

                    count++;
                }
                if (count > 1) {

                    return false;
                }

            } else

                return false;
        }

        return resp;
    }

    public static void main(String[] args) {

        String entrada = new String();

        while (isFim(entrada) == false) {

            entrada = MyIO.readLine();

            if (isVogal(entrada) == 0) {

                MyIO.print("SIM ");
            } else if (isVogal(entrada) > 0) {

                if (isFim(entrada) == true) {

                    break;
                }

                MyIO.print("NAO ");
            }

            if (isConsoante(entrada) == 0) {

                MyIO.print("SIM ");
            } else if (isConsoante(entrada) > 0) {

                if (isFim(entrada) == true) {

                    break;
                }

                MyIO.print("NAO ");
            }

            if (isInt(entrada) == 0) {

                MyIO.print("SIM ");
            } else if (isInt(entrada) > 0) {

                if (isFim(entrada) == true) {

                    break;
                }

                MyIO.print("NAO ");
            }

            if (isReal(entrada) == true) {

                MyIO.println("SIM ");
            } else if (isReal(entrada) == false) {

                if (isFim(entrada) == true) {

                    break;
                }

                MyIO.println("NAO ");
            }
        }
    }
}