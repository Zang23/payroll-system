package edu.folhaPgto.dto.request;

public class DashFuncionarioRequestDTO {
    
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public DashFuncionarioRequestDTO(Long id, String nome, String email, String telefone){

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;

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
    

}
