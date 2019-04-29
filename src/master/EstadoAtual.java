package master;

import java.util.HashMap;
import java.util.Map;

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
    private static Map<String,Cliente> listaClientes = new HashMap();
    private static Map<String,Proprietario> listaProprietarios = new HashMap();
    private static Map<String,Carro> listaCarros = new HashMap();
    private static int nrClientes;
    private static int nrProprietarios;
    private static int nrCarros;

    /**
     * metodo estatico para adicionar clientes ao programa
     * @param cliente recebe um cliente
     */
    public static void addCliente(Cliente cliente){
        nrClientes++;
        listaClientes.put(cliente.getEmail(),cliente);
    }

    /**
     * metodo estatico para adicionar proprietarios ao programa
     * @param proprietario recebe um proprietario
     */
    public static void addProprietario(Proprietario proprietario){
        nrProprietarios++;
        listaProprietarios.put(proprietario.getEmail(),proprietario);
    }

    /**
     * por fazer mas:
     * metodo estatico para adicionar um carro ao programa
     * @param carro carro
     */
    public static void addCarro(Carro carro){
        nrCarros++;
        //vai dar enrro, alterar para algo idetificalvel
        //listaCarros.put(carro.g,carro);
    }

    /**
     * verifica se um cliente com um nome ou email existe no sistema
     * @param email recebe o mail ou username
     * @return verdadeiro se existir, falso se não
     */
    public static boolean verificaCliente(String email){
        return listaClientes.containsKey(email);
    }

    /**
     * verifica se um proprietario com um nome ou email existe no sistema
     * @param email recebe o mail ou username
     * @return verdadeiro se existir, falso se não
     */
    public static boolean verificaProprietario(String email){
        return listaProprietarios.containsKey(email);
    }

    /**
     * tenta fazer o login com um email e uma password
     * @param email username ou email
     * @param password password
     * @return verdadeiro se conseguir falso se não
     */
    public static boolean tryLoginCliente(String email, String password){
        Cliente c = listaClientes.get(email);
        return c.getPassword().equals(password);
    }

    /**
     * tenta fazer o login com um email e uma password
     * @param email username ou email
     * @param password password
     * @return verdadeiro se conseguir falso se não
     */
    public static boolean tryLoginProprietario(String email, String password){
        Proprietario p = listaProprietarios.get(email);
        return p.getPassword().equals(password);
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
    public static Cliente getCliente(String email){
        return listaClientes.get(email);
    }
    public static Proprietario getProprietario(String email){
        return listaProprietarios.get(email);
    }
    //PARA DEBUG APENAS, DEPOIS REMOVER
    public static String debugString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Map de Clientes:");
        sb.append(listaClientes.toString());
        sb.append("\nMap de Proprietarios:\n");
        sb.append(listaProprietarios.toString());
        sb.append("\nMap de Carros:\n");
        sb.append(listaClientes.toString());
        sb.append("\nNumero de Clientes:").append(nrClientes);
        sb.append("\nNumero de Proprietarios:").append(nrProprietarios);
        sb.append("\nNumero de Carros:").append(nrCarros);
        return sb.toString();
    }

    public static Map<String, Cliente> getListaClientes() {
        return listaClientes;
    }
    public static Map<String, Proprietario> getListaProprietarios() {
        return listaProprietarios;
    }
    public static Map<String, Carro> getListaCarros() {
        return listaCarros;
    }
}
