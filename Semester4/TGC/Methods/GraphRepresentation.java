package Semester4.TGC.Methods;

public final class GraphRepresentation {

    public static long ForwardReverseStar(Graph graph, boolean isForward, boolean imprime) {
        long startTime = System.currentTimeMillis();
        int pos = 1, ptPos = 1;
        int n = graph.GetN(), m = graph.GetM();

        int aux = 0;
        int degree = 0;
        int vertex = 0;
        int[] vertexes = null;

        try {
            int[] pointers = isForward ? graph.forwardPointer : graph.reversePointer;
            int[] ends = isForward ? graph.parents : graph.children;

            // Fill the pointers array
            if (ends[pos] == ptPos) {
                pointers[ptPos] = pos;
            }
            while (ptPos <= n) {
                while (pos <= m && ends[pos] == ptPos) {
                    pos++;
                }
                pointers[ptPos + 1] = pos;
                ptPos++;
            }

            // Find the maximum degree
            for (int i = n; i >= 1; i--) {
                if (pointers[i] == 0) {
                    pointers[i] = pointers[i + 1];
                }
                if (i != 1) {
                    aux = pointers[i] - pointers[i - 1];
                    if (aux > degree) {
                        degree = aux;
                        vertex = i - 1;
                        vertexes = new int[degree];
                        for (int j = 0; j < degree; j++) {
                            vertexes[j] = isForward ? graph.children[pointers[i - 1] + j]
                                    : graph.parents[pointers[i - 1] + j];
                        }
                    }
                }
            }

            long endTime = System.currentTimeMillis();

            if (imprime) {
                PrintForwardReverseStar(vertex, degree, ends, isForward);
            }

            return endTime - startTime;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Erro no " + (isForward ? "forward" : "reverse") + " star: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public static void PrintForwardReverseStar(int vertex, int degree, int[] successors, boolean isForward) {

        String leftAlignFormat = "│ %-27s  │%n";
        String vertexList = "";
        System.out.println();
        System.out.format("┌─────────" + (isForward ? "Forward" : "Reverse") + " Star─────────┐%n");
        System.out.format(leftAlignFormat, "Vertex: " + vertex);
        System.out.format(leftAlignFormat, "Degree: " + degree);
        for (int i = 0; i < successors.length; i++) {
            if (i != successors.length - 1) {
                vertexList += successors[i] + ", ";
            } else {
                vertexList += successors[i];
            }
        }
        System.out.format(leftAlignFormat, isForward ? "Sucessors: " : "Predecessors: " + vertexList);
        System.out.format("└──────────────────────────────┘%n");
        System.out.println();
    }
}
