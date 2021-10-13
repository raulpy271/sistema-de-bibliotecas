package biblioteca.domain;

public class Status {
    public enum StatusEnum {
        DISPONIVEL,
        RESERVADO,
        DISPONIVEL_PARA_QUEM_RESERVOU,
        EMPRESTADO,
        PERDIDO
    }
}

