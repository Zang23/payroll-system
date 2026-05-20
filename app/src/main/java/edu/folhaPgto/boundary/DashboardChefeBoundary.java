package edu.folhaPgto.boundary;

import edu.folhaPgto.control.DashChefeControl;
import edu.folhaPgto.dto.request.DashFuncionarioRequestDTO;
import edu.folhaPgto.entity.Funcionario;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DashboardChefeBoundary {

    private VBox root = new VBox();

    private DashChefeControl chefeCtrl = new DashChefeControl();
    

    private ObservableList<DashFuncionarioRequestDTO> funcionarios = FXCollections.observableArrayList(chefeCtrl.carregarTabela());

    public DashboardChefeBoundary() {

        root.setStyle("-fx-background-color: #f5f5f5;");
        root.setPadding(new Insets(30));

        Label lblTitulo = new Label("Dashboard do Chefe");

        lblTitulo.setStyle(
            "-fx-font-size: 26px;" +
            "-fx-font-weight: bold;"
        );

       
        TableView<DashFuncionarioRequestDTO> tabela = new TableView<>();

        tabela.setItems(funcionarios);

        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<DashFuncionarioRequestDTO, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(itemData -> new ReadOnlyLongWrapper(itemData
                .getValue()
                .getId()).asObject());

        TableColumn<DashFuncionarioRequestDTO, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData
                .getValue()
                .getNome()));

        TableColumn<DashFuncionarioRequestDTO, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData
                .getValue()
                .getEmail()));

        TableColumn<DashFuncionarioRequestDTO, String> colTelefone = new TableColumn<>("Telefone");

        colTelefone.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData
                .getValue()
                .getTelefone()));

        TableColumn<DashFuncionarioRequestDTO, Void> colEditar = new TableColumn<>("Editar");

        colEditar.setCellFactory(param -> new TableCell<>() {

            private final Button btnEditar = new Button("Editar");

            {

                btnEditar.setStyle(
                        "-fx-background-color: #1976d2;" +
                                "-fx-text-fill: white;" +
                                "-fx-cursor: hand;" +
                                "-fx-font-weight: bold;");

                // funcao para editar

            }

            @Override
            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnEditar);
                }
                
            }

        });

        // ================= COLUNA EXCLUIR =================
        TableColumn<DashFuncionarioRequestDTO, Void> colExcluir = new TableColumn<>("Excluir");

        colExcluir.setCellFactory(param -> new TableCell<>() {

            private final Button btnExcluir = new Button("Excluir");

            {

                btnExcluir.setStyle(
                    "-fx-background-color: #d32f2f;" +
                    "-fx-text-fill: white;" +
                    "-fx-cursor: hand;" +
                    "-fx-font-weight: bold;"
                );

                btnExcluir.setOnAction(e -> {

                    DashFuncionarioRequestDTO dto = getTableView()
                        .getItems()
                        .get(getIndex());
                    
                    chefeCtrl.deletarFuncionario(dto);

                    getTableView().getItems().remove(dto);

                });
                
                
            }

            @Override
            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnExcluir);
                }

            }

        });

        // ================= COLUNA VER =================
        TableColumn<DashFuncionarioRequestDTO, Void> colVer = new TableColumn<>("Ver");

        colVer.setCellFactory(param -> new TableCell<>() {

            private final Button btnVer = new Button("Ver");

            {

                btnVer.setStyle(
                    "-fx-background-color: #1976d2;" +
                    "-fx-text-fill: white;" +
                    "-fx-cursor: hand;" +
                    "-fx-font-weight: bold;"
                );

                // funcao para 'ver' funcionario

            }

            @Override
            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnVer);
                }
                
            }

        });

        // ================= ADICIONA COLUNAS =================
        tabela.getColumns().addAll(
            colId,
            colNome,
            colEmail,
            colTelefone,
            colEditar,
            colExcluir,
            colVer
        );

        VBox.setVgrow(tabela, Priority.ALWAYS);

        VBox card = new VBox(20);

        card.getChildren().addAll(lblTitulo, tabela);

        card.setPadding(new Insets(25));

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;"
        );

        root.getChildren().add(card);

    }

    public Parent getRoot() {

        return root;

    }

}