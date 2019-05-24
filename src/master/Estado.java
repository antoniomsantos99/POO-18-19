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
     * Declarações de variaveis para guardar todos os objetos
     */
    private static final String path = "saves/";

    private Map<String, Cliente> listaClientes;
    private Map<String, Proprietario> listaProprietarios;
    private Map<String, Carro> listaCarros;

    private Menu menuLogin;
    private Menu menuRegisto;

    /** construtor por omissao */
    public Estado() {
        String[] opcoesLogin = {"Login Cliente","Login Proprietario"};
        String[] opcoesRegisto = {"Registo Cliente","Registo Proprietario"};
        this.menuLogin = new Menu(opcoesLogin);
        this.menuRegisto = new Menu(opcoesRegisto);

        this.listaClientes = new HashMap<>();
        this.listaProprietarios = new HashMap<>();
        this.listaCarros = new HashMap<>();
    }

    /** construtor por agrumentos */
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

    private void hubCliente(String email){
        String[] options = {"solicitar o aluguer do carro mais próximo das suas coordenadas",
                            "solicitar o aluguer do carro mais barato",
                            "solicitar o aluguer do carro mais barato dentro de uma distância que esteja disposto a percorrer a pé",
                            "solicitar o aluguer de um carro específco",
                            "solicitar o aluguer de um carro com uma autonomia desejada"};
        Menu menuHubCliente = new Menu(options);
        do{
            menuHubCliente.executa();
            switch(menuHubCliente.getOpcao()){
                case 1:
                    break;
            }
        }while(menuHubCliente.getOpcao()!=0);
    }
    private void hubProprietario(String email){
        String[] options = {"Registar novo Carro",
                            "Sinalizar que um dos seus carros está disponível ou não para alugar",
                            "Abastecer um veiculo",
                            "Alterar o preço por km de um veiculo",
                            "Aceitar/rejeitar o aluguer de um determinado cliente",
                            "Registar quanto custou a viagem"};
        Menu menuHubProprietario = new Menu(options);
        do{
            Proprietario p;
            Carro c;
            menuHubProprietario.executa();
            Set<Carro> lsC;
            String matricula;
            switch(menuHubProprietario.getOpcao()){
                case 1:
                    p = listaProprietarios.get(email);
                    c = registoCarro(email);
                    if(c!=null){
                        listaCarros.put(c.getMatricula(),c.clone());
                        p.trocarCarro(c.clone());
                        listaProprietarios.put(email,p.clone());
                    }
                    break;
                case 2:
                    p = listaProprietarios.get(email);
                    lsC = p.getSetCarros();
                    if(lsC.isEmpty()){System.out.println("Erro: Parece que ainda não tem nenhum carro.");}
                    for(Carro carro:lsC){
                        System.out.println("Matricula: " + carro.getMatricula() + " - Disponivel para alugar: " + carro.getDispAlugar());
                    }
                    matricula = lerString("Introduza a matricula do carro que pretende sinalizar: (-1 para cancelar)");
                    if(matricula.equals("-1")) break;
                    while(!listaCarros.containsKey(matricula)){
                        matricula = lerString("Erro: Matricula não existe, introduza uma matricula válida:");
                    }
                    p = listaProprietarios.get(email);
                    c = listaCarros.get(matricula);
                    boolean disponivel = lerBool("1- Sinalizar disponivel para alugar.\n0- Sinalizar não disponivel para alugar?");
                    c.setDispAlugar(disponivel);
                    p.trocarCarro(c.clone());
                    listaCarros.put(c.getMatricula(),c.clone());
                    listaProprietarios.put(p.getEmail(),p.clone());
                    break;
                case 3:
                    p = listaProprietarios.get(email);
                    lsC = p.getSetCarros();
                    if(lsC.isEmpty()){System.out.println("Erro: Parece que ainda não tem nenhum carro.");}
                    for(Carro carro:lsC){
                        System.out.println("Matricula: " + carro.getMatricula() + " - Autonomia: " + carro.getAutonomia());
                    }
                    matricula = lerString("Introduza a matricula do carro que pretende abastecer: (-1 para cancelar)");
                    if(matricula.equals("-1")) break;
                    while(!listaCarros.containsKey(matricula)){
                        matricula = lerString("Erro: Matricula não existe, introduza uma matricula válida:");
                    }
                    p = listaProprietarios.get(email);
                    c = listaCarros.get(matricula);
                    double valorAbatecer = lerDouble("Introduza a quantidade que pretende abastecer:");
                    c.abastecer(valorAbatecer);
                    p.trocarCarro(c.clone());
                    listaCarros.put(c.getMatricula(),c.clone());
                    listaProprietarios.put(p.getEmail(),p.clone());
                    break;
                case 4:
                    p = listaProprietarios.get(email);
                    lsC = p.getSetCarros();
                    if(lsC.isEmpty()){System.out.println("Erro: Parece que ainda não tem nenhum carro.");}
                    for(Carro carro:lsC){
                        System.out.println("Matricula: " + carro.getMatricula() + " - Preço/km: " + carro.getPrecoBase());
                    }
                    matricula = lerString("Introduza a matricula do carro que pretende alterar o preço/km: (-1 para cancelar)");
                    if(matricula.equals("-1")) break;
                    while(!listaCarros.containsKey(matricula)){
                        matricula = lerString("Erro: Matricula não existe, introduza uma matricula válida:");
                    }
                    p = listaProprietarios.get(email);
                    c = listaCarros.get(matricula);
                    double precoKm = lerDouble("Introduza o novo preço/km:");
                    c.setPrecoBase(precoKm);
                    p.trocarCarro(c.clone());
                    listaCarros.put(c.getMatricula(),c.clone());
                    listaProprietarios.put(p.getEmail(),p.clone());
                    break;
            }
        }while(menuHubProprietario.getOpcao()!=0);
    }

    /** realiza a operaçao de login */
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
                        System.out.println("Login efetuado com sucesso!");
                        hubCliente(email);
                    }else{
                        System.out.println("Email ou password erradas");
                    }
                    break;
                case 2:
                    System.out.println("Introduza as informações seguites para fazer login como Proprietario:");
                    email = lerString("Introduza o seu email:");
                    password = lerString("Introduza a sua password:");
                    if(tryLoginProprietario(email,password)){
                        System.out.println("Login efetuado com sucesso!");
                        hubProprietario(email);
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
    /** realiza a operacao de registo*/
    public void registo(){
        String nome,email,password,morada,dataNascimento;
        String nif;
        double locX,locY;
        Ponto localizacao;
        do{
            menuRegisto.executa();
            switch(menuRegisto.getOpcao()){
                case 1:
                    System.out.println("Introduza as informações seguites para se registar como Cliente:");
                    email = lerString("Introduza o seu email:");
                    while(verificaCliente(email)){
                        email = lerString("Erro: Email já existe, introduza um email válido:");
                    }
                    nome = lerString("Introduza o seu nome:");
                    nif = lerString("Introduza o seu nif:");
                    password = lerString("Introduza a sua password:");
                    morada = lerString("Introduza a sua morada:");
                    dataNascimento = lerString("Introduza a sua data de nascimento (DD/MM/YYYY):");
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    Cliente c = new Cliente(nome,nif,email,password,morada,dataNascimento,localizacao,new ArrayList<Integer>(),new ArrayList<Aluguer>());
                    listaClientes.put(email,c);
                    System.out.println("Cliente " + nome + " resgistado com sucesso!");
                    break;
                case 2:
                    System.out.println("Introduza as informações seguites para se registar como Proprietario:");
                    nome = lerString("Introduza o seu nome:");
                    email = lerString("Introduza o seu email:");
                    while(verificaProprietario(email)){
                        email = lerString("Erro: Email já existe, introduza um email válido:");
                    }
                    password = lerString("Introduza a sua password:");
                    morada = lerString("Introduza a sua morada:");
                    dataNascimento = lerString("Introduza a sua data de nascimento (DD/MM/YYYY):");
                    nif = lerString("Introduza o seu NIF:");
                    Proprietario p = new Proprietario(nome,nif,email,password,morada,dataNascimento,new HashSet<Carro>(),new ArrayList<Integer>(),new ArrayList<Aluguer>()); //TODO alterar hashset
                    listaProprietarios.put(email,p);
                    System.out.println("Proprietario " + nome + " resgistado com sucesso!");
                    break;
            }
        }while(menuRegisto.getOpcao()<0);
    }


    private boolean verificaCarro(String matricula){
        return listaCarros.containsKey(matricula);
    }


    private Carro registoCarro(String email){
        String[] options = {"Registar Eletrico","Registar Hibrido","Registar Gasolina"};
        Menu menuCarro = new Menu(options);
        do{
            Carro c;
            String marca, matricula;
            Proprietario proprietario = listaProprietarios.get(email);
            double velMed, precoBase;
            double consumoBateria,consumoGas;
            double autonomia;
            double locX,locY;
            Ponto localizacao;
            boolean dispAlugar;
            menuCarro.executa();
            switch(menuCarro.getOpcao()){
                case 1:
                    System.out.println("Introduza as informações seguintes para resgistar um Carro Eletrico:");
                    marca = lerString("Introduza a marca do carro:");
                    matricula = lerString("Introduza a matricula do carro:");
                    while(verificaCarro(matricula)){
                        matricula = lerString("Erro: Matricula já existe, introduza uma matricula válida:");
                    }
                    velMed = lerDouble("Introduza a velocidade media:");
                    precoBase = lerDouble("Introduza o preco base");
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    consumoBateria = lerDouble("Introduza o consumo da bateria por km");
                    autonomia = lerDouble("Bateria atual (em %)?");
                    dispAlugar = lerBool("Inicialmente disponivel para alugar? (1- sim, 0- não)");
                    c = new Eletrico(marca,matricula,proprietario.getNif(),velMed,precoBase,localizacao,new ArrayList<Aluguer>(),new ArrayList<Integer>(),consumoBateria,autonomia,dispAlugar);
                    return c;
                case 2:
                    System.out.println("Introduza as informações seguintes para resgistar um Carro Hibrido:");
                    marca = lerString("Introduza a marca do carro:");
                    matricula = lerString("Introduza a matricula do carro:");
                    while(verificaCarro(matricula)){
                        matricula = lerString("Erro: Matricula já existe, introduza uma matricula válida:");
                    }
                    velMed = lerDouble("Introduza a velocidade media:");
                    precoBase = lerDouble("Introduza o preco base");
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    consumoBateria = lerDouble("Introduza o consumo da bateria por km");
                    consumoGas = lerDouble("Introduza o consumo de gas por km");
                    autonomia = lerDouble("Autonomia atual?");
                    dispAlugar = lerBool("Inicialmente disponivel para alugar? (1- sim, 0- não)");
                    c = new Hibrido(marca,matricula,proprietario.getNif(),velMed,precoBase,localizacao,new ArrayList<Aluguer>(),new ArrayList<Integer>(),consumoGas,consumoBateria,autonomia,dispAlugar);
                    return c;
                case 3:
                    System.out.println("Introduza as informações seguintes para resgistar um Carro a Gasolina:");
                    marca = lerString("Introduza a marca do carro:");
                    matricula = lerString("Introduza a matricula do carro:");
                    while(verificaCarro(matricula)){
                        matricula = lerString("Erro: Matricula já existe, introduza uma matricula válida:");
                    }
                    velMed = lerDouble("Introduza a velocidade media:");
                    precoBase = lerDouble("Introduza o preco base");
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    consumoGas = lerDouble("Introduza o consumo de gas por km");
                    autonomia = lerDouble("Autonomia atual?");
                    dispAlugar = lerBool("Inicialmente disponivel para alugar? (1- sim, 0- não)");
                    c = new Gasolina(marca,matricula,proprietario.getNif(),velMed,precoBase,localizacao,new ArrayList<Aluguer>(),new ArrayList<Integer>(),consumoGas,autonomia,dispAlugar);
                    return c;
            }
        }while(menuCarro.getOpcao()!=0);
        return null;
    }

    //TODO
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

    //TODO
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
    //TODO
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

    //TODO
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

    //TODO
    private void CVC2Proprietario(String linha) {
        Proprietario p = null;

    }
    //TODO
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
        sb.append(listaCarros.toString());
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
     * le um double
     * @param s dá print do parametro que recebe
     * @return devolve o double incializado
     */
    public double lerDouble(String s){
        System.out.println(s);
        double output = Double.MIN_VALUE;
        while(output==Double.MIN_VALUE){
            output = lerDoubleAux();
        }
        return output;
    }
    private double lerDoubleAux(){
        Scanner sc = new Scanner(System.in);
        double d;
        try{
            d = sc.nextDouble();
        }catch(InputMismatchException e){
            System.out.println("Erro: Introduza um valor valido.");
            d = Double.MIN_VALUE;

        }
        return d;
    }
    private boolean lerBool(String s){
        System.out.println(s);
        boolean lido = false;
        boolean output = false;
        while(!lido){
            int i = lerBoolAux();
            if(i==1){output=true;lido=true;}else if(i==0){lido=true;}
        }
        return output;
    }

    private int lerBoolAux(){
        Scanner sc = new Scanner(System.in);
        int i;
        try{
            i = sc.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Erro: Introduza um valor valido.");
            i = Integer.MIN_VALUE;

        }
        return i;
    }

}
