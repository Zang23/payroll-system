package edu.folhaPgto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import edu.folhaPgto.entity.Feriado;

public class CadastroFeriadoDAO {
    
    private Connection con;

    public CadastroFeriadoDAO(Connection con){
        this.con = con;
    }

    public boolean salvar(Feriado f){

        String sql = "INSERT INTO feriado (data_feriado, descricao) VALUES (?,?)";

        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            
            stm.setDate(1, Date.valueOf(f.getDataFeriado()));
            stm.setString(2, f.getDescricao());

            stm.executeUpdate();
            stm.close();

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar novo feriado: " + e.getMessage());
        }


        return false;
    }

    public boolean editar(Feriado f){

        String sql = "UPDATE feriado SET data_feriado = ?, descricao = ? WHERE id = ?";

        try {
            
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setDate(1, Date.valueOf(f.getDataFeriado()));
            stm.setString(2, f.getDescricao());
            stm.setLong(3, f.getId());

            stm.executeUpdate();
            stm.close();

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar feriado: " + e.getMessage());
        }

        return false;

    }

}
