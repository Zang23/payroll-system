package edu.folhaPgto.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import edu.folhaPgto.dao.ConnectionFactory;
import edu.folhaPgto.dao.NovaFolhaDAO;
import edu.folhaPgto.entity.FolhaPagamento;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NovaFolhaPagamentoControl {
    
    private Connection con;

    private StringProperty valorHora = new SimpleStringProperty("");
    private StringProperty totalDias = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> dataInicial = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dataFinal = new SimpleObjectProperty<>();

    private NovaFolhaDAO folhaDAO;

    public NovaFolhaPagamentoControl(){

         try{
            
            Class.forName("org.mariadb.jdbc.Driver");
        
            con = ConnectionFactory.getConnection();

            folhaDAO = new NovaFolhaDAO(con);
            
            System.out.println("Conexao foi feita com sucesso");
        }catch(ClassNotFoundException e){

            System.out.println("Erro ao carregar classe");
            e.printStackTrace();

        }catch(SQLException e){

            System.out.println("Erro ao conectar com banco de dados");
            e.printStackTrace();
        }

    }

    public FolhaPagamento toEntity(){

        FolhaPagamento f = new FolhaPagamento();
      
        f.setValorHora(Double.parseDouble(valorHora.get()));
        f.setTotalDiasTrabalhados(Integer.parseInt(totalDias.get()));
        f.setDataInicial(dataInicial.get());
        f.setDataFinal(dataFinal.get());

        return f;

    }

    public FolhaPagamento cadastrar(Long funcionarioId) throws Exception {

        FolhaPagamento f = toEntity();

        f.setFuncionarioId(funcionarioId);

        if (f.getValorHora() <= 0) {
            throw new Exception("O valor da hora deve ser maior que 0");
        }

        if (f.getTotalDiasTrabalhados() <= 0) {
            throw new Exception("O total de dias deve ser maior que 0");
        }

        if (f.getDataInicial().isAfter(LocalDate.now())) {
            throw new Exception("A data inicial não pode ser futura");
        }

        if (f.getDataFinal().isAfter(LocalDate.now())) {
            throw new Exception("A data final não pode ser futura");
        }

        if (f.getDataFinal().isBefore(f.getDataInicial())) {
            throw new Exception("A data final deve ser depois da inicial");
        }

        if(folhaDAO.salvarFolha(f)){
            System.out.println("Sucesso ao cadastrar nova folha de pagamento");
            return f;
        }

        throw new Exception("Falha ao cadastrar a nova folha de pagamento");
        
    }

    public StringProperty valorHoraProperty(){
        return valorHora;
    }

    public StringProperty totalDiasProperty(){
        return totalDias;
    }

    public ObjectProperty<LocalDate> dataInicialProperty(){
        return dataInicial;
    }

    public ObjectProperty<LocalDate> dataFinalProperty(){
        return dataFinal;
    }
    


}
