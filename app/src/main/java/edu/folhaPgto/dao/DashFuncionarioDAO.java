package edu.folhaPgto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.folhaPgto.dto.request.DashFuncionarioRequestDTO;
import edu.folhaPgto.entity.FolhaPagamento;

public class DashFuncionarioDAO {
    
    private Connection con;

    private List<FolhaPagamento> lista = new ArrayList<>();

    public DashFuncionarioDAO(Connection con){
        this.con = con;
    }

    public List<FolhaPagamento> carregarTabela(Long dtoId){
        
        String sql = "SELECT id, data_pagamento, valor_total FROM folha_pagamento WHERE funcionario_id = ? ";
        
        try {
        
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, dtoId);

            ResultSet rs = stm.executeQuery();

            

            while(rs.next()){

                Long id = rs.getLong("id");
                LocalDate dataPagamento = rs.getDate("data_pagamento").toLocalDate();
                double valorTotal = rs.getDouble("valor_total");

                FolhaPagamento folha = new FolhaPagamento();

                folha.setId(id);
                folha.setDataPagamento(dataPagamento);
                folha.setValorTotal(valorTotal);

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
