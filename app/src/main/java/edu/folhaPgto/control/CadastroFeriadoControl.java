package edu.folhaPgto.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import edu.folhaPgto.dao.CadastroFeriadoDAO;
import edu.folhaPgto.dao.ConnectionFactory;
import edu.folhaPgto.entity.Feriado;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CadastroFeriadoControl {

    private Connection con;

    private ObjectProperty<LocalDate> data = new SimpleObjectProperty<>();
    private StringProperty descricao = new SimpleStringProperty("");

    private CadastroFeriadoDAO cadFeriadoDAO;
    public CadastroFeriadoControl(){
         
        try{
            
            Class.forName("org.mariadb.jdbc.Driver");
        
            con = ConnectionFactory.getConnection();

            cadFeriadoDAO = new CadastroFeriadoDAO(con);

            System.out.println("Conexao foi feita com sucesso");
        }catch(ClassNotFoundException e){

            System.out.println("Erro ao carregar classe");
            e.printStackTrace();

        }catch(SQLException e){

            System.out.println("Erro ao conectar com banco de dados");
            e.printStackTrace();
        }
    }

    public Feriado toEntity(){

        Feriado f = new Feriado();

        f.setDataFeriado(data.get());
        f.setDescricao(descricao.get());

        return f;
    }

    public boolean cadastrar(){
        
        Feriado f = toEntity();

        return cadFeriadoDAO.salvar(f);
    }

    public boolean editar(Feriado feriado){

        return cadFeriadoDAO.editar(feriado);
    }
    
    public ObjectProperty<LocalDate> dataProperty() {
        return data;
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }
}
