package master;

public class Main{
    public static void main(String[] args){
        System.out.println("Start of main.");
        Friend pedro = new Friend();
        pedro.Friend(19,180,10);
        System.out.println("Isto é um teste, o pedro tem " + pedro.idade + " de idade.");
        System.out.println("Isto é um teste, o pedro tem " + pedro.altura + " de altura.");
        System.out.println("Isto é um teste, o pedro tem " + pedro.dinheiro + " de dinheiro.");

        Potato batata = new Potato(5,"Semilha");

        System.out.println("Isto é um teste, a " + batata.nome + " tem de peso: " + batata.peso);

        System.out.println("End of main.");
    }
}
