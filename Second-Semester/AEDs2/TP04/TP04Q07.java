public class TP04Q07 {
    public static void printaMultiplicacaoMatriz(int[][] M1, int[][] M2) {
        for (int ln = 0; ln < M1.length; ln++) {
            for (int col = 0; col < M1.length; col++) {
                int soma = 0;
                for (int i = 0; i < M1.length; i++) {
                    soma += M1[ln][i] * M2[i][col];
                }
                System.out.print(soma + " ");
            }
            System.out.println();
        }
    }

    public static void printaSomaMatriz(int[][] M1, int[][] M2) {
        for (int ln = 0; ln < M1.length; ln++) {
            for (int col = 0; col < M1.length; col++) {
                System.out.print(M1[ln][col] + M2[ln][col] + " ");
            }
            System.out.println();
        }
    }

    public static void printaDiagonalSecundaria(int[][] M) {
        for (int ln = 0; ln < M.length; ln++) {
            for (int col = 0; col < M.length; col++) {
                if (ln + col == M.length - 1) {
                    System.out.print(M[ln][col] + " ");
                }
            }
        }
        System.out.println();
    }

    public static void printaDiagonalPrimaria(int[][] M) {
        for (int ln = 0; ln < M.length; ln++) {
            System.out.print(M[ln][ln] + " ");
        }
        System.out.println();
    }

    public static void preencheMatriz(int[][] M) {
        for (int ln = 0; ln < M.length; ln++) {
            for (int col = 0; col < M.length; col++) {
                M[ln][col] = MyIO.readInt();
            }
        }
    }

    public static void solve() {
        int l1 = MyIO.readInt();
        int c1 = MyIO.readInt();
        int[][] M1 = new int[l1][c1];
        preencheMatriz(M1);
        int l2 = MyIO.readInt();
        int c2 = MyIO.readInt();
        int[][] M2 = new int[l2][c2];
        preencheMatriz(M2);
        printaDiagonalPrimaria(M1);
        printaDiagonalSecundaria(M1);
        printaSomaMatriz(M1, M2);
        printaMultiplicacaoMatriz(M1, M2);
    }

    public static void main(String[] args) {
        int N = MyIO.readInt();
        for (int i = 0; i < N; i++) {
            solve();
        }
    }
}
