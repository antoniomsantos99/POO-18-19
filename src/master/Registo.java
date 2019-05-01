package master;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe de realizar registos de clientes e proprietarios
 *
 * metodos estaticos:
 * ComecarRegistoCliente()
 * ComecarRegistoProprietario()
 *
 * @author Pedro Oliveira
 * @version 28/04/2019
 */

public class Registo {
    /**
     * Metodo estatico que prepara tudo para o registo de um novo cliente
     * @return Cliente novo
     */
    public static Cliente comecarRegistoCliente(){
        String email;
        String nome;
        String password;
        String morada;
        String dataNascimento;
        double locX, locY;
        Pair<Double,Double> localizacao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza as informações seguites para se registar como cliente:");
        System.out.println("Introduza o seu e-mail:");
        email = sc.nextLine();
        if(EstadoAtual.verificaCliente(email)){
            System.out.println("Email já existe.");
            return null;
        }
        System.out.println("Introduza o seu nome de utilizador:");
        nome = sc.nextLine();
        System.out.println("Introduza a sua password:");
        password = sc.nextLine();
        System.out.println("Introduza a sua morada:");
        morada = sc.nextLine();
        System.out.println("Introduza a sua data de Nascimento (DD/MM/YYYY):");
        dataNascimento = sc.nextLine();
        System.out.println("Introduza a sua localizacao atual: (Primeiro X, depois Y)");
        locX = sc.nextDouble();
        locY = sc.nextDouble();
        localizacao = new Pair(locX,locY);
        Cliente c = new Cliente(email,nome,password,morada,dataNascimento,localizacao,new ArrayList<>());
        System.out.println("Cliente " + nome + " criado com sucesso!");
        return c;
    }

    /**
     * Metodo estatico que prepara tudo para o registo de um novo proprietario
     * @return Cliente novo
     */
    public static Proprietario comecarRegistoProprietario(){
        String email;
        String nome;
        String password;
        String morada;
        String dataNascimento;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza as informações seguites para se registar como Proprietario:");
        System.out.println("Introduza o seu e-mail:");
        email = sc.nextLine();
        if(EstadoAtual.verificaProprietario(email)){
            System.out.println("Email já existe.");
            return null;
        }
        System.out.println("Introduza o seu nome de utilizador:");
        nome = sc.nextLine();
        System.out.println("Introduza a sua password:");
        password = sc.nextLine();
        System.out.println("Introduza a sua morada:");
        morada = sc.nextLine();
        System.out.println("Introduza a sua data de Nascimento (DD/MM/YYYY):");
        dataNascimento = sc.nextLine();
        Proprietario p = new Proprietario(new ArrayList<>(),email,nome,password,morada,dataNascimento,0,new ArrayList<>());
        System.out.println("Proprietario " + nome + " criado com sucesso!");
        return p;

    }

    /**
     * Metodo estatico que prepara qual dos registos realizar
     *
     */
    public static void comecarRegisto(){
        int opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a opção que pretende selecionar:");
        System.out.println("1- Registar como Cliente");
        System.out.println("2- Registar como Proprietario");
        System.out.println("3- Retroceder");
        opcao = sc.nextInt();
        switch (opcao){
            case 1 :
                Cliente c = comecarRegistoCliente();
                if (c!=null){EstadoAtual.addCliente(c);}
                comecarRegisto();
                break;

            case 2 :
                Proprietario p = comecarRegistoProprietario();
                if(p!=null){EstadoAtual.addProprietario(p);}
                comecarRegisto();
                break;

            case 3 :
                break;
            default :
                System.out.println("Erro: Introduza um valor válido.\n");
                comecarRegisto();
                break;
        }
    }

}