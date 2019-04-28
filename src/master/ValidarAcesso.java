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


public class ValidarAcesso {

    /**
     * Metodo estatico que valida username ou email e password
     * se estiverem corretos dá login, se não pode tentar novamente
     * tem a opçao de retroceder escrevendo -1
     */

    public static void validarAcessoCliente(){
        String userOuEmail;
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza nome de utilizador ou email (-1 para cancelar):");
        userOuEmail = sc.nextLine();
        if(userOuEmail.equals("-1")){return;}
        if(EstadoAtual.verificaCliente(userOuEmail)){
            System.out.println("Introduza a sua password:");
            password = sc.nextLine();
            if(EstadoAtual.tryLoginCliente(userOuEmail,password)){
                System.out.println("Login efetuado com sucesso");
                //iniciar as features de ter feito o login
            }else{System.out.println("password incorreta");validarAcessoCliente();}

        }else{
            System.out.println("Nome de utilizador ou email não existe");
            validarAcessoCliente();
        }
    }

    /**
     * Metodo estatico que valida username ou email e password
     * se estiverem corretos dá login, se não pode tentar novamente
     * tem a opçao de retroceder escrevendo -1
     */

    public static void validarAcessoProprietario(){
        String userOuEmail;
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza nome de utilizador ou email (-1 para cancelar):");
        userOuEmail = sc.nextLine();
        if(userOuEmail.equals("-1")){return;}
        if(EstadoAtual.verificaProprietario(userOuEmail)){
            System.out.println("Introduza a sua password:");
            password = sc.nextLine();
            if(EstadoAtual.tryLoginProprietario(userOuEmail,password)){
                System.out.println("Login efetuado com sucesso");
                //inciciar as features de ter feito o login
            }else{System.out.println("password incorreta");validarAcessoProprietario();}

        }else{
            System.out.println("Nome de utilizador ou email não existe");
            validarAcessoProprietario();
        }
    }

    /**
     * Metodo estatico para verificar se o utilizador pretende
     * iniciar sessao como cliente ou proprietario
     * opção de retroceder 3
     *
     */

    public static void comecarValidarAcesso(){
        int opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a opção que pretende selecionar:");
        System.out.println("1- Login como Cliente");
        System.out.println("2- Login como Proprietario");
        System.out.println("3- Retroceder");
        opcao = sc.nextInt();
        switch (opcao){
            case 1 :
                validarAcessoCliente();
                comecarValidarAcesso();
                break;

            case 2 :
                validarAcessoProprietario();
                comecarValidarAcesso();
                break;

            case 3 :
                break;
            default :
                System.out.println("Erro: Introduza um valor válido.\n");
                comecarValidarAcesso();
                break;
        }
    }

}
