package master;

import java.io.Serializable;
import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Carro implements Serializable {

    //eliminar então o tipo
    //aluguer vai ter coordenadas, cliente, propietario, avaliacao do veiculo e do propieatario
    protected String marca;
    protected String matricula;
    protected String nif;
    protected int velMed;
    protected int precoBase;
    protected Ponto localizacao;
    protected List<Aluguer> historico;
    protected List<Integer> classificacao;
    protected double autonomia;
    protected boolean dispAlugar;

    public Carro() {
        this.marca = "n/a";
        this.matricula = "n/a";
        this.nif = "n/a";
        this.velMed = 0;
        this.precoBase = 0;
        this.localizacao = new Ponto();
        this.historico = new ArrayList<Aluguer>();
        this.classificacao = new ArrayList<Integer>();
        this.autonomia = 0;
        this.dispAlugar = false;
    }
    public Carro(String marca, String matricula, String nif, int velMed, int precoBase, Ponto localizacao, List<Aluguer> historico, List<Integer> classificacao, double autonomia, boolean dispAlugar) {
        this.marca = marca;
        this.matricula = matricula;
        this.nif = nif;
        this.velMed = velMed;
        this.precoBase = precoBase;
        this.localizacao = localizacao.clone();
        this.historico = new ArrayList <>();
        for(Aluguer a:historico){
            this.historico.add(a.clone());
        }
        this.classificacao = classificacao;
        this.autonomia = autonomia;
        this.dispAlugar = dispAlugar;
    }
    public Carro(Carro umCarro) {
        this.marca = umCarro.getMarca();
        this.matricula = umCarro.getMatricula();
        this.nif = umCarro.getNif();
        this.velMed = umCarro.getVelMed();
        this.precoBase = umCarro.getPrecoBase();
        this.localizacao = umCarro.getLocalizacao();
        this.historico = umCarro.getHistorico();
        this.classificacao = umCarro.getClassificacao();
        this.autonomia = umCarro.getAutonomia();
        this.dispAlugar = umCarro.getDispAlugar();
    }

    public String getMarca(){return this.marca;}
    public String getMatricula(){
        return this.matricula;
    }
    public String getNif(){
        return this.nif;
    }
    public int getVelMed() {
        return this.velMed;
    }
    public int getPrecoBase() {
        return this.precoBase;
    }
    public Ponto getLocalizacao(){return this.localizacao.clone();}
    public List<Aluguer> getHistorico() {
        return this.historico.stream().map(Aluguer::clone).collect(Collectors.toList());
    }
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

    public void setMarca(String marca){this.marca = marca;}
    public void setMatricula (String matricula){
        this.matricula = matricula;
    }
    public void setNIF (String nif){
        this.nif = nif;
    }
    public void setVelMed (int velMed) {
        this.velMed = velMed;
    }
    public void setPrecoBase (int precoBase) {
        this.precoBase = precoBase;
    }
    public void setLocalizacao(Ponto localizacao){
        this.localizacao = localizacao.clone();
    }
    public void setHistorico (List<Aluguer> historico) {
        this.historico = new ArrayList<>();
        for(Aluguer a : historico){
            this.historico.add(a.clone());
        }
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Marca: ").append(this.marca);
        sb.append(", Matricula: ").append(this.matricula);
        sb.append(", nif: ").append(this.nif);
        sb.append(", Velocidade: ").append(this.velMed);
        sb.append(", Preço/km: ").append(this.precoBase);
        sb.append(", Localizacao: ").append(this.localizacao.toString());
        sb.append(", Historico: ");
        for(Aluguer s : this.historico)
            sb.append(s);
        sb.append(", Classificacao: ").append(this.classificacao);
        sb.append(", Autonomia: ").append(this.autonomia);
        sb.append(", DisponivelAlugar: ").append(this.dispAlugar);
        return sb.toString();
    }
    public boolean equals (Object o) {
        if(o==this) return true;
        if(o==null || o.getClass ()!= this.getClass()) return false;
        Carro aux = (Carro) o;
        return  aux.getMarca().equals(this.marca) &&
                aux.getMatricula().equals(this.matricula) &&
                aux.getNif().equals(this.nif) &&
                aux.getVelMed() == this.velMed &&
                aux.getPrecoBase() == this.precoBase &&
                aux.getLocalizacao().equals(this.localizacao) &&
                aux.getHistorico().equals(this.historico) &&
                aux.getClassificacao().equals(this.classificacao) &&
                aux.getAutonomia()==this.autonomia &&
                aux.getDispAlugar()==this.dispAlugar;
    }
    public abstract Carro clone();
}

