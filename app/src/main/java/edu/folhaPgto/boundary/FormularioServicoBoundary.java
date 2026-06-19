package edu.folhaPgto.boundary;

import edu.folhaPgto.control.FormularioServicoControl;
import edu.folhaPgto.entity.DiaTrabalhado;
import edu.folhaPgto.entity.FolhaPagamento;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormularioServicoBoundary {

    private VBox root = new VBox();
    private VBox card = new VBox();

    private FormularioServicoControl formCtrl = new FormularioServicoControl();

    private TextField txtNomeProjeto = new TextField();
    private DatePicker dpDataServico = new DatePicker();
    private ComboBox<String> cbHoraInicio = new ComboBox<>();
    private ComboBox<String> cbHoraFim = new ComboBox<>();
    private CheckBox cbViagem = new CheckBox("Viagem");

    private Button btnContinuar = new Button("Continuar: ");

    public FormularioServicoBoundary(Stage stage, int diasTrabalhados, FolhaPagamento folha ) {

        root.setAlignment(Pos.CENTER);

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

        Label lblTitulo = new Label("Registro de Serviço");

        lblTitulo.setStyle("""
            -fx-font-size: 24px;
            -fx-font-weight: bold;
        """);


        txtNomeProjeto.setPromptText("Nome do Projeto");
        txtNomeProjeto.setPrefHeight(40);

        dpDataServico.setPromptText("Data Serviço");
        dpDataServico.setPrefHeight(40);
        dpDataServico.setMaxWidth(Double.MAX_VALUE);
        
        cbHoraInicio.setPromptText("Hora Início");
        cbHoraInicio.setPrefHeight(40);
        cbHoraInicio.setMaxWidth(Double.MAX_VALUE);

        cbHoraFim.setPromptText("Hora Fim");
        cbHoraFim.setPrefHeight(40);
        cbHoraFim.setMaxWidth(Double.MAX_VALUE);

        for (int hora = 0; hora < 24; hora++) {

            cbHoraInicio.getItems().add(
                String.format("%02d:00", hora)
            );

            cbHoraInicio.getItems().add(
                String.format("%02d:30", hora)
            );

            cbHoraFim.getItems().add(
                String.format("%02d:00", hora)
            );

            cbHoraFim.getItems().add(
                String.format("%02d:30", hora)
            );

        }
        

        HBox boxChecks = new HBox(20);
        boxChecks.setAlignment(Pos.CENTER_LEFT);

        boxChecks.getChildren().addAll(
            cbViagem
        );

        

        btnContinuar.setPrefHeight(45);
        btnContinuar.setMaxWidth(Double.MAX_VALUE);

        if(diasTrabalhados < folha.getTotalDiasTrabalhados()){
            btnContinuar.setText("Cadastrar (" + diasTrabalhados + "/" + folha.getTotalDiasTrabalhados() + ")");
        }else if(diasTrabalhados == folha.getTotalDiasTrabalhados()){
            btnContinuar.setText("Finalizar");
        }

        btnContinuar.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-font-weight: bold;
            -fx-background-radius: 10;
        """);

        btnContinuar.setOnAction(e -> {

            System.out.println("Folha ID = " + folha.getId());

            System.out.println("Folha recebida: " + folha);
            System.out.println("Folha ID: " + folha.getId());

            DiaTrabalhado diaValido = formCtrl.toEntity(folha.getId());

            if(formCtrl.validaDia(diaValido)){

                formCtrl.salvaDia(folha.getId());


                if(diasTrabalhados < folha.getTotalDiasTrabalhados()){
                    FormularioServicoBoundary telaServico = new FormularioServicoBoundary(stage, (diasTrabalhados + 1), folha);

                    Scene servicoScene = new Scene(telaServico.getRoot(), 900, 600);

                    stage.setScene(servicoScene);
                    
                }else if(diasTrabalhados == folha.getTotalDiasTrabalhados()){

                    String tipo = formCtrl.getTipoFuncionario(folha.getFuncionarioId());

                    DashboardFuncionarioBoundary telaFuncionario = new DashboardFuncionarioBoundary(stage, folha.getFuncionarioId(), tipo);

                    Scene funcionarioScene = new Scene(telaFuncionario.getRoot(), 900, 600);

                    stage.setScene(funcionarioScene);

                }   
            }
        
            


        });


        Bindings.bindBidirectional(txtNomeProjeto.textProperty(), formCtrl.nomeProjetoProperty());
        Bindings.bindBidirectional(dpDataServico.valueProperty(), formCtrl.dataServicoProperty());
        Bindings.bindBidirectional(cbHoraInicio.valueProperty(), formCtrl.horaInicioProperty());
        Bindings.bindBidirectional(cbHoraFim.valueProperty(), formCtrl.horaFimProperty());
        Bindings.bindBidirectional(cbViagem.selectedProperty(), formCtrl.viagemProperty());

        card.getChildren().addAll(
            lblTitulo,
            txtNomeProjeto,
            dpDataServico,
            cbHoraInicio,
            cbHoraFim,
            boxChecks,
            btnContinuar
        );

        root.getChildren().add(card);

    }

    public Parent getRoot() {

        return root;

    }

}