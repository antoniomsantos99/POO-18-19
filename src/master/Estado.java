package master;

import java.io.*;
import java.time.LocalDateTime;
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

        this.listaClientes = listaClientes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
        this.listaProprietarios = listaProprietarios.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
        this.listaCarros = listaCarros.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
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
        String[] options = {"Solicitar o aluguer do carro mais próximo das suas coordenadas.",
                "Solicitar o aluguer do carro mais barato.",
                "Solicitar o aluguer do carro mais barato dentro de uma distância que esteja disposto a percorrer a pé.",
                "Solicitar o aluguer de um carro específco.",
                "Solicitar o aluguer de um carro com uma autonomia desejada.",
                "Verificar alugueres recentes.",
                "Classificar aluguer recente."};
        Menu menuHubCliente = new Menu(options);
        do{
            Proprietario p;Optional<Proprietario> propAux;
            Carro carro;Optional<Carro> carroAux;
            Cliente cliente;
            double locX,locY;
            double distancia;
            boolean disponivel;
            Ponto localizacao,localizacao2;
            Aluguer a;
            String tipo;
            menuHubCliente.executa();
            switch(menuHubCliente.getOpcao()){
                case 1://TODO classificar proprietario e carro
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    locX = lerDouble("Introduza localizacao para onde se pretende desclocar (x)");
                    locY = lerDouble("Introduza localizacao para onde se pretende desclocar (y)");
                    localizacao2 = new Ponto(locX,locY);
                    /* vai buscar o cliente e altera a sua localizaçao atual*/
                    cliente = listaClientes.get(email);
                    cliente.setLocalizacao(localizacao);
                    /* vai buscar o carro mais proximo das suas cordenadas com autonomia > distancia*5*/
                    carroAux = listaCarros.values().stream()
                            .filter(ca -> ca.getAutonomia()>ca.getLocalizacao().distancia((localizacao2))*5)
                            .min(Comparator.comparing(x -> x.getLocalizacao().distancia(cliente.getLocalizacao())));
                    if(carroAux.isPresent())carro=carroAux.get();else {System.out.println("Erro: Não existe carro com as suas especificações.");break;}
                    distancia = carro.getLocalizacao().distancia(localizacao2);
                    if(carro instanceof Eletrico) tipo = "Eletrico";
                    else if(carro instanceof Hibrido) tipo = "Hibrido";
                    else tipo = "Gasolina";
                    a = new Aluguer(carro.getNif(),
                            cliente.getNif(),
                            carro.getMatricula(),
                            carro.getLocalizacao(),
                            localizacao2,
                            carro.calcularPreco(carro.getLocalizacao().distancia(localizacao2)), //preço base * distancia
                            carro.getLocalizacao().distancia(localizacao2),//distancia
                            LocalDateTime.now(),
                            LocalDateTime.now().plusMinutes(5*(int) distancia),//fim, tempo estimado
                            tipo,
                            "MaisPerto");
                    propAux = listaProprietarios.values().stream()
                            .filter(pr -> pr.getNif().equals(carro.getNif()))
                            .findFirst();
                    if(propAux.isPresent())p=propAux.get();else {System.out.println("Erro: Não existe proprietario com esse carro associado.");break;}
                    System.out.println("Proprietario:"+p.getNif()+" Classificação:"+p.classificacao()+'\n'+a.toString()+"É este aluguer que pretende?");
                    disponivel = lerBool("1- Sim.\n0- Não.");
                    if(!disponivel) break;
                    System.out.println("Pedido enviado com sucesso!");
                    p.addPending(a);
                    break;
                case 2:
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    locX = lerDouble("Introduza localizacao para onde se pretende desclocar (x)");
                    locY = lerDouble("Introduza localizacao para onde se pretende desclocar (y)");
                    localizacao2 = new Ponto(locX,locY);
                    /* vai buscar o cliente e altera a sua localizaçao atual*/
                    cliente = listaClientes.get(email);
                    cliente.setLocalizacao(localizacao);
                    /* vai buscar o carro mais barato */
                    carroAux = listaCarros.values().stream()
                            .filter(ca -> ca.getAutonomia()>ca.getLocalizacao().distancia((localizacao2))*5)
                            .min(Comparator.comparing(Carro::getPrecoKm));
                    if(carroAux.isPresent())carro=carroAux.get();else {System.out.println("Erro: Não existe carro com as suas especificações.");break;}
                    distancia = carro.getLocalizacao().distancia(localizacao2);
                    if(carro instanceof Eletrico) tipo = "Eletrico";
                    else if(carro instanceof Hibrido) tipo = "Hibrido";
                    else tipo = "Gasolina";
                    a = new Aluguer(carro.getNif(),
                            cliente.getNif(),
                            carro.getMatricula(),
                            carro.getLocalizacao(),
                            localizacao2,
                            carro.calcularPreco(carro.getLocalizacao().distancia(localizacao2)), //preço base * distancia
                            carro.getLocalizacao().distancia(localizacao2),//distancia
                            LocalDateTime.now(),
                            LocalDateTime.now().plusMinutes(5*(int) distancia),//fim, tempo estimado
                            tipo,
                            "MaisBarato");
                    propAux = listaProprietarios.values().stream().filter(pr -> pr.getNif().equals(carro.getNif())).findFirst();
                    if(propAux.isPresent())p=propAux.get();else {System.out.println("Erro: Não existe proprietario com esse carro associado.");break;}
                    System.out.println("Proprietario:"+p.getNif()+" Classificação:"+p.classificacao()+'\n'+a.toString()+"É este aluguer que pretende?");
                    disponivel = lerBool("1- Sim.\n0- Não.");
                    if(!disponivel) break;
                    System.out.println("Pedido enviado com sucesso!");
                    p.addPending(a);
                    break;
                case 3:
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    locX = lerDouble("Introduza localizacao para onde se pretende desclocar (x)");
                    locY = lerDouble("Introduza localizacao para onde se pretende desclocar (y)");
                    localizacao2 = new Ponto(locX,locY);
                    double distanciaPercorrer = lerDouble("Introduza a distance que esta disposto a percorrer:");
                    /* vai buscar o cliente e altera a sua localizaçao atual*/
                    cliente = listaClientes.get(email);
                    cliente.setLocalizacao(localizacao);
                    /* vai buscar o carro mais barato */
                    carroAux = listaCarros.values().stream()
                            .filter(ca -> ca.getAutonomia()>ca.getLocalizacao().distancia((cliente.getLocalizacao()))*5 && distanciaPercorrer>ca.getLocalizacao().distancia(cliente.getLocalizacao()))
                            .min(Comparator.comparing(Carro::getPrecoKm));
                    if(carroAux.isPresent())carro=carroAux.get();else {System.out.println("Erro: Não existe carro com as suas especificações.");break;}
                    distancia = carro.getLocalizacao().distancia(localizacao2);
                    if(carro instanceof Eletrico) tipo = "Eletrico";
                    else if(carro instanceof Hibrido) tipo = "Hibrido";
                    else tipo = "Gasolina";
                    a = new Aluguer(carro.getNif(),
                            cliente.getNif(),
                            carro.getMatricula(),
                            carro.getLocalizacao(),
                            localizacao2,
                            carro.calcularPreco(carro.getLocalizacao().distancia(localizacao2)), //preço base * distancia
                            carro.getLocalizacao().distancia(localizacao2),//distancia
                            LocalDateTime.now(),
                            LocalDateTime.now().plusMinutes(5*(int) distancia),//fim, tempo estimado
                            tipo,
                            "MaisBarato");
                    propAux = listaProprietarios.values().stream().filter(pr -> pr.getNif().equals(carro.getNif())).findFirst();
                    if(propAux.isPresent())p=propAux.get();else {System.out.println("Erro: Não existe proprietario com esse carro associado.");break;}
                    System.out.println("Proprietario:"+p.getNif()+" Classificação:"+p.classificacao()+'\n'+a.toString()+"É este aluguer que pretende?");
                    disponivel = lerBool("1- Sim.\n0- Não.");
                    if(!disponivel) break;
                    System.out.println("Pedido enviado com sucesso!");
                    p.addPending(a);
                    break;
                case 4:
                    String matricula = lerString("Introduza a matricula do carro que pretende alugar: (-1 para cancelar)");
                    if(matricula.equals("-1")) break;
                    while(!verificaCarro(matricula)||!listaCarros.get(matricula).getDispAlugar()){
                        matricula = lerString("Erro: Matricula não existe ou carro não está disponível.\nIntroduza outra matricula: (-1 para cancelar)");
                        if(matricula.equals("-1")) break;
                    }
                    if(matricula.equals("-1")) break;
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    locX = lerDouble("Introduza localizacao para onde se pretende desclocar (x)");
                    locY = lerDouble("Introduza localizacao para onde se pretende desclocar (y)");
                    localizacao2 = new Ponto(locX,locY);
                    /* vai buscar o cliente e altera a sua localizaçao atual*/
                    cliente = listaClientes.get(email);
                    cliente.setLocalizacao(localizacao);
                    /* vai buscar o carro mais proximo das suas cordenadas com autonomia > distancia*5*/
                    carro = listaCarros.get(matricula);
                    distancia = carro.getLocalizacao().distancia(localizacao2);
                    if(carro instanceof Eletrico) tipo = "Eletrico";
                    else if(carro instanceof Hibrido) tipo = "Hibrido";
                    else tipo = "Gasolina";
                    a = new Aluguer(carro.getNif(),
                            cliente.getNif(),
                            carro.getMatricula(),
                            carro.getLocalizacao(),
                            localizacao2,
                            carro.calcularPreco(carro.getLocalizacao().distancia(localizacao2)), //preço base * distancia
                            carro.getLocalizacao().distancia(localizacao2),//distancia
                            LocalDateTime.now(),
                            LocalDateTime.now().plusMinutes(5*(int) distancia),//fim, tempo estimado
                            tipo,
                            "MaisPerto");
                    propAux = listaProprietarios.values().stream().filter(pr -> pr.getNif().equals(carro.getNif())).findFirst();
                    if(propAux.isPresent())p=propAux.get();else {System.out.println("Erro: Não existe proprietario com esse carro associado.");break;}
                    System.out.println("Proprietario:"+p.getNif()+" Classificação:"+p.classificacao()+'\n'+a.toString()+"É este aluguer que pretende?");
                    disponivel = lerBool("1- Sim.\n0- Não.");
                    if(!disponivel) break;
                    System.out.println("Pedido enviado com sucesso!");
                    p.addPending(a);
                    break;
                case 5:
                    double autonomia = lerDouble("Introduza a autonomia minima do carro que pretende alugar: (-1 para cancelar)");
                    if(autonomia == -1) break;
                    while(autonomia<0){
                        autonomia = lerDouble("Erro: introduza um valor valido: (-1 para cancelar)");
                        if(autonomia == -1) break;
                    }
                    if(autonomia == -1) break;
                    double autonomiaFinal = autonomia; // dar cast pcausa de ser "final"
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    locX = lerDouble("Introduza localizacao para onde se pretende desclocar (x)");
                    locY = lerDouble("Introduza localizacao para onde se pretende desclocar (y)");
                    localizacao2 = new Ponto(locX,locY);
                    /* vai buscar o cliente e altera a sua localizaçao atual*/
                    cliente = listaClientes.get(email);
                    cliente.setLocalizacao(localizacao);
                    /* vai buscar o carro mais proximo das suas cordenadas com autonomia > distancia*5*/
                    carroAux = listaCarros.values().stream()
                            .filter(car->car.getAutonomia()>autonomiaFinal)
                            .min(Comparator.comparing(Carro::getPrecoKm));
                    if(carroAux.isPresent())carro=carroAux.get();else {System.out.println("Erro: Não existe carro com as suas especificações.");break;}
                    distancia = carro.getLocalizacao().distancia(localizacao2);
                    if(carro instanceof Eletrico) tipo = "Eletrico";
                    else if(carro instanceof Hibrido) tipo = "Hibrido";
                    else tipo = "Gasolina";
                    a = new Aluguer(carro.getNif(),
                            cliente.getNif(),
                            carro.getMatricula(),
                            carro.getLocalizacao(),
                            localizacao2,
                            carro.calcularPreco(carro.getLocalizacao().distancia(localizacao2)), //preço base * distancia
                            carro.getLocalizacao().distancia(localizacao2),//distancia
                            LocalDateTime.now(),
                            LocalDateTime.now().plusMinutes(5*(int) distancia),//fim, tempo estimado
                            tipo,
                            "MaisBarato");
                    propAux = listaProprietarios.values().stream().filter(pr -> pr.getNif().equals(carro.getNif())).findFirst();
                    if(propAux.isPresent())p=propAux.get();else {System.out.println("Erro: Não existe proprietario com esse carro associado.");break;}
                    System.out.println("Proprietario:"+p.getNif()+" Classificação:"+p.classificacao()+'\n'+a.toString()+"É este aluguer que pretende?");
                    disponivel = lerBool("1- Sim.\n0- Não.");
                    if(!disponivel) break;
                    System.out.println("Pedido enviado com sucesso!");
                    p.addPending(a);
                    break;
                case 6:
                    cliente = listaClientes.get(email);
                    List<Aluguer> historico = cliente.getHistorial();
                    if(historico.isEmpty()){System.out.println("Sem alugueres realizados");break;}
                    System.out.println("Alugueres mais recentes:");
                    for(int i = historico.size()-5; i < historico.size();i++){
                        if(i>=0) System.out.println(historico.get(i).toString());
                    }
                    break;
                case 7:
                    try{
                        cliente = listaClientes.get(email);
                        Aluguer alu = cliente.getConcluido();
                        System.out.println("Pretende classificar o proprietario e o carro?");
                        disponivel = lerBool("1- Sim.\n2- Não.");
                        if(disponivel){
                            int classifP = lerInt("Introduza o valor que pretende classificar o proprietario:");
                            int classifC = lerInt("Introduza o valor que pretende classificar o carro:");
                            propAux = listaProprietarios.values().stream().filter(prop -> prop.getNome().equals(alu.getNifProp())).findFirst();
                            carroAux =listaCarros.values().stream().filter(car -> car.getMatricula().equals(alu.getMatricula())).findFirst();
                            propAux.ifPresent(proprietario -> proprietario.classificar(classifP));
                            carroAux.ifPresent(car -> car.classficarCarro(classifC));
                            System.out.println("Classificados com sucesso!");
                            cliente.setConcluido(null);
                        }else{
                            cliente.setConcluido(null);
                        }
                    }catch(noAluguerException e){
                        System.out.println("Sem aluguer por classificar.");
                        break;
                    }
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
                "Registar quanto custou a viagem",
                "Verificar alugueres recentes",
                "Total faturado por uma viatura"};
        Menu menuHubProprietario = new Menu(options);
        do{
            Proprietario p;
            Cliente cliente;
            Carro c;
            Aluguer a;
            menuHubProprietario.executa();
            Set<Carro> lsC;
            String matricula;
            int escolha;
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
                    if(lsC.isEmpty()){System.out.println("Erro: Parece que ainda não tem nenhum carro.");break;}
                    for(Carro carro:lsC){
                        System.out.println("Matricula: " + carro.getMatricula() + " - Disponivel para alugar: " + carro.getDispAlugar());
                    }
                    matricula = lerString("Introduza a matricula do carro que pretende sinalizar: (-1 para cancelar)");
                    if(matricula.equals("-1")) break;
                    while(!listaCarros.containsKey(matricula)){
                        matricula = lerString("Erro: Matricula não existe, introduza uma matricula válida: (-1 para cancelar)");
                        if(matricula.equals("-1")) break;
                    }
                    if(matricula.equals("-1")) break;
                    p = listaProprietarios.get(email);
                    c = listaCarros.get(matricula);
                    boolean disponivel = lerBool("1- Sinalizar disponivel para alugar.\n0- Sinalizar não disponivel para alugar.");
                    c.setDispAlugar(disponivel);
                    p.trocarCarro(c.clone());
                    listaCarros.put(c.getMatricula(),c.clone());
                    listaProprietarios.put(p.getEmail(),p.clone());
                    break;
                case 3:
                    p = listaProprietarios.get(email);
                    lsC = p.getSetCarros();
                    if(lsC.isEmpty()){System.out.println("Erro: Parece que ainda não tem nenhum carro.");break;}
                    for(Carro carro:lsC){
                        System.out.println("Matricula: " + carro.getMatricula() + " - Autonomia: " + carro.getAutonomia());
                    }
                    matricula = lerString("Introduza a matricula do carro que pretende abastecer: (-1 para cancelar)");
                    if(matricula.equals("-1")) break;
                    while(!listaCarros.containsKey(matricula)){
                        matricula = lerString("Erro: Matricula não existe, introduza uma matricula válida: (-1 para cancelar)");
                        if(matricula.equals("-1")) break;
                    }
                    if(matricula.equals("-1")) break;
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
                    if(lsC.isEmpty()){System.out.println("Erro: Parece que ainda não tem nenhum carro.");break;}
                    for(Carro carro:lsC){
                        System.out.println("Matricula: " + carro.getMatricula() + " - Preço/km: " + carro.getPrecoKm());
                    }
                    matricula = lerString("Introduza a matricula do carro que pretende alterar o preço/km: (-1 para cancelar)");
                    if(matricula.equals("-1")) break;
                    while(!listaCarros.containsKey(matricula)){
                        matricula = lerString("Erro: Matricula não existe, introduza uma matricula válida: (-1 para cancelar)");
                        if(matricula.equals("-1")) break;
                    }
                    if(matricula.equals("-1")) break;
                    p = listaProprietarios.get(email);
                    c = listaCarros.get(matricula);
                    double precoKm = lerDouble("Introduza o novo preço/km:");
                    c.setPrecoKm(precoKm);
                    p.trocarCarro(c.clone());
                    listaCarros.put(c.getMatricula(),c.clone());
                    listaProprietarios.put(p.getEmail(),p.clone());
                    break;
                case 5:
                    p = listaProprietarios.get(email);
                    List<Aluguer> pending = p.getPending();
                    if(pending.isEmpty()){System.out.println("Erro: Parece que não tem alugueres pendentes.");break;}
                    for(int i=0;i<pending.size();i++){
                        Aluguer temp = pending.get(i);
                        Optional<Cliente> clienteAux2 = listaClientes.values().stream().filter(abc -> abc.getNif().equals(temp.getNifCliente())).findFirst();
                        if(clienteAux2.isPresent())cliente=clienteAux2.get();else {System.out.println("Erro: O cliente não existe.");break;}
                        int classif = cliente.classificacao();
                        System.out.println((i+1)+" - "+pending.get(i).toString() + "Classificação cliente: "+ classif);
                    }
                    escolha = lerInt("Introduza o aluguer que pretende aceitar ou rejeitar: (-1 para cancelar)");
                    if(escolha == -1) break;
                    while(escolha<1||escolha>pending.size()){
                        escolha = lerInt("Erro: Aluguer não existe, introduza um aluguer válido: (-1 para cancelar)");
                        if(escolha == -1) break;
                    }
                    if(escolha == -1) break;
                    p = listaProprietarios.get(email);
                    a = pending.get(escolha-1);
                    boolean aceite = lerBool("1- Aceitar pedido.\n0- Rejeitar pedido.");
                    if(aceite){
                        p.confirmAluguer(a);//adicionar o aluguer a lista de alugueres
                        Optional<Cliente> clienteAux = this.listaClientes.values().stream().filter(cl -> cl.getNif().equals(a.getNifCliente())).findFirst();
                        if(clienteAux.isPresent())cliente=clienteAux.get();else {System.out.println("Erro: O cliente não existe.");break;}
                        cliente.confirmAluguer(a);//adicionar o aluguer a lista de alugueres
                        p.removerPending(escolha-1);//escolha -1 pcausa d arrays começarem em 0
                        c = this.listaCarros.get(a.getMatricula());
                        c.updateCarro(a);

                        boolean classifica = lerBool("1- Classificar cliente.\n0- Não classificar cliente.");
                        if(classifica){
                            int classificacao = lerInt("Introduza a classficacao: (0-100)");
                            while(classificacao<0||classificacao>100){
                                classificacao = lerInt("Erro: Introduza um valor valido: (0-100)");
                            }
                            cliente.classificar(classificacao);
                            System.out.println("Cliente classificado com sucesso!");
                        }

                        p.trocarCarro(c.clone());
                        cliente.setConcluido(a);
                        listaClientes.put(cliente.getEmail(),cliente.clone());
                        listaCarros.put(c.getMatricula(),c.clone());
                        listaProprietarios.put(p.getEmail(),p.clone());
                    }else{
                        p.removerPending(escolha-1);
                    }
                    break;
                case 6:
                    p = listaProprietarios.get(email);
                    List<Aluguer> alugueres = p.getHistorial();
                    if(alugueres.isEmpty()){System.out.println("Erro: Parece que não tem alugueres.");break;}
                    for(int i=0;i<alugueres.size();i++){
                        System.out.println((i+1)+" - "+alugueres.get(i).toString());
                    }
                    escolha = lerInt("Introduza o aluguer que pretende registar: (-1 para cancelar)");
                    if(escolha == -1) break;
                    while(escolha<1||escolha>alugueres.size()){
                        escolha = lerInt("Erro: Aluguer não existe, introduza um aluguer válido: (-1 para cancelar)");
                        if(escolha == -1) break;
                    }
                    if(escolha == -1) break;
                    //p = listaProprietarios.get(email);
                    a = alugueres.get(escolha);
                    System.out.println("Custo registado com sucesso!");//TODO faço a minima do que é resgistar o valor, mas já tenho o aluguer certo...
                    break;
                case 7:
                    p = listaProprietarios.get(email);
                    List<Aluguer> historico = p.getHistorial();
                    if(historico.isEmpty()){System.out.println("Sem alugueres realizados");break;}
                    System.out.println("Alugueres mais recentes:");
                    for(int i = historico.size()-5; i < historico.size();i++){
                        if(i>=0) System.out.println(historico.get(i).toString());
                    }
                    break;
                case 8:
                    p = listaProprietarios.get(email);
                    lsC = p.getSetCarros();
                    List<Aluguer> historial = p.getHistorial();
                    if(lsC.isEmpty()){System.out.println("Erro: Parece que não tem nenhum carro.");break;}
                    for(Carro carro:lsC){
                        System.out.println("Matricula: "+carro.getMatricula()+",Disponivel Alugar: " + carro.getDispAlugar()+",Preco/Km"+carro.getPrecoKm());
                    }
                    matricula = lerString("Introduza a matricula do carro que pretende ver total faturado: (-1 para cancelar)");
                    if(matricula.equals("-1")) break;
                    while(!listaCarros.containsKey(matricula)){
                        matricula = lerString("Erro: Matricula não existe, introduza uma matricula válida: (-1 para cancelar)");
                        if(matricula.equals("-1")) break;
                    }
                    String cast = matricula;
                    if(matricula.equals("-1")) break;
                    String matTemp = matricula;
                    System.out.println("Total Faturado:");
                    System.out.println(historial.stream().filter(car -> car.getMatricula().equals(matTemp)).mapToDouble(Aluguer::getPreco).sum());
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
                    email = lerString("Introduza o seu email: (-1 para cancelar)");
                    if (email.equals("-1")) break;
                    while(verificaCliente(email)){
                        email = lerString("Erro: Email já existe, introduza um email válido: (-1 para cancelar)");
                        if (email.equals("-1")) break;
                    }
                    if (email.equals("-1")) break;
                    nome = lerString("Introduza o seu nome:");
                    nif = lerString("Introduza o seu nif:");
                    password = lerString("Introduza a sua password:");
                    morada = lerString("Introduza a sua morada:");
                    dataNascimento = lerString("Introduza a sua data de nascimento (DD/MM/YYYY):");
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    Cliente c = new Cliente(nome,nif,email,password,morada,dataNascimento,localizacao,new ArrayList<Integer>(),new ArrayList<Aluguer>());
                    listaClientes.put(email,c.clone());
                    System.out.println("Cliente " + nome + " resgistado com sucesso!");
                    break;
                case 2:
                    System.out.println("Introduza as informações seguites para se registar como Proprietario:");
                    nome = lerString("Introduza o seu nome:");
                    email = lerString("Introduza o seu email: (-1 para cancelar)");
                    if (email.equals("-1")) break;
                    while(verificaProprietario(email)){
                        email = lerString("Erro: Email já existe, introduza um email válido: (-1 para cancelar)");
                        if (email.equals("-1")) break;
                    }
                    if (email.equals("-1")) break;
                    password = lerString("Introduza a sua password:");
                    morada = lerString("Introduza a sua morada:");
                    dataNascimento = lerString("Introduza a sua data de nascimento (DD/MM/YYYY):");
                    nif = lerString("Introduza o seu NIF:");
                    Proprietario p = new Proprietario(nome,nif,email,password,morada,dataNascimento,new TreeSet<Carro>(new ComparaMatriculas()),new ArrayList<Integer>(),new ArrayList<Aluguer>(),new ArrayList<Aluguer>());
                    listaProprietarios.put(email,p.clone());
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
                    matricula = lerString("Introduza a matricula do carro: (-1 para cancelar)");
                    if (matricula.equals("-1")) break;
                    while(verificaCarro(matricula)){
                        matricula = lerString("Erro: Matricula já existe, introduza uma matricula válida: (-1 para cancelar)");
                        if (matricula.equals("-1")) break;
                    }
                    if (matricula.equals("-1")) break;
                    velMed = lerDouble("Introduza a velocidade media:");
                    precoBase = lerDouble("Introduza o preco base");
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    consumoBateria = lerDouble("Introduza o consumo da bateria por km");
                    autonomia = lerDouble("Bateria atual (em %)?");
                    dispAlugar = lerBool("Inicialmente disponivel para alugar? (1- sim, 0- não)");
                    c = new Eletrico(marca,matricula,proprietario.getNif(),velMed,precoBase,localizacao,new ArrayList<Integer>(),consumoBateria,autonomia,dispAlugar);
                    return c;
                case 2:
                    System.out.println("Introduza as informações seguintes para resgistar um Carro Hibrido:");
                    marca = lerString("Introduza a marca do carro:");
                    matricula = lerString("Introduza a matricula do carro: (-1 para cancelar)");
                    if (matricula.equals("-1")) break;
                    while(verificaCarro(matricula)){
                        matricula = lerString("Erro: Matricula já existe, introduza uma matricula válida: (-1 para cancelar)");
                        if (matricula.equals("-1")) break;
                    }
                    if (matricula.equals("-1")) break;
                    velMed = lerDouble("Introduza a velocidade media:");
                    precoBase = lerDouble("Introduza o preco base");
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    consumoBateria = lerDouble("Introduza o consumo da bateria por km");
                    consumoGas = lerDouble("Introduza o consumo de gas por km");
                    autonomia = lerDouble("Autonomia atual?");
                    dispAlugar = lerBool("Inicialmente disponivel para alugar? (1- sim, 0- não)");
                    c = new Hibrido(marca,matricula,proprietario.getNif(),velMed,precoBase,localizacao,new ArrayList<Integer>(),consumoGas,consumoBateria,autonomia,dispAlugar);
                    return c;
                case 3:
                    System.out.println("Introduza as informações seguintes para resgistar um Carro a Gasolina:");
                    marca = lerString("Introduza a marca do carro:");
                    matricula = lerString("Introduza a matricula do carro: (-1 para cancelar)");
                    if (matricula.equals("-1")) break;
                    while(verificaCarro(matricula)){
                        matricula = lerString("Erro: Matricula já existe, introduza uma matricula válida: (-1 para cancelar)");
                        if (matricula.equals("-1")) break;
                    }
                    if (matricula.equals("-1")) break;
                    velMed = lerDouble("Introduza a velocidade media:");
                    precoBase = lerDouble("Introduza o preco base");
                    locX = lerDouble("Introduza a sua localização (x)");
                    locY = lerDouble("Introduza a sua localização (y)");
                    localizacao = new Ponto(locX,locY);
                    consumoGas = lerDouble("Introduza o consumo de gas por km");
                    autonomia = lerDouble("Autonomia atual?");
                    dispAlugar = lerBool("Inicialmente disponivel para alugar? (1- sim, 0- não)");
                    c = new Gasolina(marca,matricula,proprietario.getNif(),velMed,precoBase,localizacao,new ArrayList<Integer>(),consumoGas,autonomia,dispAlugar);
                    return c;
            }
        }while(menuCarro.getOpcao()!=0);
        return null;
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
                    System.out.print("Nome do Ficheiro que pretende gravar: ");
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
                        System.out.println("Erro: Ficheiro não existe.");
                    } catch (IOException io) {
                        System.out.println("Erro: Problema ao tentar ler");
                    }
                    break;
                case 2:
                    Scanner is = new Scanner(System.in);
                    System.out.print("Nome do Ficheiro que pretende carregar: ");
                    String nomeFile = is.nextLine();
                    try {
                        lerFile(path + nomeFile);
                    } catch (FileNotFoundException e) {
                        System.out.println("Erro: Ficheiro não existe.");
                    } catch (IOException io) {
                        System.out.println("Erro: Problema ao tentar ler");
                    }
                    break;
            }
        } while (m.getOpcao() < 0);

    }

    private void lerFile(String file) throws FileNotFoundException, IOException {
        List<String> linhas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linha;

        while ((linha = br.readLine()) != null) {
            linhas.add(linha);
        }
        br.close();

        linhas.forEach(s -> {
            String[] parsed = s.split(":");
            if (parsed[0].equals("NovoProp")) CVC2Proprietario(parsed[1]);
            if (parsed[0].equals("NovoCliente")) CVC2Cliente(parsed[1]);
            if (parsed[0].equals("NovoCarro")) CVC2Carro(parsed[1]);
            if (parsed[0].equals("Aluguer")) CVC2Aluguer(parsed[1]);
            if (parsed[0].equals("Classificar")) CVC2Classificacao(parsed[1]);
        });
    }

    private void CVC2Cliente(String linha) {
        String[] parsed = linha.split(","); //[Nome,Nif,Email,Morada,X,Y]
        Cliente c = new Cliente(parsed[0],
                parsed[1],
                parsed[2],
                parsed[1],
                parsed[3],
                "1/1/1970",
                new Ponto(Double.parseDouble(parsed[4]),Double.parseDouble(parsed[5])),
                new ArrayList<Integer>(),
                new ArrayList<Aluguer>());
        listaClientes.put(c.getEmail(),c.clone());
    }
    private void CVC2Proprietario(String linha) {
        String[] parsed = linha.split(","); //[Nome,Nif,Email,Morada]
        Proprietario p = new Proprietario(parsed[0],
                parsed[1],
                parsed[2],
                parsed[1],
                parsed[3],
                "1/1/1970",
                new TreeSet<Carro>(new ComparaMatriculas()),
                new ArrayList<Integer>(),
                new ArrayList<Aluguer>(),
                new ArrayList<Aluguer>());
        listaProprietarios.put(p.getEmail(), p.clone());
    }
    private void CVC2Carro(String linha) {
        //NovoCarro:Gasolina,Tata,CB-68-97,240536003,62,1.3717524,2.1782432,457,-95.34003,65.17136
        Carro c;
        String[] parsed = linha.split(","); //[Tipo,Marca,Matricula,Nif,VelMedia,PrecoProKm,ConsumoporKm,Autonomia,X,Y]
        String email = parsed[3] + "@gmail.com";

        if (parsed[0].equals("Gasolina")) {
            c = new Gasolina(parsed[1],
                    parsed[2],
                    parsed[3],
                    Double.parseDouble(parsed[4]),
                    5,
                    new Ponto(Double.parseDouble(parsed[8]), Double.parseDouble(parsed[9])),
                    new ArrayList<Integer>(),
                    Double.parseDouble(parsed[6]),
                    Double.parseDouble(parsed[7]),
                    true);
            listaProprietarios.get(email).trocarCarro(c);
            listaCarros.put(c.getMatricula(), c.clone());
        }
        if (parsed[0].equals("Electrico")) {
            c = new Eletrico(parsed[1],
                    parsed[2],
                    parsed[3],
                    Double.parseDouble(parsed[4]),
                    5, //TODO Descobrir o preco base
                    new Ponto(Double.parseDouble(parsed[8]), Double.parseDouble(parsed[9])),
                    new ArrayList<Integer>(),
                    Double.parseDouble(parsed[6]),
                    Double.parseDouble(parsed[7]),
                    true);
            listaProprietarios.get(email).trocarCarro(c);
            listaCarros.put(c.getMatricula(), c);
        }

        if (parsed[0].equals("Hibrido")) {
            c = new Hibrido(parsed[1],
                    parsed[2],
                    parsed[3],
                    Double.parseDouble(parsed[4]),
                    5,
                    new Ponto(Double.parseDouble(parsed[8]),
                            Double.parseDouble(parsed[9])),
                    new ArrayList<Integer>(),
                    Double.parseDouble(parsed[6]),
                    Double.parseDouble(parsed[6]),
                    Double.parseDouble(parsed[7]),
                    true);
            listaProprietarios.get(email).trocarCarro(c);
            listaCarros.put(c.getMatricula(), c);


        }
    }
    private void CVC2Aluguer(String linha){
        String[] parsed = linha.split(","); //[nif cliente, X destino, Y destino, tipoCombustivel , preferencia]
        String email = parsed[0]+"@gmail.com";
        Cliente cliente = listaClientes.get(email);

        if(parsed[3].equals("Gasolina")) {
            Optional<Gasolina> carro;
            if(parsed[4].equals("MaisPerto")){
                /* vai buscar o carro mais proximo das suas cordenadas com autonomia > distancia*5*/
                carro = listaCarros.values().stream()
                        .filter(ca -> ca.getAutonomia() > ca.getLocalizacao().distancia((cliente.getLocalizacao())) * 5 && ca.getDispAlugar() && ca instanceof Gasolina)
                        .map(c -> (Gasolina) c)
                        .min(Comparator.comparing(x -> x.getLocalizacao().distancia(cliente.getLocalizacao())));
            }else{
                /* vai buscar o carro mais barato das suas cordenadas com autonomia > distancia*5*/
                carro = listaCarros.values().stream()
                        .filter(ca -> ca.getAutonomia() > ca.getLocalizacao().distancia((cliente.getLocalizacao())) * 5 && ca.getDispAlugar() && ca instanceof Gasolina)
                        .map(ca -> (Gasolina) ca)
                        .min(Comparator.comparing(x -> x.getPrecoKm() + x.getConsumoGas() * x.localizacao.distancia(new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2])))));
            }
            //se carro existir cria o aluguer, se não sai
            Gasolina carroGasolina;
            if(carro.isPresent())carroGasolina=carro.get();else return;

            Aluguer a = new Aluguer(carroGasolina.getNif(),
                    parsed[0], carroGasolina.getMatricula(),
                    carroGasolina.getLocalizacao(),
                    new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2])),
                    carroGasolina.getPrecoKm() + carroGasolina.getConsumoGas() * carroGasolina.getLocalizacao().distancia(new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2]))),
                    carroGasolina.getLocalizacao().distancia(new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2]))),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    parsed[3],
                    parsed[4]);

            Optional<Proprietario> prop = listaProprietarios.values().stream().filter(p->p.getNif().equals(carroGasolina.getNif())).findAny();
            List<Aluguer> newProp;
            if(prop.isPresent()) newProp = prop.get().getHistorial();else return;
            newProp.add(a);
            prop.get().setHistorial(newProp);


            Optional<Cliente> clie = listaClientes.values().stream().filter(c->c.getNif().equals(carroGasolina.getNif())).findAny();
            List<Aluguer> newClie;
            if(clie.isPresent()) newClie = clie.get().getHistorial();else return;
            newClie.add(a);
            clie.get().setHistorial(newClie);
        }

        if(parsed[3].equals("Electrico")) {
            /* vai buscar o carro mais proximo das suas cordenadas com autonomia > distancia*5*/
            Optional<Eletrico> carro;
            if(parsed[4].equals("MaisPerto")){
                /* vai buscar o carro mais proximo das suas cordenadas com autonomia > distancia*5*/
                carro = listaCarros.values().stream()
                        .filter(ca -> ca.getAutonomia() > ca.getLocalizacao().distancia((cliente.getLocalizacao())) * 5 && ca.getDispAlugar() && ca instanceof Eletrico)
                        .map(c -> (Eletrico) c)
                        .min(Comparator.comparing(x -> x.getLocalizacao().distancia(cliente.getLocalizacao())));
            }else{
                /* vai buscar o carro mais barato das suas cordenadas com autonomia > distancia*5*/
                carro = listaCarros.values().stream()
                        .filter(ca -> ca.getAutonomia() > ca.getLocalizacao().distancia((cliente.getLocalizacao())) * 5 && ca.getDispAlugar() && ca instanceof Eletrico)
                        .map(ca -> (Eletrico) ca)
                        .min(Comparator.comparing(x -> x.getPrecoKm() + x.getConsumoBat() * x.localizacao.distancia(new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2])))));
            }
            //se carro existir cria o aluguer, se não sai
            Eletrico carroEletrico;
            if(carro.isPresent())carroEletrico=carro.get();else return;

            Aluguer a = new Aluguer(carroEletrico.getNif(),
                    parsed[0], carroEletrico.getMatricula(),
                    carroEletrico.getLocalizacao(),
                    new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2])),
                    carroEletrico.getPrecoKm() + carroEletrico.getConsumoBat() * carroEletrico.getLocalizacao().distancia(new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2]))),
                    carroEletrico.getLocalizacao().distancia(new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2]))),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    parsed[3],
                    parsed[4]);

            Optional<Proprietario> prop = listaProprietarios.values().stream().filter(p->p.getNif().equals(carroEletrico.getNif())).findAny();
            List<Aluguer> newProp;
            if(prop.isPresent()) newProp = prop.get().getHistorial();else return;
            newProp.add(a);
            prop.get().setHistorial(newProp);

            Optional<Cliente> clie = listaClientes.values().stream().filter(c->c.getNif().equals(carroEletrico.getNif())).findAny();
            List<Aluguer> newClie;
            if(clie.isPresent()) newClie = clie.get().getHistorial();else return;
            newClie.add(a);
            clie.get().setHistorial(newClie);
        }

        if(parsed[3].equals("Hibrido")) {
            Optional<Hibrido> carro;
            if(parsed[4].equals("MaisPerto")){
                /* vai buscar o carro mais proximo das suas cordenadas com autonomia > distancia*5*/
                carro = listaCarros.values().stream()
                        .filter(ca -> ca.getAutonomia() > ca.getLocalizacao().distancia((cliente.getLocalizacao())) * 5 && ca.getDispAlugar() && ca instanceof Hibrido)
                        .map(c -> (Hibrido) c)
                        .min(Comparator.comparing(x -> x.getLocalizacao().distancia(cliente.getLocalizacao())));
            }else{
                /* vai buscar o carro mais barato das suas cordenadas com autonomia > distancia*5*/
                carro = listaCarros.values().stream()
                        .filter(ca -> ca.getAutonomia() > ca.getLocalizacao().distancia((cliente.getLocalizacao())) * 5 && ca.getDispAlugar() && ca instanceof Hibrido)
                        .map(ca -> (Hibrido) ca)
                        .min(Comparator.comparing(x -> x.getPrecoKm() + x.getConsumoBat() + x.getConsumoGas() * x.localizacao.distancia(new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2])))));
            }
            //se carro existir cria o aluguer, se não sai
            Hibrido carroHibrido;
            if(carro.isPresent())carroHibrido=carro.get();else return;

            Aluguer a = new Aluguer(carroHibrido.getNif(),
                    parsed[0], carroHibrido.getMatricula(),
                    carroHibrido.getLocalizacao(),
                    new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2])),
                    carroHibrido.getPrecoKm() + carroHibrido.getConsumoBat()+carroHibrido.getConsumoGas() * carroHibrido.getLocalizacao().distancia(new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2]))),
                    carroHibrido.getLocalizacao().distancia(new Ponto(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2]))),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    parsed[3],
                    parsed[4]);

            Optional<Proprietario> prop = listaProprietarios.values().stream().filter(p->p.getNif().equals(carroHibrido.getNif())).findAny();
            List<Aluguer> newProp;
            if(prop.isPresent()) newProp = prop.get().getHistorial();else return;
            newProp.add(a);
            prop.get().setHistorial(newProp);

            Optional<Cliente> clie = listaClientes.values().stream().filter(c->c.getNif().equals(carroHibrido.getNif())).findAny();
            List<Aluguer> newClie;
            if(clie.isPresent()) newClie = clie.get().getHistorial();else return;
            newClie.add(a);
            clie.get().setHistorial(newClie);
        }
    }
    private void CVC2Classificacao(String linha) {
        String[] parsed = linha.split(","); //[matricula ou nif (cliente ou prop) , nota (0-100)]
        if (parsed[0].length() == 8) {
            Optional<Carro> carro = listaCarros.values().stream().filter(c -> c.getMatricula().equals(parsed[0])).findAny();
            carro.ifPresent(value->value.classficarCarro(Integer.parseInt(parsed[1])));
        } else {
            Optional<Cliente> cliente = listaClientes.values().stream().filter(c -> c.getNif().equals(parsed[0])).findAny();
            cliente.ifPresent(value -> value.classificar(Integer.parseInt(parsed[1])));
            Optional<Proprietario> prop = listaProprietarios.values().stream().filter(p->p.getNif().equals(parsed[0])).findAny();
            prop.ifPresent(value -> value.classificar(Integer.parseInt(parsed[1])));
        }
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

    public void listagemClientes(){//TODO fix para dar os maiores 10
        List<Cliente> listagem = listaClientes.values().stream().limit(10).collect(Collectors.toList());
        for(Cliente c:listagem){
            System.out.println(c.toString());
        }
    }

    //PARA DEBUG APENAS, DEPOIS REMOVER
    public String debugString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Map de Clientes:\n\n");
        sb.append(listaClientes.toString());
        sb.append("\nMap de Proprietarios:\n\n");
        sb.append(listaProprietarios.toString());
        sb.append("\nMap de Carros:\n\n");
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

    /**
     * le um int
     * @param s dá print do parametro que recebe
     * @return devolve o int incializado
     */
    public int lerInt(String s){
        System.out.println(s);
        int output = Integer.MIN_VALUE;
        while(output==Integer.MIN_VALUE){
            output = lerIntAux();
        }
        return output;
    }
    private int lerIntAux(){
        Scanner sc = new Scanner(System.in);
        int d;
        try{
            d = sc.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Erro: Introduza um valor valido.");
            d = Integer.MIN_VALUE;

        }
        return d;
    }

    /**
     * le um bool
     * @param s dá print do parametro que recebe
     * @return devolve o bool incializado
     */
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
