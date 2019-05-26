package master;


import java.io.Serializable;

/**
 * Classe que implementa um Ponto num plano2D.
 * As coordenadas do Ponto sÃ£o inteiras.
 *
 * @author Grupo 21
 * @version 26/05/2019
 */

public class Ponto implements Serializable {

    //variáveis de instÃ¢ncia
    private double x;
    private double y;

    /**
     * Construtor por omissão de Ponto.
     */
    public Ponto() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Construtor parametrizado de Ponto.
     * Aceita como parametros os valores para cada coordenada.
     */
    public Ponto(double cx, double cy) {
        this.x = cx;
        this.y = cy;
    }

    /**
     * Construtor de copia de Ponto.
     * Aceita como parametro outro Ponto e utiliza os mÃ©todos
     * de acesso aos valores das variÃ¡veis de instÃ¢ncia.
     */
    public Ponto(Ponto umPonto) {
        this.x = umPonto.getX();
        //this.x = umPonto.x;
        this.y = umPonto.getY();
    }

    /**
     * Devolve o valor da coordenada em x.
     *
     * @return valor da coordenada x.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Devolve o valor da coordenada em y.
     *
     * @return valor da coordenada y.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Actualiza o valor da coordenada em x.
     *
     * @param novoX novo valor da coordenada em X
     */
    public void setX(double novoX) {
        this.x = novoX;
    }

    /**
     * Actualiza o valor da coordenada em y.
     *
     * @param novoY novo valor da coordenada em Y
     */
    public void setY(double novoY) {
        this.y = novoY;
    }

    /**
     * Metodo que desloca um ponto somando um delta Ã s coordenadas
     * em x e y.
     *
     * @param deltaX valor de deslocamento do x
     * @param deltaY valor de deslocamento do y
     */
    public void deslocamento(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    /**
     * Metodo que soma as componentes do Ponto passado como parÃ¢metro.
     * @param umPonto ponto que Ã© somado ao ponto receptor da mensagem.
     */
    public void somaPonto(Ponto umPonto) {
        this.x += umPonto.getX();
        this.y += umPonto.getY();
    }

    /**
     * Metodo que move o Ponto para novas coordenadas.
     * @param novoX novo valor de x.
     * @param novoY novo valor de y.
     */
    public void movePonto(double novoX, double novoY) {
        this.x = novoX;  // ou setX(cx)
        this.y = novoY;  // ou this.setY(cy)
    }

    /**
     * Metodo que determina se o ponto estÃ¡ no quadrante positivo de x e y
     * @return booleano que Ã© verdadeiro se x>0 e y>0
     */
    public boolean ePositivo() {
        return (this.x > 0 && this.y > 0);
    }

    /**
     * Metodo que determina a distÃ¢ncia de um Ponto a outro.
     * @param umPonto ponto ao qual se quer determinar a distÃ¢ncia
     * @return double com o valor da distÃ¢ncia
     */
    public double distancia(Ponto umPonto) {

        return Math.sqrt(Math.pow(this.x - umPonto.getX(), 2) +
                Math.pow(this.y - umPonto.getY(), 2));
    }


    /**
     * Metodo que determina se dois pontos sÃ£o iguais.
     * @return booleano que Ã© verdadeiro se os valores das duas
     * coordenadas forem iguais
     */
    public boolean iguais(Ponto umPonto) {
        return (this.x == umPonto.getX() && this.y == umPonto.getY());
    }


    /**
     * Metodo que determina se o mÃ³dulo das duas coordenadas Ã© o mesmo.
     * @return true, se as coordenadas em x e y
     * forem iguais em valor absoluto.
     */
    private boolean xIgualAy() {
        return (Math.abs(this.x) == Math.abs(this.y));
    }

    /**
     * Metodo que devolve a representação em String do Ponto.
     * @return String com as coordenadas x e y
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        Ponto p = (Ponto) o;
        return (this.x == p.getX() && this.y == p.getY());

    }


    /**
     * Metodo que faz uma cópia do objecto receptor da mensagem.
     * Para tal invoca o construtor de cópia.
     *
     * @return objecto clone do objecto que recebe a mensagem.
     */

    public Ponto clone() {
        return new Ponto(this);
    }
}