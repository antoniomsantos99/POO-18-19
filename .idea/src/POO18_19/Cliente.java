import java.lang.*;
import java.util.*;
import javafx.util.Pair;
public class Cliente
{
    public String email;
    public String nome;
    public String password;
    public String morada;
    public String dataNascimento;
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
}
