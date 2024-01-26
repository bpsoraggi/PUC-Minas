package Semester4.TGC;

import java.io.File;
import java.util.Scanner;

import Semester4.TGC.Methods.Graph;
import Semester4.TGC.Methods.GraphPaths;
import Semester4.TGC.Methods.GraphRepresentation;
import Semester4.TGC.Methods.GraphSearch;
import Semester4.TGC.Methods.GraphUtils;
import Semester4.TGC.UtilModels.Constants;

public class Main {

    private static int OptionValidation(String input, int option, int firstOption, int lastOption, Scanner sc) {
        try {
            input = sc.nextLine();
            option = Integer.parseInt(input);
            if (option < firstOption || option > lastOption) {
                System.out.println(Constants.ANSI_RED + "Invalid option!!" + Constants.ANSI_RESET);
                System.out.print(Constants.ANSI_RED + ">> " + Constants.ANSI_RESET);
            }
            return option;
        } catch (Exception e) {
            System.out.println(Constants.ANSI_RED + "The input should be a number!" + Constants.ANSI_RESET);
            System.out.print(Constants.ANSI_RED + ">> " + Constants.ANSI_RESET);
            return lastOption + 1;
        }
    }

    public static void main(String[] args) {

        // ==================== ATTRIBUTES ====================

        Scanner sc = new Scanner(System.in);
        Graph graph;

        String fileName = "";
        String input = "";
        int option = 0;
        boolean exit = false;
        long runtime = 0;

        // Console
        String leftAlignFormat = "";

        // ==================== MAIN ====================

        while (!exit) {
            leftAlignFormat = "║[%5s] %-25s  ║%n";
            System.out.format("╔═══════════════════════════════╗%n");
            System.out.format("║ " + Constants.ANSI_BOLD + "MENU                          ║%n");
            System.out.format("╠═══════════════════════════════╣%n");
            System.out.format(leftAlignFormat, Constants.ANSI_RED + "0" + Constants.ANSI_RESET, "exit");
            System.out.format(leftAlignFormat, Constants.ANSI_YELLOW + "1" + Constants.ANSI_RESET,
                    "Representacao de graph");
            System.out.format(leftAlignFormat, Constants.ANSI_GREEN + "2" + Constants.ANSI_RESET, "Busca em graph");
            System.out.format(leftAlignFormat, Constants.ANSI_CYAN + "3" + Constants.ANSI_RESET, "Caminhos Disjuntos");
            System.out.format(leftAlignFormat,
                    Constants.ANSI_PURPLE + "4" + Constants.ANSI_RESET, "Problema de Transporte");
            System.out.format("╚═══════════════════════════════╝%n");
            System.out.print(Constants.ANSI_RED + ">> " + Constants.ANSI_RESET);

            // --------------- Option validation ---------------
            int lastOption = 3;

            do {
                option = OptionValidation(input, option, 0, lastOption, sc);
            } while (option < 0 || option > lastOption);

            // --------------- Opcoes ---------------
            switch (option) {
                // Exit:
                case 0:
                    System.out.println(Constants.ANSI_RED + "Exiting..." + Constants.ANSI_RESET);
                    System.out.println();
                    exit = true;
                    break;
                // Graph representation:
                case 1:
                    System.out.format(
                            Constants.ANSI_BOLD + "───────────────┤ " + Constants.ANSI_YELLOW + "GRAPH REPRESENTATION"
                                    + Constants.ANSI_RESET
                                    + " ├───────────────%n");
                    System.out.println("Type the name of the file (without the .txt): ");
                    System.out.print(Constants.ANSI_RED + ">> " + Constants.ANSI_RESET);
                    fileName = sc.nextLine();
                    graph = new Graph(fileName);

                    System.out.println("Type " + Constants.ANSI_RED + "1 " + Constants.ANSI_RESET
                            + "for ForwardStar, " + Constants.ANSI_RED + "2 " + Constants.ANSI_RESET
                            + "for ReverseStar and " + Constants.ANSI_RED + "3 " + Constants.ANSI_RESET
                            + "for both: ");
                    System.out.print(Constants.ANSI_RED + ">> " + Constants.ANSI_RESET);
                    option = OptionValidation(input, option, 1, 3, sc);

                    switch (option) {
                        case 1:
                            runtime = GraphRepresentation.ForwardReverseStar(graph, true, true);
                            break;
                        case 2:
                            GraphUtils.Sort(graph, false);
                            runtime = GraphRepresentation.ForwardReverseStar(graph, false, true);
                            GraphUtils.Sort(graph, true);
                            break;
                        case 3:
                            runtime = GraphRepresentation.ForwardReverseStar(graph, true, true);
                            GraphUtils.Sort(graph, false);
                            runtime += GraphRepresentation.ForwardReverseStar(graph, false, true);
                            GraphUtils.Sort(graph, true);
                            break;
                    }

                    System.out.println();
                    System.out.println(
                            "Execution time: " + Constants.ANSI_CYAN + Constants.ANSI_BOLD + (runtime)
                                    + " miliseconds"
                                    + Constants.ANSI_RESET);
                    System.err.format("────────────────────────────────────────────────────%n");
                    break;
                // Busca em graph:
                case 2:
                    System.out.format(
                            Constants.ANSI_BOLD + "────────────────────┤ " + Constants.ANSI_GREEN + "GRAPH SEARCH"
                                    + Constants.ANSI_RESET
                                    + " ├────────────────────%n");
                    System.out.println("Type the name of the file (without the .txt): ");
                    System.out.print(Constants.ANSI_RED + ">> " + Constants.ANSI_RESET);
                    fileName = sc.nextLine();

                    graph = new Graph(fileName);

                    System.out.println("Type the desired vertex: ");
                    System.out.print(Constants.ANSI_RED + ">> " + Constants.ANSI_RESET);
                    input = sc.nextLine();
                    int desiredVertex = Integer.parseInt(input);
                    System.out.println();

                    runtime = GraphSearch.DepthFirstSearchAssignment(graph, desiredVertex);

                    System.out.println();
                    leftAlignFormat = "│ %-33s  │%n";
                    System.out.format("┌─────Divergent Edges of Vertex " + desiredVertex + "──┐%n");
                    System.out.format("┌──Arestas divergentes do vertice x──┐%n");

                    int numDivergentEdgesV = GraphSearch.GetNumberDivergentEdgesV();

                    for (int i = 0; i < numDivergentEdgesV; i++) {
                        String aresta = (desiredVertex + " -> " + GraphSearch.GetDivergentEdgesV()[i][0] + " : ");
                        switch (GraphSearch.GetDivergentEdgesV()[i][1]) {
                            case 1:
                                System.out.format(leftAlignFormat, aresta + "Tree");
                                break;
                            case 2:
                                System.out.format(leftAlignFormat, aresta + "Back");
                                break;
                            case 3:
                                System.out.format(leftAlignFormat, aresta + "Forward");
                                break;
                            case 4:
                                System.out.format(leftAlignFormat, aresta + "Cross");
                                break;
                        }
                    }
                    System.out.format("└─────────────────────────────────────┘%n");
                    System.out.println(
                            "Execution time: " + Constants.ANSI_CYAN + Constants.ANSI_BOLD + (runtime)
                                    + " miliseconds"
                                    + Constants.ANSI_RESET);
                    System.err.format("──────────────────────────────────────────────────────────────%n");
                    System.out.println();
                    break;
                // Caminhos Disjuntos:
                case 3:
                    try {
                        System.out.format(
                                Constants.ANSI_BOLD + "──────────────────────────────┤ " + Constants.ANSI_CYAN
                                        + "DISJOINT PATHS"
                                        + Constants.ANSI_RESET
                                        + " ├──────────────────────────────%n");

                        System.out.println("Type the name of the file (without the .txt): ");
                        System.out.print(Constants.ANSI_RED + ">> " + Constants.ANSI_RESET);
                        fileName = sc.nextLine();

                        graph = new Graph(fileName);

                        String dir = "../Graphs/" + fileName + ".txt";
                        Scanner file = new Scanner(new File(dir));
                        int origin = file.nextInt();
                        int destination = file.nextInt();

                        runtime = GraphPaths.DisjointPaths(graph, origin, destination, true);

                        System.out.println();
                        System.out.println(
                                "Execution time: " + Constants.ANSI_CYAN + Constants.ANSI_BOLD + (runtime)
                                        + " miliseconds"
                                        + Constants.ANSI_RESET);
                        System.out
                                .format("──────────────────────────────────────────────────────────────────────────────%n");
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println("Error in disjoint paths: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
            }
        }
        sc.close();
    }
}
