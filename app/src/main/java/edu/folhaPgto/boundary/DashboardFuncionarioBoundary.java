package edu.folhaPgto.boundary;

import edu.folhaPgto.entity.FolhaPagamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DashboardFuncionarioBoundary {

    private VBox root = new VBox();

    // ================= DADOS TEMPORARIOS =================
    private ObservableList<FolhaPagamento> folhas = FXCollections.observableArrayList(

            new FolhaPagamento(
                    1L,
                    "05/05/2026",
                    3500.00
            ),

            new FolhaPagamento(
                    2L,
                    "05/04/2026",
                    3400.00
            ),

            new FolhaPagamento(
                    3L,
                    "05/03/2026",
                    3300.00
            )

    );

    public DashboardFuncionarioBoundary() {

        root.setStyle("-fx-background-color: #f5f5f5;");

        root.setPadding(new Insets(30));

        // ================= TITULO =================
        Label lblTitulo = new Label("Folhas de Pagamento");

        lblTitulo.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;"
        );

        // ================= TABELA =================
        TableView<FolhaPagamento> tabela = new TableView<>();

        tabela.setItems(folhas);

        tabela.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        // ================= COLUNA ID =================
        TableColumn<FolhaPagamento, Long> colId =
                new TableColumn<>("ID");

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        // ================= COLUNA DATA =================
        TableColumn<FolhaPagamento, String> colData =
                new TableColumn<>("Data");

        colData.setCellValueFactory(
                new PropertyValueFactory<>("data")
        );

        // ================= COLUNA SALARIO =================
        TableColumn<FolhaPagamento, Double> colSalario =
                new TableColumn<>("Salário");

        colSalario.setCellValueFactory(
                new PropertyValueFactory<>("salario")
        );

        // ================= COLUNA EDITAR =================
        TableColumn<FolhaPagamento, Void> colEditar =
                new TableColumn<>("Editar");

        colEditar.setCellFactory(param -> new TableCell<>() {

            private final Button btnEditar =
                    new Button("Editar");

            {

                btnEditar.setStyle(
                        "-fx-background-color: #1976d2;" +
                        "-fx-text-fill: white;" +
                        "-fx-cursor: hand;" +
                        "-fx-font-weight: bold;"
                );

                btnEditar.setOnAction(event -> {

                    FolhaPagamento folha =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    editarFolha(folha);

                });

            }

            @Override
            protected void updateItem(
                    Void item,
                    boolean empty
            ) {

                super.updateItem(item, empty);

                if (empty) {

                    setGraphic(null);

                } else {

                    setGraphic(btnEditar);

                }

            }

        });

        // ================= COLUNA VER =================
        TableColumn<FolhaPagamento, Void> colVer =
                new TableColumn<>("Ver");

        colVer.setCellFactory(param -> new TableCell<>() {

            private final Button btnVer =
                    new Button("Ver");

            {

                btnVer.setStyle(
                        "-fx-background-color: #1976d2;" +
                        "-fx-text-fill: white;" +
                        "-fx-cursor: hand;" +
                        "-fx-font-weight: bold;"
                );

                btnVer.setOnAction(event -> {

                    FolhaPagamento folha =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    verFolha(folha);

                });

            }

            @Override
            protected void updateItem(
                    Void item,
                    boolean empty
            ) {

                super.updateItem(item, empty);

                if (empty) {

                    setGraphic(null);

                } else {

                    setGraphic(btnVer);

                }

            }

        });

        // ================= COLUNA EXCLUIR =================
        TableColumn<FolhaPagamento, Void> colExcluir =
                new TableColumn<>("Excluir");

        colExcluir.setCellFactory(param -> new TableCell<>() {

            private final Button btnExcluir =
                    new Button("Excluir");

            {

                btnExcluir.setStyle(
                        "-fx-background-color: #d32f2f;" +
                        "-fx-text-fill: white;" +
                        "-fx-cursor: hand;" +
                        "-fx-font-weight: bold;"
                );

                btnExcluir.setOnAction(event -> {

                    FolhaPagamento folha =
                            getTableView()
                                    .getItems()
                                    .get(getIndex());

                    excluirFolha(folha);

                });

            }

            @Override
            protected void updateItem(
                    Void item,
                    boolean empty
            ) {

                super.updateItem(item, empty);

                if (empty) {

                    setGraphic(null);

                } else {

                    setGraphic(btnExcluir);

                }

            }

        });

        // ================= ADICIONA COLUNAS =================
        tabela.getColumns().addAll(
                colId,
                colData,
                colSalario,
                colEditar,
                colVer,
                colExcluir
        );

        VBox.setVgrow(tabela, Priority.ALWAYS);

        // ================= CARD =================
        VBox card = new VBox(20);

        card.getChildren().addAll(
                lblTitulo,
                tabela
        );

        card.setPadding(new Insets(25));

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;"
        );

        // ================= ROOT =================
        root.getChildren().add(card);

    }

    // ================= EDITAR =================
    private void editarFolha(FolhaPagamento folha) {

        System.out.println(
                "Editar folha: " + folha.getId()
        );

    }

    // ================= VER =================
    private void verFolha(FolhaPagamento folha) {

        System.out.println(
                "Visualizar folha: " + folha.getId()
        );

    }

    // ================= EXCLUIR =================
    private void excluirFolha(FolhaPagamento folha) {

        System.out.println(
                "Excluir folha: " + folha.getId()
        );

        folhas.remove(folha);

    }

    // ================= GET ROOT =================
    public Parent getRoot() {

        return root;

    }

}