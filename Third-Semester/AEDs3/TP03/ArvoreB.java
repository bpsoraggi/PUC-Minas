import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Comparator;

public class ArvoreB {
    
    // --------------- Atributos ---------------

    private long endRaiz;
    private int qtdPaginas;
    private int maxElementos;

    private Pagina pagina;
    private RandomAccessFile arvoreB;

    // --------------- Getters e Setters ---------------

    public long getEndRaiz() {return endRaiz;}
    public void setEndRaiz(long endRaiz) {this.endRaiz = endRaiz;}
    public Pagina getPagina() {return pagina;}
    public void setPagina(Pagina pagina) {this.pagina = pagina;}

    // --------------- Construtor ---------------

    public ArvoreB() {
        this.endRaiz = -1;
        this.qtdPaginas = 0;
        this.maxElementos = 7;

        this.pagina = new Pagina();

        try {
            this.arvoreB = new RandomAccessFile("arvoreB.bin", "rw"); // cria o arquivo

            // se o arquivo estiver vazio, cria a raiz
            if (arvoreB.length() == 0) {
                Pagina raiz = new Pagina();
                raiz.setFolha(true);
                raiz.setQtdElementos(0);
                raiz.setElementos(new Elemento[maxElementos]);
                raiz.setFilhos(new long[maxElementos+1]);

                // escreve a raiz no arquivo
                arvoreB.seek(0);
                arvoreB.writeLong(12); // endRaiz
                arvoreB.writeInt(1); // qtdPaginas
                arvoreB.writeBoolean(raiz.getFolha());
                arvoreB.writeInt(raiz.getQtdElementos());
                for (int i = 0; i < maxElementos; i++) {
                    arvoreB.writeLong(-1); // filho
                    arvoreB.writeInt(-1); // idRegistro
                    arvoreB.writeLong(-1); // endRegistro
                }
                arvoreB.writeLong(-1); // ultimo filho
            }

        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao criar o arquivo de arvoreB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --------------- Classes ---------------

    public class Elemento {
        private int idRegistro;
        private long endRegistro;
        
        public Elemento() {
            this.idRegistro = -1;
            this.endRegistro = -1;
        }

        public Elemento(int idRegistro, long endRegistro) {
            this.idRegistro = idRegistro;
            this.endRegistro = endRegistro;
        }

        public int getIdRegistro() {return idRegistro;}
        public void setIdRegistro(int idRegistro) {this.idRegistro = idRegistro;}
        public long getEndRegistro() {return endRegistro;}
        public void setEndRegistro(long endRegistro) {this.endRegistro = endRegistro;}
    }

    public class Pagina {
        private boolean isFolha;
        private int qtdElementos;
        private Elemento[] elementos;
        private long[] filhos;

        public Pagina() {
            this.isFolha = true;
            this.qtdElementos = 0;
            this.elementos = new Elemento[maxElementos];
            for (int i = 0; i < elementos.length; i++) {
                elementos[i] = new Elemento();
            }
            this.filhos = new long[maxElementos+1];
            for (int i = 0; i < filhos.length; i++) {
                filhos[i] = -1;
            }
        }

        public Pagina(boolean isFolha, int qtdElementos) {
            this.isFolha = isFolha;
            this.qtdElementos = qtdElementos;
            this.elementos = new Elemento[maxElementos];
            for (int i = 0; i < elementos.length; i++) {
                elementos[i] = new Elemento();
            }
            this.filhos = new long[maxElementos+1];
            for (int i = 0; i < filhos.length; i++) {
                filhos[i] = -1;
            }
        }

        public void excluirElemento(int i) {
            this.elementos[i] = new Elemento();
            this.filhos[i+1] = -1;
        }

        public boolean getFolha() {return isFolha;}
        public void setFolha(boolean isFolha) {this.isFolha = isFolha;}
        public int getQtdElementos() {return qtdElementos;}
        public void setQtdElementos(int qtdElementos) {this.qtdElementos = qtdElementos;}
        public Elemento[] getElementos() {return elementos;}
        public void setElementos(Elemento[] elementos) {this.elementos = elementos;}
        public long[] getFilhos() {return filhos;}
        public void setFilhos(long[] filhos) {this.filhos = filhos;}
    }

    // --------------- Metodos ---------------

    public long readEndRaiz() {
        try {
            arvoreB.seek(0);
            return arvoreB.readLong();
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao ler o endereco da raiz: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public int readQtdPaginas() {
        try {
            arvoreB.seek(8);
            return arvoreB.readInt();
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao ler o a quantidade de paginas: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public long findPagina(long endPagina, int id) {
        try {
            int qtdElementos = -1;
            int idLido = -1;
            long endFilho = -1;
            boolean isFolha = false;

            arvoreB.seek(endPagina); // vai para o inicio da pagina (comeca na raiz)
            isFolha = arvoreB.readBoolean(); // le o isFolha
            qtdElementos = arvoreB.readInt(); // le a quantidade de elementos

            if (!isFolha) {
                if (qtdElementos > 1) {
                    arvoreB.skipBytes(((qtdElementos - 1) * 20) + 8); // pula para o inicio do ultimo elemento
                } else {
                    arvoreB.skipBytes(8); // pula o primeiro filho
                }

                for (int i = qtdElementos; i >= 1; i++) {
                    if (i != 1) {
                        idLido = arvoreB.readInt(); // le o id do elemento
                        if (id > idLido) { // se o id for maior que o id lido
                            arvoreB.skipBytes(8); // pula o endereco do registro
                            endFilho = arvoreB.readLong(); // le o endereco do filho
                            return findPagina(endFilho, id); // procura no filho
                        } else {
                            arvoreB.seek(arvoreB.getFilePointer() - 24); // volta para o inicio do elemento anterior
                        }
                    } else { // se for o ultimo elemento
                        idLido = arvoreB.readInt(); // le o id do elemento
                        if (id > idLido) { // se o id for maior que o id lido
                            arvoreB.skipBytes(8); // pula o endereco do registro
                            endFilho = arvoreB.readLong(); // le o endereco do filho
                            return findPagina(endFilho, id); // procura no filho
                        } else {
                            arvoreB.seek(arvoreB.getFilePointer() - 12); // volta para o inicio do primeiro filho
                            endFilho = arvoreB.readLong(); // le o endereco do filho
                            return findPagina(endFilho, id); // procura no filho
                        }
                    }
                }

            } else { // se for folha
                return endPagina; // retorna o endereco da pagina
            }
            return -1;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao procurar pagina: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public long findRegistro(long pagina, int id) {
        try {
            int qtdElementos = -1;
            int idLido = -1;
            long endRegistro = -1;

            arvoreB.seek(pagina); // vai para o inicio da pagina
            arvoreB.skipBytes(1); // pula o isFolha
            qtdElementos = arvoreB.readInt(); // le a quantidade de elementos
            arvoreB.skipBytes(8); // pula o primeiro filho

            for (int i = 0; i < qtdElementos; i++) {
                idLido = arvoreB.readInt(); // le o id do elemento
                if (id == idLido) { // se o id for igual ao id lido
                    endRegistro = arvoreB.readLong(); // le o endereco do registro
                    return endRegistro; // retorna o endereco do registro
                } else {
                    arvoreB.skipBytes(16); // pula o endereco do registro e o endereco do filho
                }
            }
            return -1; // se nao encontrar o registro retorna -1
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao procurar registro na arvore b: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public long writePagina(Pagina pagina) {
        long endereco = -1;
        try {
            arvoreB.seek(arvoreB.length()); // vai para o final do arquivo
            endereco = arvoreB.getFilePointer(); // pega o endereco
            arvoreB.writeBoolean(pagina.getFolha()); // escreve o isFolha
            arvoreB.writeInt(pagina.getQtdElementos()); // escreve a quantidade de elementos

            for (int i = 0; i < maxElementos; i++) { // escreve os elementos
                arvoreB.writeLong(pagina.getFilhos()[i]);
                arvoreB.writeInt(pagina.getElementos()[i].getIdRegistro());
                arvoreB.writeLong(pagina.getElementos()[i].getEndRegistro());
            }
            arvoreB.writeLong(pagina.getFilhos()[maxElementos]); // escreve o ultimo filho
            return endereco;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao escrever pagina: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public Pagina readPagina(long endPagina) {
        try {
            Pagina pagina = new Pagina();
            long[] filhos = new long[maxElementos+1];
            arvoreB.seek(endPagina); // vai para o endereco da pagina
            pagina.setFolha(arvoreB.readBoolean()); // le o isFolha
            pagina.setQtdElementos(arvoreB.readInt()); // le a quantidade de elementos
            for (int i = 0; i < maxElementos; i++) {
                filhos[i] = arvoreB.readLong(); // le os filhos
                pagina.getElementos()[i].setIdRegistro(arvoreB.readInt()); // le o id do elemento
                pagina.getElementos()[i].setEndRegistro(arvoreB.readLong()); // le o endereco do elemento
            }
            filhos[7] = arvoreB.readLong(); // le o ultimo filho
            pagina.setFilhos(filhos);
            return pagina;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao ler pagina: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public long shiftElementos(long endPagina, int id) {
        try {
            arvoreB.seek(endPagina); // vai para o endereco da pagina
            arvoreB.skipBytes(1); // pula o isFolha
            int qtdElementos = arvoreB.readInt(); // le a quantidade de elementos
            int qtdArredados = 0; // quantidade de elementos arredados
            int index = 0; // indice do vetor auxiliar
            Elemento[] vetor = new Elemento[maxElementos]; // vetor auxiliar para arredar os elementos
            Elemento aux = new Elemento(); // elemento auxiliar
            long espacoVazio = -1; // espaco vazio a ser retornado

            arvoreB.skipBytes(8); // pula o filho
            for (int i = 0; i < qtdElementos; i++) {
                if (arvoreB.readInt() == id) { // se o elemento for igual ao elemento a ser arredado
                    arvoreB.seek(arvoreB.getFilePointer() - 4); // volta para o inicio do elemento
                    for (int j = i; j < qtdElementos; j++) { // arreda todos os elementos a partir do elemento a ser arredado
                        aux.setIdRegistro(arvoreB.readInt()); // armazena o id do elemento
                        aux.setEndRegistro(arvoreB.readLong()); // armazena o endereco do elemento
                        vetor[index] = aux; // armazena o elemento no vetor auxiliar
                        index++; // incrementa o indice do vetor auxiliar
                        qtdArredados++; // incrementa a quantidade de elementos arredados
                        arvoreB.skipBytes(8); // pula o filho
                    }
                    i = qtdElementos; // sai do for
                } else {
                    arvoreB.skipBytes(16); // pula o endRegistro e o filho
                }
            }
            arvoreB.seek(arvoreB.getFilePointer() - (qtdArredados * 20)); // volta para o inicio dos elementos arredados
            espacoVazio = arvoreB.getFilePointer(); // pega o endereco do espaco vazio
            arvoreB.writeInt(-1); // libera espaco do elemento a ser arredado
            arvoreB.writeLong(-1); // libera espaco do elemento a ser arredado
            arvoreB.skipBytes(8); // pula o filho
            for (int i = 0; i < qtdArredados; i++) { // escreve os elementos arredados
                arvoreB.writeInt(vetor[i].getIdRegistro());
                arvoreB.writeLong(vetor[i].getEndRegistro());
                arvoreB.skipBytes(8); // pula o filho
            }
            return espacoVazio;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao arredar elementos: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public long inserir(long endPagina, Pagina pagina, int id, long posicao) {
        try {
            arvoreB.seek(0); // vai para o inicio do arquivo
            long endRaiz = arvoreB.readLong(); // le o end da raiz
            long[] vetorFilhos = new long[maxElementos+1]; // vetor auxiliar para ordenar os filhos
            long endMae = -1; // end da pagina mae
            long endInsercao = -1; // end exato de insercao do elemento
            Elemento[] vetor = new Elemento[maxElementos+1]; // vetor auxiliar para ordenar os elementos
            Elemento elemento = new Elemento(id, posicao); // cria o elemento a ser inserido
            int idAux = -1; // id auxiliar
            int posicaoNaPg = -1; // posicao do elemento na pagina

            for (int i = 0; i < maxElementos; i++) { // copia os elementos da pagina para o vetor
                vetor[i] = pagina.getElementos()[i];
                vetorFilhos[i] = pagina.getFilhos()[i];
            }
            vetor[7] = elemento; // adiciona o elemento a inserir no vetor
            vetorFilhos[7] = pagina.getFilhos()[7]; // adiciona o ultimo filho no vetor

            // ordena o vetor por idRegistro
            Arrays.sort(vetor,new Comparator<Elemento>(){  
                @Override  
                public int compare(Elemento e1, Elemento e2){  
                    return e1.getIdRegistro() - e2.getIdRegistro();  
            }  
            } );

            // Mover todos os -1 para o final do vetor
            int j = 0;
            for (int i = 0; i < vetor.length; i++) {
                if (vetor[i].getIdRegistro() != -1) {
                    Elemento temp = vetor[j];
                    vetor[j] = vetor[i];
                    vetor[i] = temp;
                    j++;
                }
            }

            for (int i = 0; i < vetor.length; i++) {
                if (vetor[i].getIdRegistro() == id) { // se o elemento ja existir
                    posicaoNaPg = i; // armazena a posicao do elemento na pagina
                    i = vetor.length; // sai do for
                }
            }

            arvoreB.seek(endPagina + 1); // vai para o endereco da pagina, pulando o isFolha
            int qtdElementos = arvoreB.readInt(); // le a quantidade de elementos

            if (qtdElementos == 0) {
                arvoreB.skipBytes(8); // pula o primeiro filho
                arvoreB.writeInt(id); // escreve o id do elemento
                arvoreB.writeLong(posicao); // escreve o endereco do elemento
                arvoreB.seek(endPagina + 1); // volta para o inicio da quantidade de elementos
                arvoreB.writeInt(1); // escreve a quantidade de elementos
                return endPagina; // retorna o endereco da pagina na qual o elemento foi inserido
            }

            arvoreB.skipBytes(8); // pula o primeiro filho
            if (qtdElementos != maxElementos) { // se a pagina tiver espaco
                arvoreB.skipBytes(posicaoNaPg * 20); // pula os elementos ja inseridos
                idAux = arvoreB.readInt(); // le o id do elemento
                if (idAux != -1) {
                    endInsercao = shiftElementos(endPagina, idAux); // arreda os elementos maiores que o elemento a ser inserido
                } else {
                    endInsercao = arvoreB.getFilePointer() - 4; // pega o endereco de insercao
                }

                arvoreB.seek(endInsercao); // vai para o endereco de insercao
                arvoreB.writeInt(id); // escreve o id do elemento
                arvoreB.writeLong(posicao); // escreve o endereco do elemento
                arvoreB.seek(endPagina + 1); // volta para o inicio da quantidade de elementos
                arvoreB.writeInt(qtdElementos + 1); // escreve a quantidade de elementos
                return endPagina; // retorna o endereco da pagina na qual o elemento foi inserido
            } else {
                endMae = findParent(endRaiz, pagina, endPagina);
                if (endMae == -1) { // se a pagina for a raiz
                    endInsercao = splitPagina(endPagina, readPagina(endPagina), true, vetor, vetorFilhos, id, posicao);
                } else { // se a pagina nao for a raiz
                    Elemento pivot = pagina.getElementos()[3]; // pega o menor elemento do meio
                    inserir(endMae, readPagina(endMae), pivot.getIdRegistro(), pivot.getEndRegistro()); // insere o pivot na pagina mae
                    endInsercao = splitPagina(endPagina, pagina, false, vetor, vetorFilhos, id, posicao); // faz split na pagina cheia
                    //inserir(endPagina, pagina, id, posicao); // insere o elemento na pagina
                }
                return endInsercao;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao inserir: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public long splitPagina(long endPagina, Pagina pagina, boolean isRaiz, Elemento[] vetor, long[] vetorFilhos, int idReg, long endReg) {
        Pagina novaPagina = new Pagina();
        Elemento pivot = vetor[3]; // pega o menor elemento do meio
        int id = -1;
        long endNovaPagina = -1;
        long endRaiz = -1;
        long endMae = -1;

        try {
            for (int i = 4; i < maxElementos; i++) {
                novaPagina.elementos[i - 4] = vetor[i]; // copia os elementos maiores para a nova pagina
                novaPagina.filhos[i-4] = vetorFilhos[i]; // copia os filhos para a nova pagina
            }
            if (vetorFilhos[7] != -1) {
                novaPagina.filhos[3] = vetorFilhos[7]; // copia o ultimo filho para a nova pagina
            }
            
            novaPagina.setQtdElementos(3);
            endNovaPagina = writePagina(novaPagina); // escreve a nova pagina no arquivo
            this.qtdPaginas++; // incrementa a quantidade de paginas

            if (isRaiz) {
                Pagina novaRaiz = new Pagina(false, 1);
                novaRaiz.elementos[0] = pivot; // adiciona o pivot na nova raiz
                novaRaiz.filhos[0] = endPagina; // filho da esquerda
                novaRaiz.filhos[1] = endNovaPagina; // filho da direita
                endRaiz = writePagina(novaRaiz); // escreve a nova raiz no arquivo
                arvoreB.seek(0); // vai para o inicio do arquivo
                arvoreB.writeLong(endRaiz); // escreve o end da nova raiz no arquivo
                this.qtdPaginas++; // incrementa a quantidade de paginas

                // apaga o pivot e elementos a direita do pivot da pagina splitada
                arvoreB.seek(endPagina + 73); // vai para o pivot
                for (int i = 3; i < maxElementos; i++) {
                    arvoreB.writeInt(-1); // apaga o id do elemento
                    arvoreB.writeLong(-1); // apaga o endereco do elemento
                    arvoreB.writeLong(-1); // apaga o filho
                    pagina.excluirElemento(i); // apaga o elemento do vetor da pagina
                    pagina.setQtdElementos(pagina.getQtdElementos()-1); // decrementa a quantidade de elementos na pagina
                }
                arvoreB.seek(endPagina + 1); // volta para o inicio da quantidade de elementos
                arvoreB.writeInt(pagina.getQtdElementos()); // escreve a quantidade de elementos
                inserir(endNovaPagina, novaPagina, idReg, endReg); // insere o elemento na pagina

                for (int i = 0; i < vetorFilhos.length; i++) {
                    if (vetorFilhos[i] != -1) {
                        arvoreB.seek(endNovaPagina); // vai para o endereco da pagina
                        arvoreB.writeBoolean(false); // escreve que a pagina nao e folha
                        i = vetorFilhos.length; // sai do for
                    }
                }
            } else {
                arvoreB.seek(0);
                endRaiz = arvoreB.readLong();
                endMae = findParent(endRaiz, pagina, endPagina); // encontra a pagina mae
                arvoreB.seek(endMae); // vai para a pagina mae
                arvoreB.skipBytes(1); // pula o isFolha
                int qtdElementos = arvoreB.readInt(); // le a quantidade de elementos
                for (int i = 0; i < qtdElementos; i++) {
                    arvoreB.skipBytes(8); // pula o ponteiro filho
                    id = arvoreB.readInt(); // le o id do elemento
                    if (id > novaPagina.getElementos()[0].getIdRegistro()) { // se o id do filho for menor
                        arvoreB.seek(arvoreB.getFilePointer() - 12); // volta para o inicio do elemento
                        arvoreB.writeLong(endNovaPagina); // escreve o ponteiro para a nova pagina
                        i = qtdElementos; // sai do loop
                    } else if (i == (qtdElementos - 1)) {
                        if (id < novaPagina.getElementos()[0].getIdRegistro()) { // se o id do filho for maior
                            arvoreB.skipBytes(8); // pula o endRegistro
                            arvoreB.writeLong(endNovaPagina); // escreve o ponteiro para a nova pagina
                            i = qtdElementos; // sai do loop
                        }
                    } else {
                        arvoreB.skipBytes(8); // pula o endRegistro
                    }
                }

                // apaga o pivot e elementos a direita do pivot da pagina splitada
                arvoreB.seek(endPagina + 73); // vai para o pivot
                for (int i = 3; i < maxElementos; i++) {
                    arvoreB.writeInt(-1); // apaga o id do elemento
                    arvoreB.writeLong(-1); // apaga o endereco do elemento
                    arvoreB.writeLong(-1); // apaga o filho
                    pagina.excluirElemento(i); // apaga o elemento do vetor da pagina
                    pagina.setQtdElementos(pagina.getQtdElementos()-1); // decrementa a quantidade de elementos na pagina
                }
                arvoreB.seek(endPagina + 1); // volta para o inicio da quantidade de elementos
                arvoreB.writeInt(pagina.getQtdElementos()); // escreve a quantidade de elementos
                inserir(endNovaPagina, novaPagina, idReg, endReg); // insere o elemento na pagina

                for (int i = 0; i < vetorFilhos.length; i++) {
                    if (vetorFilhos[i] != -1) {
                        arvoreB.seek(endNovaPagina); // vai para o endereco da pagina
                        arvoreB.writeBoolean(false); // escreve que a pagina nao e folha
                        i = vetorFilhos.length; // sai do for
                    }
                }
            }
            arvoreB.seek(8); // vai para a quantidade de paginas
            arvoreB.writeInt(this.qtdPaginas); // escreve a nova quantidade de paginas
            return endNovaPagina;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao inserir em pagina cheia: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public long findParent(long pgAtual, Pagina filho, long endFilho) {
        try {
            arvoreB.seek(pgAtual); // vai para a pagina atual (comeca na raiz)
            arvoreB.skipBytes(1); // pula o isFolha
            int qtdElementos = arvoreB.readInt(); // le a quantidade de elementos
            arvoreB.seek(arvoreB.getFilePointer() + ((qtdElementos - 1) * 20) + 8); // pula para o ultimo elemento

            while (arvoreB.getFilePointer() < arvoreB.length()) { // while !EOF
                for (int i = qtdElementos; i >= 0; i--) {
                    if (pgAtual == endFilho) {
                        return -1; // nao tem pai - raiz
                    }
                    if (filho.getElementos()[0].getIdRegistro() > arvoreB.readInt()) {
                        arvoreB.skipBytes(8); // pula o endRegistro
                        if (arvoreB.readLong() == endFilho) { // se o filho for igual ao endFilho
                            return pgAtual; // retorna o end do pai (pagina atual)
                        } else {
                            arvoreB.seek(arvoreB.getFilePointer() - 8); // volta para o ponteiro do filho
                            pgAtual = arvoreB.readLong(); // atualiza a pagina atual
                            return findParent(pgAtual, filho, endFilho); // procura na proxima pagina recursivamente
                        }
                    } else if (i == 0) {
                        arvoreB.seek(arvoreB.getFilePointer() - 4); // volta para o idRegistro
                        if (filho.getElementos()[0].getIdRegistro() < arvoreB.readInt()) {
                            arvoreB.skipBytes(8); // pula o endRegistro
                            if (arvoreB.readLong() == endFilho) { // se o filho for igual ao endFilho
                                return pgAtual; // retorna o end do pai (pagina atual)
                            } else {
                                arvoreB.seek(arvoreB.getFilePointer() - 28); // volta para o primeiro ponteiro filho
                                pgAtual = arvoreB.readLong(); // atualiza a pagina atual
                                return findParent(pgAtual, filho, endFilho); // procura na proxima pagina recursivamente
                            }
                        }
                    } else {
                        arvoreB.seek(arvoreB.getFilePointer() - 24); // vai para o elemento anterior
                    }
                }
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao encontrar pagina mae: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
        return -1;
    }
}
