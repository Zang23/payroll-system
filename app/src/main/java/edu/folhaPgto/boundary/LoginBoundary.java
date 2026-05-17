package edu.folhaPgto.boundary;

import edu.folhaPgto.control.LoginControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginBoundary extends Application {

        private TextField txtEmail = new TextField();
        private PasswordField txtSenha = new PasswordField();

        private Button btnEntrar = new Button("ENTRAR");
        private Button btnCadastrar = new Button("CADASTRAR");

        private LoginControl loginCtrl = new LoginControl();



        @Override
        public void start(Stage stage) {


                Label lblTitulo = new Label("Login");
                //css lblTitulo
                lblTitulo.setStyle("""
                        -fx-font-size: 40px;
                        -fx-font-weight: bold;
                        """);

                
                //placeholder email
                txtEmail.setPromptText("Email");
                txtEmail.setPrefHeight(45);

                //placeholder senha
                txtSenha.setPromptText("Senha");
                txtSenha.setPrefHeight(45);

                btnEntrar.setPrefHeight(45);
                btnEntrar.setMaxWidth(Double.MAX_VALUE);

                btnCadastrar.setPrefHeight(45);
                btnCadastrar.setMaxWidth(Double.MAX_VALUE);

                // VBox principal (card branco)
                VBox card = new VBox(20);

                card.getChildren().addAll(
                        lblTitulo,
                        txtEmail,
                        txtSenha,
                        btnEntrar,
                        btnCadastrar
                );

                card.setAlignment(Pos.CENTER);
                card.setPadding(new Insets(40));
                card.setMaxWidth(400);

                card.setStyle("""
                        -fx-background-color: white;
                        -fx-background-radius: 10;
                        -fx-border-radius: 10;
                        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10,0,0,4);
                        """);

                // Fundo
                StackPane root = new StackPane(card);

                root.setStyle("""
                        -fx-background-color: #f0f0f0;
                        """);

                Scene scene = new Scene(root, 700, 500);

                btnEntrar.setOnAction(e -> {

                        boolean loginValido = loginCtrl.logar();

                        if(loginValido){
                               DashboardBoundary telaDash = new DashboardBoundary();

                               Scene dashboardScene = new Scene(telaDash.getRoot(),900,600);

                               stage.setScene(dashboardScene);
                        }
                });

                btnCadastrar.setOnAction(e -> {

                        CadastroBoundary telaCadastro = new CadastroBoundary();

                        Scene cadastroScene = new Scene(telaCadastro.getRoot(), 900, 600);

                        stage.setScene(cadastroScene);

                });

                Bindings.bindBidirectional(txtEmail.textProperty(), loginCtrl.emailProperty());
                Bindings.bindBidirectional(txtSenha.textProperty(), loginCtrl.senhaProperty());


                stage.setTitle("Sistema Folha de Pagamento");
                stage.setScene(scene);
                stage.show();
        }

}
