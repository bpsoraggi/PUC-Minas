import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class DepthFirstSearch {
    // --------------- Atributos ---------------
    private int n;
    private GraphRepresentation grafo;
    private int[] pointer;
    private int[] destino;

    private int[] TD;
    private int[] TT;
    private int[] pai;
    private int tempo;

    // --------------- Construtor ---------------
    public DepthFirstSearch(GraphRepresentation grafo) {
        try {
            this.grafo = grafo;
            this.n = this.grafo.getN();
            this.pointer = this.grafo.getForwardPointer();
            this.destino = this.grafo.getDestino();

            this.TD = new int[n + 1];
            this.TT = new int[n + 1];
            this.pai = new int[n + 1];

            this.tempo = 0;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro no construtor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --------------- Setters e Getters ---------------
    public int[] getTD() {
        return TD;
    }

    public int[] getTT() {
        return TT;
    }

    public int[] getPai() {
        return pai;
    }

    public void setGrafo(GraphRepresentation grafo) {
        this.grafo = grafo;
        this.n = this.grafo.getN();
        this.pointer = this.grafo.getForwardPointer();
        this.destino = this.grafo.getDestino();
    }

    // --------------- Metodos ---------------
    public void BuscaProfundidadeContinua() {
        for (int i = 1; i <= n; i++) {
            if (TD[i] == 0) {
                BuscaProfundidadeUnica(i);
            }
        }
    }

    public void BuscaProfundidadeUnica(int raiz) {
        tempo = 0;
        for (int i = 0; i < TD.length; i++) {
            TD[i] = 0;
            TT[i] = 0;
            pai[i] = 0;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(raiz);

        while (!stack.isEmpty()) {
            int v = stack.peek();
            int index = 0;

            int numSucessores = grafo.getNumSucessores(v);
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

                    visitado = false;
                    break;
                }
            }

            if (visitado) {
                TT[v] = ++tempo;
                stack.pop();
            }
        }
    }

    public ArrayList<ArrayList<String>> acharTodosCaminhos(int inicio, int vDestino) {
        ArrayList<ArrayList<String>> caminhos = new ArrayList<>();
        ArrayList<String> caminho = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] visitado = new boolean[n + 1];

        acharTodosCaminhos(inicio, vDestino, caminhos, caminho, stack, visitado);

        return caminhos;
    }

    // Modified DFS method to find all caminhos in the format of edges
    public void acharTodosCaminhos(int vAtual, int vDestino, ArrayList<ArrayList<String>> caminhos,
            ArrayList<String> caminho, Stack<Integer> stack, boolean[] visitado) {
        stack.push(vAtual);
        visitado[vAtual] = true;

        if (vAtual == vDestino) {
            if (caminho.size() > 0) {
                caminhos.add(new ArrayList<>(caminho));
            }
        } else {
            int numSuccessors = grafo.getNumSucessores(vAtual);
            int[] successors = new int[numSuccessors];

            for (int i = 0; i < numSuccessors; i++) {
                successors[i] = destino[pointer[vAtual] + i];
            }
            Arrays.sort(successors);

            for (int successor : successors) {
                if (!visitado[successor] && !stack.contains(successor)) {
                    String edge = vAtual + ", " + successor;
                    caminho.add(edge);

                    acharTodosCaminhos(successor, vDestino, caminhos, caminho, stack, visitado);

                    caminho.remove(caminho.size() - 1);
                }
            }
        }

        stack.pop();
        visitado[vAtual] = false;
    }

    public static void main(String[] args) {
        GraphRepresentation grafo = new GraphRepresentation("graph-test-10");
        grafo.ForwardStar(false);
        DepthFirstSearch busca = new DepthFirstSearch(grafo);

        ArrayList<ArrayList<String>> caminhos = busca.acharTodosCaminhos(3, 2);

        for (ArrayList<String> path : caminhos) {
            System.out.println(path);
        }
    }
}
