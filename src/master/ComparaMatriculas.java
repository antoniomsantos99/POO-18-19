package master;

import java.util.Comparator;

public class ComparaMatriculas implements Comparator<Carro> {
    public int compare(Carro c1,Carro c2){
        return c1.getMatricula().compareTo(c2.getMatricula());
    }
}
