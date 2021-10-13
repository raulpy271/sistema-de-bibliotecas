import java.text.SimpleDateFormat;

public class View {

    public static String books(Livro[] livros) {
        String result = "";
        int counter = 0;
        for (Livro livro: livros) {
            if (livro != null) {
                result += "Livro " + counter + "\n";
                result += book(livro) + "\n";
                counter++;
            }
        }
        if (counter > 0) {
            return result;
        } else {
            return "Não foi encontrado livros";
        }
    }

    public static String book(Livro livro) {
        String result = "";
        result += "Titulo: " +  livro.getTitulo() + "\n";
        result += "Autor: " +  livro.getAutor() + "\n";
        result += "Número da estante: " + livro.getNumEstante() + "\n";
        result += "Categoria: " +  View.categorias(livro.getCategoria()) + "\n";
        return result;
    }

    public static String categorias(String[] categorias) {
        String result = ""; 
        for (String categoria: categorias) {
            result += categoria + ", ";
        }
        return result;
    }

    public static String user(Conta conta) {
        String result = "ID: " + conta.getID() + "\n";
        result += "Nome: " + conta.getNome() + "\n";
        result += "Senha: ***" + conta.getSenha().substring(3) + "\n";
        result += "Tipo: ";
        if (conta.eh_bibliotecario()) {
            result += "bibliotecário \n";
        } else {
            result += "membro \n";
        }
        return result;
    }

    public static String contaAtraso(Conta conta) {
        String result = "";
        if (conta != null) {
            result = "O seguinte usuário está atrasado com o item: \n";
            result += user(conta); 
        } else {
            result = "O item não não está fora do prazo";
        }
        return result;
    }

    public static String emprestimos(Emprestimo[] emprestimos, Biblioteca repo) {
        Item item;
        Livro livro;
        String result = "";
        int counter = 0;
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        for (Emprestimo emprestimo: emprestimos) {
            if (emprestimo != null) {
                counter++;
                item = repo.getItem(emprestimo.itemID);
                livro = repo.getLivro(item.getISBN());
                result += "Emprestimo " + counter + "\n";
                result += "Data: " + formater.format(emprestimo.data) + "\n";
                result += "Item id: " + item.getID() + "\n";
                result += book(livro) + "\n";
            }
        }
        if (counter > 0) {
            return result;   
        } else {
            return "Não há emprestimos para esse usuário";
        }
    }
}

