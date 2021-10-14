package biblioteca;

import biblioteca.domain.Item;
import biblioteca.domain.Livro;
import biblioteca.domain.Conta;
import biblioteca.domain.Status;
import biblioteca.domain.Emprestimo;
import biblioteca.utils.Utils;

import java.util.Date;


public class Biblioteca {
    private final int MAX_ITENS = 100;
    private final int MAX_LIVROS = 100;
    private final int MAX_CONTAS = 100;
    private String nome;
    private String endereço;
    private Item itensRepo[] = new Item[MAX_ITENS];
    private Livro livrosRepo[] = new Livro[MAX_LIVROS];
    private Conta contasRepo[] = new Conta[MAX_CONTAS];

    public Biblioteca(String nome, String endereço) {
        this.nome = nome;
        this.endereço = endereço;
    }

    public void createLivro(
            String isbn, 
            int ano_publicacao, 
            int num_paginas, 
            int num_estante, 
            String autor, 
            String titulo, 
            String[] categorias
            ) {
        Livro livro = getLivro(isbn);
        if (livro == null) {
            livro = new Livro();
            livro.setISBN(isbn);
            livro.setAutor(autor);
            livro.setPublicacao(ano_publicacao);
            livro.setTitulo(titulo);
            livro.setPaginas(num_paginas);
            livro.setNumEstante(num_estante);
            livro.setCategoria(categorias);
            this.addLivro(livro);
        } else {
            throw new RuntimeException("Este livro já existe");
        }
    }

    public void createItens(String isbn, int quantidade_de_itens) {
        for (int i = 0; i < quantidade_de_itens; i++) {
            Item item = new Item();
            item.setISBN(isbn);
            this.addItem(item);
        }
    }

