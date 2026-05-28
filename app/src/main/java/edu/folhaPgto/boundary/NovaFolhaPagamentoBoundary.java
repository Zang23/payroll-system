package edu.folhaPgto.boundary;

import edu.folhaPgto.control.NovaFolhaPagamentoControl;
import edu.folhaPgto.entity.FolhaPagamento;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NovaFolhaPagamentoBoundary {

    

    private NovaFolhaPagamentoControl folhaCtrl = new NovaFolhaPagamentoControl();

    private TextField txtValorHora = new TextField();
    private TextField txtTotalDias = new TextField();
    private DatePicker dpDataInicial = new DatePicker();
    private DatePicker dpDataFinal = new DatePicker();
    private Button btnContinuar = new Button("Continuar");

    private VBox root = new VBox();

    private VBox card = new VBox();

    public NovaFolhaPagamentoBoundary(Stage stage, Long funcionarioId) {

        root.setAlignment(Pos.CENTER);

        root.setSpacing(20);

        root.setPadding(new Insets(40));

        root.setStyle("""
            -fx-background-color: #f5f5f5;
        """);

        card.setAlignment(Pos.CENTER);

        card.setSpacing(15);

        card.setPadding(new Insets(30));

        card.setMaxWidth(400);

        card.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 15;
            -fx-border-radius: 15;
        """);

        Label lblTitulo =
            new Label("Nova Folha de Pagamento");

        lblTitulo.setStyle("""
            -fx-font-size: 24px;
            -fx-font-weight: bold;
        """);

        txtValorHora.setPromptText("Valor por hora");
        txtValorHora.setPrefHeight(40);

        txtTotalDias.setPromptText(
            "Total de dias trabalhados"
        );
        txtTotalDias.setPrefHeight(40);

        

        dpDataInicial.setPromptText("Data inicial");
        dpDataInicial.setPrefHeight(40);
        dpDataInicial.setMaxWidth(Double.MAX_VALUE);

        dpDataFinal.setPromptText("Data final");
        dpDataFinal.setPrefHeight(40);
        dpDataFinal.setMaxWidth(Double.MAX_VALUE);


        btnContinuar.setPrefHeight(45);
        btnContinuar.setMaxWidth(Double.MAX_VALUE);
        btnContinuar.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-font-weight: bold;
            -fx-background-radius: 10;
        """);



        btnContinuar.setOnAction(e -> {

            try{

                FolhaPagamento folhaValida = folhaCtrl.cadastrar(funcionarioId);
                
                FormularioServicoBoundary telaServico = new FormularioServicoBoundary(stage,1,folhaValida);
                
                Scene servicoScene = new Scene(telaServico.getRoot(), 900, 600);

                stage.setScene(servicoScene);

            }catch(Exception ex){
                System.out.println(ex.getMessage()); 
            }

           

        });

        Bindings.bindBidirectional(txtValorHora.textProperty(), folhaCtrl.valorHoraProperty());
        Bindings.bindBidirectional(txtTotalDias.textProperty(), folhaCtrl.totalDiasProperty());
        Bindings.bindBidirectional(dpDataFinal.valueProperty(), folhaCtrl.dataFinalProperty());
        Bindings.bindBidirectional(dpDataInicial.valueProperty(), folhaCtrl.dataInicialProperty());


        card.getChildren().addAll(
            lblTitulo,
            txtValorHora,
            txtTotalDias,
            dpDataInicial,
            dpDataFinal,
            btnContinuar
        );

        root.getChildren().add(card);

    }

    public Parent getRoot() {

        return root;

    }

}