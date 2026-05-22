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

    public LoginRequestDTO validarLogin(LoginRequestDTO dto){

        String sql = "SELECT id, email, senha, tipo FROM funcionario WHERE email = ? AND senha = ?";

        try{   

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, dto.getEmail());
            stm.setString(2, dto.getSenha());

            ResultSet rs = stm.executeQuery();
                

            if(rs.next()){

                dto.setTipo(rs.getString("tipo"));
                dto.setId(rs.getLong("id"));

            }

            rs.close();
            stm.close();

            return dto;
            
        }catch(Exception e){
            System.out.println("Erro ao carregar funcionario. " + e.getMessage());
        }

        return null;

    }

}
