package branchForTesting;

abstract class Pessoa {
    protected int idade;
    protected String nome;
}

class Atleta extends Pessoa {
    private String desporto;
    public Atleta(){
        super();
        this.desporto="";
    }
    public Atleta(String desporto){
        super();
        this.desporto=desporto;
    }
    public Atleta(Atleta a){
        super();
        this.desporto = a.desporto;
    }
    public String getDesporto(){return this.desporto;}
    public void setDesporto(String novoDesporto){this.desporto=novoDesporto;}

    @Override
    public String toString() {
        return "Atleta{" +
                "idade=" + idade +
                ", nome='" + nome + '\'' +
                ", desporto='" + desporto + '\'' +
                '}';
    }
}
