package edu.folhaPgto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.folhaPgto.dto.request.LoginRequestDTO;


public class FuncionarioLoginDAO {
    
    private Connection con;

    public FuncionarioLoginDAO(Connection con){
        this.con = con;
    }

    public boolean validarLogin(LoginRequestDTO dto){

        String sql = "SELECT email, senha FROM funcionario WHERE email = ? AND senha = ?";

        try{   

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, dto.getEmail());
            stm.setString(2, dto.getSenha());

            ResultSet rs = stm.executeQuery();

            boolean existe = rs.next();                

            rs.close();
            stm.close();

            return existe;
            
        }catch(Exception e){
            System.out.println("Erro ao carregar funcionario. " + e.getMessage());
        }

        return false;

    }

}
