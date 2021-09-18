
public class Pessoa {
    private String nome;
    private String endereço;

    public Pessoa(String nome, String endereço) {
        this.nome = nome;
        this.endereço = endereço;
    }

    public String getNome(){
        return nome;
    }

    public String getEndereço(){
        return endereço;
    }
}

