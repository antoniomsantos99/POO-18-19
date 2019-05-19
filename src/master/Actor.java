package master;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe Super do Cliente e do Proprietario
 *
 * Subclasses do Cliente e do Proprietario
 *
 * @author Pedro Oliveira
 * @version 25/04/2019
 */

public abstract class Actor {
    protected String nome;
    protected int NIF;
    protected String email;
    protected String password;
    protected String morada;
    protected String dataNascimento;

    public Actor(){
        this.nome = "n/a";
        this.NIF = 0;
        this.email = "n/a";
        this.password = "n/a";
        this.morada = "n/a";
        this.dataNascimento ="n/a";
    }
    public Actor(String nome, int NIF, String email, String password, String morada, String dataNascimento) {
        this.nome = nome;
        this.NIF = NIF;
        this.email = email;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }
    public Actor(Actor umActor){
        this.nome = umActor.getNome();
        this.NIF = umActor.getNIF();
        this.email = umActor.getEmail();
        this.password = umActor.getPassword();
        this.morada = umActor.getMorada();
        this.dataNascimento = umActor.getDataNascimento();
    }

    public String getNome() {
        return this.nome;
    }
    public int getNIF() { return this.NIF; }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getMorada() {
        return this.morada;
    }
    public String getDataNascimento() {
        return this.dataNascimento;
    }


    public void setNome (String nome) {
        this.nome = nome;
    }
    public void setEmail (String email) {
        this.email = email;
    }
    public void setNIF (int nif) {
        this.NIF = nif;
    }
    public void setPassword (String password) {
        this.password = password;
    }
    public void setMorada (String morada) {
        this.morada = morada;
    }
    public void setDataNascimento (String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Actor a = (Actor) o;
        return  a.getNome().equals(this.nome) &&
                a.getNIF() == this.NIF &&
                a.getEmail().equals(this.email) &&
                a.getPassword().equals(this.password) &&
                a.getMorada().equals(this.morada) &&
                a.getDataNascimento().equals(this.dataNascimento);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.nome);
        sb.append(", NIF: ").append(this.NIF);
        sb.append(", email: ").append(this.email);
        sb.append(", password: ").append(this.password);
        sb.append(", morada: ").append(this.morada);
        sb.append(", data de nascimento: ").append(this.dataNascimento);
        return sb.toString();
    }
}
// CORRIGIR CONSTRUTORES
class Proprietario extends Actor{
    private List<Carro> listaCarros;
    private List<Integer> classificacao;
    private List<Aluguer> historial;

    public Proprietario(){
        super();
        this.listaCarros = new ArrayList<Carro>();
        this.classificacao = new ArrayList<Integer>();
        this.historial = new ArrayList<Aluguer>();
    }
    public Proprietario(String nome, int NIF, String email, String password, String morada, String dataDeNascimento, List<Carro> listaCarros, List<Integer> classificacao, ArrayList<Aluguer> historial){
        super(nome,NIF,email,password,morada,dataDeNascimento);
        this.listaCarros = listaCarros;
        this.classificacao = classificacao;
        this.historial = historial;
    }
    public Proprietario(Proprietario p){
        super(p);
        this.listaCarros = p.getListaCarros();
        this.classificacao = p.getClassificacao();
        this.historial = p.getHistorial();
    }

    public List<Carro> getListaCarros() {return this.listaCarros.stream().map(Carro::clone).collect(Collectors.toList());}
    public List<Integer> getClassificacao() {
        return this.classificacao;
    }
    public List<Aluguer> getHistorial() {
        return this.historial.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    public void setListaCarros (ArrayList<Carro> listaCarros){
        this.listaCarros = new ArrayList<Carro>();
        for(Carro c:listaCarros){
            this.listaCarros.add(c);
        }
    }
    public void setClassificacao(List<Integer> classificacao) {
        this.classificacao = classificacao;
    }
    public void setHistorial(List<Aluguer> historial) {
        this.historial = new ArrayList<Aluguer>();
        for(Aluguer a: historial){
            this.historial.add(a);
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Proprietario:\n");
        sb.append(super.toString());
        sb.append(", listaCarros: ");
        for(Carro c : this.listaCarros){sb.append(c.toString());}
        sb.append(", classificação: ").append(this.classificacao);
        sb.append(", historial: ");
        for(Aluguer a : this.historial) {sb.append(a.toString());}
        return sb.toString();
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Proprietario p = (Proprietario) o;
        return super.equals(o) &&
                this.listaCarros.equals(p.getListaCarros()) &&
                this.classificacao.equals(p.getClassificacao()) &&
                this.historial.equals(p.getHistorial());
    }
    public Proprietario clone(){return new Proprietario(this);}
}
//CORRIGIR CONSTRUTORES
class Cliente extends Actor{
    private Ponto localizacao;
    private List<Integer> classificacao;
    private List<Aluguer> historial;

    //DUVIDA CLIENTE CONSTRUTOR

    public Cliente(){
        super();
        this.localizacao = new Ponto(0.0,0.0);
        this.classificacao = new ArrayList<Integer>();
        this.historial = new ArrayList<Aluguer>();
    }
    public Cliente( String nome, int NIF, String email, String password, String morada, String dataNascimento, Ponto localizacao, ArrayList<Integer> classificacao, ArrayList<Aluguer> historial){
        super(nome,NIF,email,password,morada,dataNascimento);
        this.localizacao = localizacao.clone();
        this.classificacao = classificacao;
        this.historial = historial;
    }
    public Cliente(Cliente c){
        super(c);
        this.localizacao=c.getLocalizacao();
        this.historial=c.getHistorial();
    }

    public Ponto getLocalizacao() {
        return this.localizacao.clone();
    }
    public List<Integer> getClassificacao(){return this.classificacao;}
    public List<Aluguer> getHistorial() {
        return this.historial.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    public void setLocalizacao(Ponto localizacao) {
        this.localizacao = localizacao.clone();
    }
    public void setClassificacao(List<Integer> classificacao){this.classificacao = classificacao;}
    public void setHistorial(List<Aluguer> historial) {
        this.historial = new ArrayList<Aluguer>();
        for(Aluguer a: historial){
            this.historial.add(a.clone());
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Cliente p = (Cliente) o;
        return  super.equals(o) &&
                this.localizacao.equals(p.getLocalizacao()) &&
                this.classificacao.equals(p.getClassificacao()) &&
                this.historial.equals(p.getHistorial());
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente:\n");
        sb.append(super.toString());
        sb.append(", localizacao: ").append(this.localizacao.toString());
        sb.append(", classificacao: ").append(this.classificacao);
        sb.append(", historial: ");
        for(Aluguer a : this.historial) {sb.append(a.toString());}
        return sb.toString();
    }
    public Cliente clone(){return new Cliente(this);}
}