class Gasolina extends Carro {
    private int consumoGas;

    public Gasolina() {
        super();
        this.consumoGas = 0;
    }
    public Gasolina (String marca, String matricula, String nif, int velMed, int precoBase, Ponto localizacao, List<Aluguer> historico, List<Integer> classificacao, int consumoGas, double autonomia, boolean dispAlugar) {
        super(marca,matricula,nif,velMed,precoBase,localizacao,historico,classificacao,autonomia,dispAlugar);
        this.consumoGas = consumoGas;
    }
    public Gasolina (Gasolina umGasolina) {
        super(umGasolina);
        this.consumoGas = umGasolina.getConsumoGas();
    }

    public int getConsumoGas() {
        return this.consumoGas;
    }

    public void setConsumoGas(int consumoGas) {
        this.consumoGas = consumoGas;
    }

    public Gasolina clone() {
        return new Gasolina(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gasolina: \n");
        sb.append(super.toString());
        sb.append(", consumoGas: ").append(this.consumoGas);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        Gasolina aux = (Gasolina) o;
        return super.equals(o) &&
                aux.getConsumoGas() == this.consumoGas;
    }
}

class Eletrico extends Carro {
    private int consumoBat;

    public Eletrico() {
        super();
        this.consumoBat = 0;
    }
    public Eletrico (String marca, String matricula, String nif, int velMed, int precoBase, Ponto localizacao, List<Aluguer> historico, List<Integer> classificacao, int consumoBat, double autonomia, boolean dispAlugar) {
        super(marca,matricula,nif,velMed,precoBase,localizacao,historico,classificacao,autonomia,dispAlugar);
        this.consumoBat = consumoBat;
    }
    public Eletrico (Eletrico umEletrico) {
        super(umEletrico);
        this.consumoBat = umEletrico.getConsumoBat();
    }

    public int getConsumoBat() {
        return this.consumoBat;
    }

    public void setConsumoBat (int consumoBat) {
        this.consumoBat = consumoBat;
    }

    public Eletrico clone() {
        return new Eletrico(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Eletrico: \n");
        sb.append(super.toString());
        sb.append(", consumoBat: ").append(this.consumoBat);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        Eletrico aux = (Eletrico) o;
        return super.equals(o) &&
                aux.getConsumoBat()==this.consumoBat;
    }
}

class Hibrido extends Carro {
    private int consumoGas;
    private int consumoBat;

    public Hibrido() {
        super();
        this.consumoGas = 0;
        this.consumoBat = 0;
    }
    public Hibrido (String marca, String matricula, String nif, int velMed, int precoBase, Ponto localizacao, List<Aluguer> historico, List<Integer> classificacao, int consumoGas, int consumoBat, double autonomia, boolean dispAlugar) {
        super(marca,matricula,nif,velMed,precoBase,localizacao,historico,classificacao,autonomia,dispAlugar);
        this.consumoGas = consumoGas;
        this.consumoBat = consumoBat;
    }
    public Hibrido (Hibrido umHibrido) {
        super(umHibrido);
        this.consumoGas = umHibrido.getConsumoGas();
        this.consumoBat = umHibrido.getConsumoBat();
    }

    public int getConsumoGas() {
        return this.consumoGas;
    }
    public int getConsumoBat() {
        return this.consumoBat;
    }

    public void setConsumoGas(int novoc1) {
        this.consumoGas = novoc1;
    }
    public void setConsumoBat(int novoc1) {
        this.consumoBat = novoc1;
    }

    public Hibrido clone() {
        return new Hibrido(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hibrido: \n");
        sb.append(super.toString());
        sb.append(", consumoGas: ").append(this.consumoGas);
        sb.append(", consumoBat: ").append(this.consumoBat);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        Hibrido aux = (Hibrido) o;
        return super.equals(o) &&
                aux.getConsumoGas()==this.consumoGas &&
                aux.getConsumoBat()==this.consumoBat;
    }
}