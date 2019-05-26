package master;

import java.io.*;

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
        String[] opcoesMenuPrincipal = {"Continuar ->","Carregar Estado (.txt)","Gravar Estado (.txt)","Debug"};
        String[] opcoesLogOuReg = {"Login","Registo","10 Clientes que mais utilizam o sistema"};
        this.menuPrincipal = new Menu(opcoesMenuPrincipal);
        this.menuLogOuReg = new Menu(opcoesLogOuReg);
        try{
            this.estado = Estado.carregaEstado("estado.obj");
        }catch(FileNotFoundException e){
            System.out.println("Primeira execução?");
            this.estado = new Estado();
        }catch(IOException e){
            System.out.println("Erro ao tentar ler Estado.");
            e.printStackTrace();
            this.estado = new Estado();
        }catch(ClassNotFoundException e){
            System.out.println("Formato do ficheiro de dados errado.");
            this.estado = new Estado();
        }
    }

    private void run(){
        do{
            menuPrincipal.executa();
            switch (menuPrincipal.getOpcao()){
                case 1:
                    loginOuRegisto();
                    break;
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
                case 4:
                    System.out.println(estado.debugString());
                    break;
            }
        }while(menuPrincipal.getOpcao()!=0);
        try{
            this.estado.guardaEstado("estado.obj");
        }
        catch(IOException e){
            System.out.println("Erro ao tentar gravar Estado.");
            e.printStackTrace();
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
                case 3:
                    this.estado.listagemClientes();
                    break;
            }
        }while(menuLogOuReg.getOpcao()!=0);
    }

}
