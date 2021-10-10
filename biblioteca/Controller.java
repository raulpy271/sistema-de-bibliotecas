import java.util.Arrays;

public class Controller {
    private Biblioteca biblioteca;
    public Controller(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public String addLivro(String[] argumentos) {
        int argumentos_necessarios = 7;
        if (argumentos.length >= argumentos_necessarios) {
            String isbn = argumentos[0];
            int ano_publicacao = Integer.parseInt(argumentos[1]);
            int num_paginas = Integer.parseInt(argumentos[2]);
            int num_estante = Integer.parseInt(argumentos[3]);
            String autor = argumentos[4];
            String titulo = argumentos[5];
            String[] categorias = Arrays.copyOfRange(argumentos, 6, argumentos.length);
            this.biblioteca.createLivro(
                isbn, ano_publicacao, num_paginas, num_estante, autor, titulo, categorias
            );
            return "Livro inserido com sucesso!";
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String search(String[] argumentos) {
        int argumentos_necessarios = 2;
        if (argumentos.length == argumentos_necessarios) {
            String tipo = argumentos[0];
            String valor_para_pesquisar = argumentos[1];
            switch (tipo) {
                case "assunto":
                    Livro[] books = biblioteca.searchLivro("", valor_para_pesquisar, "", 0);
                    return View.books(books);
                default:
                    return "Este tipo de pesquisa não é suportado";
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    private String login(String[] argumentos) {
        return "login com sucesso";
    }

    private String help(String[] argumentos) {
        return "os comandos são: help, search, exit, login.";
    }

    public String lidaComInputDoUsuario(String input) {
        String[] comando_e_argumentos = Utils.divideArgumentos(input);
        String comando = comando_e_argumentos[0];
        String[] argumentos = 
            Arrays.copyOfRange(comando_e_argumentos, 1, Utils.countNotNull(comando_e_argumentos));
        switch (comando) {
            case Commands.LOGIN:
                return this.login(argumentos);
            case Commands.SEARCH:
                return this.search(argumentos);
            case Commands.HELP:
                return this.help(argumentos);
            case Commands.ADD_LIVRO:
                return this.addLivro(argumentos);
            default:
                return "Comando não disponível. Digite HELP para ajuda.";
        }
    }
}
