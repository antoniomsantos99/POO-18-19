package master;

public class UMCarroJa{

    private static Estado estado;

    private Menu menuPrincipal;

    public static void main(String[] args){
        System.out.println("Start of main.");
        long startTime = System.currentTimeMillis();
        new UMCarroJa().run();
        long endTime = System.currentTimeMillis();
        System.out.println("Tempo de exec: " + (endTime - startTime) + " milisegundos");
        System.out.println("End of main.");
    }

    public UMCarroJa(){
        String[] opcoes = {"Novo Estado","Carregar Estado","Gravar Estado"};
        estado = new Estado();
        this.menuPrincipal = new Menu(opcoes);
    }

    private void run(){
        do{
            menuPrincipal.executa();

            switch (menuPrincipal.getOpcao()){
                case 1:
                    estado = new Estado();
                    break;
                case 2:
                    estado.carregarEstado();
                    System.out.println(estado.debugString());
                    break;
                case 3:
                    estado.gravarEstado();
                    System.out.println(estado.debugString());
                    break;
            }
        }while(menuPrincipal.getOpcao()!=0);
    }

}
