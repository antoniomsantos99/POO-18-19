package master;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe da interface de texto
 *
 * metodos especialmente estaticos
 *
 * @author Pedro Oliveira
 * @version 28/04/2019
 */

public class Estado {

    /**
     * Declarações de variaveis globais para guardar todos os objetos
     */
    private Map<String,Cliente> listaClientes;
    private Map<String,Proprietario> listaProprietarios;
    private Map<String,Carro> listaCarros;

    public Estado(){
        this.listaClientes = new HashMap<>();
        this.listaProprietarios = new HashMap<>();
        this.listaCarros = new HashMap<>();
    }

    public Estado(Map<String,Cliente> listaClientes, Map<String,Proprietario> listaProprietarios, Map<String,Carro> listaCarros){
        this.listaClientes = listaClientes.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
        this.listaProprietarios = listaProprietarios.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
        this.listaCarros = listaCarros.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    /**
     * metodo estatico para adicionar clientes ao programa
     * @param cliente recebe um cliente
     */
    public void addCliente(Cliente cliente){
        listaClientes.put(cliente.getEmail(),cliente);
    }

    /**
     * metodo estatico para adicionar proprietarios ao programa
     * @param proprietario recebe um proprietario
     */
    public void addProprietario(Proprietario proprietario){
        listaProprietarios.put(proprietario.getEmail(),proprietario);
    }

    /**
     * por fazer mas:
     * metodo estatico para adicionar um carro ao programa
     * @param carro carro
     */
    public void addCarro(Carro carro){
        listaCarros.put(carro.matricula,carro);
    }

    /**
     * verifica se um cliente com um nome ou email existe no sistema
     * @param email recebe o mail
     * @return verdadeiro se existir, falso se não
     */
    public boolean verificaCliente(String email){
        return listaClientes.containsKey(email);
    }

    /**
     * verifica se um proprietario com um nome ou email existe no sistema
     * @param email recebe o mail
     * @return verdadeiro se existir, falso se não
     */
    public boolean verificaProprietario(String email){
        return listaProprietarios.containsKey(email);
    }

    /**
     * tenta fazer o login com um email e uma password
     * @param email email
     * @param password password
     * @return verdadeiro se conseguir falso se não
     */
    public boolean tryLoginCliente(String email, String password){
        Cliente c = listaClientes.get(email);
        return c.getPassword().equals(password);
    }

    /**
     * tenta fazer o login com um email e uma password
     * @param email username ou email
     * @param password password
     * @return verdadeiro se conseguir falso se não
     */
    public boolean tryLoginProprietario(String email, String password){
        Proprietario p = listaProprietarios.get(email);
        return p.getPassword().equals(password);
    }


    public Cliente getCliente(String email){
        return listaClientes.get(email);
    }
    public Proprietario getProprietario(String email){
        return listaProprietarios.get(email);
    }
    public Carro getCarro(String matricula){return listaCarros.get(matricula);}

    public void carregarEstado(){
        String[] opcoes = {"Carregar Rápido","Carregar com Nome do ficheiro"};
        Menu m = new Menu(opcoes);
        do{
            m.executa();
            switch(m.getOpcao()){
                case 1:
                    break;
                case 2:
                    break;
            }
        }while(m.getOpcao()!=0);

    }
    public void gravarEstado(){
        String[] opcoes = {"Gravar Rápido","Gravar com Nome do ficheiro"};
        Menu m = new Menu(opcoes);

    }


    //PARA DEBUG APENAS, DEPOIS REMOVER
    public String debugString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Map de Clientes:");
        sb.append(listaClientes.toString());
        sb.append("\nMap de Proprietarios:\n");
        sb.append(listaProprietarios.toString());
        sb.append("\nMap de Carros:\n");
        sb.append(listaClientes.toString());
        return sb.toString();
    }


    /**
     * gets para debug principalmente
     */

    public Map<String, Cliente> getListaClientes() {
        return listaClientes;
    }
    public Map<String, Proprietario> getListaProprietarios() {
        return listaProprietarios;
    }
    public Map<String, Carro> getListaCarros() {
        return listaCarros;
    }
}
