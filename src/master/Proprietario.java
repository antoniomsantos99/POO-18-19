package master;

import java.lang.*;
import java.util.*;

public class Proprietario
{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private String dataNascimento;
    private int classificacao;
    private List<Aluguer> historial;

     public Proprietario() {
       this.email = "n/a";
       this.nome = "n/a";
       this.password = "n/a";
       this.morada = "n/a";
       this.dataNascimento ="n/a";
       this.classificacao = 0;
       this.historial = new ArrayList<Aluguer>();
   }

   public Proprietario (String e, String n, String p, String m, String d, int c, List<Aluguer> h) {
       this.email = e;
       this.nome = n;
       this.password = p;
       this.morada = m;
       this.dataNascimento = d;
       this.classificacao = c;
       this.historial = new ArrayList<Aluguer>();
       for(Aluguer a : h)
            this.historial.add(a);
   }

   public Proprietario (Proprietario umProprietario) {
       this.email = umProprietario.getEmail();
       this.nome = umProprietario.getNome();
       this.password = umProprietario.getPassword();
       this.morada = umProprietario.getMorada();
       this.dataNascimento = umProprietario.getDataNascimento();
       this.classificacao = umProprietario.getClassificacao();
       this.historial = umProprietario.getHistorial();
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

   public int getClassificacao() {
       return this.classificacao;
   }

   public List<Aluguer> getHistorial() {
       ArrayList<Aluguer> a = new ArrayList<>();
       for(Aluguer s : this.historial)
            a.add(s);
       return a;
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

   public void setClassificacao (int c) {
       this.classificacao = c;
   }

   public void setHistorial (List<Aluguer> h) {
       this.historial = new ArrayList<>();
       for(Aluguer a : h)
            this.historial.add(a);
   }

   public Proprietario clone() {
       return new Proprietario();
   }

   public boolean equals (Object o) {
       if(o == this) return true;
       if(o == null || o.getClass() != this.getClass()) return false;
       Proprietario c = (Proprietario) o;
       return c.getEmail().equals(this.email) &&
              c.getNome().equals(this.nome) &&
              c.getPassword().equals(this.password) &&
              c.getMorada().equals(this.morada) &&
              c.getDataNascimento().equals(this.dataNascimento) &&
              c.getClassificacao() == this.classificacao &&
              c.getHistorial().equals(this.historial);
   }

   public String toString() {
       StringBuilder st = new StringBuilder();
       st.append("Dados do cliente: \n").append("Email: ").append(this.email);
       st.append(", nome: ").append(this.nome).append(", password: ").append(this.password);
       st.append(", morada: ").append(this.morada).append(", data de nascimento: ").append(this.dataNascimento);
       st.append(", classificação: ").append(this.classificacao).append(", historial de alugueres: ");
       for(Aluguer a : this.historial)
            st.append(a);
       return st.toString();
   }
}
