package branchForTesting;

public class Main {
    public static void main(String[] args){

        Pessoa Francisco = new Pessoa(19,"Francisco");
        Atleta Pedro = new Atleta(19,"Pedro","Corrida");
        System.out.println(Francisco.getIdade());
        System.out.println(Francisco.getNome());
        System.out.println(Francisco.toString());
        System.out.println(Pedro.getIdade());
        System.out.println(Pedro.getNome());
        System.out.println(Pedro.toString());


    }
}
