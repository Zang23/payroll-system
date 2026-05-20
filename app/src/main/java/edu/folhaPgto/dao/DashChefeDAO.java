package edu.folhaPgto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.folhaPgto.dto.request.DashFuncionarioRequestDTO;

public class DashChefeDAO {
     
    private List<DashFuncionarioRequestDTO> lista = new ArrayList<>();

    private Connection con;

    public DashChefeDAO(Connection con){
        this.con = con;
    }

    public List<DashFuncionarioRequestDTO> carregar(){

        String sql = "SELECT id, nome, email, telefone FROM funcionario WHERE tipo = 'funcionario'";

        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");

                DashFuncionarioRequestDTO dto = new DashFuncionarioRequestDTO(
                    id, 
                    nome, 
                    email, 
                    telefone
                );

                lista.add(dto);

            }

            rs.close();
            stm.close();
            

        } catch (Exception e) {
            System.err.println("Erro ao carregar Funcionarios" + e.getMessage());
        }

        return lista;


    }

    public void deletarFuncionario(DashFuncionarioRequestDTO dto){

        String sql = "DELETE FROM funcionario WHERE id = ?";

        try{

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setLong(1, dto.getId());

            stm.executeUpdate();
            carregar();

            stm.close();

        }catch(Exception e){
            System.err.println("Erro ao excluir funcionario: " + e.getMessage());
        }

    }

}
