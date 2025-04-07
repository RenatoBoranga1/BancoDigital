public class Cliente {

    private String nome;
    private String senha;

    public Cliente(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean autenticar(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
