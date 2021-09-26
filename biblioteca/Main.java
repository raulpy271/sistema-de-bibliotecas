
class Main {

    public static void main(String[] args) {
        System.out.println("Digite um comando para o sistema de bibliotecas: \n");

        Pessoa p1 = new Pessoa("Andressa", "Avenida Agamenon");
        p1.statusPessoa();

        Estante e1 = new Estante(100);
        e1.statusEstante();

        Conta c1 = new Conta("Raul", "Serrita", "raul123");
        c1.statusConta();

        Conta c2 = new Conta("Raulzinho", "Serrinha", "raul000");
        c2.statusConta();

        Biblioteca lib1 = new Biblioteca("Lib da cidade", "rua ali em baixo");

        System.out.println(lib1);

        lib1.addConta(c1);

        System.out.println(lib1.getConta(0) == c1);
    }
}
