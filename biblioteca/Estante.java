
public class Estante {
    int numero;

    public Estante(int numero){
        this.numero = numero;
    }

    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public void statusEstante(){
        System.out.println("Numero de estante:"+this.numero);
    }
}