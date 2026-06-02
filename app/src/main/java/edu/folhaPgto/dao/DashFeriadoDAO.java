package edu.folhaPgto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import edu.folhaPgto.entity.Feriado;

public class DashFeriadoDAO {
    
    private List<Feriado> lista = new ArrayList<>();
    
    private Connection con;

    public DashFeriadoDAO(Connection con){
        this.con = con;
    }

    public List<Feriado> carregar(){

        String sql = "SELECT id, data_feriado, descricao FROM feriado";

        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                LocalDate data = rs.getDate("data_feriado").toLocalDate();
                String desc = rs.getString("descricao");

                Feriado f = new Feriado(id, data, desc);

                lista.add(f);

            }

            stm.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("Erro ao carregar feriados: " +  e.getMessage());
        }


        return lista;
    }

    public void deletar(Feriado f){

        String sql = "DELETE FROM feriado WHERE id = ?";

        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, f.getId());

            stm.executeUpdate();
            carregar();
            stm.close();

        } catch (Exception e) {
            System.out.println("Erro ao deletar feriado: " + e.getMessage());
        }

    }




}
