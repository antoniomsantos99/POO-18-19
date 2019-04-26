package master;

import java.lang.*;
import java.util.*;
public class Carro
{

    //eliminar então o tipo
    //aluguer vai ter coordenadas, cliente, propietario, avaliacao do veiculo e do propieatario
    protected int velMed;
    protected int precoBase;
    protected List<Aluguer> historico;
    protected int classificacao;

    public Carro() {
        this.velMed = 0;
        this.precoBase = 0;
        this.historico = new ArrayList<Aluguer>();
        this.classificacao = 0;
    }
    public Carro(int velMed, int precoBase, List<Aluguer> historico, int classificacao) {
        this.velMed = velMed;
        this.precoBase = precoBase;
        this.historico = new ArrayList <>();
        this.historico.addAll(historico);
        this.classificacao = classificacao;
    }
    public Carro(Carro umCarro) {
        this.velMed = umCarro.getVelMed();
        this.precoBase = umCarro.getPrecoBase();
        this.historico = umCarro.getHistorico();
        this.classificacao = umCarro.getClassificacao();
    }

    //DUVIDA NO GETHISTORICO

    public int getVelMed() {
        return this.velMed;
    }
    public int getPrecoBase() {
        return this.precoBase;
    }
    public List<Aluguer> getHistorico() {
        ArrayList<Aluguer> a = new ArrayList<>();
        a.addAll(this.historico);
        return a;
    }
    public int getClassificacao() {
        return this.classificacao;
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

    //DUVIDA NO CLONE

    public Carro clone() {
        return new Carro(this);
    }

    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append("A velocidade média é ").append(this.velMed);
        st.append("km/h, o preço por km é ").append(this.precoBase).append("euros por km");
        st.append("Alugueres: ");
        for(Aluguer s : this.historico)
            st.append(s);
        st.append(", o histórico de alugueres é ").append(st).append(" e a Classificação é de ").append(this.classificacao).append(" valores");
        return st.toString();
    }

    public boolean equals (Object o) {
        if(o==this) return true;
        if(o==null || o.getClass ()!= this.getClass()) return false;
        Carro aux = (Carro) o;
        return aux.getVelMed() == this.velMed
                && aux.getPrecoBase() == this.precoBase
                && aux.getHistorico().equals(this.historico)
                && aux.getClassificacao() == this.classificacao;
    }
}

class Gasolina extends Carro {
    private int consumoGas;

    public Gasolina() {
        super();
        this.consumoGas = 0;
    }
    public Gasolina (int velMed, int precoBase, List<Aluguer> historico, int classificacao, int consumoGas) {
        super(velMed,precoBase,historico,classificacao);
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
    public Eletrico (int velMed, int precoBase, List<Aluguer> historico, int classificacao, int consumoBat) {
        super(velMed,precoBase,historico,classificacao);
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
    public Hibrido (int velMed, int precoBase, List<Aluguer> historico, int classificacao, int consumoGas, int consumoBat) {
        super(velMed,precoBase,historico,classificacao);
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