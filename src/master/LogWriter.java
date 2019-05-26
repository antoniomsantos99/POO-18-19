package master;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogWriter
{
    public static void logProp(Proprietario prop,String file) throws IOException {
            FileWriter fileWriter = new FileWriter(file,true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf("NovoProp:%s,%s,%s,%s\n",prop.getNome(),prop.getNif(),prop.getEmail(),prop.getMorada());
            printWriter.close();
    }

    public static void logCliente(Cliente cliente,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("NovoCliente:%s,%s,%s,%s,%f,%f\n",cliente.getNome(),cliente.getNif(),cliente.getEmail(),cliente.getMorada(),cliente.getLocalizacao().getX(),cliente.getLocalizacao().getY());
        printWriter.close();
    }

    public static void logCarro(Carro carro,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("NovoCarro:%s,%s,%s,%s,%f,%f,%f,%f,%f\n",carro.getClass().getSimpleName(),carro.getMarca(),carro.getMatricula(),carro.getNif(),carro.getVelMed(),carro.getPrecoKm(),carro.getAutonomia(),carro.getLocalizacao().getX(),carro.getLocalizacao().getY());
        printWriter.close();
    }

    public static void logAluguer(Aluguer aluguer,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("Aluguer:%s,%f,%f,%s,%s\n",aluguer.getNifCliente(),aluguer.getLocalFinal().getX(),aluguer.getLocalFinal().getY(),aluguer.getCombustivel(),aluguer.getPreferencia());
        printWriter.close();

    }

    public static void logClassCar(Carro car,int nota,String file) throws IOException{
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("Classificar:%s,%d\n",car.getMatricula(),nota);
        printWriter.close();
    }

    public static void logClassActor(Actor actor,int nota,String file) throws IOException{
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("Classificar:%s,%d\n",actor.getNif(),nota);
        printWriter.close();
    }

}
