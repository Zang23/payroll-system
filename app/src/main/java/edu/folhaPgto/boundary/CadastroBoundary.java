package edu.folhaPgto.boundary;


import edu.folhaPgto.control.CadastroControl;
import javafx.beans.binding.Bindings;
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

public class CadastroBoundary {

    private TextField txtNome = new TextField();
    private TextField txtEmail = new TextField();
    private TextField txtTelefone = new TextField();
    private PasswordField txtSenha = new PasswordField();
    private TextField txtTipo = new TextField();

    private Button btnCadastrar = new Button("Cadastrar");

    CadastroControl cadastroCtrl = new CadastroControl();

    private VBox root = new VBox();

    public CadastroBoundary(Stage stage) {

        Label lblTitulo = new Label("Cadastro");

        // css Titulo
        lblTitulo.setStyle("""
            -fx-font-size: 28px;
            -fx-font-weight: bold;
        """);

        // Campos
        
        txtNome.setPromptText("Nome");
        txtNome.setPrefHeight(40);

        txtTelefone.setPromptText("Telefone");
        txtTelefone.setPrefHeight(40);

        txtTipo.setPromptText("Tipo");
        txtTipo.setPrefHeight(40);

        txtEmail.setPromptText("Email");
        txtEmail.setPrefHeight(40); 
        
        txtSenha.setPromptText("Senha");
        txtSenha.setPrefHeight(40);


        // Botão
        

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
            txtTelefone,
            txtTipo,
            txtEmail,
            txtSenha,
            btnCadastrar
        );

        btnCadastrar.setOnAction(e -> {

            boolean cadastroValido = cadastroCtrl.cadastrar();

            if(cadastroValido){
                LoginBoundary telaLogin = new LoginBoundary(stage);

                Scene loginScene = new Scene(telaLogin.getRoot(), 900, 600);

                stage.setScene(loginScene);

            }

        });

        Bindings.bindBidirectional(txtNome.textProperty(), cadastroCtrl.nomeProperty());
        Bindings.bindBidirectional(txtTelefone.textProperty(), cadastroCtrl.telefoneProperty());
        Bindings.bindBidirectional(txtTipo.textProperty(), cadastroCtrl.tipoProperty());
        Bindings.bindBidirectional(txtEmail.textProperty(), cadastroCtrl.emailProperty());
        Bindings.bindBidirectional(txtSenha.textProperty(), cadastroCtrl.senhaProperty());
        


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