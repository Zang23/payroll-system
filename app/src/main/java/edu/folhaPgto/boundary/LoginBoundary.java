package edu.folhaPgto.boundary;

import edu.folhaPgto.control.LoginControl;
import edu.folhaPgto.dto.request.LoginRequestDTO;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginBoundary{

        private TextField txtEmail = new TextField();
        private PasswordField txtSenha = new PasswordField();

        private Button btnEntrar = new Button("ENTRAR");
        private Button btnCadastrar = new Button("CADASTRAR");

        private LoginControl loginCtrl = new LoginControl();


        private StackPane root = new StackPane();
        
        public LoginBoundary (Stage stage) {


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

               
                root.getChildren().add(card);



                root.setStyle("""
                        -fx-background-color: #f0f0f0;
                        """);



                btnEntrar.setOnAction(e -> {


                        LoginRequestDTO dto = loginCtrl.logar();

                        if(dto == null || dto.getTipo() == null){
                                System.out.println("Email ou senha incorretos");
                                return;
                        }

                        if(dto.getTipo().equalsIgnoreCase("chefe")){
                                
                                DashboardChefeBoundary telaChefe = new DashboardChefeBoundary();

                                Scene dashChefe = new Scene(telaChefe.getRoot(), 900, 600);

                                stage.setScene(dashChefe);

                        }else if(dto.getTipo().equalsIgnoreCase("funcionario")){

                                DashboardFuncionarioBoundary telaFuncionario = new DashboardFuncionarioBoundary();

                                Scene dashFuncionario = new Scene(telaFuncionario.getRoot(), 900, 600);

                                stage.setScene(dashFuncionario);

                        }

                });

                btnCadastrar.setOnAction(e -> {

                        CadastroBoundary telaCadastro = new CadastroBoundary(stage);

                        Scene cadastroScene = new Scene(telaCadastro.getRoot(), 900, 600);

                        stage.setScene(cadastroScene);

                });

                Bindings.bindBidirectional(txtEmail.textProperty(), loginCtrl.emailProperty());
                Bindings.bindBidirectional(txtSenha.textProperty(), loginCtrl.senhaProperty());
                
        }

        public Parent getRoot(){
                return root;
        }

        

}
