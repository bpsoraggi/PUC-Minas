package Semester4.TGC.Methods;

import java.io.File;
import java.io.RandomAccessFile;

public class GraphUtils {

    public static void AddFirstLineTxt(File file) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            int origin = raf.readInt();
            int destination = raf.readInt();

            String content = origin + " " + destination + "\n";

            byte[] restOfFile = new byte[(int) raf.length()];
            raf.readFully(restOfFile);

            raf.seek(0);
            raf.writeBytes(content);

            raf.write(restOfFile);
            raf.close();
        } catch (Exception e) {
            System.out.println("Error when adding first line of text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void RemoveFirstLineTxt(File file) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

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
            System.out.println("Error when removing first line of text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void Sort(Graph graph, boolean forward) {
        if (forward) {
            QuickSort(graph.parents, graph.children, 0, graph.GetM() - 1);
        } else {
            QuickSort(graph.children, graph.parents, 0, graph.GetM() - 1);
        }
    }

    private static void QuickSort(int[] arr1, int[] arr2, int left, int right) {
        if (left < right) {
            int pos = Partition(arr1, arr2, left, right);

            QuickSort(arr1, arr2, left, pos - 1);
            QuickSort(arr1, arr2, pos + 1, right);
        }
    }

    private static int Partition(int[] arr1, int[] arr2, int left, int right) {
        int pivot = arr1[right];
        int i = left - 1;
        int aux1, aux2;

        for (int j = left; j < right; j++) {
            if (arr1[j] < pivot) {
                i++;

                // Swap elements in arr1
                aux1 = arr1[i];
                arr1[i] = arr1[j];
                arr1[j] = aux1;

                // Swap corresponding elements in arr2
                aux2 = arr2[i];
                arr2[i] = arr2[j];
                arr2[j] = aux2;
            }
        }

        // Swap pivot element to its final position
        aux1 = arr1[i + 1];
        arr1[i + 1] = arr1[right];
        arr1[right] = aux1;

        aux2 = arr2[i + 1];
        arr2[i + 1] = arr2[right];
        arr2[right] = aux2;

        return i + 1;
    }

}
