package edu.folhaPgto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.folhaPgto.entity.DiaTrabalhado;

public class VisualizarFolhaDAO {
    
    private Connection con;

    private List<DiaTrabalhado> lista = new ArrayList<>();

    public VisualizarFolhaDAO(Connection con){
        this.con = con;
    }

    public List<DiaTrabalhado> carregarDias(Long id){


        String sql = "SELECT nome_projeto, data_servico, hora_inicio, hora_fim, valor_calculado FROM dia_trabalhado WHERE folha_pagamento_id = ?";

        try {
            
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setLong(1, id);

            ResultSet rs = stm.executeQuery();

            while(rs.next()){

                DiaTrabalhado dia = new DiaTrabalhado();

                dia.setNomeProjeto(rs.getString("nome_projeto"));
                dia.setDataServico(rs.getDate("data_servico").toLocalDate());
                dia.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                dia.setHoraFim(rs.getTime("hora_fim").toLocalTime());
                dia.setValorRecebido(rs.getDouble("valor_calculado"));

                lista.add(dia);

            }

            rs.close();
            stm.close();

        } catch (Exception e) {
            System.out.println("Falha ao carregar dias: " + e.getMessage());
        }


        return lista;
    }



}
