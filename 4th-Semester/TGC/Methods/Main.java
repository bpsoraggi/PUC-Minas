import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // ==================== ATRIBUTOS ====================

        // objetos principais
        Scanner sc = new Scanner(System.in);
        GraphRepresentation grafo;
        GraphSearch busca;
        DisjointPaths camDis;

        // Variaveis auxiliares
        String nomeArquivo = "";
        String input = "";
        int opcao = 0;
        boolean sair = false;
        long runtime = 0;

        // Menu console
        String leftAlignFormat = "";
        String ANSI_BOLD = "\u001B[1m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_WHITE = "\u001B[37m";
        String ANSI_RESET = "\u001B[0m";

        // ==================== MAIN ====================

        while (!sair) {
            leftAlignFormat = "║[%5s] %-25s  ║%n";
            System.out.format("╔═══════════════════════════════╗%n");
            System.out.format("║ " + ANSI_BOLD + "MENU                          ║%n");
            System.out.format("╠═══════════════════════════════╣%n");
            System.out.format(leftAlignFormat, ANSI_RED + "0" + ANSI_RESET, "sair");
            System.out.format(leftAlignFormat, ANSI_YELLOW + "1" + ANSI_RESET, "Representacao de Grafo");
            System.out.format(leftAlignFormat, ANSI_GREEN + "2" + ANSI_RESET, "Busca em Grafo");
            System.out.format(leftAlignFormat, ANSI_CYAN + "3" + ANSI_RESET, "Caminhos disjuntos");
            System.out.format("╚═══════════════════════════════╝%n");
            System.out.print(ANSI_RED + ">> " + ANSI_RESET);

            // --------------- Validacao de opcao ---------------
            int ultimaOpcao = 3;

            do {
                try {
                    input = sc.nextLine();
                    opcao = Integer.parseInt(input);
                    if (opcao < 0 || opcao > ultimaOpcao) {
                        System.out.println(ANSI_RED + "Opcao invalida!" + ANSI_RESET);
                        System.out.print(ANSI_RED + ">> " + ANSI_RESET);
                    }
                } catch (Exception e) {
                    System.out.println(ANSI_RED + "Digite um numero!" + ANSI_RESET);
                    System.out.print(ANSI_RED + ">> " + ANSI_RESET);
                    opcao = ultimaOpcao + 1;
                }
            } while (opcao < 0 || opcao > ultimaOpcao);

            // --------------- Opcoes ---------------
            switch (opcao) {
                // Sair:
                case 0:
                    System.out.println(ANSI_RED + "Saindo..." + ANSI_RESET);
                    System.out.println();
                    sair = true;
                    break;
                // Representacao de Grafo:
                case 1:
                    System.out.format(
                            ANSI_BOLD + "───────────────┤ " + ANSI_YELLOW + "REPRESENTACAO DE GRAFO" + ANSI_RESET
                                    + " ├───────────────%n");
                    System.out.println("Digite o nome do arquivo (sem o .txt): ");
                    System.out.print(ANSI_RED + ">> " + ANSI_RESET);

                    nomeArquivo = sc.nextLine();
                    grafo = new GraphRepresentation(nomeArquivo);

                    runtime = grafo.ForwardStar(true);
                    grafo.sort();
                    grafo.reverseStar();

                    System.out.println();
                    System.out.println(
                            "Tempo de execucao: " + ANSI_CYAN + ANSI_BOLD + (runtime) + " milisegundos"
                                    + ANSI_RESET);
                    System.err.format("────────────────────────────────────────────────────%n");
                    break;
                // Busca em Grafo:
                case 2:
                    System.out.format(
                            ANSI_BOLD + "────────────────────┤ " + ANSI_GREEN + "BUSCA EM GRAFO" + ANSI_RESET
                                    + " ├────────────────────%n");
                    System.out.println("Digite o nome do arquivo (sem o .txt): ");
                    System.out.print(ANSI_RED + ">> " + ANSI_RESET);
                    nomeArquivo = sc.nextLine();

                    System.out.println("Digite o vertice desejado: ");
                    System.out.print(ANSI_RED + ">> " + ANSI_RESET);
                    input = sc.nextLine();
                    int verticeDesejado = Integer.parseInt(input);
                    System.out.println();

                    busca = new GraphSearch(verticeDesejado, nomeArquivo);
                    busca.setVertice(verticeDesejado);
                    runtime = busca.buscaProfundidade();

                    System.out.println();
                    leftAlignFormat = "│ %-33s  │%n";
                    System.out.format("┌──Arestas divergentes do vertice " + verticeDesejado + "──┐%n");

                    int numArestasDivV = busca.getNumArestasDivV();
                    for (int i = 0; i < numArestasDivV; i++) {
                        String aresta = (verticeDesejado + " -> " + busca.getArestasDivV()[i][0] + " : ");
                        switch (busca.getArestasDivV()[i][1]) {
                            case 1:
                                System.out.format(leftAlignFormat, aresta + "Arvore");
                                break;
                            case 2:
                                System.out.format(leftAlignFormat, aresta + "Retorno");
                                break;
                            case 3:
                                System.out.format(leftAlignFormat, aresta + "Avanco");
                                break;
                            case 4:
                                System.out.format(leftAlignFormat, aresta + "Cruzamento");
                                break;
                        }
                    }
                    System.out.format("└────────────────────────────────────┘%n");
                    System.out.println(
                            "Tempo de execucao: " + ANSI_CYAN + ANSI_BOLD + (runtime) + " milisegundos"
                                    + ANSI_RESET);
                    System.err.format("──────────────────────────────────────────────────────────────%n");
                    System.out.println();
                    break;
                // Caminhos Disjuntos:
                case 3:
                    System.out.format(
                            ANSI_BOLD + "────────────────────────────┤ " + ANSI_CYAN + "CAMINHOS DISJUNTOS" + ANSI_RESET
                                    + " ├────────────────────────────%n");

                    System.out.println("Digite o nome do arquivo (sem o .txt): ");
                    System.out.print(ANSI_RED + ">> " + ANSI_RESET);
                    nomeArquivo = sc.nextLine();

                    camDis = new DisjointPaths(nomeArquivo);
                    runtime = camDis.caminhosDisjuntos();

                    System.out.println();
                    System.out.println(
                            "Tempo de execucao: " + ANSI_CYAN + ANSI_BOLD + (runtime) + " milisegundos"
                                    + ANSI_RESET);
                    System.out
                            .format("──────────────────────────────────────────────────────────────────────────────%n");
                    System.out.println();
                    break;
            }
        }
        sc.close();
    }
}
