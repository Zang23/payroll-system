package edu.folhaPgto.boundary;

import edu.folhaPgto.control.CadastroFeriadoControl;
import edu.folhaPgto.entity.Feriado;
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

public class EditarFeriadoBoundary {

    private VBox root = new VBox();

    private DatePicker dpData = new DatePicker();
    private TextField txtDescricao = new TextField();

    private Button btnEditar = new Button("Editar");
    private Button btnVoltar = new Button("Voltar");

    private CadastroFeriadoControl control = new CadastroFeriadoControl();

    public EditarFeriadoBoundary(Stage stage, Feriado f) {

        Label lblTitulo = new Label("Cadastro de Feriado");

        lblTitulo.setStyle("""
            -fx-font-size: 28px;
            -fx-font-weight: bold;
        """);

        dpData.setPromptText("Data do Feriado");
        dpData.setPrefHeight(40);
        dpData.setMaxWidth(Double.MAX_VALUE);

        txtDescricao.setPromptText("Descrição");
        txtDescricao.setPrefHeight(40);

        txtDescricao.setText(f.getDescricao());
        dpData.setValue(f.getDataFeriado());

        btnEditar.setPrefWidth(Double.MAX_VALUE);
        btnEditar.setPrefHeight(45);

        btnEditar.setStyle("""
            -fx-background-color: #1976d2;
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
        """);

        btnVoltar.setPrefWidth(Double.MAX_VALUE);
        btnVoltar.setPrefHeight(45);

        btnVoltar.setStyle("""
            -fx-background-color: #B31212;
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
        """);



        VBox formulario = new VBox(15);

        formulario.setAlignment(Pos.CENTER);
        formulario.setPadding(new Insets(40));
        formulario.setMaxWidth(400);

        formulario.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 12;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 15, 0, 0, 4);
        """);

        formulario.getChildren().addAll(
            lblTitulo,
            dpData,
            txtDescricao,
            btnEditar,
            btnVoltar
        );

        btnEditar.setOnAction(e -> {

            boolean salvo = control.editar(f);

            if(salvo) {

                DashboardFeriadoBoundary tela = new DashboardFeriadoBoundary(stage);

                Scene scene = new Scene(
                    tela.getRoot(),
                    900,
                    600
                );

                stage.setScene(scene);
            }
        });

        btnVoltar.setOnAction(e -> {

            DashboardFeriadoBoundary tela = new DashboardFeriadoBoundary(stage);

            Scene scene = new Scene(tela.getRoot(),900,600);

            stage.setScene(scene);
        });

        Bindings.bindBidirectional(dpData.valueProperty(),control.dataProperty());

        Bindings.bindBidirectional(txtDescricao.textProperty(),control.descricaoProperty());

        root.setAlignment(Pos.CENTER);

        root.setStyle("""
            -fx-background-color: #f5f5f5;
        """);

        root.getChildren().add(formulario);
    }

    public Parent getRoot() {
        return root;
    }
}