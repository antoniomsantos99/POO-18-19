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


public class Login {

    private static Actor a = null;

    /**
     * Metodo estatico que valida username ou email e password
     * se estiverem corretos dá login, se não pode tentar novamente
     * tem a opçao de retroceder escrevendo -1
     */

    public static boolean loginCliente(){
        String email;
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o seu email (-1 para cancelar):");
        email = sc.nextLine();
        if(email.equals("-1")){return false;}
        if(EstadoAtual.verificaCliente(email)){
            System.out.println("Introduza a sua password:");
            password = sc.nextLine();
            if(EstadoAtual.tryLoginCliente(email,password)){
                System.out.println("Login efetuado com sucesso");
                a = EstadoAtual.getCliente(email);
                return true;
            }else{System.out.println("password incorreta");loginCliente();}

        }else{
            System.out.println("Nome de utilizador ou email não existe");
            loginCliente();
        }
        return false;
    }

    /**
     * Metodo estatico que valida username ou email e password
     * se estiverem corretos dá login, se não pode tentar novamente
     * tem a opçao de retroceder escrevendo -1
     */

    public static boolean loginProprietario(){
        String email;
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza nome de utilizador ou email (-1 para cancelar):");
        email = sc.nextLine();
        if(email.equals("-1")){return false;}
        if(EstadoAtual.verificaProprietario(email)){
            System.out.println("Introduza a sua password:");
            password = sc.nextLine();
            if(EstadoAtual.tryLoginProprietario(email,password)){
                System.out.println("Login efetuado com sucesso");
                a = EstadoAtual.getProprietario(email);
                return true;
            }else{System.out.println("password incorreta");loginProprietario();}

        }else{
            System.out.println("Nome de utilizador ou email não existe");
            loginProprietario();
        }
        return false;
    }

    /**
     * Metodo estatico para verificar se o utilizador pretende
     * iniciar sessao como cliente ou proprietario
     * opção de retroceder 3
     *
     */

    public static void comecarLogin(){
        int opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a opção que pretende selecionar:");
        System.out.println("1- Login como Cliente");
        System.out.println("2- Login como Proprietario");
        System.out.println("3- Retroceder");
        opcao = sc.nextInt();
        switch (opcao){
            case 1 :
                if(loginCliente()){Menu.menuCliente((Cliente) a);}
                comecarLogin();
                break;
            case 2 :
                if(loginProprietario()){Menu.menuProprietario((Proprietario) a);}
                comecarLogin();
                break;
            case 3 :
                break;
            default :
                System.out.println("Erro: Introduza um valor válido.\n");
                comecarLogin();
                break;
        }
    }

}
