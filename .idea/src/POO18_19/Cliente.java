import java.lang.*;
import java.util.*;
import javafx.util.Pair;
public class Cliente
{
    public String email;
    public String nome;
    public String password;
    public String morada;
    public String dataNascimento;
    private Pair<Double,Double> localizacao;
    private List<Aluguer> historial; 
}
