import java.util.Arrays;

public class Controller {
    private Biblioteca biblioteca;
    public Controller(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
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
            Arrays.copyOfRange(comando_e_argumentos, 1, comando_e_argumentos.length);
        switch (comando) {
            case Commands.LOGIN:
                return this.login(argumentos);
            case Commands.SEARCH:
                return this.search(argumentos);
            case Commands.HELP:
                return this.help(argumentos);
            default:
                return "Comando não disponível. Digite HELP para ajuda.";
        }
    }
}
