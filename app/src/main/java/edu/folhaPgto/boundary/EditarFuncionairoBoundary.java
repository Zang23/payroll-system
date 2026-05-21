package edu.folhaPgto.boundary;

import edu.folhaPgto.control.DashChefeControl;
import edu.folhaPgto.dto.request.DashFuncionarioRequestDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditarFuncionairoBoundary {

    private VBox root = new VBox(20);

    private TextField txtNome = new TextField();
    private TextField txtEmail = new TextField();
    private TextField txtTelefone = new TextField();
    private PasswordField txtSenha = new PasswordField();

    private DashChefeControl chefeCtrl = new DashChefeControl();

    private Button btnSalvar = new Button("Salvar");

    public EditarFuncionairoBoundary(Stage stage, DashFuncionarioRequestDTO dto) {

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        root.setStyle(
            "-fx-background-color: #f5f5f5;"
        );

        VBox card = new VBox(18);

        card.setMaxWidth(400);

        card.setPadding(new Insets(30));

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-border-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 15, 0, 0, 4);"
        );

        Label lblTitulo = new Label("Editar Usuário");

        lblTitulo.setStyle(
            "-fx-font-size: 26px;" +
            "-fx-font-weight: bold;"
        );


        txtNome.setPromptText("Nome");
        txtEmail.setPromptText("Email");
        txtTelefone.setPromptText("Telefone");
        txtSenha.setPromptText("Senha");

        String estiloInput =
            "-fx-pref-height: 42;" +
            "-fx-font-size: 14px;" +
            "-fx-background-radius: 8;" +
            "-fx-border-radius: 8;" +
            "-fx-border-color: #dcdcdc;" +
            "-fx-background-color: white;";

        txtNome.setStyle(estiloInput);
        txtEmail.setStyle(estiloInput);
        txtTelefone.setStyle(estiloInput);
        txtSenha.setStyle(estiloInput);

        txtNome.setText(dto.getNome());
        txtEmail.setText(dto.getEmail());
        txtTelefone.setText(dto.getTelefone());

        btnSalvar.setMaxWidth(Double.MAX_VALUE);

        btnSalvar.setStyle(
            "-fx-background-color: #1976d2;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;" +
            "-fx-pref-height: 45;"
        );

        btnSalvar.setOnAction(e -> {

            dto.setNome(txtNome.getText());
            dto.setEmail(txtEmail.getText());
            dto.setTelefone(txtTelefone.getText());

            System.out.println("Funcionario atualizado:");
            System.out.println(dto.getNome());
            System.out.println(dto.getEmail());
            System.out.println(dto.getTelefone());

            chefeCtrl.atualizarFuncionario(dto);

            DashboardChefeBoundary telaChefe = new DashboardChefeBoundary(stage);

            Scene chefeScene = new Scene(telaChefe.getRoot(), 900, 600);

            stage.setScene(chefeScene);

        });

        card.getChildren().addAll(
            lblTitulo,
            txtNome,
            txtEmail,
            txtTelefone,
            txtSenha,
            btnSalvar
        );

        root.getChildren().add(card);
    }

    public Parent getRoot() {
        return root;
    }
}