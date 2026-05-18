package edu.folhaPgto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.folhaPgto.dto.request.CadastroRequestDTO;


public class FuncionarioCadastroDAO {
    
    private Connection con;

    public FuncionarioCadastroDAO(Connection con){
        this.con = con;
    }

    public boolean cadastraFuncionario(CadastroRequestDTO dto){
        
        String sql = "INSERT INTO funcionario (nome, telefone, tipo, email, senha) VALUES (?,?,?,?,?)";

        try{

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, dto.getNome());
            stm.setString(2, dto.getTelefone());
            stm.setString(3, dto.getTipo());
            stm.setString(4, dto.getEmail());
            stm.setString(5, dto.getSenha());

            stm.executeUpdate();
            return true;

        }catch(SQLException e){
            System.err.println("Erro ao conectar ");
            e.printStackTrace();
        }

        return false;
    }

}
