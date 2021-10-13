package biblioteca.domain;

import java.util.Date;

public class Emprestimo {
    public int itemID;
    public Date data;
    public final static int MAX_DIAS_EMPRESTIMOS = 10;

   public  Emprestimo(int itemID) {
       this.data = new Date();
       this.itemID = itemID;
   }

   public void atualizaData() {
       this.data = new Date();
   }
}

