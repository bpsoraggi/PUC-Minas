
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

class Game {
    static SimpleDateFormat default_dateFormat = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);

    private String name, owners, website, developers;
    private ArrayList<String> languages, genres;
    private Date release_date;
    private int app_id, age, dlcs, avg_playtime;
    private float price, upvotes;
    private boolean windows, mac, linux;

    // construtores
    public Game() {
        this.name = this.owners = this.website = this.developers = null;
        this.languages = new ArrayList<String>();
        this.genres = new ArrayList<String>();
        this.release_date = null;
        this.app_id = this.age = this.dlcs = this.avg_playtime = -1;
        this.price = this.upvotes = -1;
        this.windows = this.mac = this.linux = false;
    }

    public Game(String name, String owners, String website, String developers, ArrayList<String> languages,
            ArrayList<String> genres, Date release_date, int app_id, int age, int dlcs, int upvotes, int avg_playtime,
            float price, boolean windows, boolean mac, boolean linux) {
        this.name = name;
        this.owners = owners;
        this.website = website;
        this.developers = developers;
        this.languages = languages;
        this.genres = genres;
        this.release_date = release_date;
        this.app_id = app_id;
        this.age = age;
        this.dlcs = dlcs;
        this.upvotes = upvotes;
        this.avg_playtime = avg_playtime;
        this.price = price;
        this.windows = windows;
        this.mac = mac;
        this.linux = linux;
    }

    // get e set
    public void setName(String name) {
        this.name = name;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public void setReleaseDate(Date release_date) {
        this.release_date = release_date;
    }

    public void setAppId(int app_id) {
        this.app_id = app_id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDlcs(int dlcs) {
        this.dlcs = dlcs;
    }

    public void setAvgPlaytime(int avg_playtime) {
        this.avg_playtime = avg_playtime;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setUpvotes(float upvotes) {
        this.upvotes = upvotes;
    }

    public void setWindows(boolean windows) {
        this.windows = windows;
    }

    public void setMac(boolean mac) {
        this.mac = mac;
    }

    public void setLinux(boolean linux) {
        this.linux = linux;
    }

    public String getName() {
        return this.name;
    }

    public String getOwners() {
        return this.owners;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getDevelopers() {
        return this.developers;
    }

    public ArrayList<String> getLanguages() {
        return this.languages;
    }

    public ArrayList<String> getGenres() {
        return this.genres;
    }

    public Date getReleaseDate() {
        return this.release_date;
    }

    public int getAppId() {
        return this.app_id;
    }

    public int getAge() {
        return this.age;
    }

    public int getDlcs() {
        return this.dlcs;
    }

    public int getAvgPlaytime() {
        return this.avg_playtime;
    }

    public float getPrice() {
        return this.price;
    }

    public float getUpvotes() {
        return this.upvotes;
    }

    public boolean getWindows() {
        return this.windows;
    }

    public boolean getMac() {
        return this.mac;
    }

    public boolean getLinux() {
        return this.linux;
    }

    // clone
    public Game clone() {
        Game cloned = new Game();
        cloned.name = this.name;
        cloned.owners = this.owners;
        cloned.website = this.website;
        cloned.developers = this.developers;
        cloned.languages = this.languages;
        cloned.genres = this.genres;
        cloned.release_date = this.release_date;
        cloned.app_id = this.app_id;
        cloned.age = this.age;
        cloned.dlcs = this.dlcs;
        cloned.avg_playtime = this.avg_playtime;
        cloned.price = this.price;
        cloned.upvotes = this.upvotes;
        cloned.windows = this.windows;
        cloned.mac = this.mac;
        cloned.linux = this.linux;
        return cloned;
    }

    // read
    public void ler(String line) {
        char c_search;
        int index = 0, atr_index = 0;

        // app_id
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.app_id = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // name
        if (line.charAt(atr_index) != ',') {
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                c_search = '\"';
            } else
                c_search = ',';
            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    this.name = line.substring(atr_index, index);
                    if (c_search == ',')
                        index++;
                    else if (c_search == '\"')
                        index += 2;
                    atr_index = index;
                    break;
                }
            }
        } else
            atr_index = ++index;

        // release date
        if (line.charAt(atr_index) != ',') {
            SimpleDateFormat df;
            if (line.charAt(atr_index) == '\"') {
                df = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
                atr_index++;
                c_search = '\"';
            } else {
                df = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
                c_search = ',';
            }
            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    try {
                        this.release_date = df.parse(line.substring(atr_index, index));
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                    if (c_search == ',')
                        index++;
                    else if (c_search == '\"')
                        index += 2;
                    atr_index = index;
                    break;
                }
            }
        } else
            atr_index = ++index;

        // owners
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.owners = line.substring(atr_index, index);
                atr_index = ++index;
                break;
            }
        }

        // age
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.age = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // price
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.price = Float.parseFloat(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // dlcs
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.dlcs = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // languages
        while (true) {
            index++;
            if (line.charAt(index) == ']') {
                index++;
                if (line.charAt(index) == ',')
                    index++;
                else if (line.charAt(index) == '\"')
                    index += 2;
                atr_index = index;
                break;
            } else if (line.charAt(index) == '\'') {
                int wordStart = index + 1;
                while (true) {
                    index++;
                    if (line.charAt(index) == '\'') {
                        this.languages.add(line.substring(wordStart, index));
                        break;
                    }
                }
            }
        }

        // site
        if (line.charAt(atr_index) != ',') {
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                c_search = '\"';
            } else
                c_search = ',';

            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    this.website = line.substring(atr_index, index);
                    atr_index = ++index;
                    break;
                }
            }
        } else
            atr_index = ++index;

        // Find windows
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.windows = Boolean.parseBoolean(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // mac
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.mac = Boolean.parseBoolean(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // linux
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.linux = Boolean.parseBoolean(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // upvotes
        int positives, negatives;
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                positives = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                negatives = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }
        this.upvotes = (float) (positives * 100) / (float) (positives + negatives);

        // avg_pt
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.avg_playtime = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // developers
        if (line.charAt(atr_index) != ',') {
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                c_search = '\"';
            } else
                c_search = ',';
            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    this.developers = line.substring(atr_index, index);
                    atr_index = ++index;
                    break;
                }
            }
        } else
            atr_index = ++index;

        // genres
        if (index < line.length() - 1) {
            if (line.charAt(index) == ',')
                atr_index = ++index;
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                while (true) {
                    index++;
                    if (line.charAt(index) == ',') {
                        this.genres.add(line.substring(atr_index, index));
                        atr_index = ++index;
                    } else if (line.charAt(index) == '\"') {
                        this.genres.add(line.substring(atr_index, line.length() - 1));
                        break;
                    }
                }
            } else
                this.genres.add(line.substring(atr_index, line.length()));
        }
    }

    public void imprimir() {
        String avg_pt = null;
        if (this.avg_playtime == 0)
            avg_pt = "null ";
        else if (this.avg_playtime < 60)
            avg_pt = this.avg_playtime + "m ";
        else {
            if (this.avg_playtime % 60 == 0)
                avg_pt = this.avg_playtime / 60 + "h ";
            else
                avg_pt = (this.avg_playtime / 60) + "h " + (this.avg_playtime % 60) + "m ";
        }

        DecimalFormat df = new DecimalFormat("##");
        System.out.println(this.app_id + " " + this.name + " " + default_dateFormat.format(this.release_date) + " "
                + this.owners + " " + this.age + " " + String.format(Locale.US, "%.2f", price) + " " + this.dlcs + " "
                + this.languages + " " + this.website + " " + this.windows + " " + this.mac + " " + this.linux + " "
                + (Float.isNaN(this.upvotes) ? "0.0% " : df.format(this.upvotes) + "% ") + avg_pt + this.developers
                + " " + this.genres);
    }
}

