package edu.folhaPgto.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import edu.folhaPgto.dao.ConnectionFactory;
import edu.folhaPgto.dao.FormularioServiceDAO;
import edu.folhaPgto.entity.DiaTrabalhado;
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

    public DiaTrabalhado toEntity(Long id){

        DiaTrabalhado d = new DiaTrabalhado();
      
        d.setNomeProjeto(nomeProjeto.get());
        d.setDataServico(dataServico.get());
        d.setHoraInicio(LocalTime.parse(horaInicio.get()));
        d.setHoraFim(LocalTime.parse(horaFim.get()));
        d.setViagem(viagem.get());
        d.setFolhaPagamentoId(id);

        return d;

    }


    public void salvaDia(Long folhaId){

        System.out.println("FOLHA ID = " + folhaId);

        DiaTrabalhado registro = toEntity(folhaId);

        //definir logica de acordo com a regra de negocio

        double valorHora = consultaValorHora(folhaId);
        double valorTotal = 0.0;
        double valorMinuto = valorHora / 60;

        LocalDateTime inicio = LocalDateTime.of(
            registro.getDataServico(),
            registro.getHoraInicio()
        );

        LocalDateTime fim = LocalDateTime.of(
            registro.getDataServico(),
            registro.getHoraFim()
        );

        if(fim.isBefore(inicio)){
            fim = fim.plusDays(1);
        }

        LocalDateTime atual = inicio;

        Map<LocalDate, Boolean> cacheFeriado = new HashMap<>();

        while(atual.isBefore(fim)){

            LocalDateTime proximo = atual.plusMinutes(1);

            if(proximo.isAfter(fim)){
                proximo = fim;
            }

            DayOfWeek diaSemana = atual.getDayOfWeek();

            boolean noturno = false;

            if(atual.getHour() >= 22 || atual.getHour() < 5){
                noturno = true;
            }

            boolean sabado = false;

            if(diaSemana == DayOfWeek.SATURDAY){
                sabado = true;
            }

            boolean domingo = false;

            if(diaSemana == DayOfWeek.SUNDAY){
                domingo = true;
            }

            double multiplicador = 1.0;

            if(noturno){
                multiplicador += 0.20;
            }

            if(registro.isViagem()){
                multiplicador += 0.25;
            }   

            LocalDate dataAtual = atual.toLocalDate();
            
            boolean feriado = cacheFeriado.computeIfAbsent(
                dataAtual, 
                d -> formDAO.ehFeriado(d)
            );

            if(feriado){
                multiplicador += 1;
            }


            if(sabado){
                multiplicador += 0.5;
            }

            if(domingo){
                multiplicador += 1;
            }

            valorTotal += valorMinuto * multiplicador;

            atual = proximo;


        }

        registro.setValorRecebido(valorTotal);

        formDAO.salvarDia(registro);
        formDAO.atualizarValorTotalFolha(folhaId);
        
    }

    public double consultaValorHora(Long id){
        return formDAO.consultaValorHora(id);
    }


    public boolean validaDia(DiaTrabalhado dia){

        if(dia.getNomeProjeto() == null || dia.getNomeProjeto().isBlank() ){
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

       if(dia.getHoraInicio().equals(dia.getHoraFim())){
            System.out.println("Horário inicial e final não podem ser iguais");
            return false;
        }


        return true;
    }

    public String getTipoFuncionario(Long idFuncionario){

        return formDAO.getTipoFuncionario(idFuncionario);

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

    public BooleanProperty viagemProperty(){
        return viagem;
    }

    

}
