package master;

import javafx.util.Pair;
import java.util.*;

/**
 * Classe Super do Cliente e do Proprietario
 *
 * Subclasses do Cliente e do Proprietario
 *
 * @author Pedro Oliveira
 * @version 25/04/2019
 */

public class Actor {
    protected String email;
    protected String nome;
    protected String password;
    protected String morada;
    protected String dataNascimento;

    public Actor(){
        this.email = "n/a";
        this.nome = "n/a";
        this.password = "n/a";
        this.morada = "n/a";
        this.dataNascimento ="n/a";
    }
    public Actor(String email, String nome, String password, String morada, String dataNascimento) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }
    public Actor(Actor umActor){
        this.email = umActor.getEmail();
        this.nome = umActor.getNome();
        this.password = umActor.getPassword();
        this.morada = umActor.getMorada();
        this.dataNascimento = umActor.getDataNascimento();
    }

    public String getEmail() {
        return this.email;
    }
    public String getNome() {
        return this.nome;
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

    public void setEmail (String e) {
        this.email = e;
    }
    public void setNome (String n) {
        this.nome = n;
    }
    public void setPassword (String p) {
        this.password = p;
    }
    public void setMorada (String m) {
        this.morada = m;
    }
    public void setDataNascimento (String d) {
        this.dataNascimento = d;
    }

    //IMPORTANTE - PERGUNTAR AO PROFESSOR ACERCA DO WARNING

    public Actor clone() {
        return new Actor();
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Actor a = (Actor) o;
        return  a.getEmail().equals(this.email) &&
                a.getNome().equals(this.nome) &&
                a.getPassword().equals(this.password) &&
                a.getMorada().equals(this.morada) &&
                a.getDataNascimento().equals(this.dataNascimento);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dados do cliente: \n");
        sb.append("Email: ").append(this.email);
        sb.append(", nome: ").append(this.nome);
        sb.append(", password: ").append(this.password);
        sb.append(", morada: ").append(this.morada);
        sb.append(", data de nascimento: ").append(this.dataNascimento);
        return sb.toString();
    }
}

class Proprietario extends Actor{
    private int classificacao;
    private List<Aluguer> historial;

    public Proprietario(){
        super();
        this.classificacao = -1;
        this.historial = new ArrayList<Aluguer>();
    }
    public Proprietario(String email, String nome, String password, String morada, String dataDeNascimento, int classificacao, ArrayList<Aluguer> historial){
        super(email,nome,password,morada,dataDeNascimento);
        this.classificacao = classificacao;
        this.historial = historial;
    }
    public Proprietario(Proprietario p){
        super(p);
        this.classificacao = p.getClassificacao();
        this.historial = p.getHistorial();
    }

    public int getClassificacao() {
        return this.classificacao;
    }
    public List<Aluguer> getHistorial() {
        return this.historial;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
    public void setHistorial(List<Aluguer> historial) {
        this.historial = historial;
    }

    public Proprietario clone(){return new Proprietario();}
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", classificação: ").append(this.classificacao);
        sb.append(", historial: ");
        for(Aluguer a : this.historial) {sb.append(a);}
        return sb.toString();
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Proprietario p = (Proprietario) o;
        return super.equals(o) &&
                this.classificacao == p.getClassificacao();
    }
}

class Cliente extends Actor{
    private Pair<Double,Double> localizacao;
    private List<Aluguer> historial;

    public Cliente(){
        super();
        this.localizacao = new Pair(0,0);
        this.historial = new ArrayList<Aluguer>();
    }
    public Cliente(String email, String nome, String password, String morada, String dataNascimento, Pair<Double,Double> localizacao, ArrayList<Aluguer> historial){
        super(email,nome,password,morada,dataNascimento);
        this.localizacao=localizacao;
        this.historial=historial;
    }
    public Cliente(Cliente c){
        super(c);
        this.localizacao=c.getLocalizacao();
        this.historial=c.getHistorial();
    }

    public Pair<Double, Double> getLocalizacao() {
        return this.localizacao;
    }
    public List<Aluguer> getHistorial() {
        return this.historial;
    }

    public void setLocalizacao(Pair<Double, Double> localizacao) {
        this.localizacao = localizacao;
    }
    public void setHistorial(List<Aluguer> historial) { this.historial = historial; }

    public Cliente clone(){return new Cliente();}
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Cliente p = (Cliente) o;
        return super.equals(o) &&
                (this.localizacao).equals(p.getLocalizacao());
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(", localizacao: ").append(this.localizacao);
        sb.append(", historial: ");
        for(Aluguer a : this.historial) {sb.append(a);}
        return sb.toString();
    }
}