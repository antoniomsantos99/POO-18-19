package master;

import java.util.Comparator;

/**
 * Classe de comparar
 *
 * @author Grupo 21
 * @version 26/05/2019
 */


public class ComparaMatriculas implements Comparator<Carro> {
    public int compare(Carro c1,Carro c2){
        return c1.getMatricula().compareTo(c2.getMatricula());
    }
}
