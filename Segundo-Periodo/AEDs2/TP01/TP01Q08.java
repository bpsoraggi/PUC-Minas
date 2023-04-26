import java.io.*;
import java.net.*;

class TP01Q08 {
    public static String getHtml(String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here
        }

        return resp;
    }

    // ------Vogais Normais------
    public static int countA(String html, int a) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == 'a') {
                a++;
            }
        }
        return a;
    }

    public static int countE(String html, int e) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == 'e') {
                e++;
            }
        }
        return e;
    }

    public static int countI(String html, int i) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == 'i') {
                i++;
            }
        }
        return i;
    }

    public static int countO(String html, int o) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == 'o') {
                o++;
            }
        }
        return o;
    }

    public static int countU(String html, int u) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == 'u') {
                u++;
            }
        }
        return u;
    }

    // ------Acento agudo------
    public static int countA1(String html, int a1) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 160) {
                a1++;
            }
        }
        return a1;
    }

    public static int countE1(String html, int e1) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 130) {
                e1++;
            }
        }
        return e1;
    }

    public static int countI1(String html, int i1) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 161) {
                i1++;
            }
        }
        return i1;
    }

    public static int countO1(String html, int o1) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 162) {
                o1++;
            }
        }
        return o1;
    }

    public static int countU1(String html, int u1) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 163) {
                u1++;
            }
        }
        return u1;
    }

    // ------Crase------
    public static int countA2(String html, int a2) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 133) {
                a2++;
            }
        }
        return a2;
    }

    public static int countE2(String html, int e2) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 138) {
                e2++;
            }
        }
        return e2;
    }

    public static int countI2(String html, int i2) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 141) {
                i2++;
            }
        }
        return i2;
    }

    public static int countO2(String html, int o2) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 149) {
                o2++;
            }
        }
        return o2;
    }

    public static int countU2(String html, int u2) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 151) {
                u2++;
            }
        }
        return u2;
    }

    // ------Til------
    public static int countA3(String html, int a3) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 198) {
                a3++;
            }
        }
        return a3;
    }

    public static int countO3(String html, int o3) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 228) {
                o3++;
            }
        }
        return o3;
    }

    // ------Acento circunflexo------
    public static int countA4(String html, int a4) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 131) {
                a4++;
            }
        }
        return a4;
    }

    public static int countE4(String html, int e4) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 136) {
                e4++;
            }
        }
        return e4;
    }

    public static int countI4(String html, int i4) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 140) {
                i4++;
            }
        }
        return i4;
    }

    public static int countO4(String html, int o4) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 147) {
                o4++;
            }
        }
        return o4;
    }

    public static int countU4(String html, int u4) {
        html.toLowerCase();
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == (char) 150) {
                u4++;
            }
        }
        return u4;
    }

    // ------Consoantes------
    public static int countConso(String html, int conso) {
        if (html.toUpperCase().matches("[QWRTYPSDFGHJKLZXCVBNM]+")) {
            conso++;
        }
        return conso;
    }

    // ------<br>------
    public static int countBr(String html, int br) {
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == '<' && html.charAt(x + 1) == 'b' && html.charAt(x + 2) == 'r'
                    && html.charAt(x + 3) == '>') {
                br++;
            }
        }
        return br;
    }

    // ------<table>------
    public static int countTable(String html, int table) {
        for (int x = 0; x < html.length(); x++) {
            if (html.charAt(x) == '<' && html.charAt(x + 1) == 't' && html.charAt(x + 2) == 'a'
                    && html.charAt(x + 3) == 'b' && html.charAt(x + 4) == 'l' && html.charAt(x + 5) == 'e'
                    && html.charAt(x + 6) == '>') {
                table++;
            }
        }
        return table;
    }

    public static void main(String[] args) {
        String endereco, nome, html;
        int a = 0, e = 0, i = 0, o = 0, u = 0, a1 = 0, e1 = 0, i1 = 0, o1 = 0, u1 = 0, a2 = 0, e2 = 0, i2 = 0, o2 = 0,
                u2 = 0, a3 = 0, o3 = 0, a4 = 0, e4 = 0, i4 = 0, o4 = 0, u4 = 0, conso = 0, br = 0, table = 0;
        int z = 0;

        do {
            // System.out.println("Digite o nome");
            nome = MyIO.readLine();

            if (nome.equals("FIM")) {
                z = 1;
            } else {
                // System.out.println("Digite o endereço");
                endereco = MyIO.readLine();

                html = getHtml(endereco);

                // Contar vogais normais
                System.out.print("a(" + countA(html, a) + ") ");
                System.out.print("e(" + countE(html, e) + ") ");
                System.out.print("i(" + countI(html, i) + ") ");
                System.out.print("o(" + countO(html, o) + ") ");
                System.out.print("u(" + countU(html, u) + ") ");

                // Contar acentos agúdos
                System.out.print((char) 160 + "(" + countA1(html, a1) + ") ");
                System.out.print((char) 130 + "(" + countE1(html, e1) + ") ");
                System.out.print((char) 161 + "(" + countI1(html, i1) + ") ");
                System.out.print((char) 162 + "(" + countO1(html, o1) + ") ");
                System.out.print((char) 163 + "(" + countU1(html, u1) + ") ");

                // Contar crases
                System.out.print((char) 133 + "(" + countA2(html, a2) + ") ");
                System.out.print((char) 138 + "(" + countE2(html, e2) + ") ");
                System.out.print((char) 141 + "(" + countI2(html, i2) + ") ");
                System.out.print((char) 149 + "(" + countO2(html, o2) + ") ");
                System.out.print((char) 151 + "(" + countU2(html, u2) + ") ");

                // Contar til
                System.out.print((char) 198 + "(" + countA3(html, a3) + ") ");
                System.out.print((char) 228 + "(" + countO3(html, o3) + ") ");

                // Contar circunflexo
                System.out.print((char) 131 + "(" + countA4(html, a4) + ") ");
                System.out.print((char) 136 + "(" + countE4(html, e4) + ") ");
                System.out.print((char) 140 + "(" + countI4(html, i4) + ") ");
                System.out.print((char) 147 + "(" + countO4(html, o4) + ") ");
                System.out.print((char) 150 + "(" + countU4(html, u4) + ") ");

                System.out.print("consoante(" + countConso(html, conso) + ") ");
                System.out.print("<br>(" + countBr(html, br) + ") ");
                System.out.print("<table>(" + countTable(html, table) + ") ");
                System.out.print(nome);

                System.out.println();
            }

        } while (z != 1);
    }
}