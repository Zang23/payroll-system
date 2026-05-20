package edu.folhaPgto.control;

import java.sql.Connection;
import java.sql.SQLException;

import edu.folhaPgto.dao.ConnectionFactory;
import edu.folhaPgto.dao.FuncionarioCadastroDAO;
import edu.folhaPgto.dto.request.CadastroRequestDTO;
import edu.folhaPgto.entity.Funcionario;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CadastroControl {
    
    private Connection con;
    private FuncionarioCadastroDAO funcionarioDAO;

    public CadastroControl(){

        try{
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Classe carregada...");

            con = ConnectionFactory.getConnection();
            System.out.println("Conexao foi feita com sucesso");

            funcionarioDAO = new FuncionarioCadastroDAO(con);

        }catch(ClassNotFoundException e){
            System.out.println("Erro ao carregar classe");
            e.printStackTrace();
        }catch(SQLException e){
            System.out.println("Erro ao conectar com banco de dados");
            e.printStackTrace();
        }

    }

    public Funcionario toEntity(){

        Funcionario f = new Funcionario();
        f.setNome(nome.get());
        f.setTelefone(telefone.get());
        f.setTipo(tipo.get());
        f.setEmail(email.get());
        f.setSenha(senha.get());

        return f;

    }

    public void fromEntity(Funcionario f){

        if(f != null){
            nome.set(f.getNome());
            telefone.set(f.getTelefone());
            tipo.set(f.getTipo());
            email.set(f.getEmail());
            senha.set(f.getSenha());
        }

    }


    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty tipo = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty senha = new SimpleStringProperty("");
    
    public boolean cadastrar(){

        Funcionario f = toEntity();

        CadastroRequestDTO cadastroDTO = new CadastroRequestDTO(
            f.getNome(),
            f.getEmail(), 
            f.getSenha(), 
            f.getTelefone(), 
            f.getTipo()
        );

        if(validaCamposFuncionario(cadastroDTO)){

            return funcionarioDAO.cadastraFuncionario(cadastroDTO);
            
        }else{
            System.out.println("Preencha os campos corretamente.");
            return false;
        }

    }

    public boolean validaCamposFuncionario(CadastroRequestDTO f){


        if(f.getNome().isBlank() || f.getNome().trim().length() < 5){
            System.err.println("O nome deve ser preenchido com pelo menos 5 caracteres");
            return false;
        }

        String telefone = f.getTelefone()
            .replaceAll("\\D", "")
            .trim();

        if(telefone.length() != 11){
            System.err.println("O telefone deve conter 11 digitos numericos");
            return false;
        }

        if(f.getTipo().isBlank()){
            System.err.println("O funcionario deve ter um tipo");
            return false;
        }

        if(f.getEmail().isBlank() ||!f.getEmail().contains("@") || !f.getEmail().contains(".")){
            System.err.println("Preencha o email corretamente.");
            return false;
        }

        if(f.getSenha().isBlank()){
            System.err.println("A senha nao pode estar vazia");
            return false;
        }

        System.out.println("Usuario cadastrado com sucesso");
        return true;
    }

    public StringProperty nomeProperty(){
        return nome;
    }

    public StringProperty telefoneProperty(){
        return telefone;
    }

    public StringProperty tipoProperty(){
        return tipo;
    }

    public StringProperty emailProperty(){
        return email;
    }

    public StringProperty senhaProperty(){
        return senha;
    }


}
