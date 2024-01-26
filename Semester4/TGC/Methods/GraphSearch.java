package Semester4.TGC.Methods;

import java.util.Arrays;
import java.util.Stack;

class SearchAttributes {
    protected int[] TD;
    protected int[] TT;
    protected int[] parent;
    protected int vertex;
    protected int timer;
    public static int[][] divergentEdgesV;

    public SearchAttributes(int n) {
        this.TD = new int[n + 1];
        this.TT = new int[n + 1];
        this.parent = new int[n + 1];
        this.timer = 0;
    }
}

public class GraphSearch {

    public static void ContinuousDepthFirstSearch(Graph graph) {
        int n = graph.GetN();
        SearchAttributes attributes = new SearchAttributes(n);

        for (int i = 1; i <= n; i++) {
            if (attributes.TD[i] == 0) {
                SingleDepthFirstSearch(graph, attributes, i);
            }
        }
    }

    public static void SingleDepthFirstSearch(Graph graph, SearchAttributes attributes, int root) {

        for (int i = 0; i < attributes.TD.length; i++) {
            attributes.TD[i] = 0;
            attributes.TT[i] = 0;
            attributes.parent[i] = 0;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            int v = stack.peek();

            int NumberSuccessors = graph.GetNumberSuccessors(v);
            int[] sucessores = new int[NumberSuccessors];

            if (attributes.TD[v] == 0) {
                attributes.TD[v] = ++attributes.timer;
            }

            for (int i = 0; i < NumberSuccessors; i++) {
                sucessores[i] = graph.children[graph.forwardPointer[v] + i];
            }
            Arrays.sort(sucessores);

            boolean visitado = true;

            for (int i = 0; i < NumberSuccessors; i++) {
                int w = sucessores[i];

                if (attributes.TD[w] == 0) {
                    stack.push(w);
                    attributes.parent[w] = v;

                    visitado = false;
                    break;
                }
            }

            if (visitado) {
                attributes.TT[v] = ++attributes.timer;
                stack.pop();
            }
        }
    }

    public static int GetNumberDivergentEdgesV() {
        return SearchAttributes.divergentEdgesV.length;
    }

    public static int[][] GetDivergentEdgesV() {
        return SearchAttributes.divergentEdgesV;
    }

    public static long DepthFirstSearchAssignment(Graph graph, int vertex) {
        long startTime = System.currentTimeMillis();
        int n = graph.GetN();
        SearchAttributes attributes = new SearchAttributes(n);
        attributes.vertex = vertex;
        int[][] divergentEdgesV = new int[graph.GetNumberSuccessors(vertex)][2];

        System.out.format("┌─────────────Tree Edges─────────────┐%n");
        for (int i = 1; i <= n; i++) {
            if (attributes.TD[i] == 0) {
                DFSAssignment(graph, attributes, i, divergentEdgesV);
            }
        }
        System.out.format("└────────────────────────────────────┘%n");

        return (System.currentTimeMillis() - startTime) / 1000;
    }

    private static void DFSAssignment(Graph graph, SearchAttributes attributes, int root, int[][] divergentEdgesV) {
        Stack<Integer> stack = new Stack<>();
        stack.push(root);

        String leftAlignFormat = "│ %-32s  │%n";

        while (!stack.isEmpty()) {
            int v = stack.peek();
            int index = 0;

            int NumberSuccessors = graph.GetNumberSuccessors(v);
            int[] sucessores = new int[NumberSuccessors];

            if (attributes.TD[v] == 0) {
                attributes.TD[v] = ++attributes.timer;
            }

            for (int i = 0; i < NumberSuccessors; i++) {
                sucessores[i] = graph.children[graph.forwardPointer[v] + i];
            }
            Arrays.sort(sucessores);

            boolean visitado = true;

            for (int i = 0; i < NumberSuccessors; i++) {
                int w = sucessores[i];

                if (attributes.TD[w] == 0) {
                    stack.push(w);
                    attributes.parent[w] = v;

                    if (v == attributes.vertex) {
                        divergentEdgesV[index][1] = 1; // 1 = tree edges
                    }

                    System.out.format(leftAlignFormat, "Edge (" + v + ", " + w + ")");
                    visitado = false;
                    break;
                }

                if (v == attributes.vertex) {
                    divergentEdgesV[index][0] = w;

                    if (divergentEdgesV[index][1] == 0) {
                        if (attributes.TT[w] == 0) {
                            divergentEdgesV[index][1] = 2; // 2 = back edges
                        } else if (attributes.TD[v] < attributes.TD[w]) {
                            divergentEdgesV[index][1] = 3; // 3 = forward edges
                        } else {
                            divergentEdgesV[index][1] = 4; // 4 = cross edges
                        }
                    }

                    index++;
                }
            }

            if (visitado) {
                attributes.TT[v] = ++attributes.timer;
                stack.pop();
            }
        }
    }

}
