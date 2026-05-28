package edu.folhaPgto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.folhaPgto.entity.FolhaPagamento;

public class NovaFolhaDAO {
    
    private Connection con;

    public NovaFolhaDAO(Connection con){
        this.con = con;
    }

    public boolean salvarFolha(FolhaPagamento folha){

        String sql = "INSERT INTO folha_pagamento (funcionario_id, valor_hora, total_dias_trabalhados, data_inicial, data_final, valor_total) VALUES (?,?,?,?,?,?)";

        try{

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setLong(1, folha.getFuncionarioId());
            stm.setDouble(2, folha.getValorHora());
            stm.setInt(3, folha.getTotalDiasTrabalhados());
            stm.setDate(4, Date.valueOf(folha.getDataInicial()));
            stm.setDate(5, Date.valueOf(folha.getDataFinal()));
            stm.setDouble(6, folha.getValorTotal());

            System.out.println(folha.toString());

            stm.executeUpdate();
            return true;

        }catch(SQLException e){
            System.err.println("Erro ao conectar ");
            e.printStackTrace();
        }

        return false;
    }

    

}
