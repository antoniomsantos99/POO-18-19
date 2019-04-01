import java.lang.*;
import java.util.*;
import javafx.util.Pair;
public class Atores
{
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
 }
}
