import java.util.Scanner;

class Main {

    public static void boasVindas(Biblioteca lib) {
        System.out.println("Seja bem vindo ao sistema de biblioteca " + lib.getNome() + "!");
        System.out.println("Digite seus comandos para interagir com o sistema, digite HELP para ajuda, ou EXIT para sair.");
    }

    public static void despedida() {
        System.out.println("Até mais, boa leitura!");
    }

    public static void main(String[] args) {
        Biblioteca lib = new Biblioteca("da cidade", "av tal");
        Controller controlador = new Controller(lib);
        Scanner leitor_de_input = new Scanner(System.in);
        String input_atual;
        String resultado_do_input;

        Main.boasVindas(lib);

        while (true) {
            System.out.print(" -> ");
            input_atual = leitor_de_input.nextLine().toLowerCase();
            if (Utils.contemNoInicio(input_atual, Commands.EXIT)) {
                break;
            }
            resultado_do_input = controlador.lidaComInputDoUsuario(input_atual);
            System.out.println(resultado_do_input);
        }

        Main.despedida();
    }
}
