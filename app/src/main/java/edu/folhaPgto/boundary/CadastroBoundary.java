package edu.folhaPgto.boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CadastroBoundary {

    private VBox root = new VBox();

    public CadastroBoundary() {

        // Título
        Label lblTitulo = new Label("Cadastro");

        lblTitulo.setStyle("""
            -fx-font-size: 28px;
            -fx-font-weight: bold;
        """);

        // Campos
        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome");
        txtNome.setPrefHeight(40);

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Email");
        txtEmail.setPrefHeight(40);

        TextField txtTelefone = new TextField();
        txtTelefone.setPromptText("Telefone");
        txtTelefone.setPrefHeight(40);

        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Senha");
        txtSenha.setPrefHeight(40);

        TextField txtTipo = new TextField();
        txtTipo.setPromptText("Tipo");
        txtTipo.setPrefHeight(40);

        // Botão
        Button btnCadastrar = new Button("Cadastrar");

        btnCadastrar.setPrefWidth(Double.MAX_VALUE);
        btnCadastrar.setPrefHeight(45);

        btnCadastrar.setStyle("""
            -fx-background-color: #1976d2;
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
        """);

        // Formulário
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
            txtNome,
            txtEmail,
            txtTelefone,
            txtSenha,
            txtTipo,
            btnCadastrar
        );

        // Root
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