package master;

import java.io.Serializable;
import java.lang.*;
import java.util.*;

/**
 * Classe do objeto carro
 *
 * @author Grupo 21
 * @version 26/05/2019
 */

public abstract class Carro implements Serializable,Alugavel{

    /** marca do carro*/
    protected String marca;
    /** matricula do carro*/
    protected String matricula;
    /** nif do proprietario*/
    protected String nif;
    /** velocidade media do carro*/
    protected double velMed;
    /** preco por km*/
    protected double precoKm;
    /** localizacao atual do carro*/
    protected Ponto localizacao;
    /** lista de classificações do carro*/
    protected List<Integer> classificacao;
    /** autonomia disponivel*/
    protected double autonomia;
    /** disponibilidade de ser alugado*/
    protected boolean dispAlugar;

    /** construtor por omissão*/
    public Carro() {
        this.marca = "n/a";
        this.matricula = "n/a";
        this.nif = "n/a";
        this.velMed = 0.0;
        this.precoKm = 0.0;
        this.localizacao = new Ponto();
        this.classificacao = new ArrayList<Integer>();
        this.autonomia = 0.0;
        this.dispAlugar = false;
    }
    /** construtor por argumentos*/
    public Carro(String marca, String matricula, String nif, double velMed, double precoKm, Ponto localizacao, List<Integer> classificacao, double autonomia, boolean dispAlugar) {
        this.marca = marca;
        this.matricula = matricula;
        this.nif = nif;
        this.velMed = velMed;
        this.precoKm = precoKm;
        this.localizacao = localizacao.clone();
        this.classificacao = classificacao;
        this.autonomia = autonomia;
        this.dispAlugar = dispAlugar;
    }
    /** construtor para clone*/
    public Carro(Carro umCarro) {
        this.marca = umCarro.getMarca();
        this.matricula = umCarro.getMatricula();
        this.nif = umCarro.getNif();
        this.velMed = umCarro.getVelMed();
        this.precoKm = umCarro.getPrecoKm();
        this.localizacao = umCarro.getLocalizacao();
        this.classificacao = umCarro.getClassificacao();
        this.autonomia = umCarro.getAutonomia();
        this.dispAlugar = umCarro.getDispAlugar();
    }

    /**getters*/
    public String getMarca(){return this.marca;}
    public String getMatricula(){
        return this.matricula;
    }
    public String getNif(){
        return this.nif;
    }
    public double getVelMed() {
        return this.velMed;
    }
    public double getPrecoKm() {
        return this.precoKm;
    }
    public Ponto getLocalizacao(){return this.localizacao.clone();}
    public List<Integer> getClassificacao() {
        List<Integer> lista = new ArrayList<Integer>();
        for(Integer i:this.classificacao){
            lista.add(i);
        }
        return lista;
    }
    public double getAutonomia(){return this.autonomia;}
    public boolean getDispAlugar(){
        return this.dispAlugar;
    }

    /** setters*/
    public void setMarca(String marca){this.marca = marca;}
    public void setMatricula (String matricula){
        this.matricula = matricula;
    }
    public void setNIF (String nif){
        this.nif = nif;
    }
    public void setVelMed (double velMed) {
        this.velMed = velMed;
    }
    public void setPrecoKm (double precoKm) {
        this.precoKm = precoKm;
    }
    public void setLocalizacao(Ponto localizacao){
        this.localizacao = localizacao.clone();
    }
    public void setClassificacao (List<Integer> classificacao) {
        this.classificacao = new ArrayList<Integer>();
        for(Integer i:classificacao){
            this.classificacao.add(i);
        }
    }
    public void setAutonomia(double autonomia){
        this.autonomia = autonomia;
    }
    public void setDispAlugar(boolean dispAlugar){
        this.dispAlugar = dispAlugar;
    }

