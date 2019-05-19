package master;

import java.util.ArrayList;

public class classeTestes {

    private static void testeActor(){
        Cliente c1 = new Cliente("Pedro",1,"pedro@gmail.com","pedro123","Rua do Pedro","1/1/1",new Ponto(5.4, 3.2),new ArrayList<>(),new ArrayList<>());
        Cliente c2 = new Cliente("Matilde",2,"matilde@gmail.com","matilde123","Rua da Matilde","2/2/2",new Ponto(0.0,-1.0),new ArrayList<>(),new ArrayList<>());
        Proprietario p1 = new Proprietario("Rui",3,"rui@gmail.com","rui123","Rua do Rui","3/3/3",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        Proprietario p2 = new Proprietario("Miguel",4,"miguel@gmail.com","miguel123","Rua do Miguel","4/4/4",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());

        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }

    public static void main(String[] args){
        testeActor();
    }

}
