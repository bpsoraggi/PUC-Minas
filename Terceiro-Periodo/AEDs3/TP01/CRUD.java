import java.io.RandomAccessFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class CRUD {
    
    private int lastId = 0;
    public RandomAccessFile raf;

    public CRUD() {
        this.lastId = 0;
        try {
            this.raf = new RandomAccessFile("banco.bin", "rw"); // cria o arquivo
            if(raf.length() != 0) { // se o arquivo não estiver vazio, lê o lastId
                raf.seek(0);
                lastId = raf.readInt();
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao criar o raf: " + e.getMessage());
        }
    }

    public int getLastId() {return lastId;}

    public void convertToBin(Netflix netflix) {
        BufferedReader br = null;

        try {
            if (raf.length() == 0) { // se o arquivo estiver vazio, cria o binario
                try {
                    try {
                        br = new BufferedReader(new InputStreamReader(new FileInputStream("netflixBD3.csv"), "iso-8859-1"));
                    } catch (Exception e) {
                        try {
                            br = new BufferedReader(new InputStreamReader(new FileInputStream("netflixBD3.csv"), "utf-8"));
                        } catch (Exception e2) {
                            System.err.println("Error: createBin");
                        }
                    }

                    raf.writeInt(0); // escreve o lastId
                    String line = br.readLine();
                    while (line != null) {
                        Core.readCSV(line, netflix); // le a linha do CSV e armazena no objeto
                        create(netflix); // cria o registro no arquivo binario
                        line = br.readLine(); // le a proxima linha
                    }

                    System.out.println();
                    System.out.println("Arquivo binario criado com sucesso!");
                } catch (Exception e) {
                    System.out.println();
                    System.out.println("Erro ao criar o arquivo binario! " + e.getMessage());
                }
            }
            raf.seek(0); // posiciona o ponteiro no inicio do arquivo
        } catch (Exception e) {
            System.out.println();
            System.out.println("Arquivo binario nao esta vazio!");
        }
    }

    // --------------- Create ---------------

    public boolean create(Netflix netflix) {

        try {
            byte[] ba = netflix.toByteArray();
            raf.seek(raf.length()); // posiciona o ponteiro no final do arquivo
            raf.writeByte(0); // escreve a lapide (ativo)
            raf.writeInt(ba.length); // escreve o tamanho do registro

            // escreve o registro
            raf.write(ba);
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
    public Netflix read(int id) {

        try {
            Netflix netflix = new Netflix();
            raf.seek(4); // posiciona o ponteiro no inicio do arquivo, pulando o lastId

            while (raf.getFilePointer() < raf.length()) { // enquanto o ponteiro não chegar no final do arquivo
                if (raf.readByte() == 0) { // se o registro estiver ativo (lapide 0)
                    int tamanho = raf.readInt(); // tamanho do registro

                    byte[] netflixByte = new byte[tamanho]; // cria um vetor de bytes com o tamanho do registro
                    raf.read(netflixByte); // le o resto do registro e armazena no vetor de bytes

                    netflix.fromByteArray(netflixByte);
                    //System.out.println(netflix.toString());
                    if (netflix.getShow_id() == id) { // se o id do objeto for igual ao id passado por parametro
                        // le o resto do registro e armazena no objeto
                        return netflix;
                    }
                } else {
                    raf.skipBytes(raf.readInt()); // pula o registro
                }
            }
            return null;
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
            byte[] ba = netflix.toByteArray();
            raf.seek(4); // posiciona o ponteiro no inicio do arquivo, pulando o lastId

            while (raf.getFilePointer() < raf.length()) { // enquanto o ponteiro não chegar no final do arquivo
                if (raf.readByte() == 0) { // se o registro estiver ativo (lapide 0)
                    int tamanho = raf.readInt(); // tamanho do registro

                    if (raf.readInt() == netflix.getShow_id()) { // se o id do registro for igual ao id a ser atualizado
                        if (tamanho >= netflix.toByteArray().length) { // se o registro for maior ou igual ao novo registro

                            raf.seek(raf.getFilePointer() - 4); // volta o ponteiro pro inicio do registro (antes do id)
                            raf.write(ba);

                            return true;
                        } else { // se o novo registro nao couber no registro existente
                            raf.seek(raf.getFilePointer() - 9); // volta o ponteiro pro inicio do registro (1 byte da lapide e 4 bytes do tamanho do registro e 4 bytes de id)
                            raf.writeByte(1); // exclui o registro (lapide 1)
                            return create(netflix);
                        }
                    } else {
                        raf.skipBytes(tamanho - 4); // pula o restante do registro (menos o id)
                    }
                } else {
                    raf.skipBytes(raf.readInt()); // pula o registro
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao atualizar registro!");
            return false;
        }
    }

    // --------------- Delete ---------------

    public boolean delete(Netflix netflix) {
        try {
            raf.seek(4); // posiciona o ponteiro no inicio do arquivo, pulando o lastId

            while (raf.getFilePointer() < raf.length()) { // enquanto o ponteiro não chegar no final do arquivo
                if (raf.readByte() == 0) { // se o registro estiver ativo (lapide 0)
                    int tamanho = raf.readInt(); // tamanho do registro
                    int id = raf.readInt(); // id do registro

                    if (id == netflix.getShow_id()) { // se o id do registro for igual ao id a ser apagado
                        raf.seek(raf.getFilePointer() - 9); // volta o ponteiro pro inicio do registro (1 byte da lapide e 4 bytes do tamanho do registro)
                        raf.writeByte(1); // exclui o registro (lapide 1)
                        return true;
                    } else {
                        raf.skipBytes(tamanho - 4); // pula o restante do registro (menos o id)
                    }
                } else {
                    raf.skipBytes(raf.readInt()); // pula o registro
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao excluir registro!");
            return false;
        }
    }

}
