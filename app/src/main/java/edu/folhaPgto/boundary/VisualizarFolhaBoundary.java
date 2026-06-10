package edu.folhaPgto.boundary;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

import edu.folhaPgto.control.VisualizarFolhaControl;
import edu.folhaPgto.entity.DiaTrabalhado;
import edu.folhaPgto.entity.FolhaPagamento;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VisualizarFolhaBoundary {

    private VBox root = new VBox();

    private VisualizarFolhaControl control = new VisualizarFolhaControl();

    private Button btnVoltar = new Button("Voltar");

    public VisualizarFolhaBoundary(Stage stage, FolhaPagamento folha) {

        root.setStyle(
            "-fx-background-color: #f5f5f5;"
        );

        root.setPadding(
            new Insets(30)
        );

        Label lblTitulo =
            new Label("Dias Trabalhados");

        lblTitulo.setStyle(
            "-fx-font-size: 26px;" +
            "-fx-font-weight: bold;"
        );

        Label lblPeriodo = new Label("Folha #" + folha.getId());

        ObservableList<DiaTrabalhado> dias = FXCollections.observableArrayList(control.buscarDiasFolha(folha.getId()));

        TableView<DiaTrabalhado> tabela = new TableView<>();

        tabela.setItems(dias);

        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<DiaTrabalhado, String> colData = new TableColumn<>("Data");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        colData.setStyle(
            "-fx-alignment: CENTER;"
        );

        colData.setCellValueFactory(item ->
            new ReadOnlyStringWrapper(
                item.getValue()
                    .getDataServico()
                    .format(formatter)
            )
        );

        TableColumn<DiaTrabalhado, String> colProjeto = new TableColumn<>("Projeto");

        colProjeto.setStyle(
            "-fx-alignment: CENTER;"
        );

        colProjeto.setCellValueFactory(item ->
            new ReadOnlyStringWrapper(
                item.getValue()
                    .getNomeProjeto()
            )
        );

        TableColumn<DiaTrabalhado, String> colHoras = new TableColumn<>("Horas Trabalhadas");

        colHoras.setStyle(
            "-fx-alignment: CENTER;"
        );

        colHoras.setCellValueFactory(item -> {

            DiaTrabalhado dia = item.getValue();

            Duration duracao;

            if (dia.getHoraFim().isBefore(dia.getHoraInicio())) {

                duracao = Duration.between(
                    dia.getHoraInicio(),
                    dia.getHoraFim().plusHours(24)
                );

            } else {

                duracao =
                    Duration.between(
                        dia.getHoraInicio(),
                        dia.getHoraFim()
                    );

            }

            long horas = duracao.toHours();

            long minutos = duracao.toMinutesPart();

            String texto = String.format(
                    "%dh%02d",
                    horas,
                    minutos
            );

            return new ReadOnlyStringWrapper(texto);
        });

        TableColumn<DiaTrabalhado, String> colValor = new TableColumn<>("Valor do Dia");

        colValor.setStyle(
            "-fx-alignment: CENTER;"
        );

        colValor.setCellValueFactory(item ->
            new ReadOnlyStringWrapper(
                String.format(
                    "R$ %.2f",
                    item.getValue()
                        .getValorRecebido()
                )
            )
        );

        tabela.getColumns().addAll(
            colData,
            colProjeto,
            colHoras,
            colValor
        );

        VBox.setVgrow(
            tabela,
            Priority.ALWAYS
        );

        btnVoltar.setStyle(
            "-fx-background-color: #d32f2f;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );

        btnVoltar.setOnAction(e -> {

            
            System.out.println("Funcionario ID = " + folha.getFuncionarioId());

            String tipo = control.getTipoFuncionario(folha.getFuncionarioId());

            DashboardFuncionarioBoundary tela = new DashboardFuncionarioBoundary(stage, folha.getFuncionarioId(), tipo);

            Scene scene = new Scene(tela.getRoot(),900,600);

            stage.setScene(scene);

        });

        HBox footer = new HBox();

        footer.setAlignment(Pos.CENTER_RIGHT);

        footer.getChildren().add(btnVoltar);

        VBox card = new VBox(20);

        card.setPadding(new Insets(25));

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;"
        );

        card.getChildren().addAll(
            lblTitulo,
            lblPeriodo,
            tabela,
            footer
        );

        root.getChildren().add(
            card
        );
    }

    public Parent getRoot() {
        return root;
    }
}