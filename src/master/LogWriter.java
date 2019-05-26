package master;

import java.io.*;

public class LogWriter {

    private static final String path = "saves/";

    public static void logProp(Proprietario prop,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("NovoProp:%s,%s,%s,%s\n",prop.getNome(),prop.getNif(),prop.getEmail(),prop.getMorada());
        printWriter.flush();
        printWriter.close();
    }

    public static void logCliente(Cliente cliente,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("NovoCliente:%s,%s,%s,%s,%s,%s\n",cliente.getNome(),cliente.getNif(),cliente.getEmail(),cliente.getMorada(),String.valueOf(cliente.getLocalizacao().getX()),String.valueOf(cliente.getLocalizacao().getY()));
        printWriter.flush();
        printWriter.close();
    }


    public static void logCarro(Carro carro,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        if(carro.getClass().getSimpleName().equals("Gasolina")) {
            Gasolina carAux = (Gasolina) carro;
            printWriter.printf("NovoCarro:%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", carro.getClass().getSimpleName(), carro.getMarca(), carro.getMatricula(), carro.getNif(), String.valueOf(carro.getVelMed()), String.valueOf(carro.getPrecoKm()), String.valueOf(carAux.getConsumoGas()), String.valueOf(carro.getAutonomia()), String.valueOf(carro.getLocalizacao().getX()), String.valueOf(carro.getLocalizacao().getY()));
        }

        if(carro.getClass().getSimpleName().equals("Eletrico")) {
            Eletrico carAux = (Eletrico) carro;
            printWriter.printf("NovoCarro:%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", carro.getClass().getSimpleName(), carro.getMarca(), carro.getMatricula(), carro.getNif(), String.valueOf(carro.getVelMed()), String.valueOf(carro.getPrecoKm()), String.valueOf(carAux.getConsumoBat()), String.valueOf(carro.getAutonomia()), String.valueOf(carro.getLocalizacao().getX()), String.valueOf(carro.getLocalizacao().getY()));
        }

        if(carro.getClass().getSimpleName().equals("Hibrido")) {
            Hibrido carAux = (Hibrido) carro;
            printWriter.printf("NovoCarro:%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", carro.getClass().getSimpleName(), carro.getMarca(), carro.getMatricula(), carro.getNif(), String.valueOf(carro.getVelMed()), String.valueOf(carro.getPrecoKm()), String.valueOf(carAux.getConsumoGas()), String.valueOf(carro.getAutonomia()), String.valueOf(carro.getLocalizacao().getX()), String.valueOf(carro.getLocalizacao().getY()));
        }
            printWriter.flush();
        printWriter.close();
    }

    public static void logAluguer(Aluguer aluguer,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("Aluguer:%s,%s,%s,%s,%s\n",aluguer.getNifCliente(),String.valueOf(aluguer.getLocalFinal().getX()),String.valueOf(aluguer.getLocalFinal().getY()),aluguer.getCombustivel(),aluguer.getPreferencia());
        printWriter.flush();
        printWriter.close();

    }

    public static void logClassCar(Carro car,int nota,String file) throws IOException{
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("Classificar:%s,%d\n",car.getMatricula(),nota);
        printWriter.flush();
        printWriter.close();
    }

    public static void logClassActor(Actor actor,int nota,String file) throws IOException{
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("Classificar:%s,%d\n",actor.getNif(),nota);
        printWriter.flush();
        printWriter.close();
    }
    public static void logToSave(String file,String fileTo)throws IOException{
        File ficheiro = new File(file);
        File ficheiroTo = new File(fileTo);
        try{copyFileUsingStream(ficheiro,ficheiroTo);}
        catch(IOException e){System.out.println("Erro: Ficheiro não existe.");}
    }


    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

}
