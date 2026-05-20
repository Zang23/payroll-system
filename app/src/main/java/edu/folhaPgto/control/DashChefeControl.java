package edu.folhaPgto.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.folhaPgto.dao.ConnectionFactory;
import edu.folhaPgto.dao.DashChefeDAO;
import edu.folhaPgto.dto.request.DashFuncionarioRequestDTO;

public class DashChefeControl {
    
    private Connection con;
    private DashChefeDAO chefeDAO;

    public DashChefeControl(){
        
        try{
            
            Class.forName("org.mariadb.jdbc.Driver");
        
            con = ConnectionFactory.getConnection();

            chefeDAO = new DashChefeDAO(con);

            System.out.println("Conexao foi feita com sucesso");
        }catch(ClassNotFoundException e){

            System.out.println("Erro ao carregar classe");
            e.printStackTrace();

        }catch(SQLException e){

            System.out.println("Erro ao conectar com banco de dados");
            e.printStackTrace();
        }

    }

    public List<DashFuncionarioRequestDTO> carregarTabela(){
        return chefeDAO.carregar();
    }

    public void editarFuncionario(DashFuncionarioRequestDTO dto){

        System.out.println("Editar funcionario: " + dto.getId());

    }

    public void deletarFuncionario(DashFuncionarioRequestDTO dto){

        System.out.println("Deletado funcionario: " + dto.getId());

        chefeDAO.deletarFuncionario(dto);
        return;
    }

    

}