    /** passa o carro para uma string*/
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Marca: ").append(this.marca);
        sb.append(", Matricula: ").append(this.matricula);
        sb.append(", nif: ").append(this.nif);
        sb.append(", Velocidade: ").append(this.velMed);
        sb.append(", Preço/km: ").append(this.precoKm);
        sb.append(", Localizacao: ").append(this.localizacao.toString());
        sb.append(", Classificacao: ").append(this.classificacao);
        sb.append(", Autonomia: ").append(this.autonomia);
        sb.append(", DisponivelAlugar: ").append(this.dispAlugar);
        return sb.toString();
    }
    /** equals*/
    public boolean equals (Object o) {
        if(o==this) return true;
        if(o==null || o.getClass ()!= this.getClass()) return false;
        Carro aux = (Carro) o;
        return  aux.getMarca().equals(this.marca) &&
                aux.getMatricula().equals(this.matricula) &&
                aux.getNif().equals(this.nif) &&
                aux.getVelMed() == this.velMed &&
                aux.getPrecoKm() == this.precoKm &&
                aux.getLocalizacao().equals(this.localizacao) &&
                aux.getClassificacao().equals(this.classificacao) &&
                aux.getAutonomia()==this.autonomia &&
                aux.getDispAlugar()==this.dispAlugar;
    }
    /** clone para encapsulamento*/
    public abstract Carro clone();

    /**
     * classificar um carro, adiciona a lista de classificações o novo int que recebe
     * @param classificacao classficiação dada
     */
    public void classficarCarro(int classificacao){
        this.classificacao.add(classificacao);
    }
}

class Gasolina extends Carro {
    /** consumo Gas do carro*/
    private double consumoGas;

    /** construtor por omissão */
    public Gasolina() {
        super();
        this.consumoGas = 0;
    }
    /** construtor com parametros */
    public Gasolina (String marca, String matricula, String nif, double velMed, double precoBase, Ponto localizacao, List<Integer> classificacao, double consumoGas, double autonomia, boolean dispAlugar) {
        super(marca,matricula,nif,velMed,precoBase,localizacao,classificacao,autonomia,dispAlugar);
        this.consumoGas = consumoGas;
    }
    /** construtor para clone*/
    public Gasolina (Gasolina umGasolina) {
        super(umGasolina);
        this.consumoGas = umGasolina.getConsumoGas();
    }

    /** getter e setter*/
    public double getConsumoGas() {
        return this.consumoGas;
    }

    public void setConsumoGas(double consumoGas) {
        this.consumoGas = consumoGas;
    }

    /** clone*/
    public Gasolina clone() {
        return new Gasolina(this);
    }

    /** passar carro para uma string*/
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gasolina: \n");
        sb.append(super.toString());
        sb.append(", consumoGas: ").append(this.consumoGas);
        return sb.toString()+'\n';
    }

    /** equals*/
    public boolean equals(Object o) {
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        Gasolina aux = (Gasolina) o;
        return super.equals(o) &&
                aux.getConsumoGas() == this.consumoGas;
    }

    /** dar update ao carro depois de um aluguer*/
    public void updateCarro(Aluguer a){
        this.autonomia -= a.getDistancia()*this.consumoGas;
    }
    /** abastece o veículo com uma determinada quantidade*/
    public void abastecer(double quantidade){this.autonomia +=quantidade;}
    /** calcular o preço de uma viagem*/
    public double calcularPreco(double distancia){return this.precoKm*distancia;}

}

class Eletrico extends Carro {
    /** consumo de batteria do carro*/
    private double consumoBat;

    /** construtor por omissão*/
    public Eletrico() {
        super();
        this.consumoBat = 0;
    }
    /** construtor por argumentos*/
    public Eletrico (String marca, String matricula, String nif, double velMed, double precoBase, Ponto localizacao, List<Integer> classificacao, double consumoBat, double autonomia, boolean dispAlugar) {
        super(marca,matricula,nif,velMed,precoBase,localizacao,classificacao,autonomia,dispAlugar);
        this.consumoBat = consumoBat;
    }
    /** construtor para copia*/
    public Eletrico (Eletrico umEletrico) {
        super(umEletrico);
        this.consumoBat = umEletrico.getConsumoBat();
    }

