package master;

import java.util.Scanner;

public class Menu{
    public static void debug(Actor a){
        System.out.println(a.toString());
        System.out.println(EstadoAtual.debugString());
    }
    public static void menuCliente(Cliente c){
        int opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a opção que pretende selecionar:");
        System.out.println("0- Debug:");
        System.out.println("1- Solicitar o aluguer de um carro mais próximo das suas coordenadas");
        System.out.println("2- Solicitar o aluguer do carro mais barato");
        System.out.println("3- Solicitar o aluguer do carro mais barato dentro de uma distância que esteja disposto a percorrer a pé");
        System.out.println("4- Solicitar o aluguer de um carro especifico");
        System.out.println("5- Solicitar o aluguer de um carro com a autonomia desejada");
        System.out.println("6- Retroceder");
        opcao = sc.nextInt();
        switch (opcao){
            case 0 :
                debug(c);
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4 :
                break;
            case 5 :
                break;
            case 6 :
                break;
            default :
                System.out.println("Erro: Introduza um valor válido.");
                menuCliente(c);
                break;
        }
    }
    public static void menuProprietario(Proprietario p){
        int opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a opção que pretende selecionar:");
        System.out.println("0- Debug:");
        System.out.println("1- Adicionar novo carro");
        System.out.println("2- Sinalizar que um carro esta disponivel para alugar");
        System.out.println("3- Abastecer veiculo");
        System.out.println("4- Alterar preço por km");
        System.out.println("5- Registar custo de viagem");
        System.out.println("6- Verificar custos de viagens registadas");
        System.out.println("7- Retroceder");
        opcao = sc.nextInt();
        switch (opcao){
            case 0 :
                debug(p);
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4 :
                break;
            case 5 :
                break;
            case 6 :
                break;
            case 7 :
                break;
            default :
                System.out.println("Erro: Introduza um valor válido.");
                menuProprietario(p);
                break;
        }
    }

}