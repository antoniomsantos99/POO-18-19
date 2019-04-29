package master;

import java.io.*;
import java.util.HashMap;

/**
 * Classe estatica que le e escreve ficheiros para serem utilizados no estado
 *
 * @author Pedro Oliveira
 * @version 29/04/2019
 */


public class fileIO {

    /**
     * metodo estatico que le e passa para o estado atual
     * tudo o que esta no ficheiro de save (que recebe como parametro)
     * @param fileName nome do ficheiro do qual vai ler
     */
    public static void readFile(String fileName){
        try{
            String linha;
            FileReader fileReader = new FileReader("saves/"+fileName+".txt");
            BufferedReader buffLeitor = new BufferedReader(fileReader);

            //MECHER NISTO PARA CRIAR O NOVO ESTADO
            while((linha = buffLeitor.readLine()) != null) {
                System.out.println(linha);
            }

            buffLeitor.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Erro: Ficheiro não existe.");
        }
        catch(IOException ex){
            System.out.println("Erro: Não foi possivel ler o ficheiro '" + fileName + "'");
        }
    }

    /**
     * metodo estatico que escreve o estado atual num ficheiro
     * @param fileName nome do ficheiro do qual vai ler
     */
    public static void writeFile(String fileName){
        try {
            FileWriter fileWriter = new FileWriter("saves/"+fileName+".txt");
            BufferedWriter buffEscritor = new BufferedWriter(fileWriter);

            buffEscritor.write("Nr Clientes: " + EstadoAtual.getNrClientes() + '\n');
            for(HashMap.Entry<String, Cliente> entry : EstadoAtual.getListaClientes().entrySet()) {
                System.out.println("Cliente Gravado");
                buffEscritor.write("Cliente\n");
                buffEscritor.write(entry.getValue().getEmail()+"\n");
                buffEscritor.write(entry.getValue().getNome()+"\n");
                buffEscritor.write(entry.getValue().getPassword()+"\n");
                buffEscritor.write(entry.getValue().getMorada()+"\n");
                buffEscritor.write(entry.getValue().getDataNascimento()+"\n");
                //buffEscritor.write(entry.getValue().getLocalizacao());
                //buffEscritor.write(entry.getValue().getHistorial());
            }
            buffEscritor.write("Nr Proprietarios: " + EstadoAtual.getNrProprietarios() + '\n');
            for(HashMap.Entry<String, Proprietario> entry : EstadoAtual.getListaProprietarios().entrySet()) {
                System.out.println("Proprietario Gravado");
                buffEscritor.write("Proprietario\n");
                buffEscritor.write(entry.getValue().getEmail()+"\n");
                buffEscritor.write(entry.getValue().getNome()+"\n");
                buffEscritor.write(entry.getValue().getPassword()+"\n");
                buffEscritor.write(entry.getValue().getMorada()+"\n");
                buffEscritor.write(entry.getValue().getDataNascimento()+"\n");
                //buffEscritor.write(entry.getValue().getLocalizacao());
                //buffEscritor.write(entry.getValue().getHistorial());
            }
            buffEscritor.write("Nr Carros: " + EstadoAtual.getNrCarros() + '\n');
            for(HashMap.Entry<String, Carro> entry : EstadoAtual.getListaCarros().entrySet()) {
                System.out.println("Carro Gravado");
                buffEscritor.write("Carro\n");
                buffEscritor.write(entry.getValue().getVelMed()+"\n");
                buffEscritor.write(entry.getValue().getPrecoBase()+"\n");
                buffEscritor.write(entry.getValue().getClassificacao()+"\n");
                //buffEscritor.write(entry.getValue().getLocalizacao());
                //buffEscritor.write(entry.getValue().getHistorial());
            }

            buffEscritor.close();
        }
        catch(IOException ex) {
            System.out.println("Erro: Não foi possivel escrever no ficheiro '" + fileName + "'");
        }
        System.out.println("Estado gravado com sucesso!\n");
    }

}