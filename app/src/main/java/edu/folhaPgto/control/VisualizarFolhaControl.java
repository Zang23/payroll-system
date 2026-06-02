package edu.folhaPgto.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.folhaPgto.dao.ConnectionFactory;
import edu.folhaPgto.dao.DashFuncionarioDAO;
import edu.folhaPgto.dao.VisualizarFolhaDAO;
import edu.folhaPgto.entity.DiaTrabalhado;

public class VisualizarFolhaControl {
    
    private Connection con;

    private VisualizarFolhaDAO visuDAO;

    public VisualizarFolhaControl(){

         try{
            
            Class.forName("org.mariadb.jdbc.Driver");
        
            con = ConnectionFactory.getConnection();

            visuDAO = new VisualizarFolhaDAO(con);

            System.out.println("Conexao foi feita com sucesso");
        }catch(ClassNotFoundException e){

            System.out.println("Erro ao carregar classe");
            e.printStackTrace();

        }catch(SQLException e){

            System.out.println("Erro ao conectar com banco de dados");
            e.printStackTrace();
        }

    }

    public List<DiaTrabalhado> buscarDiasFolha(Long id){

        return visuDAO.carregarDias(id);
    }

}
