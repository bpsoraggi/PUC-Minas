
/**
 * Caminhos Disjuntos - 09/11/2023
 * Para a implementação desse metodo, foram utilizados os
 * slides das aulas como referencia, assim como algumas discussoes
 * de melhores solucoes em feruns online. Porem, a autoria
 * do cedigo é propria.
 * 
 * @author Barbara Hellen Pereira Soraggi
 */

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class DisjointPaths {
    // --------------- Atributos ---------------
    private GraphRepresentation grafo;

    private int[] sucessores;
    private int[] pai;
    private boolean visitado[];
    private int[] custos;
    private int[] arestaCaminho;

    private int origem;
    private int destino;

    private ArrayList<ArrayList<String>> caminhos;

    private File arquivo;
    private Scanner arquivoScanner;

    String ANSI_BOLD = "\u001B[1m";
    String ANSI_CYAN = "\u001B[36m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED_BACKGROUND = "\u001B[41m";

    // --------------- Construtor ---------------
    public DisjointPaths(String nomeArquivo) {
        try {
            // String localDir = System.getProperty("user.dir");
            // String arq = localDir + "\\Grafos\\" + nomeArquivo + ".txt";
            String arq = nomeArquivo + ".txt";
            this.arquivo = new File(arq);
            this.arquivoScanner = new Scanner(arquivo);

            this.origem = arquivoScanner.nextInt();
            this.destino = arquivoScanner.nextInt();

            apagarPrimeiraLinhaTxt();

            this.grafo = new GraphRepresentation(nomeArquivo);
            grafo.ForwardStar(false);
            pai = new int[grafo.getN() + 1];

            this.caminhos = new ArrayList<ArrayList<String>>();
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro no construtor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --------------- Metodos ---------------
    public void incluirPrimeiraLinhaTxt() {
        try {
            RandomAccessFile raf = new RandomAccessFile(arquivo, "rw");
            String content = origem + " " + destino + "\n";

            byte[] restOfFile = new byte[(int) raf.length()];
            raf.readFully(restOfFile);

            raf.seek(0);
            raf.writeBytes(content);

            raf.write(restOfFile);
            raf.close();
        } catch (Exception e) {
            System.out.println("Erro ao incluir primeira linha no txt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void apagarPrimeiraLinhaTxt() {
        try {
            RandomAccessFile raf = new RandomAccessFile(arquivo, "rw");

            raf.readLine();
            long pos = raf.getFilePointer();

            byte[] restOfFile = new byte[(int) (raf.length() - pos)];
            raf.seek(pos);
            raf.readFully(restOfFile);

            raf.seek(0);
            raf.write(restOfFile);

            raf.setLength(restOfFile.length);

            raf.close();
        } catch (Exception e) {
            System.out.println("Erro ao apagar primeira linha do txt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void dijkstra() {
        ArrayList<Integer> conjunto = new ArrayList<Integer>();
        conjunto.add(origem);

        int n = grafo.getN();
        visitado = new boolean[n + 1];
        custos = new int[n + 1];

        arestaCaminho = new int[3];
        arestaCaminho[2] = Integer.MAX_VALUE;

        for (int i = 1; i < custos.length; i++) {
            if (i == origem) {
                custos[i] = 0;
                visitado[i] = true;
            } else {
                custos[i] = Integer.MAX_VALUE;
                visitado[i] = false;
            }
            pai[i] = 0;
        }

        boolean flag = false;
        while (!flag) {
            for (int vertice : conjunto) {
                sucessores = new int[grafo.getNumSucessores(vertice)];
                sucessores = grafo.getSucessores(vertice);

                for (int i = 0; i < sucessores.length; i++) {
                    if (!visitado[sucessores[i]] && arestaCaminho[2] > (custos[vertice] + 1)) {
                        arestaCaminho[0] = vertice;
                        arestaCaminho[1] = sucessores[i];
                        arestaCaminho[2] = custos[vertice] + 1;
                    }
                }
            }

            if (arestaCaminho[2] == Integer.MAX_VALUE) {
                flag = true;
            }

            custos[arestaCaminho[1]] = arestaCaminho[2];
            pai[arestaCaminho[1]] = arestaCaminho[0];
            visitado[arestaCaminho[1]] = true;
            conjunto.add(arestaCaminho[1]);
            arestaCaminho[2] = Integer.MAX_VALUE;

            if (conjunto.contains(destino)) {
                flag = true;
            }
        }
    }

    public long caminhosDisjuntos() {
        long startTime = System.currentTimeMillis();
        boolean flag = false;

        dijkstra();
        while (!flag) {
            ArrayList<String> caminho = new ArrayList<String>();
            if (pai[destino] != 0) {
                int i = destino;
                while (i != origem) {

                    String aresta = pai[i] + ", " + i;
                    caminho.add(aresta);

                    grafo.removeAresta(pai[i], i);
                    i = pai[i];
                }

                caminhos.add(caminho);
                dijkstra();
            } else {
                flag = true;
            }
        }

        long endTime = System.currentTimeMillis();
        incluirPrimeiraLinhaTxt();

        System.out.println();
        String lineAlignment = "│ %-119s │%n";
        System.out.format("┌───────────────────────────────Caminhos encontrados───────────────────────────────┐%n");
        System.out.format(lineAlignment,
                ANSI_BOLD + ANSI_RED + caminhos.size() + ANSI_RESET
                        + " caminho(s) encontrados do vertice " + ANSI_BOLD + ANSI_RED + origem + ANSI_RESET
                        + " ao vertice " + ANSI_BOLD + ANSI_RED + destino + ANSI_RESET);
        int caminhosSize = caminhos.size();
        for (int i = 0; i < caminhosSize; i++) {
            String caminhoAlignment = "│ %-38s %-54s │%n";
            String caminhoN = ("Caminho " + (i + 1) + " (" +
                    ANSI_BOLD + ANSI_CYAN + caminhos.get(i).size() + " arestas" + ANSI_RESET + "): ");
            int sizeCaminho = caminhos.get(i).size() - 1;
            for (int j = sizeCaminho; j >= 0; j -= 4) {
                String result = ("(" + caminhos.get(i).get(j) + ")");
                if (j - 1 >= 0) {
                    result += (", (" + caminhos.get(i).get(j - 1) + ")");
                }
                if (j - 2 >= 0) {
                    result += (", (" + caminhos.get(i).get(j - 2) + ")");
                }
                if (j - 3 >= 0) {
                    result += (", (" + caminhos.get(i).get(j - 3) + ")");
                }

                System.out.format(caminhoAlignment, caminhoN, result);
                caminhoN = "";
                caminhoAlignment = "│ %-25s %-54s │%n";
            }
        }
        System.out.format("└──────────────────────────────────────────────────────────────────────────────────┘%n");

        return endTime - startTime;
    }
}
