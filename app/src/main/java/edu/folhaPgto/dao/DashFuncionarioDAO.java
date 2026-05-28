package edu.folhaPgto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.folhaPgto.entity.FolhaPagamento;

public class DashFuncionarioDAO {
    
    private Connection con;

    private List<FolhaPagamento> lista = new ArrayList<>();

    public DashFuncionarioDAO(Connection con){
        this.con = con;
    }

    public List<FolhaPagamento> carregarTabela(Long dtoId){
        
        String sql = "SELECT id, valor_hora, total_dias_trabalhados, valor_total, data_inicial, data_final FROM folha_pagamento WHERE funcionario_id = ?";
        
        try {
        
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, dtoId);

            ResultSet rs = stm.executeQuery();

            

            while(rs.next()){

                FolhaPagamento folha = new FolhaPagamento();

                folha.setId(rs.getLong("id"));

                folha.setValorHora(rs.getDouble("valor_hora"));

                folha.setTotalDiasTrabalhados(
                    rs.getInt("total_dias_trabalhados")
                );

                folha.setDataInicial(
                    rs.getDate("data_inicial").toLocalDate()
                );

                folha.setDataFinal(
                    rs.getDate("data_final").toLocalDate()
                );

                folha.setValorTotal(
                    rs.getDouble("valor_total")
                );

                lista.add(folha);

            }

            rs.close();
            stm.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

}
