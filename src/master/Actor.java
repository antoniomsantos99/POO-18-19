package master;

import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe Super do Cliente e do Proprietario
 *
 * Subclasses do Cliente e do Proprietario
 *
 * @author Grupo 21
 * @version 26/05/2019
 */

public abstract class Actor implements Serializable {
    /** nome do actor*/
    protected String nome;
    /** nif do actor*/
    protected String nif;
    /** email do actor*/
    protected String email;
    /** password do actor*/
    protected String password;
    /** morada do actor*/
    protected String morada;
    /** data de nascimento do actor*/
    protected String dataNascimento;
    /** lista de alugueres*/
    private List<Aluguer> historial;
    /** lista com as classificações*/
    private List<Integer> classificacao;

    /** cosntrutor por omissão*/
    public Actor(){
        this.nome = "n/a";
        this.nif = "n/a";
        this.email = "n/a";
        this.password = "n/a";
        this.morada = "n/a";
        this.dataNascimento ="n/a";
    }
    /** construtor com argumentos*/
    public Actor(String nome, String nif, String email, String password, String morada, String dataNascimento, List<Aluguer> historial, List<Integer> classificacao) {
        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
        this.historial = historial.stream().map(Aluguer::clone).collect(Collectors.toList());
        this.classificacao = new ArrayList<>();
        for(Integer i:classificacao){this.classificacao.add(i);}
    }
    /** construtor para clone*/
    public Actor(Actor umActor){
        this.nome = umActor.getNome();
        this.nif = umActor.getNif();
        this.email = umActor.getEmail();
        this.password = umActor.getPassword();
        this.morada = umActor.getMorada();
        this.dataNascimento = umActor.getDataNascimento();
        this.historial = umActor.getHistorial();
        this.classificacao = umActor.getClassificacao();
    }

    /** getters*/
    public String getNome() {
        return this.nome;
    }
    public String getNif() { return this.nif; }
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
    public List<Aluguer> getHistorial(){
        return this.historial.stream().map(Aluguer::clone).collect(Collectors.toList());
    }
    public List<Integer> getClassificacao(){
        List<Integer> lista = new ArrayList<Integer>();
        for(Integer i:this.classificacao){
            lista.add(i);
        }
        return lista;
    }

    /** setters*/
    public void setNome (String nome) {
        this.nome = nome;
    }
    public void setEmail (String email) {
        this.email = email;
    }
    public void setNIF (String nif) {
        this.nif = nif;
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
    public void setHistorial (List<Aluguer> historial){
        this.historial = historial.stream().map(Aluguer::clone).collect(Collectors.toList());
    }
    public void setClassificacao (List<Integer> classificacao){
        this.classificacao = new ArrayList<Integer>();
        for(Integer i:classificacao){
            this.classificacao.add(i);
        }
    }

    /** clone*/
    public abstract Actor clone();
    /** equals*/
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Actor a = (Actor) o;
        return  a.getNome().equals(this.nome) &&
                a.getNif().equals(this.nif) &&
                a.getEmail().equals(this.email) &&
                a.getPassword().equals(this.password) &&
                a.getMorada().equals(this.morada) &&
                a.getDataNascimento().equals(this.dataNascimento) &&
                a.getHistorial().equals(this.historial) &&
                a.getClassificacao().equals(this.classificacao);
    }
    /** passa o actor para uma string*/
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.nome);
        sb.append(", nif: ").append(this.nif);
        sb.append(", email: ").append(this.email);
        sb.append(", password: ").append(this.password);
        sb.append(", morada: ").append(this.morada);
        sb.append(", data de nascimento: ").append(this.dataNascimento);
        sb.append(", historial: ").append(this.historial);
        sb.append(", classificacao: ").append(this.classificacao);
        return sb.toString();
    }

    /** devolve a media de classificações*/
    public int classificacao(){if(classificacao.isEmpty()) return -1; else return classificacao.stream().mapToInt(Integer::intValue).sum()/classificacao.size();}
    /** adiciona uma classificação*/
    public void classificar(int i){
        this.classificacao.add(i);
    }
    /** adiciona um aluguer ao historial*/
    public void addAluguer(Aluguer a){this.historial.add(a.clone());}
}

class Proprietario extends Actor{
    /** lista de carros do proprietario*/
    private Set<Carro> listaCarros;
    /** lista de alugueres por aceitar por parte do proprietario*/
    private List<Aluguer> pending;

