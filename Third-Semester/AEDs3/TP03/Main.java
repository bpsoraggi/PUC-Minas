import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Netflix netflix = new Netflix();
        CRUD crud = new CRUD();
        listaInvertida lista = new listaInvertida();
        int huffmanVersao = 1;
        Scanner sc = new Scanner(System.in);

        String input = "";
        int opcao = 0; // opcao do menu
        boolean sair = false; // controle do menu

        crud.convertToBin(netflix); // converte o arquivo csv para binario

        RandomAccessFile raf = new RandomAccessFile("banco.bin", "rw"); // Cria o arquivo
        Huffman huffman = new Huffman(huffmanVersao, Core.stringify(raf));

        if (raf.length() == 0) raf.writeInt(0); // Se o arquivo estiver vazio escreve 0 no inicio do arquivo para indicar o ultimo id utilizado
        raf.seek(0); // Volta para o inicio do arquivo

        while (!sair) {
            System.out.println("_____________MENU_____________");
            System.out.println("|                             |");
            System.out.println("| 0 - Sair                    |");
            System.out.println("| 1 - Cadastrar filme/serie   |");
            System.out.println("| 2 - Ler registro            |");
            System.out.println("| 3 - Atualizar registro      |");
            System.out.println("| 4 - Excluir registro        |");
            System.out.println("| 5 - Lista Invertida         |");
            System.out.println("| 6 - Arvore B                |");
            System.out.println("| 7 - Hash Extensivel         |");
            System.out.println("| 8 - Compressao de Arquivo   |");
            System.out.println("| 9 - Casamento de Padroes    |");
            System.out.println("|_____________________________|");
            System.out.print("-> ");;

            do {
                try {
                    input = sc.nextLine();
                    opcao = Integer.parseInt(input);
                    if (opcao < 0 || opcao > 9) {
                        System.out.println("Opcao invalida!");
                        System.out.print("->");
                    }
                } catch (Exception e) { // se o usuario digitar algo que não seja um numero inteiro
                    System.out.println("Digite um numero!");
                    System.out.print("-> ");;
                    System.out.println("erro menu: " + e.getMessage());
                    e.printStackTrace();
                    sc.nextLine();
                    break;
                }
            } while (opcao < 0 || opcao > 9); // enquanto a opcao for invalida

            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    System.out.println();
                    raf.close();
                    sair = true;
                break;
                case 1: // cadastrar registro
                    System.out.println("_____________CADASTRAR REGISTRO_____________");

                    // Type
                    System.out.println("Digite o tipo: ");
                    System.out.println("1 - Filme");
                    System.out.println("2 - Serie");
                    System.out.print("-> ");;

                    do {
                        try {
                            input = sc.nextLine();
                            opcao = Integer.parseInt(input);
                            if (opcao < 1 || opcao > 2) {
                                System.out.println("Opcao invalida!");
                                System.out.print("-> ");
                            }
                        } catch (Exception e) { // se o usuario digitar algo que não seja um numero inteiro
                            System.out.println("Digite um numero!");
                            System.out.print("-> ");
                            sc.nextLine();
                            break;
                        }
                    } while (opcao < 1 || opcao > 2); // enquanto a opcao for invalida

                    if (opcao == 1) {
                        netflix.setType("Movies");
                    } else {
                        netflix.setType("Series");
                    }

                    // Title
                    System.out.println("Digite o titulo: ");
                    System.out.print("-> ");
                    netflix.setTitle(sc.nextLine());

                    // Director
                    System.out.println("Digite a quantidade de diretores: ");
                    System.out.print("-> ");
                    input = sc.nextLine();
                    netflix.setQtdDirectors(Integer.parseInt(input));
                    if (netflix.getQtdDirectors() > 0) {
                        String[] directors = new String[netflix.getQtdDirectors()];
                        for (int i = 0; i < netflix.getQtdDirectors(); i++) {
                            System.out.println("Nome do diretor" + (i + 1) + ": ");
                            System.out.print("-> ");
                            directors[i] = sc.nextLine();
                        }
                        netflix.setDirector(directors);
                    }

                    // Cast
                    System.out.println("Digite a quantidade de atores: ");
                    System.out.print("-> ");
                    input = sc.nextLine();
                    netflix.setQtdCast(Integer.parseInt(input));
                    if (netflix.getQtdCast() > 0) {
                        String[] actors = new String[netflix.getQtdCast()];
                        for (int i = 0; i < netflix.getQtdCast(); i++) {
                            System.out.println("Nome do ator" + (i + 1) + ": ");
                            System.out.print("-> ");
                            actors[i] = sc.nextLine();
                        }
                        netflix.setCast(actors);
                    }

                    // Country
                    System.out.println("Digite a quantidade de paises: ");
                    System.out.print("-> ");
                    input = sc.nextLine();
                    netflix.setQtdCountries(Integer.parseInt(input));
                    if (netflix.getQtdCountries() > 0) {
                        String[] countries = new String[netflix.getQtdCountries()];
                        for (int i = 0; i < netflix.getQtdCountries(); i++) {
                            System.out.println("Nome do pais" + (i + 1) + ": ");
                            System.out.print("-> ");
                            countries[i] = sc.nextLine();
                        }
                        netflix.setCountry(countries);
                    }

                    // Date Added
                    System.out.println("Digite a data de adicao (aaaa-mm-dd): ");
                    System.out.print("-> ");
                    netflix.setDateString(sc.nextLine());
                    netflix.setDate_added(netflix.getDateString());

                    // Release Year
                    System.out.println("Digite o ano de lancamento: ");
                    System.out.print("-> ");
                    input = sc.nextLine();
                    netflix.setRelease_year(Integer.parseInt(input));

                    // Rating
                    System.out.println("Digite a classificacao: ");
                    System.out.print("-> ");
                    netflix.setRating(sc.nextLine());

                    // Duration
                    System.out.println("Digite a duracao: ");
                    System.out.print("-> ");
                    netflix.setDuration(sc.nextLine());

                    netflix.setShow_id(crud.getLastId() + 1); // incrementa o ultimo id utilizado e armazena no objeto

                    if (crud.create(netflix)) {
                        System.out.println();
                        System.out.println("Registro criado com sucesso!");
                        System.out.println("ID: " + netflix.getShow_id()); // mostra o id do novo registro
                        System.out.println("____________________________________________"); // formatacao
                        System.out.println();
                        opcao = 0; // para voltar ao menu
                    } else {
                        System.out.println("Erro ao criar registro!");
                    }

                break;
                case 2: // ler registro
                    System.out.println("_____________LER REGISTRO_____________");

                    System.out.println("Digite o id do registro: ");
                    System.out.print("-> ");
                    input = sc.nextLine();
                    int id = Integer.parseInt(input);

                    // Type
                    System.out.println("Como deseja pesquisar? ");
                    System.out.println("1 - Hash");
                    System.out.println("2 - Arvore B");
                    System.out.print("-> ");;

                    do {
                        try {
                            input = sc.nextLine();
                            opcao = Integer.parseInt(input);
                            if (opcao < 1 || opcao > 2) {
                                System.out.println("Opcao invalida!");
                                System.out.print("-> ");
                            }
                        } catch (Exception e) { // se o usuario digitar algo que não seja um numero inteiro
                            System.out.println("Digite um numero!");
                            System.out.print("-> ");
                            sc.nextLine();
                            break;
                        }
                    } while (opcao < 1 || opcao > 2); // enquanto a opcao for invalida

                    if (opcao == 1) {
                        // TODO - Hash
                    } else {
                        // TODO - Arvore B
                    }

                    netflix = crud.read(id, opcao);

                    if (netflix != null) {
                        System.out.println();
                        System.out.println("Registro encontrado!");
                        System.out.println("______________________________________"); // formatacao
                        System.out.println();
                        Core.print(netflix); // imprime o registro
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("Registro nao encontrado!");
                        System.out.println("______________________________________"); // formatacao
                        System.out.println();
                    }

                break;
                case 3: // atualizar registro
                System.out.println("_____________ATUALIZAR REGISTRO_____________");
                System.out.println("Digite o id do registro: ");
                System.out.print("-> ");
                input = sc.nextLine();
                int id2 = Integer.parseInt(input);

                netflix = crud.read(id2, 1);

                if (netflix == null) {
                    System.out.println();
                    System.out.println("Registro nao encontrado!");
                    System.out.println("____________________________________________"); // formatacao
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("Registro encontrado!");
                    System.out.println();
                    Core.print(netflix);

                    System.out.println("_____________________________");
                    System.out.println("| O que deseja atualizar?   |");
                    System.out.println("| 0 - Cancelar              |");
                    System.out.println("| 1 - Tipo                  |");
                    System.out.println("| 2 - Titulo                |");
                    System.out.println("| 3 - Diretores             |");
                    System.out.println("| 4 - Elenco                |");
                    System.out.println("| 5 - Paises                |");
                    System.out.println("| 6 - Data de adicao        |");
                    System.out.println("| 7 - Ano de lancamento     |");
                    System.out.println("| 8 - Classificacao         |");
                    System.out.println("| 9 - Duracao               |");
                    System.out.println("|___________________________|");
                    System.out.print("-> ");

                    do {
                        try {
                            input = sc.nextLine();
                            opcao = Integer.parseInt(input);
                            if (opcao < 0 || opcao > 9) {
                                System.out.println("Opcao invalida!");
                                System.out.print("-> ");
                            }
                        } catch (Exception e) { // se o usuario digitar algo que não seja um numero inteiro
                            System.out.println("Digite um numero!");
                            System.out.print("-> ");
                            sc.nextLine();
                            break;
                        }
                    } while (opcao < 0 || opcao > 9); // enquanto a opcao for invalida

                    switch (opcao) { // atualiza o registro
                        case 0:
                            System.out.println();
                            System.out.println("Operacao cancelada!");
                            System.out.println("____________________________________________"); // formatacao
                            System.out.println();
                        break;
                        case 1: // type
                            System.out.println();
                            System.out.println("Digite o novo tipo: ");
                            System.out.println("1 - Filme");
                            System.out.println("2 - Serie");
                            System.out.print("-> ");

                            do {
                                try {
                                    input = sc.nextLine();
                                    opcao = Integer.parseInt(input);
                                    if (opcao < 1 || opcao > 2) {
                                        System.out.println("Opcao invalida!");
                                        System.out.print("-> ");
                                    }
                                } catch (Exception e) { // se o usuario digitar algo que não seja um numero inteiro
                                    System.out.println("Digite um numero!");
                                    System.out.print("-> ");
                                    sc.nextLine();
                                    break;
                                }
                            } while (opcao < 1 || opcao > 2); // enquanto a opcao for invalida

                            if (opcao == 1) {
                                netflix.setType("Movies");
                            } else {
                                netflix.setType("Series");
                            }
                        break;
                        case 2: // title
                            System.out.println();
                            System.out.println("Digite o novo titulo: ");
                            System.out.print("-> ");
                            netflix.setTitle(sc.nextLine());        
                        break;
                        case 3: // directors
                            System.out.println();
                            System.out.println("Digite a nova quantidade de diretores: ");
                            System.out.print("-> ");
                            input = sc.nextLine();
                            netflix.setQtdDirectors(Integer.parseInt(input));
                            String[] directors2 = new String[netflix.getQtdDirectors()];
                            for (int i = 0; i < netflix.getQtdDirectors(); i++) {
                                System.out.println("Novo nome do diretor" + (i + 1) + ": ");
                                System.out.print("-> ");
                                directors2[i] = sc.nextLine();
                            }
                            netflix.setDirector(directors2);
                        break;
                        case 4: // cast
                            System.out.println();
                            System.out.println("Digite a nova quantidade de atores: ");
                            System.out.print("-> ");
                            input = sc.nextLine();
                            netflix.setQtdCast(Integer.parseInt(input));
                            String[] actors2 = new String[netflix.getQtdCast()];
                            for (int i = 0; i < netflix.getQtdCast(); i++) {
                                System.out.println("Novo nome do ator" + (i + 1) + ": ");
                                System.out.print("-> ");
                                actors2[i] = sc.nextLine();
                            }
                            netflix.setCast(actors2);
                        break;
                        case 5: // country
                            System.out.println();
                            System.out.println("Digite a nova quantidade de paises: ");
                            System.out.print("-> ");
                            input = sc.nextLine();
                            netflix.setQtdCountries(Integer.parseInt(input));
                            String[] countries2 = new String[netflix.getQtdCountries()];
                            for (int i = 0; i < netflix.getQtdCountries(); i++) {
                                System.out.println("Novo nome do pais" + (i + 1) + ": ");
                                System.out.print("-> ");
                                countries2[i] = sc.nextLine();
                            }
                            netflix.setCountry(countries2);
                        break;
                        case 6: // date_added
                            System.out.println();
                            System.out.println("Digite a nova data de adicao (aaaa-mm-dd): ");
                            System.out.print("-> ");
                            netflix.setDateString(sc.nextLine());
                            netflix.setDate_added(netflix.getDateString());
                        break;
                        case 7: // release_year
                            System.out.println();
                            System.out.println("Digite o novo ano de lancamento: ");
                            System.out.print("-> ");
                            input = sc.nextLine();
                            netflix.setRelease_year(Integer.parseInt(input));
                        break;
                        case 8: // rating
                            System.out.println();
                            System.out.println("Digite a nova classificacao: ");
                            System.out.print("-> ");
                            netflix.setRating(sc.nextLine());
                        break;
                        case 9: // duration
                            System.out.println();
                            System.out.println("Digite a nova duracao: ");
                            System.out.print("-> ");
                            netflix.setDuration(sc.nextLine());
                        break;
                    }

                    if (opcao != 0) { // se o usuario nao quiser voltar
                        if (crud.update(netflix)) {
                            System.out.println();
                            System.out.println("Registro atualizado com sucesso!");
                            System.out.println("____________________________________________"); // formatacao
                            System.out.println();
                            System.out.println("-------------REGISTRO ATUALIZADO-------------");
                            Core.print(netflix);
                        } else {
                            System.out.println();
                            System.out.println("Erro ao atualizar registro!");
                            System.out.println("____________________________________________"); // formatacao
                            System.out.println();
                        }
                    }
                }
                break;
                case 4: // excluir registro
                    System.out.println("_____________EXCLUIR REGISTRO_____________");
                    System.out.println("Digite o id do registro: ");
                    System.out.print("-> ");
                    input = sc.nextLine();
                    int id3 = Integer.parseInt(input);

                    netflix = crud.read(id3, 1);

                    if (netflix == null) {
                        System.out.println();
                        System.out.println("Registro nao encontrado!");
                        System.out.println("__________________________________________"); // formatacao
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("Registro encontrado!");
                        System.out.println("__________________________________________"); // formatacao
                        System.out.println();
                        Core.print(netflix); // imprime o registro encontrado

                        System.out.println("Deseja realmente excluir esse registro?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Nao");
                        System.out.print("-> ");

                        do {
                            try {
                                input = sc.nextLine();
                                opcao = Integer.parseInt(input);
                                if (opcao < 1 || opcao > 2) {
                                    System.out.println("Opcao invalida!");
                                    System.out.print("-> ");
                                }
                            } catch (Exception e) { // se o usuario digitar algo que não seja um numero inteiro
                                System.out.println("Digite um numero!");
                                System.out.print("-> ");
                                sc.nextLine();
                                break;
                            }
                        } while (opcao < 1 || opcao > 2); // enquanto a opcao for invalida

                        if (opcao == 1) {
                            if (crud.delete(netflix)) {
                                System.out.println();
                                System.out.println("Registro excluido com sucesso!");
                                System.out.println("__________________________________________"); // formatacao
                                System.out.println();
                            } else {
                                System.out.println();
                                System.out.println("Erro ao excluir registro!");
                                System.out.println("__________________________________________"); // formatacao
                                System.out.println();
                            }
                        } else {
                            System.out.println("Operacao cancelada!");
                            System.out.println("__________________________________________"); // formatacao
                            System.out.println();
                        }
                    }
                break;
                case 5: 
                    System.out.println("Escolha o tipo de lista invertida:");
                    System.out.println("1) Pelo tipo (Movies/Series)");
                    System.out.println("2) Pelo ano de lançamento");

                    input = sc.nextLine();
                    int tipoLista = Integer.parseInt(input);
                    //int tipoLista = sc.nextInt();

                    if(tipoLista == 1){
                        System.out.print("\n-> Digite o tipo (Movies/Series): ");
                        String tipo = sc.nextLine();
                        if(lista.listarTipo(raf, tipo)) {
                            System.out.println("\n-> Listado com sucesso!");
                        }else {
                            System.out.println("\n-> Erro ao listar!");
                        }

                    }else if(tipoLista == 2){
                        System.out.print("\n-> Digite o ano de lançamento: ");
                        input = sc.nextLine();
                        int lancamento = Integer.parseInt(input);
                        if(lista.listarLancamento(raf, lancamento)) {
                            System.out.println("\n-> Listado com sucesso!");
                        } else {
                            System.out.println("\n-> Erro ao listar!");
                        }
                    }else{
                        System.out.println("2) Pelo ano de lançamento");
                    }
                break;
                case 6: // ArvoreB
                    System.out.println("_____________ARVORE B_____________");
                    System.out.println("_______________________________");
                    System.out.println("| O que deseja saber?         |");
                    System.out.println("| 0 - Cancelar                |");
                    System.out.println("| 1 - Endereco da raiz        |");
                    System.out.println("| 2 - Quantidade de paginas   |");
                    System.out.println("| 3 - Endereco de um registro |");
                    System.out.println("|_____________________________|");
                    System.out.print("-> ");

                    do {
                        try {
                            input = sc.nextLine();
                            opcao = Integer.parseInt(input);
                            if (opcao < 0 || opcao > 3) {
                                System.out.println("Opcao invalida!");
                                System.out.print("-> ");
                            }
                        } catch (Exception e) { // se o usuario digitar algo que não seja um numero inteiro
                            System.out.println("Digite um numero!");
                            System.out.print("-> ");
                            sc.nextLine();
                            break;
                        }
                    } while (opcao < 0 || opcao > 3); // enquanto a opcao for invalida

                    switch (opcao) {
                        case 0:
                            System.out.println();
                            System.out.println("Operacao cancelada!");
                            System.out.println("____________________________________________"); // formatacao
                            System.out.println();
                        break;
                        case 1:
                            System.out.println();
                            System.out.println("Endereco da Raiz: " + crud.getArvoreRaiz());
                            System.out.println("__________________________________________"); // formatacao
                            System.out.println();
                        break;
                        case 2:
                            System.out.println();
                            System.out.println("Quantidade de paginas: " + crud.getArvoreQtdPgs());
                            System.out.println("__________________________________________"); // formatacao
                            System.out.println();
                        break;
                        case 3:
                            System.out.println();
                            System.out.println("Digite o id do registro: ");
                            System.out.print("-> ");
                            input = sc.nextLine();
                            int id4 = Integer.parseInt(input);
                            long endRegistro = crud.findArvore(id4);

                            System.out.println("Registro encontrado!");
                            System.out.println();
                            System.out.println("ID: " + id4);
                            System.out.println("Endereco: " + endRegistro);
                            System.out.println("______________________________________"); // formatacao
                            System.out.println();
                        break;
                    }
                break;
                case 7: // Pesquisar no hash
                    crud.getHashInfo();

                    System.out.println("_____________HASH EXTENSIVEL_____________");
                    System.out.println("_______________________________");
                    System.out.println("| O que deseja saber?         |");
                    System.out.println("| 0 - Cancelar                |");
                    System.out.println("| 1 - Valor de pGlobal        |");
                    System.out.println("| 2 - Quantidade de buckets   |");
                    System.out.println("| 3 - Endereco de um registro |");
                    System.out.println("|_____________________________|");
                    System.out.print("-> ");

                    do {
                        try {
                            input = sc.nextLine();
                            opcao = Integer.parseInt(input);
                            if (opcao < 0 || opcao > 3) {
                                System.out.println("Opcao invalida!");
                                System.out.print("-> ");
                            }
                        } catch (Exception e) { // se o usuario digitar algo que não seja um numero inteiro
                            System.out.println("Digite um numero!");
                            System.out.print("-> ");
                            sc.nextLine();
                            break;
                        }
                    } while (opcao < 0 || opcao > 3); // enquanto a opcao for invalida

                    switch (opcao) {
                        case 0:
                            System.out.println();
                            System.out.println("Operacao cancelada!");
                            System.out.println("____________________________________________"); // formatacao
                            System.out.println();
                        break;
                        case 1:
                            System.out.println();
                            System.out.println("Valor de pGlobal: " + crud.getHashPGlobal());
                            System.out.println("__________________________________________"); // formatacao
                            System.out.println();
                        break;
                        case 2:
                            System.out.println();
                            System.out.println("Quantidade de buckets: " + crud.getHashQtdBuckets());
                            System.out.println("__________________________________________"); // formatacao
                            System.out.println();
                        break;
                        case 3:
                            System.out.println();
                            System.out.println("Digite o id do registro: ");
                            System.out.print("-> ");
                            input = sc.nextLine();
                            int id4 = Integer.parseInt(input);
                            long endRegistro = crud.findHash(id4);

                            System.out.println("Registro encontrado!");
                            System.out.println();
                            System.out.println("ID: " + id4);
                            System.out.println("Endereco: " + endRegistro);
                            System.out.println("______________________________________"); // formatacao
                            System.out.println();
                        break;
                    }

                break;
                case 8:
                            System.out.println("__________COMPRESSAO DE ARQUIVOS__________");
                            System.out.println("_______________________________");
                            System.out.println("| O que deseja fazer?         |");
                            System.out.println("| 0 - Cancelar                |");
                            System.out.println("| 1 - Compactar arquivo       |");
                            System.out.println("| 2 - Descompactar arquivo    |");
                            System.out.println("|_____________________________|");
                            System.out.print("-> ");

                            do {
                                try {
                                    input = sc.nextLine();
                                    opcao = Integer.parseInt(input);
                                    if (opcao < 0 || opcao > 2) {
                                        System.out.println("Opcao invalida!");
                                        System.out.print("-> ");
                                    }
                                } catch (Exception e) { // se o usuario digitar algo que não seja um numero inteiro
                                    System.out.println("Digite um numero!");
                                    System.out.print("-> ");
                                    sc.nextLine();
                                    break;
                                }
                            } while (opcao < 0 || opcao > 2); // enquanto a opcao for invalida

                            switch (opcao) {
                                case 0:
                                    System.out.println();
                                    System.out.println("Operacao cancelada!");
                                    System.out.println("____________________________________________"); // formatacao
                                    System.out.println();
                                break;
                                case 1:
                                    Boolean huffmanCompact = false;
                                    System.out.println("Compactando arquivo...");

                                    // huffman
                                    huffmanCompact = huffman.compactar(raf.length(), huffmanVersao);

                                    // LZW
                                    LZW lzw = new LZW();
                                    long startTime = System.currentTimeMillis(); // tempo inicial
                                    String arqLZW = lzw.arquivoExiste(huffmanVersao);
                                    lzw.comprimir("banco.bin", arqLZW);
                                    long finishTime = System.currentTimeMillis(); // tempo inicial
                                    long tempoTotal = finishTime - startTime;
                                    long tamanhoOriginal = new RandomAccessFile("banco.bin", "r").length();// tamanho do arquivo
                                    long tamanhoFinal = new RandomAccessFile(arqLZW, "r").length(); // tamanho do arquivo compactado
                                    float taxaCompressao = (tamanhoFinal / tamanhoOriginal);
                                    float reducao = 100 * (1 - taxaCompressao); // percentual de reducao
                                    huffmanVersao++;

                                    if (huffmanCompact) {
                                        System.out.println("Arquivo compactado com sucesso!");
                                        System.out.println();
                                        System.out.println("HUFFMAN: ");
                                        System.out.println("Percentual de reducao: " + huffman.getReducao() + "%");
                                        System.out.println("Tempo de execucao: " + huffman.getRuntime() + "ms");
                                        System.out.println();
                                        System.out.println("LZW: ");
                                        System.out.println("Percentual de reducao: " + reducao + "%");
                                        System.out.println("Tempo de execucao: " + tempoTotal + "ms");

                                        if (huffman.getRuntime() < tempoTotal) {
                                            System.out.println("____________________________________________"); // formatacao
                                            System.out.println("Melhor metodo: Huffman");
                                            System.out.println();
                                        } else if (huffman.getRuntime() > tempoTotal) {
                                            System.out.println("____________________________________________"); // formatacao
                                            System.out.println("Melhor metodo: LZW");
                                            System.out.println();
                                        } else {
                                            System.out.println("____________________________________________"); // formatacao
                                            System.out.println("Os metodos possuiram o mesmo tempo de execucao");
                                            System.out.println();
                                        }

                                        System.out.println("____________________________________________"); // formatacao
                                    } else {
                                        System.out.println("Arquivo compactado com sucesso!");
                                        System.out.println();
                                        System.out.println("LZW: ");
                                        System.out.println("Percentual de reducao: " + reducao + "%");
                                        System.out.println("Tempo de execucao: " + tempoTotal + "ms");
                                        System.out.println("____________________________________________"); // formatacao
                                    }
                                break;
                                case 2:
                                    Boolean huffmanDescompact = false;

                                    System.out.println();
                                    System.out.println("Digite a versao do arquivo: ");
                                    System.out.print("-> ");
                                    input = sc.nextLine();
                                    int versaoX = Integer.parseInt(input);
                                    String nomeArquivo = "bancoHuffmanCompressao" + versaoX + ".bin";
                                    String nomeArquivoLZW = "bancoLZWCompressao" + versaoX + ".bin";

                                    System.out.println("Descompactando arquivo...");
                                    // huffman
                                    huffmanDescompact = huffman.descompactar(nomeArquivo);

                                    // LZW
                                    LZW lzw2 = new LZW();
                                    long startTime2 = System.currentTimeMillis(); // tempo inicial
                                    lzw2.descomprimir(nomeArquivoLZW, "banco.bin");
                                    long finishTime2 = System.currentTimeMillis(); // tempo inicial
                                    long tempoTotal2 = finishTime2 - startTime2;

                                    if (huffmanDescompact) {
                                        System.out.println();
                                        System.out.println("____________________________________________"); // formatacao
                                        System.out.println("Arquivo descompactado com sucesso!");
                                        System.out.println();
                                        System.out.println("Tempo de execucao (Huffman): " + huffman.getRuntime() + "ms");
                                        System.out.println("Tempo de execucao (LZW): " + tempoTotal2 + "ms");

                                        if (huffman.getRuntime() < tempoTotal2) {
                                            System.out.println();
                                            System.out.println("Melhor metodo: Huffman");
                                            System.out.println("____________________________________________"); // formatacao
                                            System.out.println();
                                        } else if (huffman.getRuntime() > tempoTotal2) {
                                            System.out.println();
                                            System.out.println("Melhor metodo: LZW");
                                            System.out.println("____________________________________________"); // formatacao
                                            System.out.println();
                                        } else {
                                            System.out.println();
                                            System.out.println("Os metodos possuiram o mesmo tempo de execucao");
                                            System.out.println("____________________________________________"); // formatacao
                                            System.out.println();
                                        }
                                    } else {
                                        System.out.println("Arquivo descompactado com sucesso!");
                                        System.out.println();
                                        System.out.println("Tempo de execucao (LZW): " + tempoTotal2 + "ms");
                                        System.out.println("____________________________________________"); // formatacao
                                    }
                                break;
                            }
                        break;
                        case 9:
                            System.out.println("__________CASAMENTO DE PADROES__________");
                            System.out.println("Digite o padrao que deseja procurar: ");
                            System.out.print("-> ");
                            input = sc.nextLine();
                            
                            //TODO
                            // KMP(input);
                            // BM(input);
                        break;
            }
        }
        
        sc.close();

    }
}
