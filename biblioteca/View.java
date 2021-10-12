
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
        return result;
    }
}

