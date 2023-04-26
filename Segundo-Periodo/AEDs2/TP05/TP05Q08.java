import java.util.Scanner;

class BSTclasse {
    class no {
        int item;
        no esq, dir;

        public no(int valor) {
            item = valor;
            esq = dir = null;
        }
    }

    // nó raiz
    no raiz;

    // construtor para BST --> árvore inicial vazia
    BSTclasse() {
        raiz = null;
    }

    // remover nó da BST
    void removerItem(int item) {
        raiz = removerRecursivo(raiz, item);
    }

    // remover recursivo
    no removerRecursivo(no raiz, int item) {
        // árvore vazia
        if (raiz == null) {
            return raiz;
        }

        // caminhar pela BST
        if (item < raiz.item) { // ir para a esquerda
            raiz.esq = removerRecursivo(raiz.esq, item);
        } else if (item > raiz.item) { // ir para a direita
            raiz.dir = removerRecursivo(raiz.dir, item);
        } else {
            if (raiz.esq == null) { // nó tem apenas um filho
                return raiz.dir;
            } else if (raiz.dir == null) {
                return raiz.esq;
            }

            // nó tem DOIS filhos
            // get sucessor na ordem correta (valor mínimo na subárvore da direita)
            raiz.item = minValor(raiz.dir);

            // remover sucessor
            raiz.dir = removerRecursivo(raiz.dir, raiz.item);
        }
        return raiz;
    }

    int minValor(no raiz) {
        // minvalor inicial = raiz
        int minval = raiz.item;

        while (raiz.esq != null) {
            minval = raiz.esq.item;
            raiz = raiz.esq;
        }
        return minval;
    }

    // inserir nó na BST
    void inserir(int item) {
        raiz = inserirRecursivo(raiz, item);
    }

    // inserir recursivo
    no inserirRecursivo(no raiz, int item) {
        // árvore vazia
        if (raiz == null) {
            raiz = new no(item);
            return raiz;
        }

        // caminhar pela árvore
        if (item < raiz.item) {
            raiz.esq = inserirRecursivo(raiz.esq, item);
        } else if (item > raiz.item) {
            raiz.dir = inserirRecursivo(raiz.dir, item);
        }
        return raiz;
    }

    // imprimir pré
    void printPre() {
        printPreRecursivo(raiz);
    }

    // imprimir pré por recursividade
    void printPreRecursivo(no raiz) {
        if (raiz != null) {
            System.out.print(raiz.item + " ");
            printPreRecursivo(raiz.esq);
            printPreRecursivo(raiz.dir);
        }
    }

    // imprimir in
    void printIn() {
        printInRecursivo(raiz);
    }

    // imprimir por recursividade
    void printInRecursivo(no raiz) {
        if (raiz != null) {
            printInRecursivo(raiz.esq);
            System.out.print(raiz.item + " ");
            printInRecursivo(raiz.dir);
        }
    }

    // imprimir pós
    void printPos() {
        printPosRecursivo(raiz);
    }

    // imprimir por recursividade
    void printPosRecursivo(no raiz) {
        if (raiz != null) {
            printPosRecursivo(raiz.esq);
            printPosRecursivo(raiz.dir);
            System.out.print(raiz.item + " ");
        }
    }

    boolean pesquisar(int item) {
        raiz = pesquisarRecursivo(raiz, item);
        if (raiz != null) {
            return true;
        } else {
            return false;
        }
    }

    // pesquisar recursivo
    no pesquisarRecursivo(no raiz, int item) {
        // caso base: raiz é nulo ou item está na raiz
        if (raiz == null || raiz.item == item) {
            return raiz;
        }
        // valor é menor que a raiz
        if (raiz.item > item) {
            return pesquisarRecursivo(raiz.esq, item);
        } else {
            // valor é maior que a raiz
            return pesquisarRecursivo(raiz.dir, item);
        }
    }
}

class TP05Q08 {
    public static void main(String[] args) {
        int c = 0, n = 0, item = 0;
        Scanner sc = new Scanner(System.in);

        // criar objeto BST
        BSTclasse bst = new BSTclasse();
        /*
         * exemplo da BST
         * .......8
         * .../...... \
         * ..3....... 10
         * ./.. \.... / ..\
         * 1.....6.........14
         * ..../..\......./...\
         * ...4....7.....13
         */

        // System.out.println("case");
        c = sc.nextInt();
        // System.out.println("n");
        n = sc.nextInt();

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < n; j++) {
                item = sc.nextInt();
                bst.inserir(item);
            }

            System.out.println("Case " + c + ":");
            System.out.print("Pré.: ");
            bst.printPre();
            System.out.println();

            System.out.print("In..: ");
            bst.printIn();
            System.out.println();

            System.out.print("Pós.: ");
            bst.printPos();
        }

        sc.close();
    }
}