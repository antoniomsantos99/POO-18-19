package master;

public interface Alugavel {
    /** abastece o veículo com uma determinada quantidade*/
    public void abastecer(double quantidade);
    /** calcular o preço de uma viagem*/
    public double calcularPreco(double distancia);
    /** dar update ao carro depois de um aluguer*/
    public void updateCarro(Aluguer a);
    /** classificar um carro*/
    public void classficarCarro(int classificacao);
}
