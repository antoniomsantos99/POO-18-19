package master;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe da interface de texto
 *
 * metodos especialmente estaticos
 *
 * @author Pedro Oliveira
 * @version 28/04/2019
 */


public class EstadoAtual {

    /**
     * Declarações de variaveis globais para guardar todos os objetos
     */
    private static List<Cliente> listaClientes = new ArrayList();
    private static List<Proprietario> listaProprietarios = new ArrayList();
    private static List<Carro> listaCarros = new ArrayList();
    private static int nrClientes;
    private static int nrProprietarios;
    private static int nrCarros;

    /**
     * metodo estatico para adicionar clientes ao programa
     * @param cliente recebe um cliente
     */
    public static void addCliente(Cliente cliente){
        nrProprietarios++;
        listaClientes.add(cliente);
    }

    /**
     * metodo estatico para adicionar proprietarios ao programa
     * @param proprietario recebe um proprietario
     */
    public static void addProprietario(Proprietario proprietario){
        nrProprietarios++;
        listaProprietarios.add(proprietario);
    }

    /**
     * por fazer mas:
     * metodo estatico para adicionar um carro ao programa
     * @param carro carro
     */
    public static void addCarro(Carro carro){
        nrCarros++;
        listaCarros.add(carro);
    }

    /**
     * verifica se um cliente com um nome ou email existe no sistema
     * @param userOuEmail recebe o mail ou username
     * @return verdadeiro se existir, falso se não
     */
    public static boolean verificaCliente(String userOuEmail){
        for(Cliente c:listaClientes){
            if(c.getNome().equals(userOuEmail)){return true;}
            if(c.getEmail().equals(userOuEmail)){return true;}
        }
        return false;
    }

    /**
     * verifica se um proprietario com um nome ou email existe no sistema
     * @param userOuEmail recebe o mail ou username
     * @return verdadeiro se existir, falso se não
     */
    public static boolean verificaProprietario(String userOuEmail){
        for(Proprietario p:listaProprietarios){
            if(p.getNome().equals(userOuEmail)){return true;}
            if(p.getEmail().equals(userOuEmail)){return true;}
        }
        return false;
    }

    /**
     * tenta fazer o login com um email e uma password
     * @param userOuEmail username ou email
     * @param password password
     * @return verdadeiro se conseguir falso se não
     */
    public static boolean tryLoginCliente(String userOuEmail, String password){
        for(Cliente p:listaClientes){
            if(p.getNome().equals(userOuEmail)){
                return(p.getPassword().equals(password));
            }
            if(p.getEmail().equals(userOuEmail)){
                return(p.getPassword().equals(password));
            }
        }
        return false;
    }

    /**
     * tenta fazer o login com um email e uma password
     * @param userOuEmail username ou email
     * @param password password
     * @return verdadeiro se conseguir falso se não
     */
    public static boolean tryLoginProprietario(String userOuEmail, String password){
        for(Proprietario p:listaProprietarios){
            if(p.getNome().equals(userOuEmail)){
                return(p.getPassword().equals(password));
            }
            if(p.getEmail().equals(userOuEmail)){
                return(p.getPassword().equals(password));
            }
        }
        return false;
    }

    /**
     * gets para debug principalmente
     */
    public static int getNrClientes() {
        return nrClientes;
    }
    public static int getNrProprietarios() {
        return nrProprietarios;
    }
    public static int getNrCarros() {
        return nrCarros;
    }

}
