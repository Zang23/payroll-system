package edu.folhaPgto.dto.request;

public class DashFuncionarioRequestDTO {
    
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String tipo;

    public DashFuncionarioRequestDTO(Long id, String nome, String email, String telefone, String tipo){

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipo = tipo;

    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

}

