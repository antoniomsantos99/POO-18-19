package master;

public class LoginRegisto {


    private Menu menuLoginRegisto;


    public LoginRegisto(){
        String[] opcoes = {"Registar","Login"};
        this.menuLoginRegisto = new Menu(opcoes);
    }


    public void run(Estado e){
        do{
            menuLoginRegisto.executa();
            switch(menuLoginRegisto.getOpcao()){
                case 1:
                    registo(e);
                    break;
                case 2:
                    login(e);
                    break;
            }
        }while(menuLoginRegisto.getOpcao()!=0);
    }


    private void login(Estado e){
        String[] opcoes = {"Login Cliente","Login Proprietario"};
        Menu menuLogin = new Menu(opcoes);
        do{
            menuLogin.executa();
            switch(menuLogin.getOpcao()){
                case 1:
                    System.out.println("Tentou login cliente");
                    break;
                case 2:
                    System.out.println("Tentou login proprietario");
                    break;
            }
        }while(menuLogin.getOpcao()!=0);

    }
    private void registo(Estado e){
        String[] opcoes = {"Registo Cliente","Registo Proprietario"};
        Menu menuRegisto = new Menu(opcoes);
        do{
            menuRegisto.executa();
            switch(menuRegisto.getOpcao()){
                case 1:
                    System.out.println("Tentou registo cliente");
                    break;
                case 2:
                    System.out.println("Tentou registo proprietario");
                    break;
            }
        }while(menuRegisto.getOpcao()<0);

    }
}
