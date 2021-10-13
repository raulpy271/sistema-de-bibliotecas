package biblioteca.domain;

import biblioteca.domain.Status;
import biblioteca.utils.IDGenerator;

public class Item {
    private String ISBN;
    private int itemID;
    private Status.StatusEnum status = Status.StatusEnum.DISPONIVEL;
    private int idContaReservada = -1;
    private static IDGenerator IDItemGen = new IDGenerator();

    public Item() {
        setID(Item.IDItemGen.gen());
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

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

