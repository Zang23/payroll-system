package edu.folhaPgto.dto.request;

public class CadastroRequestDTO {
    
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private String tipo;

    public CadastroRequestDTO(String nome, String email, String senha, String telefone, String tipo){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public String getTipo() {
        return tipo;
    }

}
