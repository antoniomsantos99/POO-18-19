package master;

import javafx.util.Pair;

/**
 * Classe do objeto de aluguer
 *
 * @author Pedro Oliveira
 * @version 30/04/19
 *
 */


public class Aluguer {
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private Proprietario proprietario;
    private Cliente cliente;
    private Carro carro;
    private Pair<Double,Double> localAtual;
    private Pair<Double,Double> localFinal;
    private Double preco;
    private Double distancia;
    private Double tempo;

    /**
     * Construtores
     */
    public Aluguer() {
        this.proprietario = null;
        this.cliente = null;
        this.carro = null;
        this.localAtual = new Pair<>(0.0,0.0);
        this.localFinal = new Pair<>(0.0,0.0);
        this.preco = -1.0;
        this.distancia = -1.0;
        this.tempo = -1.0;
    }
    public Aluguer(Proprietario proprietario, Cliente cliente, Carro carro, Pair<Double, Double> localAtual, Pair<Double, Double> localFinal, Double preco, Double distancia, Double tempo) {
        this.proprietario = proprietario;
        this.cliente = cliente;
        this.carro = carro;
        this.localAtual = localAtual;
        this.localFinal = localFinal;
        this.preco = preco;
        this.distancia = distancia;
        this.tempo = tempo;
    }
    public Aluguer(Aluguer a){
        this.proprietario = a.getProprietario();
        this.cliente = a.getCliente();
        this.carro = a.getCarro();
        this.localAtual = a.getLocalAtual();
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
        return proprietario;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public Carro getCarro() {
        return carro;
    }
    public Pair<Double, Double> getLocalAtual() {
        return localAtual;
    }
    public Pair<Double, Double> getLocalFinal() {
        return localFinal;
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
        this.proprietario = proprietario;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setCarro(Carro carro) {
        this.carro = carro;
    }
    public void setLocalAtual(Pair<Double, Double> localAtual) {
        this.localAtual = localAtual;
    }
    public void setLocalFinal(Pair<Double, Double> localFinal) {
        this.localFinal = localFinal;
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
                aluguer.getLocalAtual().equals(this.getLocalAtual()) &&
                aluguer.getLocalFinal().equals(this.getLocalFinal()) &&
                aluguer.getPreco().equals(this.getPreco()) &&
                aluguer.getDistancia().equals(this.distancia) &&
                aluguer.getTempo().equals(this.tempo);


    }
    public String toString() {
        return "Aluguer: [" +
                "proprietario=" + proprietario +
                ", cliente=" + cliente +
                ", carro=" + carro +
                ", localAtual=" + localAtual +
                ", localFinal=" + localFinal +
                ", preco=" + preco +
                ", distancia=" + distancia +
                ", tempo=" + tempo +
                ']';
    }
    public Aluguer clone(){
        return new Aluguer(this);
    }
}
