package master;

import java.util.Scanner;

/**
 * Classe da interface de texto
 *
 * metodos especialmente estaticos
 *
 * @author Pedro Oliveira
 * @version 28/04/2019
 */

public class IniciarEstado {
    /**
     * Metodo estatico que Inicializa um novo estado
     * começa por preparar novos registos ou logins
     * de inicio nao tem registos portanto nao e possivel
     * fazer log in
     */
    public static void novoEstado(){
        int opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a opção que pretende selecionar:");
        System.out.println("1- Fazer Registo");
        System.out.println("2- Fazer Login");
        System.out.println("3- Retroceder");
        opcao = sc.nextInt();
        switch (opcao){
            case 1 :
                Registo.comecarRegisto();
                novoEstado();
                break;
            case 2 :
                Login.comecarLogin();
                novoEstado();
                break;
            case 3 :
                break;
            default :
                System.out.println("Erro: Introduza um valor válido.\n");
                novoEstado();
                break;
        }
    }
    public static void carregarEstado(){
        int opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a opção que pretende selecionar:");
        System.out.println("1- Carregar estado gravado anteriormente (nome fixo)");
        System.out.println("2- Carregar estado com nome variavel");
        System.out.println("3- Retroceder");
        opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                fileIO.readFile("state");
                break;
            case 2:
                System.out.println("Introduza o nome do ficheiro que pretende carregar (-1 para cancelar)");
                String nome;
                nome = sc.nextLine();
                if(nome.equals("-1")){return;}
                fileIO.writeFile(nome);
                break;
            case 3:
                break;
            default:
                System.out.println("Erro: Introduza um valor válido.\n");
                carregarEstado();
                break;
        }
    }
    public static void gravarEstado(){
        int opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a opção que pretende selecionar:");
        System.out.println("1- Gravar estado rapido (nome fixo, apaga o escrito desta forma anteriormente)");
        System.out.println("2- Gravar estado com nome variavel");
        System.out.println("3- Retroceder");
        opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                fileIO.writeFile("state");
                break;
            case 2:
                Scanner scOp2 = new Scanner(System.in);
                System.out.println("Introduza o nome com que pretende gravar (-1 para cancelar)");
                String nome;
                nome = scOp2.nextLine();
                if(nome.equals("-1")){return;}
                fileIO.writeFile(nome);
                break;
            case 3:
                break;
            default:
                System.out.println("Erro: Introduza um valor válido.\n");
                gravarEstado();
                break;
        }
    }
    public static void start() {
        int opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a opção que pretende selecionar:");
        System.out.println("1- Novo Estado (começar a limpo)");
        System.out.println("2- Carregar Estado");
        System.out.println("3- Gravar Estado Atual");
        System.out.println("4- Fechar Programa");
        opcao = sc.nextInt();
        switch (opcao){
            case 1 :
                novoEstado();
                start();
                break;
            case 2 :
                carregarEstado();
                start();
                break;
            case 3 :
                gravarEstado();
                start();
                break;
            case 4 :
                System.out.println("Programa terminado.");
                break;
            default :
                System.out.println("Erro: Introduza um valor válido.\n");
                start();
                break;
        }
    }
}
