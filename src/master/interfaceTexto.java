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

public class interfaceTexto {
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
                //System.out.println(EstadoAtual.getNrActores());
                novoEstado();
                break;
            case 2 :
                ValidarAcesso.comecarValidarAcesso();
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
                System.out.println("2 selecionado, WIP");
                break;
            case 3 :
                System.out.println("3 selecionado, WIP");
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
