package master;

import java.io.Serializable;
import java.lang.*;

/**
 * Classe do objeto de aluguer
 *
 * @author Pedro Oliveira
 * @version 30/04/19
 *
 */


public class Aluguer implements Serializable {
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private Proprietario proprietario;
    private Cliente cliente;
    private Carro carro;
    private Ponto localInicial;
    private Ponto localFinal;
    private Double preco;
    private Double distancia;
    private Double tempo;

    /**
     * Construtores
     */
    public Aluguer() {
        this.proprietario = new Proprietario();
        this.cliente = new Cliente();
        this.carro = null;
        this.localInicial = new Ponto(0.0,0.0);
        this.localFinal = new Ponto(0.0,0.0);
        this.preco = 0.0;
        this.distancia = 0.0;
        this.tempo = 0.0;
    }
    public Aluguer(Proprietario proprietario, Cliente cliente, Carro carro, Ponto localAtual, Ponto localFinal, Double preco, Double distancia, Double tempo) {
        this.proprietario = proprietario.clone();
        this.cliente = cliente.clone();
        this.carro = carro.clone();
        this.localInicial = localAtual.clone();
        this.localFinal = localFinal.clone();
        this.preco = preco;
        this.distancia = distancia;
        this.tempo = tempo;
    }
    public Aluguer(Aluguer a){
        this.proprietario = a.getProprietario();
        this.cliente = a.getCliente();
        this.carro = a.getCarro();
        this.localInicial = a.getLocalInicial();
        this.localFinal = a.getLocalFinal();
        this.preco = a.getPreco();
        this.distancia = a.getDistancia();
        this.tempo = a.getTempo();
    }

    /**
     * definicao dos gets
     * @return return do que é pedido
     */

    public Proprietario getProprietario() {
        return proprietario.clone();
    }
    public Cliente getCliente() {
        return cliente.clone();
    }
    public Carro getCarro() {
        return carro.clone();
    }
    public Ponto getLocalInicial() {
        return localInicial.clone();
    }
    public Ponto getLocalFinal() {
        return localFinal.clone();
    }
    public Double getPreco() {
        return preco;
    }
    public Double getDistancia() {
        return distancia;
    }
    public Double getTempo() {
        return tempo;
    }

    /**
     * Defenicao dos sets
     * Da set do q for pedido
     */

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario.clone();
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente.clone();
    }
    public void setCarro(Carro carro) {
        this.carro = carro.clone();
    }
    public void setLocalInicial(Ponto localInicial) {
        this.localInicial = localInicial.clone();
    }
    public void setLocalFinal(Ponto localFinal) {
        this.localFinal = localFinal.clone();
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }
    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Aluguer aluguer = (Aluguer) o;
        return  aluguer.getProprietario().equals(this.getProprietario()) &&
                aluguer.getCliente().equals(this.getCliente()) &&
                aluguer.getCarro().equals(this.getCarro()) &&
                aluguer.getLocalInicial().equals(this.getLocalInicial()) &&
                aluguer.getLocalFinal().equals(this.getLocalFinal()) &&
                aluguer.getPreco().equals(this.getPreco()) &&
                aluguer.getDistancia().equals(this.distancia) &&
                aluguer.getTempo().equals(this.tempo);


    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aluguer:\n");
        sb.append("Proprietario: ").append(this.proprietario.toString());
        sb.append(", Cliente: ").append(this.cliente.toString());
        sb.append(", Carro").append(this.carro.toString());
        sb.append(", LocalInicial: ").append(this.localInicial.toString());
        sb.append(", LocalFinal: ").append(this.localFinal.toString());
        sb.append(", Preco: ").append(this.preco);
        sb.append(", Distancia: ").append(this.distancia);
        sb.append(", Tempo: ").append(this.tempo);
        return sb.toString();
    }
    public Aluguer clone(){
        return new Aluguer(this);
    }
}
