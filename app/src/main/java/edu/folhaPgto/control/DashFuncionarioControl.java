package edu.folhaPgto.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.folhaPgto.dao.ConnectionFactory;
import edu.folhaPgto.dao.DashChefeDAO;
import edu.folhaPgto.dao.DashFuncionarioDAO;
import edu.folhaPgto.dto.request.DashFuncionarioRequestDTO;
import edu.folhaPgto.entity.FolhaPagamento;

public class DashFuncionarioControl {
    
    private Connection con;
    private DashFuncionarioDAO funcDao;
    

    public DashFuncionarioControl(){

         try{
            
            Class.forName("org.mariadb.jdbc.Driver");
        
            con = ConnectionFactory.getConnection();

            funcDao = new DashFuncionarioDAO(con);

            System.out.println("Conexao foi feita com sucesso");
        }catch(ClassNotFoundException e){

            System.out.println("Erro ao carregar classe");
            e.printStackTrace();

        }catch(SQLException e){

            System.out.println("Erro ao conectar com banco de dados");
            e.printStackTrace();
        }

    }

    public List<FolhaPagamento> carregarTabela(Long dtoId){

        return funcDao.carregarTabela(dtoId);

    }

}
