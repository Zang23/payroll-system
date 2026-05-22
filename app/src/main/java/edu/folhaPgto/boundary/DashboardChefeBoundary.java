package edu.folhaPgto.boundary;

import edu.folhaPgto.control.DashChefeControl;
import edu.folhaPgto.dto.request.DashFuncionarioRequestDTO;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardChefeBoundary {

    private VBox root = new VBox();

    private DashChefeControl chefeCtrl = new DashChefeControl();

    private Button btnAdcionar = new Button("Adicionar Funcionario");


    private ObservableList<DashFuncionarioRequestDTO> funcionarios = FXCollections.observableArrayList(chefeCtrl.carregarTabela());

    public DashboardChefeBoundary(Stage stage) {

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
        colId.setStyle(
            "-fx-alignment: CENTER;" 
        );
        
        colId.setCellValueFactory(itemData -> new ReadOnlyLongWrapper(itemData
                .getValue()
                .getId()).asObject());

        TableColumn<DashFuncionarioRequestDTO, String> colNome = new TableColumn<>("Nome");
        colNome.setStyle(
            "-fx-alignment: CENTER;" 
        );

        colNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData
                .getValue()
                .getNome()));

        TableColumn<DashFuncionarioRequestDTO, String> colEmail = new TableColumn<>("Email");
        colEmail.setStyle(
            "-fx-alignment: CENTER;" 
        );

        colEmail.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData
                .getValue()
                .getEmail()));

        TableColumn<DashFuncionarioRequestDTO, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setStyle(
            "-fx-alignment: CENTER;" 
        );

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
                    "-fx-font-weight: bold;"
                );

                btnEditar.setOnAction(e -> {

                    DashFuncionarioRequestDTO dto = getTableView()
                        .getItems()
                        .get(getIndex());

                    EditarFuncionairoBoundary telaEditar = new EditarFuncionairoBoundary(stage, dto);

                    Scene editarScene = new Scene(telaEditar.getRoot(), 900, 600);

                    stage.setScene(editarScene);

                });

                

            }

            @Override
            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnEditar);
                    setStyle("-fx-alignment: CENTER;");
                }
                
            }

        });

        TableColumn<DashFuncionarioRequestDTO, Void> colExcluir = new TableColumn<>("Excluir");

        colExcluir.setCellFactory(param -> new TableCell<>() {

            private final Button btnExcluir = new Button("Excluir");

            {

                btnExcluir.setStyle(
                    "-fx-alignment: CENTER;" +
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
                    setStyle("-fx-alignment: CENTER;");
                }

            }

        });

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

                btnVer.setOnAction(e ->  {

                    DashFuncionarioRequestDTO dto = getTableView()
                        .getItems().get(getIndex());

                    DashboardFuncionarioBoundary telaFunc = new DashboardFuncionarioBoundary(stage, dto.getId());

                    Scene funcScene = new Scene(telaFunc.getRoot(), 900, 600);

                    stage.setScene(funcScene);



                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setStyle("-fx-alignment: CENTER;");
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

        btnAdcionar.setStyle(
            "-fx-background-color: #2e7d32;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );

        btnAdcionar.setOnAction(e -> {

            CadastroBoundary telaCadastro = new CadastroBoundary(stage);

            Scene cadastroScene = new Scene(telaCadastro.getRoot(), 900, 600);

            stage.setScene(cadastroScene);

        });

        HBox footer = new HBox();

        footer.setAlignment(Pos.CENTER_RIGHT);

        footer.getChildren().add(btnAdcionar);

        VBox card = new VBox(20);

        card.getChildren().addAll(lblTitulo, tabela, footer);

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