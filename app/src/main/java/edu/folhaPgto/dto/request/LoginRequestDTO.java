package edu.folhaPgto.dto.request;

public class LoginRequestDTO {
    
    private Long id;
    private String email;
    private String senha;
    private String tipo;

    public LoginRequestDTO(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
