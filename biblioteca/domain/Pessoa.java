package biblioteca.domain;

class Pessoa {
    private String nome;
    private String endereço;
    private int id;
    
    public Pessoa(String nome, String endereço) {
        this.nome = nome;
        this.endereço = endereço;
    }

    public int getID() {
        return this.id;
    }
    public void setID(int id) {
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEndereço(){
        return this.endereço;
    }

    public void setEndereço(String endereço){
        this.endereço = endereço;
    }

    public void statusPessoa(){
        System.out.println("Nome:"+this.nome);
        System.out.println("Endereço:"+this.endereço);
    }
}
