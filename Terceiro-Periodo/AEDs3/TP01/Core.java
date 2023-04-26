import java.text.ParseException;

public class Core extends CRUD{

    // --------------- Print ---------------

    public static void print(Netflix netflix) {
        // imprime o id do registro
        if (netflix.getShow_id() < 10) {
            System.out.println("_____________REGISTRO " + "000" + netflix.getShow_id() + "_____________");
        } else if (netflix.getShow_id() < 100) {
            System.out.println("_____________REGISTRO " + "00" + netflix.getShow_id() + "_____________");
        } else if (netflix.getShow_id() < 1000) {
            System.out.println("_____________REGISTRO " + "0" + netflix.getShow_id() + "_____________");
        } else {
            System.out.println("_____________REGISTRO " + netflix.getShow_id() + "_____________");
        }
        // imprime os dados do registro
        System.out.println("ID: " + netflix.getShow_id());
        System.out.println("Tipo: " + netflix.getType());
        System.out.println("Titulo: " + netflix.getTitle());
        System.out.println("Diretores: " + netflix.getQtdDirectors());
        if (netflix.getQtdDirectors() > 0) {
            for (int i = 0; i < netflix.getQtdDirectors(); i++) {
                System.out.println("Diretor " + (i + 1) + ": " + netflix.getDirector()[i]);
            }
        }   
        System.out.println("Elenco: " + netflix.getQtdCast());
        if (netflix.getQtdCast() > 0) {
            for (int i = 0; i < netflix.getQtdCast(); i++) {
                System.out.println("Ator(a) " + (i + 1) + ": " + netflix.getCast()[i]);
            }
        }
        System.out.println("Paises: " + netflix.getQtdCountries());
        if (netflix.getQtdCountries() > 0) {
            for (int i = 0; i < netflix.getQtdCountries(); i++) {
                System.out.println("Pais " + (i + 1) + ": " + netflix.getCountry()[i]);
            }
        }
        if (netflix.getDateString().equals("1010-10-10")) {
            System.out.println("Data adicionada: Sem data");
        } else {
            System.out.println("Data adicionada: " + netflix.getDate_added());
        }
        System.out.println("Lancamento: " + netflix.getRelease_year());
        System.out.println("Classificacao: " + netflix.getRating());
        System.out.println("Duracao: " + netflix.getDuration());

        System.out.println("_______________________________________"); // formatacao
        System.out.println();
    }


    // --------------- Le CSV ---------------

