package edu.folhaPgto.dto.request;

public class LoginRequestDTO {
    
    private String email;
    private String senha;
    private String tipo;

    public LoginRequestDTO(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }




}
