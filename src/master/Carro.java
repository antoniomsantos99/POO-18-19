package master;

import java.io.Serializable;
import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Carro implements Serializable {

    //eliminar então o tipo
    //aluguer vai ter coordenadas, cliente, propietario, avaliacao do veiculo e do propieatario
    protected String matricula;
    protected Proprietario proprietario;
    protected int velMed;
    protected int precoBase;
    protected Ponto localizacao;
    protected List<Aluguer> historico;
    protected List<Integer> classificacao;

    public Carro() {
        this.matricula = "";
        this.proprietario = new Proprietario();
        this.velMed = 0;
        this.precoBase = 0;
        this.localizacao = new Ponto();
        this.historico = new ArrayList<Aluguer>();
        this.classificacao = new ArrayList<Integer>();
    }
    public Carro(String matricula, Proprietario proprietario, int velMed, int precoBase, Ponto localizacao, List<Aluguer> historico, List<Integer> classificacao) {
        this.matricula = matricula;
        this.proprietario = proprietario.clone();
        this.velMed = velMed;
        this.precoBase = precoBase;
        this.localizacao = localizacao.clone();
        this.historico = new ArrayList <>();
        for(Aluguer a:historico){
            this.historico.add(a.clone());
        }
        this.classificacao = classificacao;
    }
    public Carro(Carro umCarro) {
        this.matricula = umCarro.getMatricula();
        this.proprietario = umCarro.getProprietario();
        this.velMed = umCarro.getVelMed();
        this.precoBase = umCarro.getPrecoBase();
        this.localizacao = umCarro.getLocalizacao();
        this.historico = umCarro.getHistorico();
        this.classificacao = umCarro.getClassificacao();
    }

    public String getMatricula(){
        return this.matricula;
    }
    public Proprietario getProprietario(){
        return this.proprietario.clone();
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

    public void setMatricula (String matricula){
        this.matricula = matricula;
    }
    public void setProprietario (Proprietario proprietario){
        this.proprietario = proprietario;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matricula: ").append(this.matricula);
        sb.append(", Proprietario: ").append(this.proprietario.toString());
        sb.append(", Velocidade: ").append(this.velMed);
        sb.append(", Preço/km: ").append(this.precoBase);
        sb.append(", Localizacao: ").append(this.localizacao.toString());
        sb.append(", Historico: ");
        for(Aluguer s : this.historico)
            sb.append(s);
        sb.append(", Classificacao: ").append(this.classificacao);
        return sb.toString();
    }
    public boolean equals (Object o) {
        if(o==this) return true;
        if(o==null || o.getClass ()!= this.getClass()) return false;
        Carro aux = (Carro) o;
        return  aux.getMatricula().equals(this.matricula) &&
                aux.getProprietario().equals(this.proprietario) &&
                aux.getVelMed() == this.velMed &&
                aux.getPrecoBase() == this.precoBase &&
                aux.getLocalizacao().equals(this.localizacao) &&
                aux.getHistorico().equals(this.historico) &&
                aux.getClassificacao().equals(this.classificacao);
    }
    public abstract Carro clone();
}

class Gasolina extends Carro {
    private int consumoGas;

    public Gasolina() {
        super();
        this.consumoGas = 0;
    }
    public Gasolina (String matricula, Proprietario proprietario, int velMed, int precoBase, Ponto localizacao, List<Aluguer> historico, List<Integer> classificacao, int consumoGas) {
        super(matricula,proprietario,velMed,precoBase,localizacao,historico,classificacao);
        this.consumoGas = consumoGas;
    }
    public Gasolina (Gasolina umGasolina) {
        super(umGasolina);
        this.consumoGas = getConsumoGas();
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
        return super.equals(o) && aux.getConsumoGas()==this.consumoGas;
    }
}

class Eletrico extends Carro {
    private int consumoBat;

    public Eletrico() {
        super();
        this.consumoBat = 0;
    }
    public Eletrico (String matricula, Proprietario proprietario, int velMed, int precoBase, Ponto localizacao, List<Aluguer> historico, List<Integer> classificacao, int consumoBat) {
        super(matricula,proprietario,velMed,precoBase,localizacao,historico,classificacao);
        this.consumoBat = consumoBat;
    }
    public Eletrico (Eletrico umEletrico) {
        super(umEletrico);
        this.consumoBat = getConsumoBat();
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
        return super.equals(o) && aux.getConsumoBat()==this.consumoBat;
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
    public Hibrido (String matricula, Proprietario proprietario, int velMed, int precoBase, Ponto localizacao, List<Aluguer> historico, List<Integer> classificacao, int consumoGas, int consumoBat) {
        super(matricula,proprietario,velMed,precoBase,localizacao,historico,classificacao);
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
        return super.equals(o) && aux.getConsumoGas()==this.consumoGas && aux.getConsumoBat()==this.consumoBat;
    }
}