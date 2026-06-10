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

        String sql = "SELECT id, nome, email, telefone, tipo FROM funcionario WHERE tipo = 'funcionario'";

        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                String tipo = rs.getString("tipo");

                DashFuncionarioRequestDTO dto = new DashFuncionarioRequestDTO(
                    id, 
                    nome, 
                    email, 
                    telefone,
                    tipo
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

    public void atualizarFuncionario(DashFuncionarioRequestDTO dto) {
     
        String sql = "UPDATE funcionario SET nome = ?, email = ?, telefone = ? WHERE id = ?";

        try{

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, dto.getNome());
            stm.setString(2, dto.getEmail());
            stm.setString(3, dto.getTelefone());
            stm.setLong(4, dto.getId());

            stm.executeUpdate();
            carregar();

            stm.close();


        }catch(Exception e){
            System.err.println("Erro ao editar funcionario: " + e.getMessage());
        }

    }

}