public class TP02Q05 {
    // criar lista
    public static CLista listaDeJogos = new CLista();

    public static boolean isFim(String line) {
        return (line.length() == 3 && line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M');
    }

    public static void carregar(String linhaEntrada) throws FileNotFoundException {
        int flag = 0;
        FileInputStream path = new FileInputStream("/tmp/games.csv");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(path));
            String linhaCSV;
            do { // arquivo
                linhaCSV = br.readLine();
                if (linhaCSV != null) { // quando acabar arquivo
                    String[] nome = linhaCSV.split(",");
                    if (linhaEntrada.equals(nome[0])) {
                        Game jogo = new Game();
                        jogo.ler(linhaCSV);
                        listaDeJogos.insereFim(jogo);
                        flag++;
                    }
                }
            } while (linhaCSV != null && flag == 0); // quando acabar arquivo
            flag = 0;
            path.getChannel().position(0);
            br = new BufferedReader(new InputStreamReader(path));
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void carregarFIM(String linhaEntrada) throws FileNotFoundException {
        int flag = 0;
        FileInputStream path = new FileInputStream("/tmp/games.csv");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(path));
            String linhaCSV;
            do { // arquivo
                linhaCSV = br.readLine();
                if (linhaCSV != null) { // quando acabar arquivo
                    String[] nome = linhaCSV.split(",");
                    if (linhaEntrada.equals(nome[0])) {
                        Game jogo = new Game();
                        jogo.ler(linhaCSV);
                        listaDeJogos.insereFim(jogo);
                        flag++;
                    }
                }
            } while (linhaCSV != null && flag == 0); // quando acabar arquivo
            flag = 0;
            path.getChannel().position(0);
            br = new BufferedReader(new InputStreamReader(path));
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void carregarIndice(String linhaEntrada, int indice) throws FileNotFoundException {
        int flag = 0;
        FileInputStream path = new FileInputStream("/tmp/games.csv");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(path));
            String linhaCSV;
            do { // arquivo
                linhaCSV = br.readLine();
                if (linhaCSV != null) { // quando acabar arquivo
                    String[] nome = linhaCSV.split(",");
                    if (linhaEntrada.equals(nome[0])) {
                        Game jogo = new Game();
                        jogo.ler(linhaCSV);
                        listaDeJogos.insereIndice(jogo, indice);
                        flag++;
                    }
                }
            } while (linhaCSV != null && flag == 0); // quando acabar arquivo
            flag = 0;
            path.getChannel().position(0);
            br = new BufferedReader(new InputStreamReader(path));
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void carregarInicio(String linhaEntrada) throws FileNotFoundException {
        int flag = 0;
        FileInputStream path = new FileInputStream("/tmp/games.csv");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(path));
            String linhaCSV;
            do { // arquivo
                linhaCSV = br.readLine();
                if (linhaCSV != null) { // quando acabar arquivo
                    String[] nome = linhaCSV.split(",");
                    if (linhaEntrada.equals(nome[0])) {
                        Game jogo = new Game();
                        jogo.ler(linhaCSV);
                        listaDeJogos.insereComeco(jogo);
                        flag++;
                    }
                }
            } while (linhaCSV != null && flag == 0); // quando acabar arquivo
            flag = 0;
            path.getChannel().position(0);
            br = new BufferedReader(new InputStreamReader(path));
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String linhaEntrada;
        // carregar app_id
        do {
            linhaEntrada = sc.nextLine();
            if (isFim(linhaEntrada) == false) {
                try {
                    carregar(linhaEntrada);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } while (isFim(linhaEntrada) == false);

        int qtdEntrada = sc.nextInt();
        sc.nextLine();
        String options;

        while (sc.hasNext()) {
            options = sc.nextLine();
            String[] items = options.split(" ");
            if (items[0].equals("II")) {
                try {
                    carregarInicio(items[1]);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (items[0].equals("IF")) {
                try {
                    carregarFIM(items[1]);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (items[0].equals("I*")) {
                try {
                    int indice = Integer.valueOf(items[1]);
                    carregarIndice(items[2], indice);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (items[0].equals("RF")) {
                Game removido = (Game) listaDeJogos.removeRetornaFim();
                System.out.println("(R) " + removido.getName());
            } else if (items[0].equals("RI")) {
                Game removido = (Game) listaDeJogos.removeRetornaComeco();
                System.out.println("(R) " + removido.getName());
            } else if (items[0].equals("R*")) {
                int indice = Integer.valueOf(items[1]);
                Game removido = (Game) listaDeJogos.removeRetornaIndice(indice);
                System.out.println("(R) " + removido.getName());
            }
        }

        // print lista
        int n = listaDeJogos.quantidade();
        for (int i = 1; i <= n; i++) {
            Game imprime = (Game) listaDeJogos.retornaIndice(i);
            System.out.print("[" + (i - 1) + "] ");
            imprime.imprimir();
        }
    }

}

// lista
class CCelula {
    public Object item;
    public CCelula prox;

    public CCelula(Object valorItem, CCelula proxCelula) {
        item = valorItem;
        prox = proxCelula;
    }

    public CCelula(Object valorItem) {
        item = valorItem;
        prox = null;
    }

    public CCelula() {
        item = null;
        prox = null;
    }
}

class CLista {

    private CCelula primeira; // Referencia a c�lula cabe�a
    private CCelula ultima; // Referencia a �ltima c�lula da lista
    private int qtde; // Contador de itens na lista. Deve ser incrementado sempre que um item for
                      // inserido e decrementado quando um item for exclu�do.

    // Fun��o construtora. Aloca a c�lula cabe�a e faz todas as refer�ncias
    // apontarem para ela.
    public CLista() {
        primeira = new CCelula();
        ultima = primeira;
    }

    // Verifica se a lista est� vazia. Retorna TRUE se a lista estiver vazia e FALSE
    // se ela tiver elementos.
    public boolean vazia() {
        return primeira == ultima;
    }

    // Insere um novo Item no fim da lista.
    public void insereFim(Object valorItem) {
        ultima.prox = new CCelula(valorItem);
        ultima = ultima.prox;
        qtde++;
    }

    // Insere um novo Item no come�o da lista
    public void insereComeco(Object valorItem) {
        CCelula aux = primeira.prox;
        primeira.prox = new CCelula(valorItem, aux);
        if (aux == null)
            ultima = primeira.prox;
        qtde++;
    }

    public void insereComeco_old(Object valorItem) {
        primeira.prox = new CCelula(valorItem, primeira.prox);
        if (primeira.prox.prox == null)
            ultima = primeira.prox;
        qtde++;
    }

    // Insere o Item passado por par�metro na posi��o determinada.
    // O par�metro "valorItem" � o item a ser inserido na lista.
    // O par�metro "posicao" � a posi��o na qual o elemento ser� inserido. O
    // primeiro elemento est� na posi��o 1, e assim por diante.
    // Se a posi��o existir e o m�todo conseguir inserir o elemento, retorna TRUE.
    // Caso a posi��o n�o exista, retorna FALSE.
    public boolean insereIndice(Object valorItem, int posicao) {
        int tamanho = quantidade();
        if (posicao < 0 || posicao > tamanho) {
            return false;
        } else if (posicao == 0) {
            insereComeco(valorItem);
        } else if (posicao == tamanho) {
            insereFim(valorItem);
        } else {
            CCelula aux = primeira;
            for (int i = 0; i < posicao; i++, aux = aux.prox)
                ;
            CCelula tmp = new CCelula(valorItem);
            tmp.prox = aux.prox;
            aux.prox = tmp;
            tmp = aux = null;
            qtde++;
        }
        return true;
    }

    // Imprime todos os elementos da lista usando o comando while
    public void imprime() {
        CCelula aux = primeira.prox;

        while (aux != null) {
            System.out.println(aux.item);
            aux = aux.prox;
        }
    }

    // Imprime todos os elementos da lista usando o comando for
    public void imprimeFor() {
        for (CCelula aux = primeira.prox; aux != null; aux = aux.prox)
            System.out.print(aux.item + " ");
    }

    // Imprime todos os elementos simulando formato de lista:
    // [/]->[7]->[21]->[13]->null
    public void imprimeFormatoLista() {
        System.out.print("[/]->");
        for (CCelula aux = primeira.prox; aux != null; aux = aux.prox)
            System.out.print("[" + aux.item + "]->");
        System.out.println("null");
    }

    // Imprime todos os elementos simulando formato de lista:
    // [/]->[7]->[21]->[13]->null
    public void imprimeFormatoLista(String titulo) {
        System.out.println(titulo + "\n");
        System.out.print("[/]->");
        for (CCelula aux = primeira.prox; aux != null; aux = aux.prox)
            System.out.print("[" + aux.item + "]->");
        System.out.println("null");
    }

    // Verifica se o elemento passado como par�metro est� contido na lista.
    // O par�metro "elemento" � o item a ser localizado. O m�todo retorna TRUE caso
    // o Item esteja presente na lista.
    public boolean contem(Object elemento) {
        boolean achou = false;
        CCelula aux = primeira.prox;
        while (aux != null && !achou) {
            achou = aux.item.equals(elemento);
            aux = aux.prox;
        }
        return achou;
    }

    // Verifica se o elemento passado como par�metro est� contido na lista. (Obs:
    // usando o comando FOR)
    // O par�metro "elemento" � o item a ser localizado. O m�todo retorna TRUE caso
    // o Item esteja presente na lista.
    public boolean contemFor(Object elemento) {
        boolean achou = false;
        for (CCelula aux = primeira.prox; aux != null && !achou; aux = aux.prox)
            achou = aux.item.equals(elemento);
        return achou;
    }

    // Retorna um Object contendo o primeiro Item da lista. Se a lista estiver vazia
    // a fun��o retorna null.
    public Object retornaPrimeiro() {
        if (primeira != ultima)
            return primeira.prox.item;
        else
            return null;
    }

    // Retorna um Object contendo o �ltimo Item da lista. Se a lista estiver vazia a
    // fun��o retorna null.
    public Object retornaUltimo() {
        if (primeira != ultima)
            return ultima.item;
        else
            return null;
    }

    // Retorna o Item contido na posi��o passada por par�metro
    public Object retornaIndice(int posicao) {
        // EXERC�CIO : deve retornar o elemento da posi��o p passada por par�metro
        // [cabe�a]->[7]->[21]->[13]->null
        // retornaIndice(2) deve retornar o elemento 21. retornaIndice de uma posi�ao
        // inexistente deve retornar null.
        // Verifica se � uma posi��o v�lida e se a lista possui elementos
        if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {

            // Procura a posicao a ser retornada
            CCelula aux = primeira.prox;
            for (int i = 1; i < posicao; i++, aux = aux.prox)
                ;
            return aux.item;
        }
        return null;
    }

    // Remove e retorna o primeiro item da lista (remo��o f�sica, ou seja, elimina a
    // c�lula que cont�m o elemento).
    // Retorna um Object contendo o Item removido ou null caso a lista esteja vazia.
    public Object removeRetornaComeco() {
        // Verifica se h� elementos na lista
        if (primeira != ultima) {
            CCelula aux = primeira.prox;
            primeira.prox = aux.prox;
            if (primeira.prox == null) // Se a c�lula cabe�a est� apontando para null, significa que o �nico elemento
                                       // da lista foi removido
                ultima = primeira;
            qtde--;
            return aux.item;
        }
        return null;
    }

    // Remove e retorna o primeiro item da lista (remo��o l�gica, ou seja, remove a
    // c�lula cabe�a fazendo com que a c�lula seguinte ela se torne a nova c�lula
    // cabe�a).
    // Retorna um Object contendo o item removido ou null caso a lista esteja vazia.
    public Object removeRetornaComecoSimples() {
        // Verifica se h� elementos na lista
        if (primeira != ultima) {
            primeira = primeira.prox;
            qtde--;
            return primeira.item;
        }
        return null;
    }

    // Remove o primeiro item da lista fazendo com que a c�lula seguinte � c�lula
    // cabe�a se torne a nova c�lula cabe�a. N�o retorna o item removido.
    public void removeComecoSemRetorno() {
        if (primeira != ultima) {
            primeira = primeira.prox;
            qtde--;
        }
    }

    // Remove o �ltimo Item da lista.
    // Retorna um Object contendo o Item removido ou null caso a lista esteja vazia.
    public Object removeRetornaFim() {
        if (primeira != ultima) {
            CCelula aux = primeira;
            while (aux.prox != ultima)
                aux = aux.prox;
            CCelula aux2 = aux.prox;
            ultima = aux;
            ultima.prox = null;
            qtde--;
            return aux2.item;
        }
        return null;
    }

    // Remove o �ltimo Item da lista sem retorn�-lo.
    public void removeFimSemRetorno() {
        if (primeira != ultima) {
            CCelula aux = primeira;
            while (aux.prox != ultima)
                aux = aux.prox;
            ultima = aux;
            ultima.prox = null;
            qtde--;
        }
    }

    // Localiza o Item passado por par�metro e o remove da Lista
    // O par�metro "valorItem" � o item a ser removido da lista.
    public void remove(Object valorItem) {
        if (primeira != ultima) {
            CCelula aux = primeira;
            boolean achou = false;
            while (aux.prox != null && !achou) {
                achou = aux.prox.item.equals(valorItem);
                if (!achou)
                    aux = aux.prox;
            }
            if (achou) {
                // achou o elemento
                aux.prox = aux.prox.prox;
                if (aux.prox == null)
                    ultima = aux;
                qtde--;
            }
        }
    }

    // Remove o elemento na posi��o passada como par�metro.
    // O par�metro "posicao" � a posi��o a ser removida. OBS: o primeiro elemento
    // est� na posi��o 1, e assim por diante.
    // O m�todo retorna TRUE se a posi��o existe e foi removida com sucesso, e FALSE
    // caso a posi��o n�o exista.
    public boolean removeIndice(int posicao) {
        // Verifica se � uma posi��o v�lida e se a lista possui elementos
        if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {
            int i = 0;
            CCelula aux = primeira;
            while (i < posicao - 1) {
                aux = aux.prox;
                i++;
            }
            aux.prox = aux.prox.prox;
            if (aux.prox == null)
                ultima = aux;
            qtde--;
            return true;
        }
        return false;
    }

    // Remove e retorna o elemento na posi��o passada como par�metro.
    // O par�metro "posicao" � a posi��o a ser removida. OBS: o primeiro elemento
    // est� na posi��o 1, e assim por diante.
    // O m�todo retorna um object contendo o elemento removido da lista. Caso a
    // posi��o seja inv�lida, retorna null.
    public Object removeRetornaIndice(int posicao) {
        // Verifica se � uma posi��o v�lida e se a lista possui elementos
        if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {
            int i = 0;
            CCelula aux = primeira;
            while (i < posicao) {
                aux = aux.prox;
                i++;
            }
            CCelula aux2 = aux.prox;
            aux.prox = aux.prox.prox;
            if (aux.prox == null)
                ultima = aux;
            qtde--;
            return aux2.item;
        }
        return null;
    }

    // M�todo(getter) que retorna a quantidade de itens da lista.
    public int quantidade() {
        return qtde;
    }

}