
import java.util.Date;

public class Biblioteca {
    private final int MAX_ITENS = 100;
    private final int MAX_LIVROS = 100;
    private final int MAX_CONTAS = 100;
    private final int MAX_AUTORES = 100;
    private String nome;
    private String endereço;
    private Item itensRepo[] = new Item[MAX_ITENS];
    private Livro livrosRepo[] = new Livro[MAX_LIVROS];
    private Conta contasRepo[] = new Conta[MAX_CONTAS];
    private Pessoa autoresRepo[] = new Pessoa[MAX_AUTORES];

    public Biblioteca(String nome, String endereço) {
        this.nome = nome;
        this.endereço = endereço;
    }

    public void addLivro(Livro livro) {
        for (int i = 0; i < MAX_LIVROS; i++) {
            if (this.livrosRepo[i] == null) {
                this.livrosRepo[i] = livro;
                return;
            }
        }
    }

    public Livro getLivro(String ISBN) {
        Livro livro_found = null;
        for (Livro livro: this.livrosRepo) {
            if (livro != null && livro.getISBN().equals(ISBN)) {
                livro_found = livro;
                break;
            }
        }
        return livro_found;
    }

    public void addItem(Item item_to_insert) {
        for (int i = 0; i < MAX_ITENS; i++) {
            if (this.itensRepo[i] == null) {
                this.itensRepo[i] = item_to_insert;
                return;
            }
        }
    }

    public Item getItem(int id) {
        Item item_found = null;
        for (Item item : this.itensRepo) {
            if (item != null && item.getID() == id) {
                item_found = item;
                break;
            }
        }
        return item_found;
    }

    public int createConta(String nome, String endereço, String senha) {
        Conta conta = new Conta(nome, endereço, senha);
        int id = conta.getID();
        this.addConta(conta);
        return id;
    }

    public void addConta(Conta conta) {
        for (int i = 0; i < MAX_CONTAS; i++) {
            if (this.contasRepo[i] == null) {
                this.contasRepo[i] = conta;
                return;
            }
        }
    }

    public Conta getConta(int id) {
        Conta conta_found = null;
        for (Conta conta : this.contasRepo) {
            if (conta != null && conta.getID() == id) {
                conta_found = conta;
                break;
            }
        }
        return conta_found;
    }

    public void addAutor(Pessoa autor) {
        for (int i = 0; i < MAX_AUTORES; i++) {
            if (this.autoresRepo[i] == null) {
                this.autoresRepo[i] = autor;
                return;
            }
        }
    }

    public Pessoa getAutor(int id) {
        Pessoa conta_found = null;
        for (Pessoa conta : this.autoresRepo) {
            if (conta != null && conta.getID() == id) {
                conta_found = conta;
                break;
            }
        }
        return conta_found;
    }

    public void checkoutLivro(int itemID, int contaID) {
        Conta conta = this.getConta(contaID);
        Item item = this.getItem(itemID);
        if (item.getStatus() == Status.StatusEnum.DISPONIVEL) {
            item.setStatus(Status.StatusEnum.EMPRESTADO);
            conta.addEmprestimo(itemID);
        } else {
            throw new RuntimeException("Este item já foi emprestado");
        }
    }

    public void devolverLivro(int itemID, int contaID) {
        Conta conta = this.getConta(contaID);
        Item item = this.getItem(itemID);
        item.setStatus(Status.StatusEnum.DISPONIVEL);
        conta.removeEmprestimo(itemID);
    }

    public boolean temAtraso(int itemID, int contaID) {
        Conta conta = this.getConta(contaID);
        Emprestimo emprestimo = conta.getEmprestimo(itemID);
        if (emprestimo == null) {
            throw new RuntimeException("Não há emprestimo para este item");
        }
        Date hoje = new Date();
        if ( Emprestimo.MAX_DIAS_EMPRESTIMOS < Utils.converteMilisegundosParaDias(hoje.getTime() - emprestimo.data.getTime())) {
            return true;
        } else {
            return false;
        }
    }

    public Livro[] searchLivro(String titulo, String assunto, int ano) {
        Livro livrosEncontrados[] = new Livro[MAX_LIVROS/2];
        int livros_adicionados = 0;
        for (Livro livro : this.livrosRepo) {
            if (livro != null) {
                if (
                        livro.getTitulo().contains(titulo) ||
                        livro.getPublicacao() == ano ||
                        Utils.stringEmArray(livro.getCategoria(), assunto)
                   ) {
                        livrosEncontrados[livros_adicionados] = livro;
                        livros_adicionados++;
                   }
            }
        }
        return livrosEncontrados;
    }

    public String toString() {
        return ("Bem-vindo a biblioteca, " + this.nome) + 
               (", estamos no endereço, " + this.endereço + "!\n");
    }
}