    /** getter e setter*/
    public double getConsumoBat() {
        return this.consumoBat;
    }

    public void setConsumoBat (double consumoBat) {
        this.consumoBat = consumoBat;
    }

    /** clone*/
    public Eletrico clone() {
        return new Eletrico(this);
    }

    /** passa o carro para uma string*/
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Eletrico: \n");
        sb.append(super.toString());
        sb.append(", consumoBat: ").append(this.consumoBat);
        return sb.toString()+'\n';
    }

    /** equals*/
    public boolean equals(Object o) {
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        Eletrico aux = (Eletrico) o;
        return super.equals(o) &&
                aux.getConsumoBat()==this.consumoBat;
    }

    /** abastece o veículo com uma determinada quantidade*/
    public void abastecer(double quantidade){this.autonomia +=quantidade;}
    /** calcular o preço de uma viagem*/
    public double calcularPreco(double distancia){return this.precoKm*distancia;}
    /** dar update ao carro depois de um aluguer*/
    public void updateCarro(Aluguer a){
        this.autonomia -= a.getDistancia()*this.consumoBat;
    }
}

class Hibrido extends Carro {
    /** consumo de gas do carro*/
    private double consumoGas;
    /** consumo de bateria do carro*/
    private double consumoBat;

    /** construtor por omissão*/
    public Hibrido() {
        super();
        this.consumoGas = 0;
        this.consumoBat = 0;
    }
    /** construtor com argumentos*/
    public Hibrido (String marca, String matricula, String nif, double velMed, double precoBase, Ponto localizacao, List<Integer> classificacao, double consumoGas, double consumoBat, double autonomia, boolean dispAlugar) {
        super(marca,matricula,nif,velMed,precoBase,localizacao,classificacao,autonomia,dispAlugar);
        this.consumoGas = consumoGas;
        this.consumoBat = consumoBat;
    }
    /** construtor para clone*/
    public Hibrido (Hibrido umHibrido) {
        super(umHibrido);
        this.consumoGas = umHibrido.getConsumoGas();
        this.consumoBat = umHibrido.getConsumoBat();
    }


    /**getters e setters*/
    public double getConsumoGas() {
        return this.consumoGas;
    }
    public double getConsumoBat() {
        return this.consumoBat;
    }

    public void setConsumoGas(double novoc1) {
        this.consumoGas = novoc1;
    }
    public void setConsumoBat(double novoc1) {
        this.consumoBat = novoc1;
    }

    /** clone*/
    public Hibrido clone() {
        return new Hibrido(this);
    }

    /** passa o carro para uma string*/
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hibrido: \n");
        sb.append(super.toString());
        sb.append(", consumoGas: ").append(this.consumoGas);
        sb.append(", consumoBat: ").append(this.consumoBat);
        return sb.toString()+'\n';
    }

    /**equals*/
    public boolean equals(Object o) {
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        Hibrido aux = (Hibrido) o;
        return super.equals(o) &&
                aux.getConsumoGas()==this.consumoGas &&
                aux.getConsumoBat()==this.consumoBat;
    }

    /** abastece o veículo com uma determinada quantidade*/
    public void abastecer(double quantidade){this.autonomia +=quantidade;}
    /** calcular o preço de uma viagem*/
    public double calcularPreco(double distancia){return this.precoKm*distancia;}
    /** dar update ao carro depois de um aluguer*/
    public void updateCarro(Aluguer a){
        if(a.getCombustivel().equals("Eletrico")){
            this.autonomia -= a.getDistancia()*this.consumoBat;
        }else{
            this.autonomia -= a.getDistancia()*this.consumoGas;
        }
    }
}