    public static void readCSV(String linha, Netflix netflix) throws ParseException {
        String aux = "";
        int pointer = 0;

        // ---------- Show ID ----------
        for (int i = 0; i < 4; i++) { // primeiros 4 caracteres (id)
            aux += linha.charAt(i); // adiciona o caractere na string auxiliar
            pointer++; // incrementa o ponteiro
        }
        netflix.setShow_id(Integer.parseInt(aux)); // converte a string para inteiro e armazena no objeto
        aux = ""; // limpa a string auxiliar
        pointer++; // incrementa o ponteiro (pula a virgula)

        // ---------- Type ----------
        for (int i = pointer; i < 11; i++) {
            aux += linha.charAt(i);
            pointer++;
        }
        pointer++;
        netflix.setType(aux);
        aux = "";

        // ---------- Title ----------
        // #region Title
        if (linha.charAt(pointer) != '\"') {
            for (int i = pointer; i < linha.length(); i++) {
                if (linha.charAt(i) != ',') {
                    aux += linha.charAt(i);
                    pointer++;
                } else {
                    pointer++;
                    i = linha.length(); // sair do loop
                }
            }
        } else {
            pointer++;
            for (int i = pointer; i < linha.length(); i++) {
                if (linha.charAt(i) != '\"' || (linha.charAt(i) == '\"' && linha.charAt(i + 1) == '\"') || (linha.charAt(i) == '\"' && linha.charAt(i - 1) == '\"' && linha.charAt(i + 1) != ',')) {
                    aux += linha.charAt(i);
                    pointer++;
                } else {
                    pointer += 2;
                    i = linha.length();
                }
            }
        }
        netflix.setTitle(aux);
        aux = "";
        // #endregion Title

        // ---------- Director ----------
        // #region Director
        if (linha.charAt(pointer) == '\"') {
            pointer++;
            for (int i = pointer; i < linha.length(); i++) {
                if (linha.charAt(i) != '\"' || (linha.charAt(i) == '\"' && linha.charAt(i + 1) == '\"') || (linha.charAt(i) == '\"' && linha.charAt(i - 1) == '\"')) {
                    aux += linha.charAt(i);
                    pointer++;
                } else {
                    pointer += 2;
                    i = linha.length();
                }
            }
        } else {
            for (int i = pointer; i < linha.length(); i++) {
                if (linha.charAt(i) != ',') {
                    aux += linha.charAt(i);
                    pointer++;
                } else {
                    pointer++;
                    i = linha.length();
                }
            }
        }

        if (aux != "") {
            netflix.setDirector(aux.split(", "));
            netflix.setQtdDirectors(netflix.getDirector().length);
        } else {
            netflix.setQtdDirectors(0);
        }
        aux = "";
        // #endregion Director

        // ---------- Cast ----------
        // #region Cast
        if (linha.charAt(pointer) == '\"') {
            pointer++;
            for (int i = pointer; i < linha.length(); i++) {
                if (linha.charAt(i) != '\"' || (linha.charAt(i) == '\"' && linha.charAt(i + 1) == '\"') || (linha.charAt(i) == '\"' && linha.charAt(i - 1) == '\"')) {
                    aux += linha.charAt(i);
                    pointer++;
                } else {
                    pointer += 2;
                    i = linha.length();
                }
            }
        } else {
            for (int i = pointer; i < linha.length(); i++) {
                if (linha.charAt(i) != ',') {
                    aux += linha.charAt(i);
                    pointer++;
                } else {
                    pointer++;
                    i = linha.length();
                }
            }
        }

        if (aux != "") {
            netflix.setCast(aux.split(", "));
            netflix.setQtdCast(netflix.getCast().length);
        } else {
            netflix.setQtdCast(0);
        }
        aux = "";
        // #endregion Cast  

        // ---------- Country ----------
        // #region Country
        if (linha.charAt(pointer) == '\"') {
            pointer++;
            for (int i = pointer; i < linha.length(); i++) {
                if (linha.charAt(i) != '\"' || (linha.charAt(i) == '\"' && linha.charAt(i + 1) == '\"') || (linha.charAt(i) == '\"' && linha.charAt(i - 1) == '\"')) {
                    aux += linha.charAt(i);
                    pointer++;
                } else {
                    pointer += 2;
                    i = linha.length();
                }
            }
        } else {
            for (int i = pointer; i < linha.length(); i++) {
                if (linha.charAt(i) != ',') {
                    aux += linha.charAt(i);
                    pointer++;
                } else {
                    pointer++;
                    i = linha.length();
                }
            }
        }
        if (aux != "") {
            netflix.setCountry(aux.split(", "));
            netflix.setQtdCountries(netflix.getCountry().length);
        } else {
            netflix.setQtdCountries(0);
        }
        aux = "";
        // #endregion Country

        // ---------- Date Added ----------
        // #region Date Added
        String year = "";
        String month = "";
        String day = "";
        String[] aux2 = new String[2];

        if (linha.charAt(pointer) == '\"') {
            pointer++; // pula as aspas
            for (int i = pointer; i < linha.length(); i++) {
                if (linha.charAt(i) != '\"') {
                    aux += linha.charAt(i);
                    pointer++;
                } else {
                    pointer += 2;
                    i = linha.length();
                }
            }

            aux2 = aux.split(", ");

            // separar mes
            for (int i = 0; i < aux.length(); i++) {
                if (aux2[0].charAt(i) != ' ') {
                    month += aux2[0].charAt(i);
                } else {
                    i = aux.length();
                }
            }

            // separar dia
            for (int i = 0; i < aux.length(); i++) {
                if (aux2[0].charAt(i) == ' ') {
                    day += aux.charAt(i + 1);
                    if (aux.charAt(i + 2) != ',') {
                        day += aux.charAt(i + 2);
                    }
                    i = aux.length();
                }
            }

            // separar ano
            for (int i = 0; i < 4; i++) {
                year += aux2[1].charAt(i);
            }

            month = month.toLowerCase();
            switch (month) {
                case "january":
                    month = "01";
                    break;
                case "february":
                    month = "02";
                    break;
                case "march":
                    month = "03";
                    break;
                case "april":
                    month = "04";
                    break;
                case "may":
                    month = "05";
                    break;
                case "june":
                    month = "06";
                    break;
                case "july":
                    month = "07";
                    break;
                case "august":
                    month = "08";
                    break;
                case "september":
                    month = "09";
                    break;
                case "october":
                    month = "10";
                    break;
                case "november":
                    month = "11";
                    break;
                case "december":
                    month = "12";
                    break;
            }

            if (day.length() < 2) { // se o dia for menor que 10, acrescenta um 0 na frente
                aux = year + "-" + month + "-" + "0" + day;
            } else {
                aux = year + "-" + month + "-" + day;
            }
            
            netflix.setDateString(aux);
            netflix.setDate_added(aux);
            aux = "";
        } else {
            // se nao tiver aspas, a data nao existe
            netflix.setDateString("Sem data");
            netflix.setDate_added("1010-10-10"); // data ficticia
            pointer += 2;
        }
        // #endregion Date Added 

        // ---------- Release Year ----------
        for (int i = pointer; i < linha.length(); i++) {
            if (linha.charAt(i) != ',') {
                aux += linha.charAt(i);
                pointer++;
            } else {
                pointer++;
                i = linha.length();
            }
        }
        netflix.setRelease_year(Integer.parseInt(aux));
        aux = "";

        // ---------- Rating ----------
        for (int i = pointer; i < linha.length(); i++) {
            if (linha.charAt(i) != ',') {
                aux += linha.charAt(i);
                pointer++;
            } else {
                pointer++;
                i = linha.length();
            }
        }
        netflix.setRating(aux);
        aux = "";

        // ---------- Duration ----------
        for (int i = pointer; i < linha.length(); i++) {
            if (linha.charAt(i) != ',') {
                aux += linha.charAt(i);
                pointer++;
            } else {
                pointer++;
                i = linha.length();
            }
        }
        netflix.setDuration(aux);
        aux = "";
    }
    
}
