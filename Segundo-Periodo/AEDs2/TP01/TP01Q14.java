class TP01Q14 {

    public static int primeiro(String s) {

        return primeiro(s, 0);
    }

    public static int primeiro(String s, int begin) {

        for (int i = begin; i < s.length(); i++) {

            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {

                return i;
            }
        }

        return 0;
    }

    public static int ultimo(String s) {

        int not = s.lastIndexOf("not");
        int and = s.lastIndexOf("and");
        int or = s.lastIndexOf("or");

        if (not > and && not > or) {

            return not;
        } else if (and > not && and > or) {

            return and;
        } else if (or > not && or > and) {

            return or;
        }

        return 0;
    }

    public static void main(String[] args) {

        while (true) {

            int n = MyIO.readInt();// quantidade de letras

            if (n == 0) {// flag

                break;
            }

            int[] a = new int[n];

            for (int i = 0; i < n; i++) {

                a[i] = MyIO.readInt();
            }

            String entrada = MyIO.readLine();

            if (entrada.charAt(entrada.length() - 1) == ' ') {

                entrada = entrada.substring(0, entrada.length() - 1);
            }

            for (int i = 0; i < n; i++) {

                if (i == 0) {// A

                    entrada = entrada.replace("not(A)", a[0] == 0 ? "1" : "0");
                    entrada = entrada.replace("A", a[0] == 0 ? "0" : "1");
                } else if (i == 1) {// B

                    entrada = entrada.replace("not(B)", a[1] == 0 ? "1" : "0");
                    entrada = entrada.replace("B", a[1] == 0 ? "0" : "1");
                } else if (i == 2) {// C

                    entrada = entrada.replace("not(C)", a[2] == 0 ? "1" : "0");
                    entrada = entrada.replace("C", a[2] == 0 ? "0" : "1");
                }
            }

            while (entrada.length() > 1) {

                int last = ultimo(entrada);

                String exp = entrada.substring(last, entrada.indexOf(")", last) + 1);

                if (exp.charAt(0) != 'n') {

                    int count = 1;// quantas virgulas

                    for (int i = 0; i < exp.length(); i++) {

                        if (exp.charAt(i) == ',') {

                            count++;
                        }
                    }

                    int[] p = new int[count];
                    int pos = 0;

                    for (int i = 0; i < count; i++) {

                        pos = primeiro(exp, pos);

                        String num = exp.substring(pos, ++pos);

                        p[i] = Integer.parseInt(num);
                    }

                    if (exp.charAt(0) == 'a') {

                        String resp = "1";

                        if (count == 1) {

                            resp = String.format("%i", p[0]);
                        } else {

                            for (int i = 0; i < count; i++) {

                                if (p[i] == 0) {

                                    resp = "0";
                                    break;
                                }
                            }
                        }

                        entrada = entrada.replace(exp, resp);
                    } else if (exp.charAt(0) == 'o') {

                        String resp = "0";

                        if (count == 1) {

                            resp = String.format("%i", p[0]);
                        } else {

                            for (int i = 0; i < count; i++) {

                                if (p[i] == 1) {

                                    resp = "1";
                                    break;
                                }
                            }
                        }

                        entrada = entrada.replace(exp, resp);

                    }
                } else {// troca

                    if (exp.equals("not(0)")) {

                        entrada = entrada.replace("not(0)", "1");
                    } else if (exp.equals("not(1)")) {

                        entrada = entrada.replace("not(1)", "0");
                    }
                }
            }

            MyIO.println(entrada);

        }
    }
}