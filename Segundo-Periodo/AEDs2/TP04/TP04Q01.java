class Celula {
    public GameOficial game;
    public Celula prox;

    public Celula() {
        this.game = null;
        this.prox = null;
    }

    public Celula(GameOficial game) {
        this.game = game;
        this.prox = null;
    }
}

class CLista {
    private Celula primeiro;
    private Celula ultimo;
    private int quantidade;

    public CLista() {
        this.primeiro = new Celula();
        this.ultimo = this.primeiro;
        this.quantidade = 0;
    }

    public void inserirInicio(GameOficial jogo) {
        Celula tmp = new Celula(jogo);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (quantidade == 0) {
            ultimo = tmp;
        }
        quantidade++;
    }

    public void inserirFim(GameOficial jogo) {
        Celula tmp = new Celula(jogo);
        ultimo.prox = tmp;
        tmp.prox = null;
        ultimo = tmp;
        quantidade++;
    }

    public void inserirPos(GameOficial jogo, int pos) {
        if (pos == 1) {
            inserirInicio(jogo);
        } else if (pos == quantidade) {
            inserirFim(jogo);
        } else {
            Celula tmp = new Celula(jogo);
            Celula a = primeiro.prox;
            for (int i = 1; i < pos; i++, a = a.prox)
                ;
            tmp.prox = a.prox;
            a.prox = tmp;
            quantidade++;
        }
    }

    public String removerInicio() {
        String nome = primeiro.prox.game.getName();
        primeiro.prox = primeiro.prox.prox;
        quantidade--;
        return nome;
    }

    public String removerFim() {
        String nome = ultimo.game.getName();
        if (primeiro == ultimo) {
            return nome;
        }
        Celula a = primeiro;
        for (int i = 1; i < quantidade; i++, a = a.prox) {
        }
        ultimo = a;
        a.prox = null;
        quantidade--;
        return nome;
    }

    public String removerPos(int pos) {
        Celula a = primeiro.prox;
        for (int i = 1; i < pos; i++, a = a.prox) {
        }
        String nome = a.prox.game.getName();
        a.prox = a.prox.prox;
        quantidade--;
        return nome;
    }

    public void imprime() {
        Celula a = primeiro.prox;
        for (int i = 0; i < quantidade; i++, a = a.prox) {
            System.out.print("[" + i + "] ");
            a.game.Imprime();
        }
    }
}

class GameOficial {
    // variaveis
    private int app_id;
    private String name;
    private String release_data;
    private String owners;
    private int age;
    private float price;
    private int dlcs;
    private String languages;
    private String website;
    private Boolean windows;
    private Boolean mac;
    private Boolean linux;
    private float upvotes;
    private int avg_pt;
    private String developers;
    private String genres;

    // construtor vazio
    public GameOficial() {
        setAppId(0);
        setName("");
        setData("");
        setOwners("");
        setAge(0);
        setPrice(0);
        setDlcs(0);
        setLanguages(null);
        setWebsite("");
        setWindows(true);
        setMac(true);
        setLinux(true);
        setUpVotes(0);
        setAvgPt(0);
        setDevelopers("");
        setGenres("");
    }

    // construtor normal
    public GameOficial(int AppId, String nome, String data, String owner, int idade, float preco, int dlc,
            String linguas, String web, Boolean window, Boolean mec, Boolean lin, float upvotos, int avg,
            String develop, String gen) {
        setAppId(AppId);
        setName(nome);
        setData(data);
        setOwners(owner);
        setAge(idade);
        setPrice(preco);
        setDlcs(dlc);
        setLanguages(linguas);
        setWebsite(web);
        setWindows(window);
        setMac(mec);
        setLinux(lin);
        setUpVotes(upvotos);
        setAvgPt(avg);
        setDevelopers(develop);
        setGenres(gen);
    }

    // getters e setters
    public void setAppId(int AppId) {
        app_id = AppId;
    }

    public int getAppId() {
        return app_id;
    }

    public void setName(String nome) {
        name = nome;
    }

    public String getName() {
        return name;
    }

    public void setData(String data) {
        release_data = data;
    }

    public String getData() {
        return release_data;
    }

    public void setOwners(String owner) {
        owners = owner;
    }

    public String getOwners() {
        return owners;
    }

    public void setAge(int idade) {
        age = idade;
    }

    public int getAge() {
        return age;
    }

    public void setPrice(float preco) {
        price = preco;
    }

    public float getPrice() {
        return price;
    }

    public void setDlcs(int dlc) {
        dlcs = dlc;
    }

    public int getDlcs() {
        return dlcs;
    }

    public void setLanguages(String linguas) {
        languages = linguas;
    }

    public String getLanguages() {
        return languages;
    }

    public void setWebsite(String web) {
        website = web;
    }

