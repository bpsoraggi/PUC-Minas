import java.util.Scanner;

class BSTclasse {
    class no {
        char item;
        no esq, dir;

        public no(char valor) {
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

    // inserir nó na BST
    void inserir(char item) {
        raiz = inserirRecursivo(raiz, item);
    }

    // inserir recursivo
    no inserirRecursivo(no raiz, char item) {
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

    boolean pesquisar(char item) {
        raiz = pesquisarRecursivo(raiz, item);
        if (raiz != null) {
            return true;
        } else {
            return false;
        }
    }

    // pesquisar recursivo
    no pesquisarRecursivo(no raiz, char item) {
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

class TP05Q09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String comando = "";
        char elemento;
        boolean existe = false;

        // criar objeto BST
        BSTclasse bst = new BSTclasse();

        while (sc.hasNext()) {

            comando = sc.nextLine();

            // System.out.println(comando);

            if (comando.equals("INFIXA")) {
                bst.printIn();
                System.out.println();
            } else if (comando.equals("PREFIXA")) {
                bst.printPre();
                System.out.println();
            } else if (comando.equals("POSFIXA")) {
                bst.printPos();
                System.out.println();
            } else {
                elemento = comando.charAt(comando.length() - 1);
                // System.out.println(elemento);

                if (comando.charAt(0) == 'I') {
                    bst.inserir(elemento);
                } else if (comando.charAt(0) == 'P') {
                    existe = bst.pesquisar(elemento);

                    if (existe) {
                        // System.out.println(existe);
                        System.out.println(elemento + " existe");
                    } else {
                        // System.out.println(existe);
                        System.out.println(elemento + " nao existe");
                    }
                }
            }

        }

        sc.close();
    }
}