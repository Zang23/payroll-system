package edu.folhaPgto.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import edu.folhaPgto.dao.ConnectionFactory;
import edu.folhaPgto.dao.FormularioServiceDAO;
import edu.folhaPgto.entity.DiaTrabalhado;
import edu.folhaPgto.entity.FolhaPagamento;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FormularioServicoControl {
    
    private Connection con;
    private FormularioServiceDAO formDAO;

    private StringProperty nomeProjeto = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> dataServico = new SimpleObjectProperty<>();
    private StringProperty horaInicio = new SimpleStringProperty("");
    private StringProperty horaFim = new SimpleStringProperty("");
    private BooleanProperty feriado = new SimpleBooleanProperty(false);
    private BooleanProperty viagem = new SimpleBooleanProperty(false);
    
    public FormularioServicoControl(){

       try{
            
            Class.forName("org.mariadb.jdbc.Driver");
        
            con = ConnectionFactory.getConnection();

            formDAO = new FormularioServiceDAO(con);
            
            System.out.println("Conexao foi feita com sucesso");
        }catch(ClassNotFoundException e){

            System.out.println("Erro ao carregar classe");
            e.printStackTrace();

        }catch(SQLException e){

            System.out.println("Erro ao conectar com banco de dados");
            e.printStackTrace();
        }

    }

    public DiaTrabalhado toEntity(){

        DiaTrabalhado d = new DiaTrabalhado();
      
        d.setNomeProjeto(nomeProjeto.get());
        d.setDataServico(dataServico.get());
        d.setHoraInicio(LocalTime.parse(horaInicio.get()));
        d.setHoraFim(LocalTime.parse(horaFim.get()));
        d.setFeriado(feriado.get());
        d.setViagem(viagem.get());

        return d;

    }


    public void salvaDia(){

        DiaTrabalhado dia = toEntity();

        //definir logica de acordo com a regra de negocio

    }

    public boolean validaDia(DiaTrabalhado dia){

        if(dia.getNomeProjeto().isBlank()){
            System.out.println("Preencha o nome do projeto");
            return false;
        }

       if(dia.getDataServico() == null){
            System.out.println("Preencha o dia que foi trabalho");
            return false;
       }

       if(dia.getHoraInicio() == null){
            System.out.println("Preencha o horario de inicio corretamente");
            return false;
       }

       if(dia.getHoraFim() == null){
            System.out.println("Preencha o horario de fim corretamente");
            return false;
       }

        return true;
    }




    public StringProperty nomeProjetoProperty(){
        return nomeProjeto;
    }

    public ObjectProperty<LocalDate> dataServicoProperty(){
        return dataServico;
    }

    public StringProperty horaInicioProperty(){
        return horaInicio;
    }

    public StringProperty horaFimProperty(){
        return horaFim;
    }

    public BooleanProperty feriadoProperty(){
        return feriado;
    }

    public BooleanProperty viagemProperty(){
        return viagem;
    }

    

}
