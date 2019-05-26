package master;

import java.io.Serializable;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe de menu
 *
 * @author Grupo 21
 * @version 26/05/2019
 */

public class Menu  implements Serializable {

    /** lista de opçoes que se pretendem apresentar*/
    private List<String> opcoes;

    /** opçao escolhida */
    private int opcao;

    /** get para saver a opçao que foi escolhida*/
    public int getOpcao(){
        return this.opcao;
    }

    /** construtor do Menu, recebe a lista de opções que vai dar display*/
    public Menu(String[] opcoes){
        this.opcoes = Arrays.asList(opcoes);
        this.opcao = 0;
    }

    /** executa para mostar o menu e recebe uma opçao*/
    public void executa(){
        do{
            showMenu();
            this.opcao = lerOpcao();
        }while(this.opcao == -1);
    }

    /** mostra o menu com as opçoes que tem*/
    public void showMenu(){
        System.out.println(" *** Menu *** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }

    /** le a opcao apartir de input do utilizador*/
    public int lerOpcao(){
        int opcao;
        Scanner is = new Scanner(System.in);

        System.out.print("Opção: ");
        try {
            opcao = is.nextInt();
        }
        catch (InputMismatchException e) { // Não foi inscrito um int
            opcao = -1;
        }
        if (opcao<0 || opcao>this.opcoes.size()) {
            System.out.println("Erro: Introduza um valor válido.");
            opcao = -1;
        }
        return opcao;
    }

}
