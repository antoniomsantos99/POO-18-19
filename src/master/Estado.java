package master;

import java.io.*;
import java.util.*;
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
    private final String path = "saves/";

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
                    try{
                        lerCVC(path+"state.txt");
                    }catch(FileNotFoundException e){
                        System.out.println("Erro: Não foi possivel ler o ficheiro.");
                    }catch(IOException io){
                        System.out.println("Erro: Problema ao tentar ler");
                    }
                    break;
                case 2:
                    Scanner is = new Scanner(System.in);
                    System.out.print("Nome do Ficheiro que pretende carregar (.txt): ");
                    String nomeFile = is.nextLine();
                    try{
                        lerCVC(path+nomeFile);
                    }catch(FileNotFoundException e){
                        System.out.println("Erro: Não foi possivel ler o ficheiro.");
                    }catch(IOException io){
                        System.out.println("Erro: Problema ao tentar ler");
                    }
                    break;
            }
        }while(m.getOpcao()<0);

    }
    public void gravarEstado(){
        String[] opcoes = {"Gravar Rápido","Gravar com Nome do ficheiro"};
        Menu m = new Menu(opcoes);
        do{
            m.executa();
            switch(m.getOpcao()){
                case 1:
                    try{
                        escreverFile(path+"state.txt");
                    }catch(FileNotFoundException e){
                        System.out.println("Erro: Não foi possivel guardar o ficheiro.");
                    }
                    break;
                case 2:
                    Scanner is = new Scanner(System.in);
                    System.out.print("Nome do Ficheiro que pretende gravar (.txt): ");
                    String nomeFile = is.nextLine();
                    try{
                        escreverFile(path+nomeFile);
                    }catch(FileNotFoundException e){
                        System.out.println("Erro: Não foi possivel guardar o ficheiro.");
                    }
                    break;
            }
        }while(m.getOpcao()<0);

    }

    private void escreverFile(String file) throws FileNotFoundException {

        PrintWriter fich = new PrintWriter(file);
        for(Cliente c: this.listaClientes.values())
            fich.println(c.toString());
        for(Proprietario p: this.listaProprietarios.values())
            fich.println(p.toString());
        for(Carro c: this.listaCarros.values())
            fich.println(c.toString());

        fich.flush();
        fich.close();
    }

    private void lerCVC(String file) throws FileNotFoundException, IOException {
        List<String> linhas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linha;

        while( (linha = br.readLine()) != null ) {
            linhas.add(linha);
        }
        br.close();
        System.out.println(linhas);

        linhas.forEach(s -> {
            if(s.equals("NovoProp:")) CVC2Proprietario(s);
            if(s.equals("NovoCliente:")) CVC2Cliente(s);
            if(s.equals("NovoCarro:")) CVC2Carro(s);
        });
    }

    //TODO
    private void CVC2Cliente(String linha){
        Cliente c = null;

    }
    private void CVC2Proprietario(String linha){
        Proprietario p = null;

    }
    private void CVC2Carro(String linha){
        Carro c = null;

    }

    //PARA DEBUG APENAS, DEPOIS REMOVER
    public String debugString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Map de Clientes:\n");
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
