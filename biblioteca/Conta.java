
public class Conta extends Pessoa {
    private String senha;
    private static IDGenerator IDContaGen = new IDGenerator();
    private final int MAX_EMPRESTIMO = 5;
    private Emprestimo emprestimos[] = new Emprestimo[MAX_EMPRESTIMO];

    public Conta(String nome, String end, String senha) {
        super(nome, end);
        this.setID(IDContaGen.gen());
        this.senha = senha;
    }

    public void resetSenha(String senha) {
        this.senha = senha;
    }

    public void addEmprestimo(int itemID) {
        for (int i = 0; i < MAX_EMPRESTIMO; i++) {
            if (this.emprestimos[i] == null) {
                this.emprestimos[i] = new Emprestimo(itemID);
                return;
            }
        }
    }

    public void removeEmprestimo(int itemID) {
        for (int i = 0; i < MAX_EMPRESTIMO; i++) {
            if (
                    this.emprestimos[i] != null && 
                    this.emprestimos[i].itemID == itemID) {
                this.emprestimos[i] = null;
                return;
            }
        }
    }

    public Emprestimo getEmprestimo(int itemID) {
        for (int i = 0; i < MAX_EMPRESTIMO; i++) {
            if (
                    this.emprestimos[i] != null && 
                    this.emprestimos[i].itemID == itemID) {
                return this.emprestimos[i];
            }
        }
        return null;
    }

    public void statusConta(){
        this.statusPessoa();
        System.out.println("Senha: ***"+ this.senha.substring(3));
        System.out.println("ID: "+ getID());
    }
}


