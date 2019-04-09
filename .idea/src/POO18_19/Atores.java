import java.lang.*;
import java.util.*;
import javafx.util.Pair;
public class Atores
{
    
    //fazer duas classes separadas para cada ator
    public String email;
    public String nome;
    public String password;
    public String morada;
    public String dataNascimento;
    
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
    
 public class Cliente extends Atores 
 {
   private Pair<Double,Double> localizacao;
   private List<Aluguer> historial; 
   
   public Cliente() {
       this.email = "n/a";
       this.nome = "n/a";
       this.password = "n/a";
       this.morada = "n/a";
       this.dataNascimento ="n/a";
       this.localizacao = new Pair(0,0);
       this.historial = new ArrayList<Aluguer>();
   }
   
   public Cliente (String e, String n, String p, String m, String d, Pair<Double,Double> l, List<Aluguer> h) {
       this.email = e;
       this.nome = n;
       this.password = p;
       this.morada = m;
       this.dataNascimento = d;
       this.localizacao = l;
       this.historial = new ArrayList<Aluguer>();
       for(Aluguer a : h)
            this.historial.add(a);
   }
   
   public Cliente (Cliente umCliente) {
       this.email = umCliente.getEmail();
       this.nome = umCliente.getNome();
       this.password = umCliente.getPassword();
       this.morada = umCliente.getMorada();
       this.dataNascimento = umCliente.getDataNascimento();
       this.localizacao = umCliente.getLocalizacao();
       this.historial = umCliente.getHistorial();
   }
   
   public Pair<Double,Double> getLocalizacao() {
       return this.localizacao;
   }
   
   public List<Aluguer> getHistorial() {
       ArrayList<Aluguer> a = new ArrayList<>();
       for(Aluguer s : this.historial)
            a.add(s);
       return a;
   }
   
   public void setLocalizacao (Pair<Double,Double> l) {
       this.localizacao = l;
   }
   
   public void setHistorial (List<Aluguer> h) {
       this.historial = new ArrayList<>();
       for(Aluguer a : h)
            this.historial.add(a);
   }
   
   public Cliente clone() {
       return new Cliente();
   }
   
   public boolean equals (Object o) {
       if(o == this) return true;
       if(o == null || o.getClass() != this.getClass()) return false;
       Cliente c = (Cliente) o;
       return c.getEmail().equals(this.email) &&
              c.getNome().equals(this.nome) && 
              c.getPassword().equals(this.password) &&
              c.getMorada().equals(this.morada) &&
              c.getDataNascimento().equals(this.dataNascimento) &&
              c.getLocalizacao().equals(this.localizacao) &&
              c.getHistorial().equals(this.historial);
   }
   
   public String toString() {
       StringBuilder st = new StringBuilder();
       st.append("Dados do cliente: \n").append("Email: ").append(this.email);
       st.append(", nome: ").append(this.nome).append(", password: ").append(this.password);
       st.append(", morada: ").append(this.morada).append(", data de nascimento: ").append(this.dataNascimento);
       st.append(", localização: ").append(this.localizacao).append(", historial de alugueres: ");
       for(Aluguer a : this.historial)
            st.append(a);
       return st.toString();
   }
 } 
 public class Proprietario extends Atores 
 {
     private int classificacao;
     private List<Aluguer> historico;
     
   public Proprietario () {
       this.email = "n/a";
       this.nome = "n/a";
       this.password = "n/a";
       this.morada = "n/a";
       this.dataNascimento = "n/a";
       this.classificacao = 0;
       this.historico = new ArrayList<>();
   }
   
   public Proprietario (String e, String n, String p, String m, String d, int c, List<Aluguer> h) {
       this.email = e;
       this.nome = n;
       this.password = p;
       this.morada = m;
       this.dataNascimento = d;
       this.classificacao = c;
       this.historico = new ArrayList<Aluguer>();
       for(Aluguer a : h)
            this.historico.add(a);
   }
   
   public Proprietario (Proprietario umProprietario) {
       this.email = umProprietario.getEmail();
       this.nome = umProprietario.getNome();
       this.password = umProprietario.getPassword();
       this.morada = umProprietario.getMorada();
       this.dataNascimento = umProprietario.getDataNascimento();
       this.classificacao = umProprietario.getClassificacao();
       this.historico = umProprietario.getHistorico();
   }
   
   
   public int getClassificacao() {
       return this.classificacao;
   }
   
   public List<Aluguer> getHistorico() {
       ArrayList<Aluguer> a = new ArrayList<>();
       for(Aluguer s : this.historico)
            a.add(s);
       return a;
   }
   
   public void setClassificacao (int c) {
       this.classificacao = c;
   }
   
   public void setHistorico (List<Aluguer> h) {
       this.historico = new ArrayList<>();
       for(Aluguer a : h)
            this.historico.add(a);
   }
   
   public Proprietario clone() {
       return new Proprietario();
   }
   
   public boolean equals (Object o) {
       if(o == this) return true;
       if(o == null || o.getClass() != this.getClass()) return false;
       Proprietario p = (Proprietario) o;
       return p.getEmail().equals(this.email) &&
              p.getNome().equals(this.nome) && 
              p.getPassword().equals(this.password) &&
              p.getMorada().equals(this.morada) &&
              p.getDataNascimento().equals(this.dataNascimento) &&
              p.getClassificacao() == this.classificacao &&
              p.getHistorico().equals(this.historico);
   }
   
   public String toString() {
       StringBuilder st = new StringBuilder();
       st.append("Dados do proprietário: \n").append("Email: ").append(this.email);
       st.append(", nome: ").append(this.nome).append(", password: ").append(this.password);
       st.append(", morada: ").append(this.morada).append(", data de nascimento: ").append(this.dataNascimento);
       st.append(", classificação: ").append(this.classificacao).append(", historial de alugueres: ");
       for(Aluguer a : this.historico)
            st.append(a);
       return st.toString();
   }
 }
}
