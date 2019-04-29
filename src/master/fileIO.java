package master;

import java.io.*;
import java.util.HashMap;

public class fileIO {
    public static void readFile(String fileName){
        System.out.println(fileName);
    }
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
            System.out.println("Erro ao tentar escrever no ficheiro: '" + fileName + "'");
        }
        System.out.println("Estado gravado com sucesso!\n");
    }
}