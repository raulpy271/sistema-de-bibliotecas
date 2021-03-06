package biblioteca.domain;

public class Livro {
    public final static int MAX_CATEGORIAS = 50;
    private String ISBN;
    private String autor;
    private int publicacao;
    private String titulo;
    private int paginas;
    private int numEstante;
    private String[] categoria = new String[MAX_CATEGORIAS];

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(int publicacao) {
        this.publicacao = publicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getNumEstante() {
        return numEstante;
    }

    public void setNumEstante(int numEstante) {
        this.numEstante = numEstante;
    }

    public String[] getCategoria() {
        return categoria;
    }

    public void setCategoria(String[] categoria) {
        this.categoria = categoria;
    }

}
