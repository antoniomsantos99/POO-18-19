package master;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UMCarroJa{

    private Estado estado;

    private Menu menuPrincipal;

    private Menu menuLogOuReg;

    public static void main(String[] args){
        System.out.println("Start of main.");
        long startTime = System.currentTimeMillis();
        new UMCarroJa().run();
        long endTime = System.currentTimeMillis();
        System.out.println("Tempo de exec: " + (endTime - startTime) + " milisegundos");
        System.out.println("End of main.");
    }

    public UMCarroJa(){
        try{
            this.estado = Estado.carregaEstado("estado.obj");
        }catch(FileNotFoundException e){
            System.out.println("Primeira execução?");
            this.estado = new Estado();
        }catch(IOException e){
            System.out.println("Erro ao tentar ler Estado.");
            this.estado = new Estado();
        }catch(ClassNotFoundException e){
            System.out.println("Formato do ficheiro de dados errado.");
            this.estado = new Estado();
        }
        String[] opcoesMenuPrincipal = {"Continuar ->","Carregar Estado (.txt)","Gravar Estado (.txt)"};
        String[] opcoesLogOuReg = {"Login","Registo"};
        this.menuPrincipal = new Menu(opcoesMenuPrincipal);
        this.menuLogOuReg = new Menu(opcoesLogOuReg);
    }

    private void run(){
        do{
            menuPrincipal.executa();
            switch (menuPrincipal.getOpcao()){
                case 1:
                    loginOuRegisto();
                case 2:
                    //WIP
                    estado.carregarEstadoTXT();
                    //System.out.println(estado.debugString());
                    break;
                case 3:
                    //WIP
                    estado.gravarEstadoTXT();
                    //System.out.println(estado.debugString());
                    break;
            }
        }while(menuPrincipal.getOpcao()!=0);
        try{
            this.estado.guardaEstado("estado.obj");
        }catch(IOException e){
            System.out.println("Erro ao tentar gravar Estado.");
        }
    }

    private void loginOuRegisto(){
        do{
            menuLogOuReg.executa();
            switch(menuLogOuReg.getOpcao()){
                case 1:
                    this.estado.login();
                    break;
                case 2:
                    this.estado.registo();
                    break;
            }
        }while(menuLogOuReg.getOpcao()!=0);
    }

}
