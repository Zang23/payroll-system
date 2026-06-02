package edu.folhaPgto.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.folhaPgto.dao.ConnectionFactory;
import edu.folhaPgto.dao.DashChefeDAO;
import edu.folhaPgto.dao.DashFeriadoDAO;
import edu.folhaPgto.entity.Feriado;

public class DashFeriadoControl {
    
    private Connection con;
    private DashFeriadoDAO feriadoDAO;

    public DashFeriadoControl(){
         try{
            
            Class.forName("org.mariadb.jdbc.Driver");
        
            con = ConnectionFactory.getConnection();

            feriadoDAO = new DashFeriadoDAO(con);

            System.out.println("Conexao foi feita com sucesso");
        }catch(ClassNotFoundException e){

            System.out.println("Erro ao carregar classe");
            e.printStackTrace();

        }catch(SQLException e){

            System.out.println("Erro ao conectar com banco de dados");
            e.printStackTrace();
        }
    }

    public List<Feriado> carregarTabela(){
        return feriadoDAO.carregar();
    }

    public void deletarFeriado(Feriado f){

        System.out.println("Feriado: " + f.getDescricao() + ". Deletado com sucesso!");
        
        feriadoDAO.deletar(f);
        return; 

    }

}
