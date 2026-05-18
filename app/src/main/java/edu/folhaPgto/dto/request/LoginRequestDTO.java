package edu.folhaPgto.dto.request;

public class LoginRequestDTO {
    
    private String email;
    private String senha;

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



}
