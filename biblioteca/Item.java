
public class Item extends Livro {
    // Criando essa classe apenas para conseguir utiliza-la em Biblioteca.java
    private int itemID;
    private Status.StatusEnum status = Status.StatusEnum.DISPONIVEL;

    public int getID() {
        return this.itemID;
    }

    public void setID(int id) {
        this.itemID = id;
    }

    public void setStatus(Status.StatusEnum status) {
        this.status = status;

    }

    public Status.StatusEnum getStatus() {
        return this.status;
    }
}
