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

public class Estado implements Serializable{

    /**
     * Declarações de variaveis globais para guardar todos os objetos
     */
    private static final String path = "saves/";

    private Map<String, Cliente> listaClientes;
    private Map<String, Proprietario> listaProprietarios;
    private Map<String, Carro> listaCarros;

    private Menu menuLogin;
    private Menu menuRegisto;

    public Estado() {
        String[] opcoesLogin = {"Login Cliente","Login Proprietario"};
        String[] opcoesRegisto = {"Registo Cliente","Registo Proprietario"};
        this.menuLogin = new Menu(opcoesLogin);
        this.menuRegisto = new Menu(opcoesRegisto);

        this.listaClientes = new HashMap<>();
        this.listaProprietarios = new HashMap<>();
        this.listaCarros = new HashMap<>();
    }

    public Estado(Map<String, Cliente> listaClientes, Map<String, Proprietario> listaProprietarios, Map<String, Carro> listaCarros) {
        String[] opcoesLogin = {"Login Cliente","Login Proprietario"};
        String[] opcoesRegisto = {"Registo Cliente","Registo Proprietario"};
        this.menuLogin = new Menu(opcoesLogin);
        this.menuRegisto = new Menu(opcoesRegisto);

        this.listaClientes = listaClientes.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
        this.listaProprietarios = listaProprietarios.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
        this.listaCarros = listaCarros.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    /**
     * tenta fazer o login com um email e uma password
     *
     * @param email    email
     * @param password password
     * @return verdadeiro se conseguir falso se não
     */
    private boolean tryLoginCliente(String email, String password) {
        if(!listaClientes.containsKey(email)) return false;
        Cliente c = listaClientes.get(email);
        return c.getPassword().equals(password);
    }

    /**
     * tenta fazer o login com um email e uma password
     *
     * @param email    email
     * @param password password
     * @return verdadeiro se conseguir falso se não
     */
    private boolean tryLoginProprietario(String email, String password) {
        if(!listaProprietarios.containsKey(email)) return false;
        Proprietario p = listaProprietarios.get(email);
        return p.getPassword().equals(password);
    }

    public void login(){
        String email,password;
        do{
            menuLogin.executa();
            switch(menuLogin.getOpcao()){
                case 1:
                    System.out.println("Introduza as informações seguites para fazer login como Cliente:");
                    email = lerString("Introduza o seu email:");
                    password = lerString("Introduza a sua password:");
                    if(tryLoginCliente(email,password)){
                        System.out.println("Login cliente efetuado com sucesso!");
                    }else{
                        System.out.println("Email ou password erradas");
                    }
                    break;
                case 2:
                    System.out.println("Introduza as informações seguites para fazer login como Proprietario:");
                    email = lerString("Introduza o seu email:");
                    password = lerString("Introduza a sua password:");
                    if(tryLoginProprietario(email,password)){
                        System.out.println("Login proprietario efetuado com sucesso!");
                    }else{
                        System.out.println("Email ou password erradas.");
                    }
                    break;
            }
        }while(menuLogin.getOpcao()!=0);
    }
    /**
     * verifica se um cliente com um nome ou email existe no sistema
     *
     * @param email recebe o mail
     * @return verdadeiro se existir, falso se não
     */
    public boolean verificaCliente(String email) {
        return listaClientes.containsKey(email);
    }

    /**
     * verifica se um proprietario com um nome ou email existe no sistema
     *
     * @param email recebe o mail
     * @return verdadeiro se existir, falso se não
     */
    public boolean verificaProprietario(String email) {
        return listaProprietarios.containsKey(email);
    }

    public void registo(){
        String nome,email,password,morada,dataNascimento;
        int NIF;
        double locX,locY;
        Ponto localizacao;
        do{
            menuRegisto.executa();
            switch(menuRegisto.getOpcao()){
                case 1:
                    System.out.println("Introduza as informações seguites para se registar como Cliente:");
                    email = lerString("Introduza o seu email:");
                    while(verificaCliente(email)){
                        email = lerString("Erro: Email já existe, introduza um email válido.");
                    }
                    nome = lerString("Introduza o seu nome:");
                    NIF = lerInt("Introduza o seu NIF:");
                    password = lerString("Introduza a sua password:");
                    morada = lerString("Introduza a sua morada:");
                    dataNascimento = lerString("Introduza a sua data de nascimento (DD/MM/YYYY):");
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    Cliente c = new Cliente(nome,NIF,email,password,morada,dataNascimento,localizacao,new ArrayList<Integer>(),new ArrayList<Aluguer>());
                    listaClientes.put(email,c);
                    System.out.println("Cliente " + nome + " resgistado com sucesso!");
                    break;
                case 2:
                    System.out.println("Introduza as informações seguites para se registar como Proprietario:");
                    nome = lerString("Introduza o seu nome:");
                    email = lerString("Introduza o seu email:");
                    while(verificaProprietario(email)){
                        email = lerString("Erro: Email já existe, introduza um email válido.");
                    }
                    password = lerString("Introduza a sua password:");
                    morada = lerString("Introduza a sua morada:");
                    dataNascimento = lerString("Introduza a sua data de nascimento (DD/MM/YYYY):");
                    NIF = lerInt("Introduza o seu NIF:");
                    Proprietario p = new Proprietario(nome,NIF,email,password,morada,dataNascimento,new ArrayList<Carro>(),new ArrayList<Integer>(),new ArrayList<Aluguer>());
                    listaProprietarios.put(email,p);
                    System.out.println("Proprietario " + nome + " resgistado com sucesso!");
                    break;
            }
        }while(menuRegisto.getOpcao()<0);
    }

    public Cliente getCliente(String email) {
        return listaClientes.get(email);
    }

    public Proprietario getProprietario(String email) {
        return listaProprietarios.get(email);
    }

    public Carro getCarro(String matricula) {
        return listaCarros.get(matricula);
    }

    public void carregarEstadoTXT() {
        String[] opcoes = {"Carregar Rápido", "Carregar com Nome do ficheiro"};
        Menu m = new Menu(opcoes);
        do {
            m.executa();
            switch (m.getOpcao()) {
                case 1:
                    try {
                        lerFile(path + "state.txt");
                    } catch (FileNotFoundException e) {
                        System.out.println("Erro: Não foi possivel ler o ficheiro.");
                    } catch (IOException io) {
                        System.out.println("Erro: Problema ao tentar ler");
                    }
                    break;
                case 2:
                    Scanner is = new Scanner(System.in);
                    System.out.print("Nome do Ficheiro que pretende carregar (.txt): ");
                    String nomeFile = is.nextLine();
                    try {
                        lerFile(path + nomeFile);
                    } catch (FileNotFoundException e) {
                        System.out.println("Erro: Não foi possivel ler o ficheiro.");
                    } catch (IOException io) {
                        System.out.println("Erro: Problema ao tentar ler");
                    }
                    break;
            }
        } while (m.getOpcao() < 0);

    }

    public void gravarEstadoTXT() {
        String[] opcoes = {"Gravar Rápido", "Gravar com Nome do ficheiro"};
        Menu m = new Menu(opcoes);
        do {
            m.executa();
            switch (m.getOpcao()) {
                case 1:
                    try {
                        escreverFile(path + "state.txt");
                    } catch (FileNotFoundException e) {
                        System.out.println("Erro: Não foi possivel guardar o ficheiro.");
                    }
                    break;
                case 2:
                    Scanner is = new Scanner(System.in);
                    System.out.print("Nome do Ficheiro que pretende gravar (.txt): ");
                    String nomeFile = is.nextLine();
                    try {
                        escreverFile(path + nomeFile);
                    } catch (FileNotFoundException e) {
                        System.out.println("Erro: Não foi possivel guardar o ficheiro.");
                    }
                    break;
            }
        } while (m.getOpcao() < 0);

    }

    private void escreverFile(String file) throws FileNotFoundException {

        PrintWriter fich = new PrintWriter(file);
        for (Cliente c : this.listaClientes.values())
            fich.println(c.toString());
        for (Proprietario p : this.listaProprietarios.values())
            fich.println(p.toString());
        for (Carro c : this.listaCarros.values())
            fich.println(c.toString());

        fich.flush();
        fich.close();
    }

    private void lerFile(String file) throws FileNotFoundException, IOException {
        List<String> linhas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linha;

        while ((linha = br.readLine()) != null) {
            linhas.add(linha);
        }
        br.close();
        System.out.println(linhas);

        linhas.forEach(s -> {
            if (s.equals("NovoProp:")) CVC2Proprietario(s);
            if (s.equals("NovoCliente:")) CVC2Cliente(s);
            if (s.equals("NovoCarro:")) CVC2Carro(s);
        });
    }

    //TODO
    private void CVC2Cliente(String linha) {
        Cliente c = null;

    }

    private void CVC2Proprietario(String linha) {
        Proprietario p = null;

    }

    private void CVC2Carro(String linha) {
        Carro c = null;

    }
    /**
     * Método que guarda em ficheiro de objectos o objecto que recebe a mensagem.
     */
    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException,IOException {
        FileOutputStream fos = new FileOutputStream(path+nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this); //guarda-se o objecto de uma só vez
        oos.flush();
        oos.close();
    }
    /**
     * Método que recupera uma instância de Estado de um ficheiro de objectos.
     * Este método tem de ser um método de classe que devolva uma instância já
     * construída de Estado.
     *
     * @param nomeFicheiro do ficheiro onde está guardado um objecto do tipo Estado
     * @return objecto Estado inicializado
     */

    public static Estado carregaEstado(String nomeFicheiro) throws FileNotFoundException,IOException,ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path+nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Estado e = (Estado) ois.readObject();
        ois.close();
        return e;
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
     * le uma String
     * @param s dá print do parametro que recebe
     * @return devolve a string inicializada
     */
    public String lerString(String s){
        System.out.println(s);
        Scanner sc = new Scanner(System.in);
        String output;
        do{
            try{
                output = sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Erro: Introduza uma valor valido.");
                output = "";
            }
        }while(output.equals(""));
        return output;
    }
    /**
     * le um int
     * @param s dá print do parametro que recebe
     * @return devolve o int inicializado
     */
    public int lerInt(String s){
        System.out.println(s);
        Scanner sc = new Scanner(System.in);
        Integer output;
        do{
            try{
                output = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Erro: Introduza um valor valido.");
                output = null;

            }
        }while(output==null);
        return output;
    }
    /**
     * le um double
     * @param s dá print do parametro que recebe
     * @return devolve o double incializado
     */
    public double lerDouble(String s){
        System.out.println(s);
        Scanner sc = new Scanner(System.in);
        Double output;
        do{
            try{
                output = sc.nextDouble();
            }catch(InputMismatchException e){
                System.out.println("Erro: Introduza um valor valido.");
                output = null;

            }
        }while(output==null);
        return output;
    }

}
