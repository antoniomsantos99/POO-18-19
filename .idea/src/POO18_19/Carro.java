import java.lang.*;
import java.util.*;
public class Carro
{
    private String tipo;
    private int velMed;
    private int precoBase;
    private int consumo;
    private List<Aluguer> historico;
    private int classificacao;
    
    public Carro() {
        this.tipo = "n/a";
        this.velMed = 0;
        this.precoBase = 0;
        this.consumo = 0;
        this.historico = new ArrayList<Aluguer>();
        this.classificacao = 0;
    }
    
    public Carro(String t, int v, int p, int c1, List<Aluguer> h, int c2) {
        this.tipo = t;
        this.velMed = v;
        this.precoBase = p;
        this.consumo = c1;
        this.historico = new ArrayList <>();
        for(Aluguer a : h)
            this.historico.add(a);
        this.classificacao = c2;
    }
    
    public Carro (Carro umCarro) {
        this.tipo = umCarro.getTipo();
        this.velMed = umCarro.getVelMed();
        this.precoBase = umCarro.getPrecoBase();
        this.consumo = umCarro.getConsumo();
        this.historico = umCarro.getHistorico();
        this.classificacao = umCarro.getClassificacao();
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public int getVelMed() {
        return this.velMed;
    }
    
    public int getPrecoBase() {
        return this.precoBase;
    }
    
    public int getConsumo() {
        return this.consumo;
    }
    
    public List<Aluguer> getHistorico() {
       ArrayList<Aluguer> a = new ArrayList<>();
       for(Aluguer s : this.historico)
            a.add(s);
       return a;
    }
    
    public int getClassificacao() {
        return this.classificacao;
    }
    
    public void setTipo (String novoT) {
        this.tipo = novoT;
    }
    
    public void setVelMed (int novoV) {
        this.velMed = novoV;
    }
    
    public void setPrecoBase (int novoP) {
        this.precoBase = novoP;
    }
    
    public void setConsumo (int novoC1) {
        this.consumo = novoC1;
    }
    
    public void setHistorico (List<Aluguer> novoH) {
       this.historico = new ArrayList<>();
       for(Aluguer s : novoH)
            this.historico.add(s);
    }
    
    public void setClassificacao (int novoC2) {
        this.classificacao = novoC2;
    }
    
    public Carro clone() {
        return new Carro();
    }
    
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append("Tipo = ").append(this.tipo).append(", a velocidade média é ").append(this.velMed).append("km/h, o preço por km é ").append(this.precoBase).append("euros por km");
        if(tipo.equals("Gasolina")){ 
            StringBuilder sb = new StringBuilder();
            sb.append("Alugueres: ");
            for(Aluguer s : this.historico)
            sb.append(s);
            st.append(", consumo é ").append(this.consumo).append(" Volts por km, o histórico de alugueres é ").append(sb).append(" e a Classificação é de ").append(this.classificacao).append(" valores");
        }
        else if(tipo.equals("Elétrico")){ 
                StringBuilder sb = new StringBuilder();
                sb.append("Alugueres: ");
                for(Aluguer s : this.historico)
                sb.append(s);
                st.append(", consumo é ").append(this.consumo).append(" litros por km, o histórico de alugueres é ").append(sb).append(" e a Classificação é de ").append(this.classificacao).append(" valores");
        }
        else if(tipo.equals("Híbrido")){ 
                StringBuilder sb = new StringBuilder();
                sb.append("Alugueres: ");
                for(Aluguer s : this.historico)
                sb.append(s);
                st.append(", consumo é ").append(this.consumo).append(" litros por km, o histórico de alugueres é ").append(sb).append(" e a Classificação é de ").append(this.classificacao).append(" valores");
        }
        return st.toString();
    }
    
    public boolean equals (Object o) {
        if(o==this) return true;
        if(o==null || o.getClass ()!= this.getClass()) return false;
        Carro aux = (Carro) o;
        return aux.getTipo().equals(this.tipo) 
            && aux.getVelMed() == this.velMed 
            && aux.getPrecoBase() == this.precoBase
            && aux.getConsumo() == this.consumo 
            && aux.getHistorico().equals(this.historico) 
            && aux.getClassificacao() == this.classificacao;
    }
}
