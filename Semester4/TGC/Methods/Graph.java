package Semester4.TGC.Methods;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {

    // --------------- Attributes ---------------
    private int n;
    private int m;

    protected int[] parents;
    protected int[] children;

    protected int[] forwardPointer;
    protected int[] reversePointer;

    private Scanner file;

    // --------------- Constructor ---------------
    public Graph(String fileName) {
        int pos = 1;
        try {
            String dir = "Semester4/TGC/Graphs/" + fileName + ".txt";
            this.file = new Scanner(new File(dir));

            this.n = file.nextInt();
            this.m = file.nextInt();

            this.parents = new int[m + 1];
            this.children = new int[m + 1];
            this.forwardPointer = new int[n + 2];
            this.reversePointer = new int[n + 2];

            while (file.hasNextInt()) {
                parents[pos] = file.nextInt();
                children[pos] = file.nextInt();
                pos++;
            }

            this.forwardPointer[n + 1] = m + 1;
            this.reversePointer[n + 1] = m + 1;

        } catch (Exception e) {
            System.out.println();
            System.out.println("Error in constructor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --------------- Getters ---------------

    public int GetN() {
        return n;
    }

    public int GetM() {
        return m;
    }

    public int GetNumberSuccessors(int vertex) {
        return forwardPointer[vertex + 1] - forwardPointer[vertex];
    }

    public int[] GetSucessors(int vertex) {
        int startPos = forwardPointer[vertex];
        int endPos = forwardPointer[vertex + 1];

        int[] sucessors = new int[endPos - startPos];
        for (int i = 0; i < sucessors.length; i++) {
            sucessors[i] = children[startPos + i];
        }

        return sucessors;
    }

    // --------------- Methods ---------------

    public void AddEdge(int parent, int child) {
        for (int i = 1; i <= m; i++) {
            if (parents[i] == parent && children[i] == child) {
                System.out.println("Edge already exists: (" + parent + " -> " + child + ")");
                return;
            }
        }

        if (m == parents.length - 1) {
            int newSize = parents.length * 2;
            parents = Arrays.copyOf(parents, newSize);
            children = Arrays.copyOf(children, newSize);
        }

        m++;
        parents[m] = parent;
        children[m] = child;

        // Update forwardPointer
        for (int j = parent + 1; j < forwardPointer.length; j++) {
            forwardPointer[j]++;
        }
    }

    public void RemoveEdge(int parent, int child) {
        boolean found = false;

        for (int i = 1; i <= m; i++) {
            if (parents[i] == parent && children[i] == child) {
                // move elements to the left
                for (int j = i; j < m; j++) {
                    parents[j] = parents[j + 1];
                    children[j] = children[j + 1];
                }
                m--;

                // Update forwardPointer
                for (int j = parent + 1; j < forwardPointer.length; j++) {
                    forwardPointer[j]--;
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Edge not found: (" + parent + " -> " + child + ")");
        }
    }

    public void InvertEdge(int parent, int child) {
        try {
            int startPos = forwardPointer[parent];
            int endPos = forwardPointer[parent + 1];

            int edgePos = -1;
            for (int i = startPos; i < endPos; i++) {
                if (child == children[i]) {
                    edgePos = i;
                    break;
                }
            }

            if (edgePos != -1) {
                children[edgePos] = children[endPos - 1];
                children[endPos - 1] = parent;

                forwardPointer[parent + 1]--;
                m--;
            } else {
                System.out.println("Edge not found: (" + parent + ", " + child + ")");
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error when inverting edge: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
