package master;

import java.io.*;

/**
 * Classe principal da aplicação
 *
 * @author Grupo 21
 * @version 26/05/2019
 */

public class UMCarroJa{

    /** estado do programa*/
    private Estado estado;
    /** menu inicial*/
    private Menu menuPrincipal;
    /** menu para dar login ou registo*/
    private Menu menuLogOuReg;

    /** main do programa*/
    public static void main(String[] args){
        System.out.println("Start of main.");
        long startTime = System.currentTimeMillis();
        new UMCarroJa().run();
        long endTime = System.currentTimeMillis();
        System.out.println("Tempo de exec: " + (endTime - startTime) + " milisegundos");
        System.out.println("End of main.");
    }

    /** "cerebro" do programa, dirige tudo para o sitio correto*/
    public UMCarroJa(){
        String[] opcoesMenuPrincipal = {"Continuar ->","Carregar Estado","Gravar Estado"};
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

    /** correr o menu principal*/
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
    /**corre o menu de login ou registo*/
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
