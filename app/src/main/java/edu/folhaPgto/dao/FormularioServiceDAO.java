package edu.folhaPgto.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

import edu.folhaPgto.entity.DiaTrabalhado;

public class FormularioServiceDAO {
    
    private Connection con;

    public FormularioServiceDAO(Connection con){
        this.con = con;
    }

    public boolean salvarDia(DiaTrabalhado registro){

        System.out.println("ID recebido: " + registro.getFolhaPagamentoId());

        String sql = "INSERT INTO dia_trabalhado (folha_pagamento_id,nome_projeto, data_servico, hora_inicio, hora_fim, viagem, valor_calculado) VALUES (?,?,?,?,?,?,?)";

        try{

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setLong(1, registro.getFolhaPagamentoId());
            stm.setString(2, registro.getNomeProjeto());
            stm.setDate(3, Date.valueOf(registro.getDataServico()));
            stm.setTime(4, Time.valueOf(registro.getHoraInicio()));
            stm.setTime(5, Time.valueOf(registro.getHoraFim()));
            stm.setBoolean(6, registro.isViagem());
            stm.setBigDecimal(7, BigDecimal.valueOf(registro.getValorRecebido()));

            stm.executeUpdate();
            return true;

        }catch(SQLException e){
            System.err.println("Erro ao conectar ");
            e.printStackTrace();
        }


        return false;
    }

    public double consultaValorHora(Long id){

        String sql = "SELECT valor_hora FROM folha_pagamento WHERE id = ?";

        double valor = 0.0;

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            
            stm.setLong(1, id);

            ResultSet rs = stm.executeQuery();
            
            if(rs.next()){
                valor = rs.getDouble("valor_hora");
            }

            rs.close();
            stm.close();
        

        } catch (Exception e) {
            e.getMessage();
        }

        return valor;

    }

    public boolean ehFeriado(LocalDate data){

        String sql =
            "SELECT COUNT(*) qtd " +
            "FROM feriado " +
            "WHERE data_feriado = ?";

        try{

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setDate(1,java.sql.Date.valueOf(data));

            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                return rs.getInt("qtd") > 0;
            }

            stm.close();
            rs.close();

        }catch(Exception e){
            e.printStackTrace();
        }



        return false;
    }

    public void atualizarValorTotalFolha(Long folhaId) {

        String sql =
            "UPDATE folha_pagamento " +
            "SET valor_total = (" +
            "   SELECT COALESCE(SUM(valor_calculado),0) " +
            "   FROM dia_trabalhado " +
            "   WHERE folha_pagamento_id = ?" +
            ") WHERE id = ?";

        try {
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setLong(1, folhaId);
            stm.setLong(2, folhaId);

            stm.executeUpdate();

            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
