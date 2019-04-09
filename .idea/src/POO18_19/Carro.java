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
    
    public Carro(int v, int p, List<Aluguer> h, int c2) {
        this.velMed = v;
        this.precoBase = p;
        this.historico = new ArrayList <>();
        for(Aluguer a : h)
            this.historico.add(a);
        this.classificacao = c2;
    }
    
    public Carro(Carro umCarro) {
        this.velMed = umCarro.getVelMed();
        this.precoBase = umCarro.getPrecoBase();
        this.historico = umCarro.getHistorico();
        this.classificacao = umCarro.getClassificacao();
    }
    
    public int getVelMed() {
        return this.velMed;
    }
    
    public int getPrecoBase() {
        return this.precoBase;
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
    
    public void setVelMed (int novoV) {
        this.velMed = novoV;
    }
    
    public void setPrecoBase (int novoP) {
        this.precoBase = novoP;
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
        st.append("A velocidade média é ").append(this.velMed).append("km/h, o preço por km é ").append(this.precoBase).append("euros por km");
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
    
    public class Gasolina extends Carro {
          private int consumo;
          
          public Gasolina() {
              super();
              this.consumo = 0;
          }
          
          public Gasolina (int v, int p, List<Aluguer> h,int c1, int c2) {
              super(v,p,h,c1);
              this.consumo = c2;
          }
          
          public Gasolina (Gasolina umGasolina) {
              super(umGasolina);
              this.consumo = getConsumo();
          }
          
          public int getConsumo() {
              return this.consumo;
          }
          
          public void setConsumo (int novoc1) {
               this.consumo = novoc1;
          }
          
          public Gasolina clone() {
              return new Gasolina();
          }
          
          public String toString() {
              StringBuilder st = new StringBuilder();
              st.append(super.toString()).append(", consumo é igual a ").append(this.consumo).append(" litros por km");
              return st.toString();
          }
          
          public boolean equals(Object o) {
              if(o==this) return true;
              else if(o==null || o.getClass()!=this.getClass()) return false;
              Gasolina aux = (Gasolina) o;
              return super.equals(o) && aux.getConsumo()==this.consumo;
          }
   }
   
   public class Hibrido extends Carro {
          private int consumo;
          
          public Hibrido() {
              super();
              this.consumo = 0;
          }
          
          public Hibrido (int v, int p, List<Aluguer> h,int c1, int c2) {
              super(v,p,h,c1);
              this.consumo = c2;
          }
          
          public Hibrido (Hibrido umHibrido) {
              super(umHibrido);
              this.consumo = getConsumo();
          }
          
          public int getConsumo() {
              return this.consumo;
          }
          
          public void setConsumo (int novoc1) {
               this.consumo = novoc1;
          }
          
          public Hibrido clone() {
              return new Hibrido();
          }
          
          public String toString() {
              StringBuilder st = new StringBuilder();
              st.append(super.toString()).append(", consumo é igual a ").append(this.consumo).append(" litros por km");
              return st.toString();
          }
          
          public boolean equals(Object o) {
              if(o==this) return true;
              else if(o==null || o.getClass()!=this.getClass()) return false;
              Hibrido aux = (Hibrido) o;
              return super.equals(o) && aux.getConsumo()==this.consumo;
          }
   }
   
   public class Eletrico extends Carro {
          private int consumo;
          
          public Eletrico() {
              super();
              this.consumo = 0;
          }
          
          public Eletrico (int v, int p, List<Aluguer> h,int c1, int c2) {
              super(v,p,h,c1);
              this.consumo = c2;
          }
          
          public Eletrico (Eletrico umEletrico) {
              super(umEletrico);
              this.consumo = getConsumo();
          }
          
          public int getConsumo() {
              return this.consumo;
          }
          
          public void setConsumo (int novoc1) {
               this.consumo = novoc1;
          }
          
          public Eletrico clone() {
              return new Eletrico();
          }
          
          public String toString() {
              StringBuilder st = new StringBuilder();
              st.append(super.toString()).append(", consumo é igual a ").append(this.consumo).append(" volts por km");
              return st.toString();
          }
          
          public boolean equals(Object o) {
              if(o==this) return true;
              else if(o==null || o.getClass()!=this.getClass()) return false;
              Eletrico aux = (Eletrico) o;
              return super.equals(o) && aux.getConsumo()==this.consumo;
          }
   }
}

