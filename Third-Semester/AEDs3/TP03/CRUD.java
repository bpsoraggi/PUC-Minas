import java.io.RandomAccessFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class CRUD {

    // --------------- Atributos ---------------

    private int lastId;
    // Hash
    private int hashPGlobal;
    private int hashQtdBuckets;
    // ArvoreB
    private long arvoreRaiz;
    private int arvoreQtdPgs;
    // Huffman

    public RandomAccessFile raf;

    Hash hash = new Hash();
    ArvoreB arvoreB = new ArvoreB();

    // --------------- Construtor ---------------

    public CRUD() {
        this.lastId = 0;
        this.hashPGlobal = 1;
        this.hashQtdBuckets = 0;
        this.arvoreRaiz = -1;
        this.arvoreQtdPgs = 0;
        try {
            this.raf = new RandomAccessFile("banco.bin", "rw"); // cria o arquivo
            if (raf.length() != 0) { // se o arquivo não estiver vazio, lê o lastId
                raf.seek(0);
                lastId = raf.readInt();
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao criar o raf: " + e.getMessage());
        }
    }

    // --------------- Setters e Getters ---------------

    public int getLastId() {return lastId;}

    public int getHashPGlobal() {return hashPGlobal;}
    public int getHashQtdBuckets() {return hashQtdBuckets;}

    public long getArvoreRaiz() {return arvoreRaiz;}
    public int getArvoreQtdPgs() {return arvoreQtdPgs;}

    public RandomAccessFile getRaf() {return raf;}

    // --------------- Metodos ---------------

    public void convertToBin(Netflix netflix) {
        BufferedReader br = null;

        try {
            if (raf.length() == 0) { // se o arquivo estiver vazio, cria o binario
                try {
                    try {
                        br = new BufferedReader(
                                new InputStreamReader(new FileInputStream("banco.csv"), "iso-8859-1"));
                    } catch (Exception e) {
                        try {
                            br = new BufferedReader(
                                    new InputStreamReader(new FileInputStream("banco.csv"), "utf-8"));
                        } catch (Exception e2) {
                            System.out.println();
                            System.err.println("Erro ao criar BufferedReader!");
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Criando arquivos...");
                    raf.writeInt(0); // escreve o lastId
                    String line = br.readLine();
                    while (line != null) {
                        Core.readCSV(line, netflix); // le a linha do CSV e armazena no objeto
                        create(netflix); // cria o registro no arquivo binario
                        line = br.readLine(); // le a proxima linha
                    }

                    System.out.println();
                    System.out.println("Banco binario criado com sucesso!");
                    System.out.println("Arvore B criada com sucesso!");
                    System.out.println("Arquivos hash criados com sucesso!");
                    System.out.println();
                } catch (Exception e) {
                    System.out.println();
                    System.out.println("Erro ao criar o arquivo binario! " + e.getMessage());
                }
            }
            raf.seek(0); // posiciona o ponteiro no inicio do arquivo
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao criar arquivos: !");
            e.printStackTrace();
        }
    }

    // --------------- Hash ---------------

    public void getHashInfo() {
        hash.readQbAndPg();
        this.hashPGlobal = hash.getpGlobal();
        this.hashQtdBuckets = hash.getQtdBuckets();
    }

    public long findHash(int id) {
        return hash.find(id); // busca o id no hash
    }

    // --------------- Arvore ---------------

    public void getArvoreInfo() {
        this.arvoreRaiz = arvoreB.readEndRaiz(); // le o endereco da raiz
        this.arvoreQtdPgs = arvoreB.readQtdPaginas(); // le a quantidade de paginas
    }

    public long findArvore(int id) {
        long raiz = arvoreB.readEndRaiz(); // le o endereco da raiz
        long pagina = arvoreB.findPagina(raiz, id); // encontra a pagina que contem o id
        
        if (pagina != -1) { // se a pagina existir
            return arvoreB.findRegistro(pagina, id); // encontra o endereco do registro
        } else { return -1; }
    }

    // --------------- Create ---------------

    public boolean create(Netflix netflix) {
        long endNetflix = 0;
        long endPagina = -1;

        try {
            byte[] ba = netflix.toByteArray();
            raf.seek(raf.length()); // posiciona o ponteiro no final do arquivo
            endNetflix = raf.getFilePointer(); // pega a posicao do ponteiro
            raf.writeByte(0); // escreve a lapide (ativo)
            raf.writeInt(ba.length); // escreve o tamanho do registro

            raf.write(ba); // escreve o registro
            hash.inserir(netflix.getShow_id(), endNetflix);

            endPagina = arvoreB.findPagina(arvoreB.readEndRaiz(), netflix.getShow_id());
            arvoreB.setPagina(arvoreB.readPagina(endPagina));
            arvoreB.inserir(endPagina, arvoreB.getPagina(), netflix.getShow_id(), endNetflix);

            lastId++;
            raf.seek(0); // posiciona o ponteiro no inicio do arquivo
            raf.writeInt(lastId); // incrementa o lastId e escreve no arquivo
            return true;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao criar registro!");
            return false;
        }
    }

    // --------------- Read ---------------
    public Netflix read(int id, int opcao) {
        try {
            Netflix netflix = new Netflix();
            long endereco = -1;

            if (opcao == 1) {
                endereco = hash.find(id);
            } else {
                long raiz = arvoreB.readEndRaiz(); // le o endereco da raiz
                long pagina = arvoreB.findPagina(raiz, id); // encontra a pagina que contem o id
                
                if (pagina != -1) { // se a pagina existir
                    endereco = arvoreB.findRegistro(pagina, id); // encontra o endereco do registro
                }
            }
            if (endereco != -1) {
                raf.seek(endereco + 1); // posiciona o ponteiro no endereco hash, pulando a lapide
                int tamanho = raf.readInt(); // tamanho do registro

                byte[] netflixByte = new byte[tamanho]; // cria um vetor de bytes com o tamanho do registro
                raf.read(netflixByte); // le o resto do registro e armazena no vetor de bytes

                netflix.fromByteArray(netflixByte);

                return netflix;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao ler registro!" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // --------------- Update ---------------
    public boolean update(Netflix netflix) {
        try {
            long endereco = hash.find(netflix.getShow_id());
            byte[] ba = netflix.toByteArray();

            if (endereco != -1) {
                raf.seek(endereco + 1); // posiciona o ponteiro no endereco hash, pulando a lapide
                int tamanho = raf.readInt(); // tamanho do registro
                if (tamanho >= netflix.toByteArray().length) { // se o registro for maior ou igual ao novo registro
                    raf.write(ba);
                    return true;

                } else { // se o novo registro nao couber no registro existente
                    raf.writeByte(1); // exclui o registro (lapide 1)
                    return create(netflix);
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao atualizar registro!");
            return false;
        }
    }

    // --------------- Delete ---------------
    //excluir(int id, long endRegistro, long endBucket)

    public boolean delete(Netflix netflix) {
        try {
            long endereco = hash.find(netflix.getShow_id());

            if (endereco != -1) {
                raf.seek(endereco); // posiciona o ponteiro no endereco hash
                raf.writeByte(1); // exclui o registro (lapide 1)
                getHashInfo();
                hash.excluir(netflix.getShow_id(), hash.funcaoHash(netflix.getShow_id(), getHashPGlobal())); // exclui o registro no hash
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao excluir registro!");
            return false;
        }
    }
}