    public void editLivro(
            String isbn, 
            int ano_publicacao, 
            int num_paginas, 
            int num_estante, 
            String autor, 
            String titulo, 
            String[] categorias
            ) {
        Livro livro = getLivro(isbn);
        if (livro != null) {
            livro.setAutor(autor);
            livro.setPublicacao(ano_publicacao);
            livro.setTitulo(titulo);
            livro.setPaginas(num_paginas);
            livro.setNumEstante(num_estante);
            livro.setCategoria(categorias);
        } else {
            throw new RuntimeException("Este livro não existe");
        }
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

    public void removeItem(int item_id) {
        for (int i = 0; i < MAX_ITENS; i++) {
            if (this.itensRepo[i] != null && this.itensRepo[i].getID() == item_id) {
                this.itensRepo[i] = null;
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

    public int getItemDisponivel(String isbn, int contaID) {
        int item_id = -1;
        for (Item item : this.itensRepo) {
            if (
                    item != null && 
                    item.getISBN().equals(isbn) &&
                    this.checkDisponivel(item, contaID)) {
                item_id = item.getID();
                break;
            }
        }
        if (item_id != -1) {
            return item_id;
        } else {
            throw new RuntimeException("Não há itens disponiveis desse isbn: " + isbn);
        }
    }

    public int createConta(String nome, String endereço, String senha, boolean bibliotecario) {
        Conta conta = new Conta(nome, endereço, senha);
        conta.setBibliotecario(bibliotecario);
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

    public int searchContaQFezCheckout(int item_id) {
        Emprestimo emprestimo;
        int id_conta = -1;
        for (int i = 0; i < MAX_CONTAS; i++) {
            if (this.contasRepo[i] != null) {
                emprestimo = this.contasRepo[i].getEmprestimo(item_id);
                if (emprestimo != null) {
                    id_conta = contasRepo[i].getID();
                    break;
                }
            }
        }
        if (id_conta != -1) {
            return id_conta;
        } else {
            throw new RuntimeException("Nem uma conta fez checkout desse item");
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

    public Conta getContaPorNome(String nome) {
        Conta conta_found = null;
        for (Conta conta : this.contasRepo) {
            if (conta != null && conta.getNome().equals(nome)) {
                conta_found = conta;
                break;
            }
        }
        return conta_found;
    }

    public void removeConta(int id) {
        int conta_position = -1;
        for (int i = 0; i < MAX_CONTAS; i++) {
            if (
                    this.contasRepo[i] != null &&
                    this.contasRepo[i].getID() == id) {
                conta_position = i;
                break;
            }
        }
        if (conta_position > -1) {
            this.contasRepo[conta_position] = null;
        } else {
            throw new RuntimeException("Este conta não existe");
        }
    }

    public boolean checkRemoveLivro(String isbn) {
        for (int i = 0; i < MAX_ITENS; i++) {
            if (
                    this.itensRepo[i] != null &&
                    this.itensRepo[i].getISBN().equals(isbn) &&
                    this.itensRepo[i].getStatus() != Status.StatusEnum.DISPONIVEL) {
                return false;
            }
        }
        return true;
    }

    private void removeItensOf(String isbn) {
        for (int i = 0; i < MAX_ITENS; i++) {
            if (
                    this.itensRepo[i] != null &&
                    this.itensRepo[i].getISBN().equals(isbn)) {
                this.itensRepo[i] = null;
            }
        }
    }

    public void removeLivro(String isbn) {
        for (int i = 0; i < MAX_LIVROS; i++) {
            if (
                    this.livrosRepo[i] != null &&
                    this.livrosRepo[i].getISBN().equals(isbn)) {
                this.livrosRepo[i] = null;
                break;
            }
        }
        removeItensOf(isbn);
    }

    public void checkoutLivro(int itemID, int contaID) {
        Conta conta = this.getConta(contaID);
        Item item = this.getItem(itemID);
        if (conta == null || item == null) {
            throw new RuntimeException("O item ou a conta com este ID não foi encontrado");
        }
        if (!conta.canCheckout()) {
            throw new RuntimeException("O limite de checkout foi atingido");
        } 
        if (this.checkDisponivel(item, contaID)) {
            item.setStatus(Status.StatusEnum.EMPRESTADO);
            item.setIDContaReservada(-1);
            conta.addEmprestimo(itemID);
        } else {
            throw new RuntimeException("Este item já foi emprestado");
        }
    }

    public void devolverLivro(int itemID, int contaID) {
        Conta conta = this.getConta(contaID);
        Item item = this.getItem(itemID);
        if (conta != null && item != null) {
            if (conta.getEmprestimo(itemID) != null) {
                if (item.getStatus() == Status.StatusEnum.RESERVADO) {
                    item.setStatus(Status.StatusEnum.DISPONIVEL_PARA_QUEM_RESERVOU);
                } else {
                    item.setStatus(Status.StatusEnum.DISPONIVEL);
                }
                conta.removeEmprestimo(itemID);
            } else {
                throw new RuntimeException("O item precisa estar emprestado para ser devolvido");
            }
        } else {
            throw new RuntimeException("Algum ID está inválido, ou ambos");
        }
    }

    public void reservarLivro(int itemID, int contaID) {
        Item item = this.getItem(itemID);
        Conta conta = this.getConta(contaID);
        if (conta == null || item == null) {
            throw new RuntimeException("O item ou a conta com este ID não foi encontrado");
        }
        if (conta.getEmprestimo(itemID) != null) {
            throw new RuntimeException("Não é possivel reservar itens emprestádos a si mesmo");
        }
        if (item.getStatus() == Status.StatusEnum.EMPRESTADO) {
            item.setStatus(Status.StatusEnum.RESERVADO);
            item.setIDContaReservada(contaID);
        } else {
            throw new RuntimeException("O item precisa estar emprestado para ser reservado");
        }
    }

    public void renovarLivro(int itemID, int contaID) {
        Conta conta = this.getConta(contaID);
        Item item = this.getItem(itemID);
        if (conta == null || item == null) {
            throw new RuntimeException("O item ou a conta com este ID não foi encontrado");
        }
        if (item.getStatus() == Status.StatusEnum.EMPRESTADO) {
            Emprestimo emprestimo = conta.getEmprestimo(itemID);
            if (emprestimo != null) {
                emprestimo.atualizaData();
            } else {
                throw new RuntimeException("O item não está emprestado. Precisa fazer checkout antes de renovar");
            }
        } else {
            throw new RuntimeException("O item já foi reservado ou não está disponivel");
        }
    }

    public Conta getContaComAtraso(int itemID) {
        Item item = getItem(itemID);
        if (item == null) {
            throw new RuntimeException("Não possue item com esse ID");
        }
        for (Conta conta: this.contasRepo) {
            if (conta != null && conta.getEmprestimo(itemID) != null) {
                if (temAtraso(itemID, conta.getID())) {
                    return conta;
                } else {
                    return null;
                }
            }
        }
        return null;
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

    public Livro[] searchLivro(String titulo, String assunto, String autor, int ano) {
        Livro livrosEncontrados[] = new Livro[MAX_LIVROS/2];
        int livros_adicionados = 0;
        for (Livro livro : this.livrosRepo) {
            if (livro != null) {
                if (
                        ((!autor.equals("")) && livro.getAutor().contains(autor)) ||
                        ((!titulo.equals("")) && livro.getTitulo().contains(titulo)) ||
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

    private boolean checkDisponivel(Item item, int user_id) {
        if (item.getStatus() == Status.StatusEnum.DISPONIVEL) {
            return true;
        } else if (
                item.getStatus() == Status.StatusEnum.DISPONIVEL_PARA_QUEM_RESERVOU &&
                item.getIDContaReservada() == user_id) {
            return true;
        } else {
            return false;
        } 
    }

    public String getNome() {
        return this.nome;
    }

    public String getEnd() {
        return this.endereço;
    }

    public String toString() {
        return ("Bem-vindo a biblioteca, " + this.nome) + 
               (", estamos no endereço, " + this.endereço + "!\n");
    }
}

