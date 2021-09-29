
class IDGenerator {
    private int count = -1;

    public int gen() {
        count++;
        return count;
    }
}

public class Conta extends Pessoa {
    private String senha;
    private static IDGenerator IDContaGen = new IDGenerator();

    public Conta(String nome, String end, String senha) {
        super(nome, end);
        this.setID(IDContaGen.gen());
        this.senha = senha;
    }

    public void resetSenha(String senha) {
        this.senha = senha;
    }

    public void statusConta(){
        this.statusPessoa();
        System.out.println("Senha: ***"+ this.senha.substring(3));
        System.out.println("ID: "+ getID());
    }
}