    public String getWebsite() {
        return website;
    }

    public void setWindows(Boolean window) {
        windows = window;
    }

    public Boolean getWindows() {
        return windows;
    }

    public void setMac(Boolean mec) {
        mac = mec;
    }

    public Boolean getMac() {
        return mac;
    }

    public void setLinux(Boolean lin) {
        linux = lin;
    }

    public Boolean getLinux() {
        return linux;
    }

    public void setUpVotes(float upvotos) {
        upvotes = upvotos;
    }

    public float getUpVotes() {
        return upvotes;
    }

    public void setAvgPt(int avg) {
        avg_pt = avg;
    }

    public int getAvgPt() {
        return avg_pt;
    }

    public void setDevelopers(String develop) {
        developers = develop;
    }

    public String getDevelopers() {
        return developers;
    }

    public void setGenres(String gen) {
        genres = gen;
    }

    public String getGenres() {
        return genres;
    }

    public void Criador(String ID) {
        setAppId(Integer.parseInt(ID));
        String linhaf = "";
        Arq.openRead("/tmp/games.csv", "UTF-8");
        int lixo = 0; // sai do loop quando acha a linha certa
        while (Arq.hasNext() && lixo == 0) {
            String linha = Arq.readLine();
            int max = (linha.length() < ID.length()) ? linha.length() : ID.length();
            String verificaID = linha.substring(0, max);
            if (verificaID.equals(ID)) {
                linhaf = linha;
                lixo = 1;
            }
        }
        // encontrar virgulas
        int[] V = new int[16];
        int a = 0;
        for (int i = 0; i < linhaf.length(); i++) {
            if (linhaf.charAt(i) == 91) {
                for (int x = i; x < linhaf.length(); x++) {
                    if (linhaf.charAt(x) == 93) {
                        i = x;
                        x = linhaf.length();
                    }
                }
            } else if (linhaf.charAt(i) == 34) {
                for (int x = i + 1; x < linhaf.length(); x++) {
                    if (linhaf.charAt(x) == 34) {
                        i = x;
                        x = linhaf.length();
                    }
                }
            } else if (linhaf.charAt(i) == 44) {
                V[a] = i;
                a++;
            }
        }
        // enviar o AppId
        int AppID = Integer.parseInt(ID);
        setAppId(AppID);

        // enviar nome do jogo
        String nome = linhaf.substring(V[0] + 1, V[1]);
        setName(nome);

        // enviar data
        String data = linhaf.substring(V[1] + 2, V[1] + 5) + "/" + linhaf.substring(V[2] - 5, V[2] - 1);
        setData(data);

        // enviar donos
        String owners = linhaf.substring(V[2] + 1, V[3]);
        setOwners(owners);

        // enviar idade
        int age = Integer.parseInt(linhaf.substring(V[3] + 1, V[4]));
        setAge(age);

        // enviar preço
        float preco = Float.valueOf(linhaf.substring(V[4] + 1, V[5]));
        setPrice(preco);

        // enviar dlcs
        int dlc = Integer.parseInt(linhaf.substring(V[5] + 1, V[6]));
        setDlcs(dlc);

        // enviar vetor linguagens
        String[] linguas = new String[1000];
        String linhaG = "";
        a = 0;
        for (int x = V[6] + 2; x < V[7]; x++) {
            if (linhaf.charAt(x) == 39) {
                if (a == 0) {
                    linguas[a] = "[";
                    linhaG += "[";
                } else {
                    linguas[a] = " ";
                    linhaG += " ";
                }
                for (int n = x + 1; n < V[7]; n++) {
                    if (linhaf.charAt(n) == 39) {
                        linguas[a] += linhaf.substring(x + 1, n);
                        linhaG += linhaf.substring(x + 1, n);
                        if (linhaf.charAt(n + 1) == 44) {
                            linguas[a] += ",";
                            linhaG += ",";
                        } else {
                            linguas[a] += "]";
                            linhaG += "]";
                        }

                        a++;
                        x = n;
                        n = V[7];
                    }
                }
            }
        }
        setLanguages(linhaG);

        // enviar site
        String web = linhaf.substring(V[7] + 1, V[8]);
        if (web.length() == 0) {
            setWebsite("null");
        } else {
            setWebsite(web);
        }

        // enviar windows
        Boolean windows;
        if ((linhaf.substring(V[8] + 1, V[9]).equals("True"))) {
            windows = true;
        } else {
            windows = false;
        }
        setWindows(windows);

        // enviar Mac
        Boolean mac;
        if ((linhaf.substring(V[9] + 1, V[10]).equals("True"))) {
            mac = true;
        } else {
            mac = false;
        }
        setMac(mac);

        // enviar Linux
        Boolean linux;
        if ((linhaf.substring(V[10] + 1, V[11]).equals("True"))) {
            linux = true;
        } else {
            linux = false;
        }
        setLinux(linux);

        // % votos positivos
        int positivo = Integer.parseInt(linhaf.substring(V[11] + 1, V[12]));
        int negativo = Integer.parseInt(linhaf.substring(V[12] + 1, V[13]));
        float media = (float) positivo / ((float) positivo + (float) negativo);
        setUpVotes(media);

        // horas
        int hTotal = Integer.parseInt(linhaf.substring(V[13] + 1, V[14]));
        int horas = hTotal / 60;
        int min = hTotal % 60;
        if (horas == 0 && min == 0) {
            setAvgPt(0);
        } else {
            setAvgPt(hTotal);
        }

        // empresa
        String empresa = linhaf.substring(V[14] + 1, V[15]);
        if (empresa.charAt(0) != 34) {
            setDevelopers(empresa);
        } else {
            setDevelopers(empresa.substring(1, empresa.length() - 1));
        }

        // genre
        String generoG = "[";
        a = 0;
        if (linhaf.charAt(V[15] + 1) == 34) {
            for (int i = V[15] + 2; i < linhaf.length(); i++) {
                for (int x = i; x < linhaf.length(); x++) {
                    if (linhaf.charAt(x) == 44) {
                        generoG += linhaf.substring(i, x);
                        generoG += ", ";
                        i = x;
                        x = linhaf.length();
                    } else if (linhaf.charAt(x) == 34) {
                        generoG += linhaf.substring(i, x);
                        generoG += "]";
                        i = linhaf.length();
                        x = linhaf.length();
                    }
                }
            }
        } else {
            generoG += linhaf.substring(V[15] + 1, linhaf.length());
            generoG += "]";
        }
        setGenres(generoG);
    }

