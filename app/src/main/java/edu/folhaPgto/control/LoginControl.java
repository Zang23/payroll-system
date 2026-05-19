package edu.folhaPgto.control;


import java.sql.Connection;
import java.sql.SQLException;

import edu.folhaPgto.dao.FuncionarioLoginDAO;
import edu.folhaPgto.database.ConnectionFactory;
import edu.folhaPgto.dto.request.LoginRequestDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LoginControl {

    private Connection con;
    private FuncionarioLoginDAO funcionarioDAO;

    public LoginControl(){

        try{
            
            Class.forName("org.mariadb.jdbc.Driver");
        
            con = ConnectionFactory.getConnection();

            funcionarioDAO = new FuncionarioLoginDAO(con);

            System.out.println("Conexao foi feita com sucesso");
        }catch(ClassNotFoundException e){
            System.out.println("Erro ao carregar classe");
            e.printStackTrace();
        }catch(SQLException e){
            System.out.println("Erro ao conectar com banco de dados");
            e.printStackTrace();
        }

    }

    private StringProperty email = new SimpleStringProperty("");
    private StringProperty senha = new SimpleStringProperty("");

    public LoginRequestDTO logar(){

        
        LoginRequestDTO loginDto = new LoginRequestDTO(
            email.get(), 
            senha.get()
        );

        if(validaCamposLogin(loginDto)){
            
            return funcionarioDAO.validarLogin(loginDto);

            //fazer meio de mostrar que o usuario errou o email ou senha
        }else{
            System.out.println("Preencha os campos de Email e senha corretamente.");
            return null;
        }


    }

    public StringProperty emailProperty(){
        return email;
    }

    public StringProperty senhaProperty(){
        return senha;
    }

    public boolean validaCamposLogin(LoginRequestDTO dto){

        if(dto.getEmail().isBlank() || !dto.getEmail().contains("@") || !dto.getEmail().contains(".")){
            System.out.println("Preencha corretamente o email");
            return false;
        }

        if(dto.getSenha().isBlank()){
            System.out.println("A senha nao pode ficar em branco. Preencha corretamente");
            return false;
        }

        return true;

    }

    

}
