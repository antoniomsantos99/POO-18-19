package branchForTesting;

public class Pessoa {
    protected int idade;
    protected String nome;

    protected Pessoa(){
        this.idade = -1;
        this.nome = "";
    }
    protected Pessoa(int idade, String nome){
        this.idade = idade;
        this.nome = nome;
    }

    protected int getIdade() {
        return idade;
    }
    protected String getNome() {
        return nome;
    }

    protected void setIdade(int idade) {
        this.idade = idade;
    }
    protected void setNome(String nome) {
        this.nome = nome;
    }
}

class Atleta extends Pessoa {
    private String desporto;
    public Atleta(){
        super();
        this.desporto="";
    }
    public Atleta(int idade, String nome, String desporto){
        super(idade,nome);
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