    public void Imprime() {
        MyIO.print(getAppId() + " ");
        MyIO.print(getName() + " ");
        MyIO.print(getData() + " ");
        MyIO.print(getOwners() + " ");
        MyIO.print(getAge() + " ");
        if (getPrice() == 0) {
            MyIO.print(getPrice() + "0 ");
        } else {
            MyIO.print(getPrice() + " ");
        }
        MyIO.print(getDlcs() + " ");
        MyIO.print(getLanguages() + " ");
        MyIO.print(getWebsite() + " ");
        if (getWindows() == true) {
            MyIO.print("true ");
        } else {
            MyIO.print("false ");
        }
        if (getMac() == true) {
            MyIO.print("true ");
        } else {
            MyIO.print("false ");
        }
        if (getLinux() == true) {
            MyIO.print("true ");
        } else {
            MyIO.print("false ");
        }
        MyIO.printf("%.0f", getUpVotes() * 100);
        MyIO.print("% ");
        if (getAvgPt() == 0) {
            MyIO.print("null ");
        } else if (getAvgPt() < 60) {
            MyIO.print(getAvgPt() % 60 + "m ");
        } else if (getAvgPt() % 60 == 0) {
            MyIO.print(getAvgPt() / 60 + "h ");
        } else {
            MyIO.print(getAvgPt() / 60 + "h " + getAvgPt() % 60 + "m ");
        }
        if (getDevelopers().charAt(0) == 34) {
            MyIO.print(getDevelopers().substring(1, getDevelopers().length() - 1) + " ");
        } else {
            MyIO.print(getDevelopers() + " ");
        }
        MyIO.println(getGenres());

    }
}

public class TP04Q01 {
    public static void main(String[] args) {
        CLista lista = new CLista();
        String ID = MyIO.readLine();
        while (!(ID.equals("FIM"))) {
            GameOficial game = new GameOficial();
            game.Criador(ID);
            lista.inserirFim(game);
            ID = MyIO.readLine();
        }
        int N = MyIO.readInt();
        for (int i = 0; i < N; i++) {
            String input = MyIO.readLine();
            String[] str = input.split(" ");
            if (str[0].equals("RF")) {
                System.out.println("(R) " + lista.removerFim());
            } else if (str[0].equals("RI")) {
                System.out.println("(R) " + lista.removerInicio());
            } else if (str[0].equals("R*")) {
                int pos = Integer.parseInt(str[1]);
                System.out.println("(R) " + lista.removerPos(pos));
            } else if (str[0].equals("II")) {
                GameOficial game = new GameOficial();
                game.Criador(str[1]);
                lista.inserirInicio(game);
            } else if (str[0].equals("IF")) {
                GameOficial game = new GameOficial();
                game.Criador(str[1]);
                lista.inserirFim(game);
            } else if (str[0].equals("I*")) {
                int pos = Integer.parseInt(str[1]);
                GameOficial game = new GameOficial();
                game.Criador(str[2]);
                lista.inserirPos(game, pos);
            }
        }
        lista.imprime();
    }
}