    /** construtor por omissão*/
    public Proprietario(){
        super();
        this.listaCarros = new TreeSet<Carro>(new ComparaMatriculas());
        this.pending = new ArrayList<Aluguer>();
    }
    /** construtor por argumentos*/
    public Proprietario(String nome, String nif, String email, String password, String morada, String dataDeNascimento, Set<Carro> listaCarros, List<Integer> classificacao, ArrayList<Aluguer> historial, ArrayList<Aluguer> pending){
        super(nome,nif,email,password,morada,dataDeNascimento,historial,classificacao);
        this.listaCarros = listaCarros.stream().map(Carro::clone).collect(Collectors.toSet());
        this.pending = pending.stream().map(Aluguer::clone).collect(Collectors.toList());
    }
    /** construtor para clone*/
    public Proprietario(Proprietario p){
        super(p);
        this.listaCarros = p.getSetCarros();
        this.pending = p.getPending();
    }

    /** getters*/
    public Set<Carro> getSetCarros() {
        return this.listaCarros.stream().map(Carro::clone).collect(Collectors.toSet());
    }
    public List<Aluguer> getPending() {
        return this.pending.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    /** setters*/
    public void setSetCarros (Set<Carro> listaCarros){
        this.listaCarros = listaCarros.stream().map(Carro::clone).collect(Collectors.toSet());
    }
    public void setPending(List<Aluguer> pending){
        this.pending = pending.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    /** troca o carro no set se já tiver essa matricula, se não adiciona*/
    public void trocarCarro (Carro c){
        this.listaCarros = this.listaCarros.stream().filter(carro -> !carro.getMatricula().equals(c.getMatricula())).collect(Collectors.toSet());
        this.listaCarros.add(c);
    }
    /** verifica se o carro estiste na lista de carros*/
    public boolean verificaCarro(String s){
        return this.listaCarros.stream().anyMatch(c->c.getMatricula().equals(s));
    }

    /**
     * adiciona um novo aluguer a lista de alugueres por aceitar o recusar por parte do proprietario
     * @param a aluguer em questão
     */
    public void addPending(Aluguer a){
        this.pending.add(a.clone());
    }

    /**
     * remove o aluguer da lista de alugeres por aceitar
     * @param index indez do aluguer a remover
     */
    public void removerPending(int index){
        this.pending.remove(index);
    }

    /** passa o proprietario para uma string*/
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", listaCarros: ");
        for(Carro c : this.listaCarros){sb.append(c.toString());}
        sb.append(", pending: ");
        for(Aluguer a : this.pending){sb.append(a.toString());}
        return sb.toString()+'\n';
    }
    /** equals*/
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Proprietario p = (Proprietario) o;
        return super.equals(o) &&
                this.listaCarros.equals(p.getSetCarros()) &&
                this.pending.equals(p.getPending());
    }
    /** clone*/
    public Proprietario clone(){return new Proprietario(this);}
}

class Cliente extends Actor{
    /** localização atual do cliente*/
    private Ponto localizacao;
    /** aluguer concluido para ser classificado, null se já tiver classificado ou não tiver feito alugueres*/
    private Aluguer concluido;

    /** construtor por omissão*/
    public Cliente(){
        super();
        this.localizacao = new Ponto(0.0,0.0);
    }
    /** construtor por argumentos*/
    public Cliente( String nome, String nif, String email, String password, String morada, String dataNascimento, Ponto localizacao, ArrayList<Integer> classificacao, ArrayList<Aluguer> historial){
        super(nome,nif,email,password,morada,dataNascimento,historial,classificacao);
        this.localizacao = localizacao.clone();
    }
    /** construtor para clone*/
    public Cliente(Cliente c){
        super(c);
        this.localizacao=c.getLocalizacao();
    }
    /** getters e setters*/
    public Ponto getLocalizacao() {
        return this.localizacao.clone();
    }
    public void setLocalizacao(Ponto localizacao) {
        this.localizacao = localizacao.clone();
    }

    /**
     * devolve um aluguer se este existir, se não, lança uma excepção
     * @return um aluguer se não for null
     * @throws noAluguerException se o aluguer for null numa excepção
     */
    public Aluguer getConcluido()throws noAluguerException{
        if(this.concluido==null) throw new noAluguerException();
        else return this.concluido.clone();
    }
    /**
     * coloca um aluguer para poder ser classificado
     * @param concluido pode ser null
     */
    public void setConcluido(Aluguer concluido){
        try{
            this.concluido=concluido.clone();
        }catch(NullPointerException e){
            this.concluido = null;
        }
    }

    /** equals*/
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Cliente p = (Cliente) o;
        return  super.equals(o) &&
                this.localizacao.equals(p.getLocalizacao());
    }
    /** passa o cliente para uma string*/
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", localizacao: ").append(this.localizacao.toString());
        return sb.toString()+'\n';
    }
    /** clone*/
    public Cliente clone(){return new Cliente(this);}
}