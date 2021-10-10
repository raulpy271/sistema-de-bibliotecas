import java.util.Arrays;

public class Controller {
    private Biblioteca biblioteca;
    public Controller(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public String addAutor(String[] argumentos) {
        int argumentos_necessarios = 2;
        if (argumentos.length >= argumentos_necessarios) {
            String nome = argumentos[0];
            String end = argumentos[1];
            int autor_id = biblioteca.createAutor(nome, end);
            return "Autor inserido. id: " + autor_id;
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    private String login(String[] argumentos) {
        return "login com sucesso";
    }

    private String search(String[] argumentos) {
        return "Livro não encontrado";
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
            case Commands.ADD_AUTOR:
                return this.addAutor(argumentos);
            default:
                return "Comando não disponível. Digite HELP para ajuda.";
        }
    }
}
