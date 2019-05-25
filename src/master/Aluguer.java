package master;

import java.io.Serializable;
import java.lang.*;
import java.time.LocalDateTime;

/**
 * Classe do objeto de aluguer
 *
 * @author Pedro Oliveira
 * @version 30/04/19
 *
 */


public class Aluguer implements Serializable {
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private String nifProp;
    private String nifCliente;
    private String matricula;
    private Ponto localInicial;
    private Ponto localFinal;
    private double preco;
    private double distancia;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private String combustivel;
    private String preferencia;

    /**
     * Construtores
     */
    public Aluguer() {
        this.nifProp = "n/a";
        this.nifCliente = "n/a";
        this.matricula = "n/a";
        this.localInicial = new Ponto(0.0,0.0);
        this.localFinal = new Ponto(0.0,0.0);
        this.preco = 0.0;
        this.distancia = 0.0;
        this.inicio = LocalDateTime.now();
        this.fim = LocalDateTime.now();
        this.combustivel = "n/a";
        this.preferencia = "n/a";
    }
    public Aluguer(String nifProp, String nifCliente, String matricula, Ponto lI, Ponto lF, double preco, double distancia, LocalDateTime inicio, LocalDateTime fim, String combustivel, String preferencia) {
        this.nifProp = nifProp;
        this.nifCliente = nifCliente;
        this.matricula = matricula;
        this.localInicial = lI.clone();
        this.localFinal = lF.clone();
        this.preco = preco;
        this.distancia = distancia;
        this.inicio = inicio;
        this.fim = fim;
        this.combustivel = combustivel;
        this.preferencia = preferencia;
    }
    public Aluguer(Aluguer a){
        this.nifProp = a.getNifProp();
        this.nifCliente = a.getNifCliente();
        this.matricula = a.getMatricula();
        this.localInicial = a.getLocalInicial();
        this.localFinal = a.getLocalFinal();
        this.preco = a.getPreco();
        this.distancia = a.getDistancia();
        this.inicio = a.getInicio();
        this.fim = a.getFim();
        this.combustivel = a.getCombustivel();
        this.preferencia = a.getPreferencia();
    }

    public String getNifProp() {
        return this.nifProp;
    }
    public void setNifProp(String nifProp) {
        this.nifProp = nifProp;
    }
    public String getNifCliente() {
        return this.nifCliente;
    }
    public void setNifCliente(String nifCliente) {
        this.nifCliente = nifCliente;
    }
    public String getMatricula() {
        return this.matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public Ponto getLocalInicial() {
        return this.localInicial.clone();
    }
    public void setLocalInicial(Ponto localInicial) {
        this.localInicial = localInicial.clone();
    }
    public Ponto getLocalFinal() {
        return this.localFinal.clone();
    }
    public void setLocalFinal(Ponto localFinal) {
        this.localFinal = localFinal.clone();
    }
    public Double getPreco() {
        return this.preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public Double getDistancia() {
        return this.distancia;
    }
    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }
    public LocalDateTime getInicio() {
        return this.inicio;
    }
    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }
    public LocalDateTime getFim() {
        return this.fim;
    }
    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }
    public String getCombustivel() {
        return this.combustivel;
    }
    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
    public String getPreferencia() {
        return this.preferencia;
    }
    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluguer aluguer = (Aluguer) o;
        return  this.nifProp.equals(aluguer.nifProp) &&
                this.nifCliente.equals(aluguer.nifCliente) &&
                this.matricula.equals(aluguer.matricula) &&
                this.localInicial.equals(aluguer.localInicial) &&
                this.localFinal.equals(aluguer.localFinal) &&
                this.preco == aluguer.preco &&
                this.distancia == aluguer.distancia &&
                this.inicio.equals(aluguer.inicio) &&
                this.fim.equals(aluguer.fim) &&
                this.combustivel.equals(aluguer.combustivel) &&
                this.preferencia.equals(aluguer.preferencia);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aluguer: ");
        sb.append("{NifProp: ").append(this.nifProp);
        sb.append(", NifCliente: ").append(this.nifCliente);
        sb.append(", Matricula: ").append(this.matricula);
        sb.append(", LocalInicial: ").append(this.localInicial.toString());
        sb.append(", LocalFinal: ").append(this.localFinal.toString());
        sb.append(", Preco: ").append(this.preco);
        sb.append(", Distancia: ").append(this.distancia);
        sb.append(", TempoInicio: ").append(this.inicio.toString());
        sb.append(", TempoFim: ").append(this.fim.toString());
        sb.append(", Combustivel: ").append(this.combustivel);
        sb.append(", Preferencia: ").append(this.preferencia);
        return sb.toString()+"}\n";
    }
    public Aluguer clone(){
        return new Aluguer(this);
    }
}
