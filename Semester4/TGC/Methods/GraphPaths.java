package Semester4.TGC.Methods;

import java.util.ArrayList;

import Semester4.TGC.UtilModels.Constants;

class PathAttributes {
    protected int n;
    protected int[] successors;
    protected int[] parent;
    protected boolean visited[];
    protected int[] costs;
    protected int[] edgePath;

    protected ArrayList<ArrayList<String>> paths;

    public PathAttributes(Graph graph) {
        this.n = graph.GetN();
        this.parent = new int[n + 1];
        this.paths = new ArrayList<ArrayList<String>>();
    }
}

public class GraphPaths {

    public static void Dijkstra(Graph graph, PathAttributes attributes, int origin, int destination) {
        ArrayList<Integer> vertexList = new ArrayList<Integer>();
        vertexList.add(origin);

        attributes.visited = new boolean[attributes.n + 1];
        attributes.costs = new int[attributes.n + 1];

        attributes.edgePath = new int[3];
        attributes.edgePath[2] = Integer.MAX_VALUE;

        for (int i = 1; i < attributes.costs.length; i++) {
            if (i == origin) {
                attributes.costs[i] = 0;
                attributes.visited[i] = true;
            } else {
                attributes.costs[i] = Integer.MAX_VALUE;
                attributes.visited[i] = false;
            }
            attributes.parent[i] = 0;
        }

        boolean flag = false;
        while (!flag) {
            for (int vertex : vertexList) {
                attributes.successors = new int[graph.GetNumberSuccessors(vertex)];
                attributes.successors = graph.GetSucessors(vertex);

                for (int i = 0; i < attributes.successors.length; i++) {
                    if (!attributes.visited[attributes.successors[i]]
                            && attributes.edgePath[2] > (attributes.costs[vertex] + 1)) {
                        attributes.edgePath[0] = vertex;
                        attributes.edgePath[1] = attributes.successors[i];
                        attributes.edgePath[2] = attributes.costs[vertex] + 1;
                    }
                }
            }

            if (attributes.edgePath[2] == Integer.MAX_VALUE) {
                flag = true;
            }

            attributes.costs[attributes.edgePath[1]] = attributes.edgePath[2];
            attributes.parent[attributes.edgePath[1]] = attributes.edgePath[0];
            attributes.visited[attributes.edgePath[1]] = true;
            vertexList.add(attributes.edgePath[1]);
            attributes.edgePath[2] = Integer.MAX_VALUE;

            if (vertexList.contains(destination)) {
                flag = true;
            }
        }
    }

    public static long DisjointPaths(Graph graph, int origin, int destination, boolean print) {
        long startTime = System.currentTimeMillis();
        PathAttributes attributes = new PathAttributes(graph);
        boolean flag = false;

        Dijkstra(graph, attributes, origin, destination);
        while (!flag) {
            ArrayList<String> path = new ArrayList<String>();
            if (attributes.parent[destination] != 0) {
                int i = destination;
                while (i != origin) {

                    String edge = attributes.parent[i] + ", " + i;
                    path.add(edge);

                    graph.RemoveEdge(attributes.parent[i], i);
                    i = attributes.parent[i];
                }

                attributes.paths.add(path);
                Dijkstra(graph, attributes, origin, destination);
            } else {
                flag = true;
            }
        }

        long endTime = System.currentTimeMillis();

        if (print) {
            PrintDisjointPaths(attributes, origin, destination);
        }

        return endTime - startTime;
    }

    public static void PrintDisjointPaths(PathAttributes attributes, int origin, int destination) {
        System.out.println();
        String lineAlignment = "│ %-119s │%n";
        System.out.format("┌───────────────────────────────Paths Found───────────────────────────────┐%n");
        System.out.format(lineAlignment,
                Constants.ANSI_BOLD + Constants.ANSI_RED + attributes.paths.size() + Constants.ANSI_RESET
                        + " Path(s) found from vertex " + Constants.ANSI_BOLD + Constants.ANSI_RED + origin
                        + Constants.ANSI_RESET
                        + " to vertex " + Constants.ANSI_BOLD + Constants.ANSI_RED + destination
                        + Constants.ANSI_RESET);
        int pathsSize = attributes.paths.size();
        for (int i = 0; i < pathsSize; i++) {
            String pathAlignment = "│ %-38s %-54s │%n";
            String pathN = ("Path " + (i + 1) + " (" +
                    Constants.ANSI_BOLD + Constants.ANSI_CYAN + attributes.paths.get(i).size() + " edges"
                    + Constants.ANSI_RESET
                    + "): ");
            int sizepath = attributes.paths.get(i).size() - 1;
            for (int j = sizepath; j >= 0; j -= 4) {
                String result = ("(" + attributes.paths.get(i).get(j) + ")");
                if (j - 1 >= 0) {
                    result += (", (" + attributes.paths.get(i).get(j - 1) + ")");
                }
                if (j - 2 >= 0) {
                    result += (", (" + attributes.paths.get(i).get(j - 2) + ")");
                }
                if (j - 3 >= 0) {
                    result += (", (" + attributes.paths.get(i).get(j - 3) + ")");
                }

                System.out.format(pathAlignment, pathN, result);
                pathN = "";
                pathAlignment = "│ %-25s %-54s │%n";
            }
        }
        System.out.format("└──────────────────────────────────────────────────────────────────────────────────┘%n");
    }

}
