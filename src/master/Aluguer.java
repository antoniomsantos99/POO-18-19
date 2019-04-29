package master;

import javafx.util.Pair;

import java.util.Objects;

/**
 * Escreva a descrição da classe master.Aluguer aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 *
 *
 *
 *
 * Exemplo de método - substitua este comentário pelo seu próprio
 *
 * //@param  y   exemplo de um parâmetro de método
 * //@return     a soma de x com y
 *
 *
 * public int sampleMethod(int y) {
 * // ponha seu código aqui
 * return x + y;
}
 *
 */


public class Aluguer {
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private Pair<Double,Double> localAtual;
    private Pair<Double,Double> localFinal;

    /**
     * Construtor para objetos da classe master.Aluguer
     */
    public Aluguer() {
        this.localAtual = new Pair<>(0.0,0.0);
        this.localFinal = new Pair<>(0.0,0.0);
    }
    public Aluguer(Pair<Double,Double> localAtual,Pair<Double,Double> localFinal){
        this.localAtual = localAtual;
        this.localFinal = localFinal;
    }
    public Aluguer(Aluguer a){
        this.localAtual = a.getLocalAtual();
        this.localFinal = a.getLocalFinal();
    }

    public Pair<Double, Double> getLocalAtual() {
        return this.localAtual;
    }
    public Pair<Double, Double> getLocalFinal() {
        return this.localFinal;
    }

    public void setLocalAtual(Pair<Double, Double> localAtual) {
        this.localAtual = localAtual;
    }
    public void setLocalFinal(Pair<Double, Double> localFinal) {
        this.localFinal = localFinal;
    }

    public boolean equals(Object a) {
        if (this == a) return true;
        if (a == null || this.getClass() != a.getClass()) return false;
        Aluguer aluguer = (Aluguer) a;
        return  localAtual.equals(aluguer.localAtual) &&
                localFinal.equals(aluguer.localFinal);
    }
    public Aluguer clone(){
        return new Aluguer(this);
    }
}
