package master;

import java.lang.*;
import java.util.*;


//DUVIDA CARRO TEM MATRICULA?
//DUVIDA CARRO TEM PROPRIETARIO ASSICIADO?
//DUVIDA PROPRIETARIO TEM LISTA DE CARROS ASSOCIADOS?
//DUVIDA CLIENTE TEM CLASSIFICAÇÃO?
//DUVIDAS NOS ENRROS

public class Carro {

    //eliminar então o tipo
    //aluguer vai ter coordenadas, cliente, propietario, avaliacao do veiculo e do propieatario
    protected String matricula;
    protected Proprietario proprietario;
    protected int velMed;
    protected int precoBase;
    protected List<Aluguer> historico;
    protected int classificacao;

    public Carro() {
        this.matricula = "";
        this.proprietario = null;
        this.velMed = 0;
        this.precoBase = 0;
        this.historico = new ArrayList<Aluguer>();
        this.classificacao = 0;
    }
    public Carro(String matricula, Proprietario proprietario, int velMed, int precoBase, List<Aluguer> historico, int classificacao) {
        this.matricula = matricula;
        this.proprietario = proprietario;
        this.velMed = velMed;
        this.precoBase = precoBase;
        this.historico = new ArrayList <>();
        this.historico.addAll(historico);
        this.classificacao = classificacao;
    }
    public Carro(Carro umCarro) {
        this.matricula = umCarro.getMatricula();
        this.proprietario = umCarro.getProprietario();
        this.velMed = umCarro.getVelMed();
        this.precoBase = umCarro.getPrecoBase();
        this.historico = umCarro.getHistorico();
        this.classificacao = umCarro.getClassificacao();
    }

    public String getMatricula(){
        return this.matricula;
    }
    public Proprietario getProprietario(){
        return this.proprietario;
    }
    public int getVelMed() {
        return this.velMed;
    }
    public int getPrecoBase() {
        return this.precoBase;
    }
    public List<Aluguer> getHistorico() {
        return new ArrayList<>(this.historico);
    }
    public int getClassificacao() {
        return this.classificacao;
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
    public void setHistorico (List<Aluguer> novoH) {
        this.historico = new ArrayList<>();
        this.historico.addAll(novoH);
    }
    public void setClassificacao (int classificacao) {
        this.classificacao = classificacao;
    }

    public Carro clone() {
        return new Carro(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nDados do carro: \n");
        sb.append("Matricula: ").append(this.matricula);
        sb.append(", Proprietario: \n").append(this.proprietario.toString());
        sb.append(", Velocidade: ").append(this.velMed);
        sb.append(", Preço/km: ").append(this.precoBase);
        sb.append(", Alugueres: ");
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
                aux.getHistorico().equals(this.historico) &&
                aux.getClassificacao() == this.classificacao;
    }
}

//DAR UPDATE AO EQUALS

class Gasolina extends Carro {
    private int consumoGas;

    public Gasolina() {
        super();
        this.consumoGas = 0;
    }
    public Gasolina (String matricula, Proprietario proprietario, int velMed, int precoBase, List<Aluguer> historico, int classificacao, int consumoGas) {
        super(matricula,proprietario,velMed,precoBase,historico,classificacao);
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
        StringBuilder st = new StringBuilder();
        st.append(super.toString()).append(", consumoGas é igual a ").append(this.consumoGas).append(" litros por km");
        return st.toString();
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
    public Eletrico (String matricula, Proprietario proprietario, int velMed, int precoBase, List<Aluguer> historico, int classificacao, int consumoBat) {
        super(matricula,proprietario,velMed,precoBase,historico,classificacao);
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
        StringBuilder st = new StringBuilder();
        st.append(super.toString()).append(", consumoBat é igual a ").append(this.consumoBat).append(" volts por km");
        return st.toString();
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
    public Hibrido (String matricula, Proprietario proprietario, int velMed, int precoBase, List<Aluguer> historico, int classificacao, int consumoGas, int consumoBat) {
        super(matricula,proprietario,velMed,precoBase,historico,classificacao);
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
        StringBuilder st = new StringBuilder();
        st.append(super.toString()).append(", consumoGas é igual a ").append(this.consumoGas).append(" litros por km");
        st.append(super.toString()).append(", consumoBat é igual a ").append(this.consumoBat).append(" energias por km");
        return st.toString();
    }

    public boolean equals(Object o) {
        if(o==this) return true;
        else if(o==null || o.getClass()!=this.getClass()) return false;
        Hibrido aux = (Hibrido) o;
        return super.equals(o) && aux.getConsumoGas()==this.consumoGas && aux.getConsumoBat()==this.consumoBat;
    }
}