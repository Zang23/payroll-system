package edu.folhaPgto.control;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LoginControl {


    private static final String DB_JDBC_URI = "jdbc:mariadb://localhost:3306/folha_pagamento?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private Connection con;

    public LoginControl(){

        try{
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Classe carregada...");
            con = DriverManager.getConnection(DB_JDBC_URI, DB_USER, DB_PASS);
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

    public boolean logar(){

        try{

            String sql = "SELECT email, senha FROM funcionario WHERE email = ? AND senha = ?";

            PreparedStatement stm = con.prepareStatement(sql);
           
            stm.setString(1, email.get());
            stm.setString(2, senha.get());

            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                
                System.out.println("Login realizado com sucesso");

                return true;
                
            }

            System.out.println("Email ou senha incorretos, preencha corretamente.");

            rs.close();
            stm.close();

        }catch(Exception e){
            System.err.println("Erro ao carregar funcionarios: " + e.getMessage());
        }

        return false;

    }

    public StringProperty emailProperty(){
        return email;
    }

    public StringProperty senhaProperty(){
        return senha;
    }

    

}
