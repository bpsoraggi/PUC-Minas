
/**
 * Representacao de Grafo - 27/08/2023
 * Estrutura: Forward/Reverse star
 * A escolha da estrutura se deve ao fato de ser uma implementacao simples. Como
 * o grafo nao muda de tamanho, nao ha necessidade de se utilizar uma estrutura
 * dinamica, como lista encadeada ou matriz de adjacencia. O beneficio do forward e
 * reverse star nesse caso e a economizacao de memoria. A unica referencia utilizada foi
 * para o metodo de quicksort.
 * 
 * @author Barbara Hellen Pereira Soraggi
 */

import java.io.File;
import java.util.Scanner;

class GraphRepresentation {

    // --------------- Atributos ---------------
    private int n;
    private int m;
    private int[] origem;
    private int[] destino;

    private int[] forwardPointer;
    private int[] reversePointer;

    // private boolean forwardFeito;
    private Scanner arquivo;

    // --------------- Construtor ---------------

    public GraphRepresentation(String nomeArquivo) {
        int pos = 1;
        try {
            // String localDir = System.getProperty("user.dir");
            // nomeArquivo = localDir + "\\Grafos\\" + nomeArquivo + ".txt";
            nomeArquivo = nomeArquivo + ".txt";
            this.arquivo = new Scanner(new File(nomeArquivo));

            this.n = arquivo.nextInt();
            this.m = arquivo.nextInt();

            this.origem = new int[m + 1];
            this.destino = new int[m + 1];
            this.forwardPointer = new int[n + 2];
            this.reversePointer = new int[n + 2];

            while (arquivo.hasNextInt()) {
                origem[pos] = arquivo.nextInt();
                destino[pos] = arquivo.nextInt();
                pos++;
            }

            this.forwardPointer[n + 1] = m + 1;
            this.reversePointer[n + 1] = m + 1;

        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro no construtor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --------------- Getters ---------------
    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int[] getOrigem() {
        return origem;
    }

    public int[] getDestino() {
        return destino;
    }

    public int[] getForwardPointer() {
        return forwardPointer;
    }

    public int[] getReversePointer() {
        return reversePointer;
    }

    public int getNumSucessores(int vertice) {
        return forwardPointer[vertice + 1] - forwardPointer[vertice];
    }

    public int[] getSucessores(int vertice) {
        int startPos = forwardPointer[vertice];
        int endPos = forwardPointer[vertice + 1];

        int[] sucessores = new int[endPos - startPos];
        for (int i = 0; i < sucessores.length; i++) {
            sucessores[i] = destino[startPos + i];
        }

        return sucessores;
    }

    // --------------- Metodos ---------------

    public void sort() {
        quickSort(destino, origem, 0, m - 1);
    }

    public void quickSort(int[] destino, int[] origem, int esq, int dir) {
        if (esq < dir) {
            int pos = 0;
            int pivot = destino[dir];
            int i = esq - 1;
            int auxDestino = 0;
            int auxOrigem = 0;

            for (int j = esq; j < dir; j++) {
                if (destino[j] < pivot) {
                    i++;

                    auxDestino = destino[i];
                    destino[i] = destino[j];
                    destino[j] = auxDestino;

                    auxOrigem = origem[i];
                    origem[i] = origem[j];
                    origem[j] = auxOrigem;
                }
            }

            auxDestino = destino[i + 1];
            destino[i + 1] = destino[dir];
            destino[dir] = auxDestino;

            auxOrigem = origem[i + 1];
            origem[i + 1] = origem[dir];
            origem[dir] = auxOrigem;

            pos = i + 1;

            quickSort(destino, origem, esq, pos - 1);
            quickSort(destino, origem, pos + 1, dir);
        }
    }

    public void removeAresta(int pai, int filho) {
        boolean achou = false;

        for (int i = 1; i <= m; i++) {
            if (origem[i] == pai && destino[i] == filho) {
                // mover elementos para esquerda
                for (int j = i; j < m; j++) {
                    origem[j] = origem[j + 1];
                    destino[j] = destino[j + 1];
                }
                m--;

                // Update forwardPointer
                for (int j = pai + 1; j < forwardPointer.length; j++) {
                    forwardPointer[j]--;
                }

                achou = true;
                break;
            }
        }

        if (!achou) {
            System.out.println("Aresta nao encontrada: " + pai + " -> " + filho);
        }
    }

    public void inverteAresta(int origin, int destination) {
        try {
            int startPos = forwardPointer[origin];
            int endPos = forwardPointer[origin + 1];

            int edgePos = -1;
            for (int i = startPos; i < endPos; i++) {
                if (destination == destino[i]) {
                    edgePos = i;
                    break;
                }
            }

            if (edgePos != -1) {
                int temp = destino[edgePos];
                destino[edgePos] = destino[endPos - 1];
                destino[endPos - 1] = origin;

                forwardPointer[origin + 1]--;
                m--;
            } else {
                System.out.println("Aresta nao encontrada: (" + origin + ", " + destination + ")");
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro ao inverter aresta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public long ForwardStar(boolean imprime) {
        long startTime = System.currentTimeMillis();
        int pos = 1;
        int ptPos = 1;

        int aux = 0;
        int grauSaida = 0;
        int vertice = 0;
        int[] sucessores = null;

        try {
            // preenche vetor forwardPointer ----------
            if (origem[pos] == ptPos) {
                forwardPointer[ptPos] = pos;
            }
            while (ptPos <= n) {
                while (pos <= m && origem[pos] == ptPos) {
                    pos++;
                }
                forwardPointer[ptPos + 1] = pos;
                ptPos++;
            }

            // pega maior grau de saida ----------
            for (int i = n; i >= 1; i--) {
                if (forwardPointer[i] == 0) {
                    forwardPointer[i] = forwardPointer[i + 1];
                }
                if (i != 1) {
                    aux = forwardPointer[i] - forwardPointer[i - 1];
                    if (aux > grauSaida) {
                        grauSaida = aux;
                        vertice = i - 1;
                        sucessores = new int[grauSaida];
                        for (int j = 0; j < grauSaida; j++) {
                            sucessores[j] = destino[forwardPointer[i - 1] + j];
                        }
                    }
                }
            }
            long endTime = System.currentTimeMillis();

            if (imprime) {
                // imprime ----------
                String leftAlignFormat = "│ %-27s  │%n";
                String sucess = "";
                System.out.println();
                System.out.format("┌─────────Forward Star─────────┐%n");
                System.out.format(leftAlignFormat, "Vertice: " + vertice);
                System.out.format(leftAlignFormat, "Grau de saida: " + grauSaida);
                for (int i = 0; i < sucessores.length; i++) {
                    if (i != sucessores.length - 1) {
                        sucess += sucessores[i] + ", ";
                    } else {
                        sucess += sucessores[i];
                    }
                }
                System.out.format(leftAlignFormat, "Sucessores: " + sucess);
                System.out.format("└──────────────────────────────┘%n");
                System.out.println();
            }
            return endTime - startTime;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro no forward star: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public void reverseStar() {
        int pos = 1;
        int ptPos = 1;

        int aux = 0;
        int grauEntrada = 0;
        int vertice = 0;
        int[] predecessores = null;
        try {
            // preenche vetor reversePointer ----------
            if (destino[pos] == ptPos) {
                reversePointer[ptPos] = pos;
            }
            while (ptPos <= n) {
                while (pos <= m && destino[pos] == ptPos) {
                    pos++;
                }
                reversePointer[ptPos + 1] = pos;
                ptPos++;
            }

            // pega maior grau de saida ----------
            for (int i = n; i >= 1; i--) {
                if (reversePointer[i] == 0) {
                    reversePointer[i] = reversePointer[i + 1];
                }
                if (i != 1) {
                    aux = reversePointer[i] - reversePointer[i - 1];
                    if (aux > grauEntrada) {
                        grauEntrada = aux;
                        vertice = i - 1;
                        predecessores = new int[grauEntrada];
                        for (int j = 0; j < grauEntrada; j++) {
                            predecessores[j] = origem[reversePointer[i - 1] + j];
                        }
                    }
                }
            }

            String leftAlignFormat = "│ %-27s  │%n";
            String predec = "";
            System.out.format("┌─────────Reverse Star─────────┐%n");
            System.out.format(leftAlignFormat, "Vertice: " + vertice);
            System.out.format(leftAlignFormat, "Grau de entrada: " + grauEntrada);
            for (int i = 0; i < predecessores.length; i++) {
                if (i != predecessores.length - 1) {
                    predec += predecessores[i] + ", ";
                } else {
                    predec += predecessores[i];
                }
            }
            System.out.format(leftAlignFormat, "Predecessores: " + predec);
            System.out.format("└──────────────────────────────┘%n");
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro no reverse star: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --------------- Main ---------------

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o nome do arquivo (sem o txt): ");
        String nomeArquivo = sc.nextLine();
        GraphRepresentation grafo = new GraphRepresentation(nomeArquivo);
        sc.close();

        grafo.ForwardStar(true);
        grafo.inverteAresta(2, 4);

        long runtime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Tempo de execucao: " + runtime + " segundos");
    }

}