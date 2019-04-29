package master;

public class Main{
    public static void main(String[] args){
        System.out.println("Start of main.");
        long startTime = System.currentTimeMillis();
        IniciarEstado.start();
        long endTime = System.currentTimeMillis();
        System.out.println("Tempo de exec: " + (endTime - startTime) + " milisegundos");
        System.out.println("End of main.");
    }
}
