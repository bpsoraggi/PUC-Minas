import java.io.File;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Huffman {

    // --------------- Atributos ---------------

    private String nomeArquivo; // nome do arquivo
    private RandomAccessFile huffman; // arquivo

    private String banco; // string a ser comprimida
    private No raiz; // raiz da arvore de Huffman
    private LinkedList<Dicionario> dicionario; // dicionario de Huffman
    private float reducao; // percentual de reducao
    private long runtime;

    // --------------- Construtor ---------------

    public Huffman(int versao, String str) {
        this.nomeArquivo = "bancoHuffmanCompressao" + versao + ".bin";
        this.banco = str;
        this.raiz = new No();
        this.dicionario = new LinkedList<Dicionario>();
        this.reducao = 0;
        this.runtime = 0;
    }

    // --------------- Setters e Getters ---------------

    public float getReducao() { return reducao; }
    public long getRuntime() { return runtime; }

    // --------------- Classes ---------------

    public class No {
        private char caracter; // caracter
        private int frequencia; // frequencia
        private boolean isFolha; // se e folha
        private No noEsq; // no da esquerda
        private No noDir; // no da direita

        public No() {
            this.caracter = '\0';
            this.frequencia = 0;
            this.isFolha = false;
            this.noEsq = null;
            this.noDir = null;
        }

        public char getCaracter() { return caracter; }
        public void setCaracter(char caracter) { this.caracter = caracter; }
        public int getFrequencia() { return frequencia; }
        public void setFrequencia(int frequencia) { this.frequencia = frequencia; }
        public boolean isFolha() { return isFolha; }
        public void setFolha(boolean isFolha) { this.isFolha = isFolha; }
        public No getNoEsq() { return noEsq; }
        public void setNoEsq(No noEsq) { this.noEsq = noEsq; }
        public No getNoDir() { return noDir; }
        public void setNoDir(No noDir) { this.noDir = noDir; }
    }

    public class Dicionario {
        private char caracter; // caracter
        private String caminho; // caminho ate o caracter

        public Dicionario() {
            this.caracter = '\0';
            this.caminho = "";
        }

        public Dicionario(char caracter, String caminho) {
            this.caracter = caracter;
            this.caminho = caminho;
        }

        public char getCaracter() { return caracter; }
        public void setCaracter(char caracter) { this.caracter = caracter; }
        public String getCaminho() { return caminho; }
        public void setCaminho(String caminho) { this.caminho = caminho; }
    }

    // --------------- Metodos ---------------

    public String arquivoExiste(int versao) {
        try {
            String nomeArquivo = "bancoHuffmanCompressao" + versao + ".bin";
            File arquivo = new File(nomeArquivo);
            if (!arquivo.exists()) { // se o arquivo nao existir
                arquivo.delete(); // deleta o arquivo de testes
                return nomeArquivo; // retorna o nome do arquivo
            } else {
                versao++; // incrementa a versao
                nomeArquivo = "bancoHuffmanCompressao" + versao + ".bin";
                return arquivoExiste(versao); // chama o metodo novamente
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao verificar se arquivo existe: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public boolean compactar(float tamanhoOriginal, int versao) {
        String caminho = "";
        long startTime = System.currentTimeMillis(); // tempo inicial

        try {
            nomeArquivo = arquivoExiste(versao); // verifica se o arquivo existe
            this.huffman = new RandomAccessFile(nomeArquivo, "rw"); // cria o arquivo

            huffman.seek(0);
            huffman.writeInt(0); // escreve o tamanho do dicionario

            // compactacao
            createHuff(); // cria arvore de Huffman
            gerarCaminho(this.raiz, caminho); // gera o caminho ate cada caracter
            writeArquivo(); // escreve o arquivo codificado

            // calculos
            float tamanhoFinal = huffman.length(); // tamanho do arquivo compactado
            float taxaCompressao = (tamanhoFinal / tamanhoOriginal);

            System.out.println();
            System.out.println("Tamanho do arquivo original: " + tamanhoOriginal + " bytes");
            System.out.println("Tamanho do arquivo compactado: " + tamanhoFinal + " bytes");
            System.out.println();

            this.reducao = 100 * (1 - taxaCompressao); // percentual de reducao
            this.runtime = (System.currentTimeMillis() - startTime); // tempo de execução do programa em milissegundos
            return true;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao compactar arquivo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean descompactar(String arquivo) {
        try {
            File arq = new File(arquivo);
            if (!arq.exists()) { // se o arquivo nao existir
                System.out.println();
                System.out.println("Arquivo nao encontrado!");
                return false;
            }

            long startTime = System.currentTimeMillis(); // tempo inicial
            RandomAccessFile versao = new RandomAccessFile(arquivo, "rw"); // arquivo a ser descompactado
            LinkedList<Dicionario> dicionario = new LinkedList<Dicionario>();
            StringBuilder caminhos = new StringBuilder();
            StringBuilder banco = new StringBuilder();
            StringBuilder sbAux = new StringBuilder();
            char charAux;
            byte bitsUteis, lastByte;
            char caracter = '\0';
            String strAux = "";

            versao.seek(0);
            int tamanhoDicionario = versao.readInt(); // le o tamanho do dicionario

            // -------- le o codigo de cada caracter --------
            for (int i = 0; i < tamanhoDicionario; i++) {
                caminhos.setLength(0); // limpa a string de caminhos
                charAux = (char) versao.readByte();
                if (charAux == '}') {
                    i = tamanhoDicionario; // sai do loop
                } else {
                    caracter = charAux;
                    charAux = (char) versao.readByte();
                    while (charAux != ';') {
                        while (charAux != ']') {
                            // transforma o byte em uma string de 8 bits
                            caminhos.append(String.format("%8s", Integer.toBinaryString(charAux & 0xFF)).replace(' ', '0'));
                            charAux = (char) (versao.readByte() & 0xFF);
                        }
                        if (charAux == ']') {
                            bitsUteis = versao.readByte();
                            if (bitsUteis != 0) {
                                // transforma o byte em uma string de 8 bits
                                strAux = String.format("%8s", Integer.toBinaryString(versao.readByte() & 0xFF)).replace(' ', '0');
                                // pega apenas os bits validos
                                strAux = strAux.substring((strAux.length() - bitsUteis));
                                caminhos.append(strAux); // concatena os bits validos
                                charAux = (char) (versao.readByte() & 0xFF);
                            } else {
                                caminhos.append(String.format("%8s", Integer.toBinaryString(versao.readByte() & 0xFF)).replace(' ', '0'));
                                charAux = (char) (versao.readByte() & 0xFF);
                            }
                        }
                    }
                    dicionario.add(new Dicionario(caracter, caminhos.toString())); // adiciona o caracter e seu caminho no dicionario
                }
            }

            // -------- le o banco codificado --------
                versao.seek(tamanhoDicionario); // vai para o inicio do arquivo
                charAux = (char) (versao.readByte() & 0xFF);
                while (versao.getFilePointer() < versao.length()-1) {
                    // concatena o banco em uma string
                    banco.append(String.format("%8s", Integer.toBinaryString(charAux & 0xFF)).replace(' ', '0'));
                    charAux = (char) (versao.readByte() & 0xFF);
                }
                
                versao.seek(versao.getFilePointer()-1); // volta um byte (leu um byte a mais no while)
                bitsUteis = versao.readByte();
                lastByte = versao.readByte();
                if (bitsUteis != 0) {
                    strAux = String.format("%8s", Integer.toBinaryString(lastByte & 0xFF)).replace(' ', '0');
                    banco.append(strAux.substring((strAux.length() - bitsUteis)));
                }

                // -------- descompacta o banco --------
                sbAux.setLength(0);
                int bancoLength = banco.length();
                int dicSize = dicionario.size();
                for (int i = 0; i < bancoLength; i++) {
                    caminhos.append(banco.charAt(i));
                    for (int j = 0; j < dicSize; j++) {
                        strAux = caminhos.toString();
                        // se um caminho valido for encontrado
                        if (strAux.equals(dicionario.get(j).getCaminho())) {
                            sbAux.append(dicionario.get(j).getCaracter()); // concatena o caracter no banco
                            caminhos.setLength(0); // limpa o a string de caminhos
                            j = dicSize; // sai do loop
                        }
                    }
                }
                // converte a string decodificada para os tipos certos (int, char, etc) e escreve no arquivo
                converteArquivo(sbAux.toString());
            this.runtime = (System.currentTimeMillis() - startTime); // tempo de execução do programa em milissegundos
            versao.close();
            return true;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao descompactar arquivo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }



    public void converteArquivo(String banco) {
        try {
            RandomAccessFile arqBanco = new RandomAccessFile("banco2.bin", "rw"); // arquivo destino
            StringBuilder aux = new StringBuilder();
            char charAux;
            int index = 0;
            int lastId = 0;
            int auxInt = 0;
            int auxInt2 = 0;

            // ----- escrevendo no arquivo descompactado -----
            // lastId
            for (int i = 0; i < 4; i++) {
                aux.append(banco.charAt(i));
            }
            lastId = Integer.parseInt(aux.toString());
            arqBanco.writeInt(lastId);
            banco = banco.substring(4); // remove o lastId da string

            int bancoLength = banco.length();
            // percorre o banco, registro por registro (lastId = quantidade de registros)
            for (int i = 0; i < lastId; i++) { // percorre o banco, pulando lastId
                if (index < bancoLength) {
                    // lapide
                    auxInt = Character.getNumericValue(banco.charAt(index));
                    index++;
                    arqBanco.writeByte(auxInt); // escreve a lapide

                    // tamanho do registro
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve o tamanho do registro

                    // id
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve o id

                    // type
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 6; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }
                    arqBanco.writeUTF(aux.toString()); // escreve o tipo

                    // title
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 2; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    aux.setLength(0); // limpa a string auxiliar
                    charAux = banco.charAt(index);
                    for (int k = 0; k < auxInt; k++) {
                        aux.append(charAux);
                        index++;
                        charAux = banco.charAt(index);
                    }
                    arqBanco.writeUTF(aux.toString()); // escreve o titulo

                    // qtdDirectors
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve a qtd de diretores

                    // directors
                    for (int k = 0; k < auxInt; k++) {
                        aux.setLength(0); // limpa a string auxiliar
                        for (int y = 0; y < 2; y++) {
                            aux.append(banco.charAt(index));
                            index++;
                        }  
                        auxInt2 = Integer.parseInt(aux.toString());
                        aux.setLength(0); // limpa a string auxiliar
                        charAux = banco.charAt(index);
                        for (int y = 0; y < auxInt2; y++) {
                            aux.append(charAux);
                            index++;
                            charAux = banco.charAt(index);
                        }
                        arqBanco.writeUTF(aux.toString()); // escreve o diretor
                    }

                    // qtdCast
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve a qtd de diretores

                    // cast
                    for (int k = 0; k < auxInt; k++) {
                        aux.setLength(0); // limpa a string auxiliar
                        for (int y = 0; y < 2; y++) {
                            aux.append(banco.charAt(index));
                            index++;
                        }  
                        auxInt2 = Integer.parseInt(aux.toString());
                        aux.setLength(0); // limpa a string auxiliar
                        charAux = banco.charAt(index);
                        for (int y = 0; y < auxInt2; y++) {
                            aux.append(charAux);
                            index++;
                            charAux = banco.charAt(index);
                        }
                        arqBanco.writeUTF(aux.toString()); // escreve o ator
                    }

                    // qtdCountries
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve a qtd de diretores

                    // country
                    for (int k = 0; k < auxInt; k++) {
                        aux.setLength(0); // limpa a string auxiliar
                        for (int y = 0; y < 2; y++) {
                            aux.append(banco.charAt(index));
                            index++;
                        }  
                        auxInt2 = Integer.parseInt(aux.toString());
                        aux.setLength(0); // limpa a string auxiliar
                        charAux = banco.charAt(index);
                        for (int y = 0; y < auxInt2; y++) {
                            aux.append(charAux);
                            index++;
                            charAux = banco.charAt(index);
                        }
                        arqBanco.writeUTF(aux.toString()); // escreve o pais
                    }

                    // dateString
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 10; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }
                    arqBanco.writeUTF(aux.toString()); // escreve a data

                    // release_year
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve o ano de lancamento

                    // rating
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 2; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    aux.setLength(0); // limpa a string auxiliar
                    charAux = banco.charAt(index);
                    for (int k = 0; k < auxInt; k++) {
                        aux.append(charAux);
                        index++;
                        charAux = banco.charAt(index);
                    }
                    arqBanco.writeUTF(aux.toString()); // escreve a classificacao

                    // duration
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 2; k++) {
                        aux.append(banco.charAt(index));
                        index++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    aux.setLength(0); // limpa a string auxiliar
                    charAux = banco.charAt(index);
                    for (int k = 0; k < auxInt; k++) {
                        aux.append(charAux);
                        index++;
                        if (index < banco.length()) {
                            charAux = banco.charAt(index);
                        }
                        
                    }
                    arqBanco.writeUTF(aux.toString()); // escreve a duracao
                }
                
            }
            arqBanco.close();
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao converter arquivo compactado: " + e.getMessage());
            e.printStackTrace();
        }
    }








    public void converteArquivo2(String banco) {
        try {
            RandomAccessFile arqBanco = new RandomAccessFile("banco2.bin", "rw"); // arquivo destino
            StringBuilder aux = new StringBuilder();
            char charAux;
            int lastId = 0;
            int auxInt = 0;
            int auxInt2 = 0;

            // ----- escrevendo no arquivo descompactado -----
            // lastId
            for (int i = 0; i < 4; i++) {
                aux.append(banco.charAt(i));
            }
            lastId = Integer.parseInt(aux.toString());
            arqBanco.writeInt(lastId);
            banco = banco.substring(4); // remove o lastId da string

            int bancoLength = banco.length();
            // percorre o banco, registro por registro (lastId = quantidade de registros)
            for (int i = 0; i < lastId; i++) { // percorre o banco, pulando lastId
                for (int j = 0; j < bancoLength; j++) {
                    // lapide
                    auxInt = Character.getNumericValue(banco.charAt(j));
                    j++;
                    arqBanco.writeByte(auxInt); // escreve a lapide

                    // tamanho do registro
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve o tamanho do registro

                    // id
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve o id

                    // type
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 6; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }
                    arqBanco.writeUTF(aux.toString()); // escreve o tipo

                    // title
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 2; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    aux.setLength(0); // limpa a string auxiliar
                    charAux = banco.charAt(j);
                    for (int k = 0; k < auxInt; k++) {
                        aux.append(charAux);
                        j++;
                        charAux = banco.charAt(j);
                    }
                    arqBanco.writeUTF(aux.toString()); // escreve o titulo

                    // qtdDirectors
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve a qtd de diretores

                    // directors
                    for (int k = 0; k < auxInt; k++) {
                        aux.setLength(0); // limpa a string auxiliar
                        for (int y = 0; y < 2; y++) {
                            aux.append(banco.charAt(j));
                            j++;
                        }  
                        auxInt2 = Integer.parseInt(aux.toString());
                        aux.setLength(0); // limpa a string auxiliar
                        charAux = banco.charAt(j);
                        for (int y = 0; y < auxInt2; y++) {
                            aux.append(charAux);
                            j++;
                            charAux = banco.charAt(j);
                        }
                        arqBanco.writeUTF(aux.toString()); // escreve o diretor
                    }

                    // qtdCast
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve a qtd de diretores

                    // cast
                    for (int k = 0; k < auxInt; k++) {
                        aux.setLength(0); // limpa a string auxiliar
                        for (int y = 0; y < 2; y++) {
                            aux.append(banco.charAt(j));
                            j++;
                        }  
                        auxInt2 = Integer.parseInt(aux.toString());
                        aux.setLength(0); // limpa a string auxiliar
                        charAux = banco.charAt(j);
                        for (int y = 0; y < auxInt2; y++) {
                            aux.append(charAux);
                            j++;
                            charAux = banco.charAt(j);
                        }
                        arqBanco.writeUTF(aux.toString()); // escreve o ator
                    }

                    // qtdCountries
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve a qtd de diretores

                    // country
                    for (int k = 0; k < auxInt; k++) {
                        aux.setLength(0); // limpa a string auxiliar
                        for (int y = 0; y < 2; y++) {
                            aux.append(banco.charAt(j));
                            j++;
                        }  
                        auxInt2 = Integer.parseInt(aux.toString());
                        aux.setLength(0); // limpa a string auxiliar
                        charAux = banco.charAt(j);
                        for (int y = 0; y < auxInt2; y++) {
                            aux.append(charAux);
                            j++;
                            charAux = banco.charAt(j);
                        }
                        arqBanco.writeUTF(aux.toString()); // escreve o pais
                    }

                    // dateString
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 10; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }
                    arqBanco.writeUTF(aux.toString()); // escreve a data

                    // release_year
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 4; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    arqBanco.writeInt(auxInt); // escreve o ano de lancamento

                    // rating
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 2; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    aux.setLength(0); // limpa a string auxiliar
                    charAux = banco.charAt(j);
                    for (int k = 0; k < auxInt; k++) {
                        aux.append(charAux);
                        j++;
                        charAux = banco.charAt(j);
                    }
                    arqBanco.writeUTF(aux.toString()); // escreve a classificacao

                    // duration
                    aux.setLength(0); // limpa a string auxiliar
                    for (int k = 0; k < 2; k++) {
                        aux.append(banco.charAt(j));
                        j++;
                    }  
                    auxInt = Integer.parseInt(aux.toString());
                    aux.setLength(0); // limpa a string auxiliar
                    charAux = banco.charAt(j);
                    for (int k = 0; k < auxInt; k++) {
                        aux.append(charAux);
                        j++;
                        charAux = banco.charAt(j);
                    }
                    j--;
                    arqBanco.writeUTF(aux.toString()); // escreve a duracao
                }
            }
            arqBanco.close();
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao converter arquivo compactado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public LinkedList<No> tabelaFrequencia() {
        int tabela[] = new int[256]; // tabela de frequencia de cada char (ASCII)

        // cria a tabela de frequencia de cada caracter ASCII
        for (int i = 0; i < 256; i++) { // inicializa a tabela com 0, evitando lixo de memoria
            tabela[i] = 0;
        }
        for (int i = 0; i < this.banco.length(); i++) {
            tabela[this.banco.charAt(i)]++; // incrementa a frequencia de cada char
        }

        // cria a lista de nos
        LinkedList<No> lista = new LinkedList<No>(); // cria a lista de nos
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] > 0) { // se o char aparece no banco
                No no = new No(); // cria um no
                no.caracter = (char) i; // seta o caracter
                no.frequencia = tabela[i]; // seta a frequencia
                no.isFolha = true; // seta como folha
                lista.add(no); // adiciona na lista
            }
        }

        // ordena a lista por frequencia
        Collections.sort(lista, Comparator.comparingInt(No::getFrequencia));
        return lista;
    }

    public int getIndexLista(LinkedList<No> lista, int frequencia) { // retorna o indice do no com a frequencia
        for (int i = 0; i < lista.size(); i++) {
            // pega o ultimo elemento da lista com aquela frequencia
            if (i < (lista.size() - 2)) {
                if (lista.get(i) != null && lista.get(i).getFrequencia() == (frequencia) && lista.get(i + 1).getFrequencia() != frequencia) {
                    return i;
                }
            }
        }
        return -1;// nao existe na lista
    }

    public void gerarCaminho(No no, String caminho) {
        Dicionario elemDicionario = new Dicionario(); // cria um elemento do dicionario

        if (no.isFolha) {
            elemDicionario.caracter = no.caracter; // seta o caracter
            elemDicionario.caminho = caminho; // seta o caminho
            writeHuffCodes(elemDicionario); // escreve no arquivo
            this.dicionario.add(elemDicionario); // adiciona no dicionario
        } else {
            gerarCaminho(no.noEsq, caminho + "0");
            gerarCaminho(no.noDir, caminho + "1");
        }
    }

    public void createHuff() {
        LinkedList<No> lista = tabelaFrequencia(); // recebe a lista de nos ordenados por frequencia
        int index;
        
        while (lista.size() > 1) { // enquanto a lista nao estiver vazia
            No mae = new No(); // cria o no mae
            No primeiro = lista.pollFirst(); // pega o no da lista de apaga
            No segundo = lista.pollFirst(); // pega o no da lista e apaga

            mae.caracter = ';'; // seta o caracter da mae
            mae.frequencia = primeiro.frequencia + segundo.frequencia; // seta a frequencia da mae
            mae.noEsq = primeiro; // seta o no da esquerda
            mae.noDir = segundo; // seta o no da direita
            mae.isFolha = false; // seta como nao folha

            index = getIndexLista(lista, mae.getFrequencia()); // pega o indice do no com a mesma frequencia da mae

            if (index != -1) { // se existir algum no com a mesma frequencia da mae
                lista.add((index + 1), mae); // adiciona na lista depois de todos os nos com a mesma frequencia
            } else {
                lista.add(mae); // adiciona no final da lista
                Collections.sort(lista, Comparator.comparingInt(No::getFrequencia)); // ordena a lista por frequencia
            }
            this.raiz = mae; // seta a raiz
        }
        //printHuff(this.raiz);
    }

    public void writeHuffCodes(Dicionario dicionario) {
        byte bitsUteis;
        String bytes[] = dicionario.caminho.split("(?<=\\G.{" + 8 + "})"); // divide a string em grupos de 8 bits

        try {
            huffman.seek(huffman.length()); // vai para o final do arquivo
            huffman.writeByte((byte) dicionario.caracter); // escreve o caracter

            for (int i = 0; i < bytes.length; i++) {
                bitsUteis = (byte) (bytes[i].length() % 8); // verifica se o byte esta completo

                // se for o ultimo byte
                if (i == (bytes.length - 1)) {
                    huffman.writeByte(']');
                    huffman.writeByte(bitsUteis); // quantos bits uteis o byte tem
                }
                huffman.writeByte((byte) Integer.parseInt(bytes[i], 2)); // escreve o byte
            }
            huffman.writeByte(';'); // sinaliza o fim do char
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao escrever arvore huffman: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void writeArquivo() {
        StringBuilder codificado = new StringBuilder();
        int mod = 0;
        String bancoBits = "";
        String bitsFinais = "";
        byte b = 0;

        try {
            huffman.writeByte('}');
            int tamanho = (int) huffman.length(); // seta o tamanho do dicionario
            huffman.seek(0); // vai para o inicio do arquivo
            huffman.writeInt(tamanho); // escreve o tamanho do dicionario


            // codifica o banco
            int bancoLength = this.banco.length();
            int dicSize = this.dicionario.size();
            for (int i = 0; i < bancoLength; i++) {
                for (int j = 0; j < dicSize; j++) {
                    if (this.dicionario.get(j).getCaracter() == this.banco.charAt(i)) {
                        codificado.append(this.dicionario.get(j).getCaminho());
                        j = this.dicionario.size(); // sai do loop
                    }
                }
            }

            mod = codificado.length() % 8;
            bancoBits = codificado.substring(0, (codificado.length() - mod));
            bitsFinais = codificado.substring((codificado.length() - mod));

            huffman.seek(huffman.length()); // vai para o final do arquivo
            for (int i = 0; i < bancoBits.length()-7; i+=8) {
                b = (byte) Integer.parseInt(bancoBits.substring(i, i+8), 2);
                huffman.writeByte(b);
            }
            if (mod != 0) {
                b = Byte.parseByte(bitsFinais, 2);
                huffman.writeByte(mod);
                huffman.writeByte(b);
            } else {
                huffman.writeByte(0);
                huffman.writeByte(0);
            }

        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao escrever arquivo codificado: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // public void printHuff(No raiz) {
    //     if (raiz.isFolha) { // se a raiz for folha
    //         System.out.println(raiz.caracter + " - " + raiz.frequencia);
    //     } else {
    //         printHuff(raiz.noEsq);
    //         printHuff(raiz.noDir);
    //     }
    // }
}