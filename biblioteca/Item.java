
public class Item extends Livro {
    // Criando essa classe apenas para conseguir utiliza-la em Biblioteca.java
    private int itemID;
    private Status.StatusEnum status = Status.StatusEnum.DISPONIVEL;
    private int idContaReservada = -1;

    public int getID() {
        return this.itemID;
    }

    public void setID(int id) {
        this.itemID = id;
    }

    public void setStatus(Status.StatusEnum status) {
        this.status = status;

    }

    public void setIDContaReservada(int id) {
        this.idContaReservada = id;
    }

    public int getIDContaReservada() {
        return this.idContaReservada;
    }

    public Status.StatusEnum getStatus() {
        return this.status;
    }
}
