package edu.folhaPgto.boundary;

import edu.folhaPgto.control.DashFuncionarioControl;
import edu.folhaPgto.dto.request.DashFuncionarioRequestDTO;
import edu.folhaPgto.entity.FolhaPagamento;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardFuncionarioBoundary {

        private VBox root = new VBox();

        private DashFuncionarioControl funcCtrl = new DashFuncionarioControl();

        private Button btnAdcionar = new Button("Adicionar Folha");
        private Button btnVoltar = new Button("Voltar");


        public DashboardFuncionarioBoundary(Stage stage, Long dtoId) {

                ObservableList<FolhaPagamento> folhas = FXCollections.observableArrayList(funcCtrl.carregarTabela(dtoId));

                root.setStyle("-fx-background-color: #f5f5f5;");

                root.setPadding(new Insets(30));

                Label lblTitulo = new Label("Folhas de Pagamento");

                lblTitulo.setStyle(
                        "-fx-font-size: 26px;" +
                        "-fx-font-weight: bold;"
                );

                TableView<FolhaPagamento> tabela = new TableView<>();

                tabela.setItems(folhas);

                tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

                TableColumn<FolhaPagamento, Long> colId = new TableColumn<>("ID");

                colId.setCellValueFactory(
                        new PropertyValueFactory<>("id")
                );

                TableColumn<FolhaPagamento, String> colData = new TableColumn<>("Data Pagamento");

                colData.setCellValueFactory(
                        new PropertyValueFactory<>("dataPagamento")
                );

                TableColumn<FolhaPagamento, Double> colValorTotal = new TableColumn<>("Valor Folha");

                colValorTotal.setCellValueFactory(
                        new PropertyValueFactory<>("valorTotal")
                );

                TableColumn<FolhaPagamento, Void> colEditar = new TableColumn<>("Editar");

                colEditar.setCellFactory(param -> new TableCell<>() {

                        private final Button btnEditar = new Button("Editar");

                        {

                                btnEditar.setStyle(
                                        "-fx-background-color: #1976d2;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-cursor: hand;" +
                                        "-fx-font-weight: bold;"
                                );

                               // funcao editar

                        }

                        @Override
                        protected void updateItem(
                                        Void item,
                                        boolean empty) {

                                super.updateItem(item, empty);

                                if (empty) {

                                        setGraphic(null);

                                } else {

                                        setGraphic(btnEditar);

                                }

                        }

                });

                TableColumn<FolhaPagamento, Void> colVer = new TableColumn<>("Ver");

                colVer.setCellFactory(param -> new TableCell<>() {

                        private final Button btnVer = new Button("Ver");

                        {

                                btnVer.setStyle(
                                        "-fx-background-color: #1976d2;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-cursor: hand;" +
                                        "-fx-font-weight: bold;"
                                );
                                        

                        }

                        @Override
                        protected void updateItem(
                                        Void item,
                                        boolean empty) {

                                super.updateItem(item, empty);

                                if (empty) {

                                        setGraphic(null);

                                } else {

                                        setGraphic(btnVer);

                                }

                        }

                });

                TableColumn<FolhaPagamento, Void> colExcluir = new TableColumn<>("Excluir");

                colExcluir.setCellFactory(param -> new TableCell<>() {

                        private final Button btnExcluir = new Button("Excluir");

                        {

                                btnExcluir.setStyle(
                                        "-fx-background-color: #d32f2f;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-cursor: hand;" +
                                        "-fx-font-weight: bold;"
                                );

                               //funcao excluir

                        }

                        @Override
                        protected void updateItem(
                                        Void item,
                                        boolean empty) {

                                super.updateItem(item, empty);

                                if (empty) {

                                        setGraphic(null);

                                } else {

                                        setGraphic(btnExcluir);

                                }

                        }

                });

                tabela.getColumns().addAll(
                        colId,
                        colData,
                        colValorTotal,
                        colEditar,
                        colVer,
                        colExcluir
                );

                VBox.setVgrow(tabela, Priority.ALWAYS);

                btnAdcionar.setStyle(
                        "-fx-background-color: #2e7d32;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
                );      

                btnAdcionar.setOnAction(e -> {

                        NovaFolhaPagamentoBoundary telaNovaFolha = new NovaFolhaPagamentoBoundary(stage, dtoId);
                        Scene nFolhaScene = new Scene(telaNovaFolha.getRoot(), 900, 600);
                        stage.setScene(nFolhaScene);

                });

                btnVoltar.setStyle(
                        "-fx-background-color: #B31212;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
                );

                btnVoltar.setOnAction(e ->{
                        
                        DashboardChefeBoundary telaChefe = new DashboardChefeBoundary(stage);

                        Scene chefeScene = new Scene(telaChefe.getRoot(), 900, 600);

                        stage.setScene(chefeScene);

                });

                HBox footer = new HBox(20);

                footer.setAlignment(Pos.CENTER_RIGHT);

                footer.getChildren().addAll(btnAdcionar,btnVoltar);

                VBox card = new VBox(20);

                card.getChildren().addAll(lblTitulo,tabela, footer);

                card.setPadding(new Insets(25));

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-border-radius: 10;");

                // ================= ROOT =================
                root.getChildren().add(card);

        }

        

        public Parent getRoot() {

                return root;

        }

}