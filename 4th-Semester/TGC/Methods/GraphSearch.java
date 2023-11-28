
/**
 * Busca em Profundidade - 02/10/2023
 * Para a implementação dessa busca, utilizou-se a estrutura de
 * dados stack para fugir da recursividade (que daria StackOverflow
 * para grafos grandes). Para essa implementacao, foi necessario
 * pesquisar a respeito de stacks, mas nenhum metodo especificamente
 * foi mais referenciado que outro. Fora isso, apenas os slides das
 * aulas foram usados como referencia. De resto, o codigo é de autoria propria.
 * 
 * @author Barbara Hellen Pereira Soraggi
 */

import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;

public class GraphSearch {

    // --------------- Atributos ---------------
    private int n;
    private GraphRepresentation forwardStar;
    private int[] pointer;
    private int[] destino;

    private int[] TD;
    private int[] TT;
    private int[] pai;
    private int tempo;
    private int vertice;
    private int[][] arestasDivV;

    // --------------- Construtor ---------------
    public GraphSearch(int vertice, String nomeArquivo) {
        try {
            this.forwardStar = new GraphRepresentation(nomeArquivo);
            forwardStar.ForwardStar(false);
            this.n = forwardStar.getN();
            this.pointer = forwardStar.getForwardPointer();
            this.destino = forwardStar.getDestino();

            this.TD = new int[n + 1];
            this.TT = new int[n + 1];
            this.pai = new int[n + 1];

            this.tempo = 0;

            this.vertice = vertice;
            this.arestasDivV = new int[forwardStar.getNumSucessores(vertice)][2];
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro no construtor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --------------- Setters e Getters ---------------
    public void setVertice(int vertice) {
        this.vertice = vertice;
    }

    public int getNumArestasDivV() {
        return arestasDivV.length;
    }

    public int[][] getArestasDivV() {
        return arestasDivV;
    }

    // --------------- Metodos ---------------
    public long buscaProfundidade() {
        long startTime = System.currentTimeMillis();
        System.out.format("┌─────────Arestas de Arvore─────────┐%n");
        for (int i = 1; i <= n; i++) {
            if (TD[i] == 0) {
                BF(i);
            }
        }
        System.out.format("└───────────────────────────────────┘%n");
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public void BF(int raiz) {
        Stack<Integer> stack = new Stack<>();
        stack.push(raiz);

        String leftAlignFormat = "│ %-32s  │%n";

        while (!stack.isEmpty()) {
            int v = stack.peek();
            int index = 0;

            int numSucessores = forwardStar.getNumSucessores(v);
            int[] sucessores = new int[numSucessores];

            if (TD[v] == 0) {
                TD[v] = ++tempo;
            }

            for (int i = 0; i < numSucessores; i++) {
                sucessores[i] = destino[pointer[v] + i];
            }
            Arrays.sort(sucessores);

            boolean visitado = true;

            for (int i = 0; i < numSucessores; i++) {
                int w = sucessores[i];

                if (TD[w] == 0) {
                    stack.push(w);
                    pai[w] = v;

                    if (v == vertice) {
                        arestasDivV[index][1] = 1; // 1 = arvore
                    }

                    System.out.format(leftAlignFormat, "Aresta (" + v + ", " + w + ")");
                    visitado = false;
                    break;
                }

                if (v == vertice) {
                    arestasDivV[index][0] = w;

                    if (arestasDivV[index][1] == 0) {
                        if (TT[w] == 0) {
                            arestasDivV[index][1] = 2; // 2 = retorno
                        } else if (TD[v] < TD[w]) {
                            arestasDivV[index][1] = 3; // 3 = avanco
                        } else {
                            arestasDivV[index][1] = 4; // 4 = cruzamento
                        }
                    }

                    index++;
                }
            }

            if (visitado) {
                TT[v] = ++tempo;
                stack.pop();
            }
        }
    }

    // --------------- Main ---------------
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o nome do arquivo (sem o .txt): ");
        String nomeArquivo = sc.nextLine();
        System.out.println("Digite o vertice desejado: ");
        int verticeDesejado = sc.nextInt();
        sc.close();

        // String nomeArquivo = "graph-test-10";
        // int verticeDesejado = 3;

        GraphSearch grafo = new GraphSearch(verticeDesejado, nomeArquivo);
        grafo.setVertice(verticeDesejado);
        grafo.buscaProfundidade();

        System.out.println("\nArestas divergentes do vertice " + verticeDesejado + ": ");
        int numArestasDivV = grafo.getNumArestasDivV();
        for (int i = 0; i < numArestasDivV; i++) {
            System.out.print(verticeDesejado + " -> " + grafo.arestasDivV[i][0] + " : ");
            switch (grafo.arestasDivV[i][1]) {
                case 1:
                    System.out.println("Arvore");
                    break;
                case 2:
                    System.out.println("Retorno");
                    break;
                case 3:
                    System.out.println("Avanco");
                    break;
                case 4:
                    System.out.println("Cruzamento");
                    break;
            }
        }

        long runtime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Tempo de execucao: " + runtime + " segundos");
    }
}
