
class Main {

    public static void main(String[] args) {
        System.out.println("Digite um comando para o sistema de bibliotecas: \n");

        Pessoa p1 = new Pessoa("Andressa", "Avenida Agamenon");
        p1.statusPessoa();

        Estante e1 = new Estante(100);
        e1.statusEstante();
    }
}