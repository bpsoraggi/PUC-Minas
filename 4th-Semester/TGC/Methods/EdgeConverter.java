import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EdgeConverter {

    public static void main(String[] args) {
        String arquivo = "kRegular1000.txt";

        try {
            ArrayList<String> arestasOriginais = lerArestas(arquivo);

            ArrayList<String> arestasNovas = converter(arestasOriginais);

            writearestaList(arquivo, arestasNovas);

            System.out.println("Deu bom");

        } catch (IOException e) {
            System.err.println("Deu ruim: " + e.getMessage());
        }
    }

    private static ArrayList<String> lerArestas(String arquivo) throws IOException {
        ArrayList<String> arestas = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(arquivo))) {
            while (scanner.hasNextLine()) {
                arestas.add(scanner.nextLine());
            }
        }
        return arestas;
    }

    private static ArrayList<String> converter(ArrayList<String> arestasOriginais) {
        ArrayList<String> arestasNovas = new ArrayList<>();
        for (String aresta : arestasOriginais) {
            String[] vertices = aresta.split(" ");
            int u = Integer.parseInt(vertices[0]) + 1;
            int v = Integer.parseInt(vertices[1]) + 1;
            arestasNovas.add(u + " " + v);
        }
        return arestasNovas;
    }

    private static void writearestaList(String arquivo, ArrayList<String> arestas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (String aresta : arestas) {
                writer.write(aresta);
                writer.newLine();
            }
        }
    }
}
