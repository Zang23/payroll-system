package edu.folhaPgto.boundary;

import java.time.format.DateTimeFormatter;

import edu.folhaPgto.control.DashFeriadoControl;
import edu.folhaPgto.entity.Feriado;
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

public class DashboardFeriadoBoundary {

    private VBox root = new VBox();
    
    private DashFeriadoControl feriadoCtrl = new DashFeriadoControl();

    private Button btnNovoFeriado = new Button("Novo Feriado");
    private Button btnVoltar = new Button("Voltar");

    
    private ObservableList<Feriado> feriados = FXCollections.observableArrayList(feriadoCtrl.carregarTabela());

    public DashboardFeriadoBoundary(Stage stage) {

        root.setStyle("-fx-background-color: #f5f5f5;");
        root.setPadding(new Insets(30));

        Label lblTitulo = new Label("Gerenciamento de Feriados");

        lblTitulo.setStyle(
            "-fx-font-size: 26px;" +
            "-fx-font-weight: bold;"
        );

        TableView<Feriado> tabela = new TableView<>();

        tabela.setItems(feriados);

        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);



        TableColumn<Feriado, Long> colId = new TableColumn<>("ID");

        colId.setStyle(
            "-fx-alignment: CENTER;"
        );

        colId.setCellValueFactory(item -> new ReadOnlyLongWrapper(
            item.getValue().getId()).asObject()
        );


        TableColumn<Feriado, String> colData = new TableColumn<>("Data");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        colData.setStyle(
            "-fx-alignment: CENTER;"
        );

        colData.setCellValueFactory(item -> new ReadOnlyStringWrapper(
            item.getValue()
                .getDataFeriado()
                .format(formatter)
        ));


        TableColumn<Feriado, String> colDescricao = new TableColumn<>("Descrição");

        colDescricao.setStyle(
            "-fx-alignment: CENTER;"
        );

        colDescricao.setCellValueFactory(item -> new ReadOnlyStringWrapper(
            item.getValue()
                .getDescricao()
        ));


        TableColumn<Feriado, Void> colEditar = new TableColumn<>("Editar");

        colEditar.setCellFactory(param ->
            new TableCell<>() {

                private final Button btnEditar = new Button("Editar");

                {

                    btnEditar.setStyle(
                        "-fx-background-color: #1976d2;" +
                        "-fx-text-fill: white;" +
                        "-fx-cursor: hand;" +
                        "-fx-font-weight: bold;"
                    );

                    btnEditar.setOnAction(e -> {

                        Feriado feriado =
                            getTableView()
                            .getItems()
                            .get(getIndex());

                        EditarFeriadoBoundary telaEditar = new EditarFeriadoBoundary(stage, feriado);

                        Scene editarScene = new Scene(telaEditar.getRoot(), 900, 600);

                        stage.setScene(editarScene);

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
                        setStyle(
                            "-fx-alignment: CENTER;"
                        );

                    }

                }

            }
        );

       

        TableColumn<Feriado, Void> colExcluir = new TableColumn<>("Excluir");

        colExcluir.setCellFactory(param ->
            new TableCell<>() {

                private final Button btnExcluir =
                    new Button("Excluir");

                {

                    btnExcluir.setStyle(
                        "-fx-background-color: #d32f2f;" +
                        "-fx-text-fill: white;" +
                        "-fx-cursor: hand;" +
                        "-fx-font-weight: bold;"
                    );

                    btnExcluir.setOnAction(e -> {

                        Feriado feriado =
                            getTableView()
                            .getItems()
                            .get(getIndex());

                        feriadoCtrl.deletarFeriado(feriado);
                        getTableView().getItems().remove(feriado);
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
                        setStyle(
                            "-fx-alignment: CENTER;"
                        );

                    }

                }

            }
        );

        tabela.getColumns().addAll(
            colId,
            colData,
            colDescricao,
            colEditar,
            colExcluir
        );

        VBox.setVgrow(
            tabela,
            Priority.ALWAYS
        );

        btnNovoFeriado.setStyle(
            "-fx-background-color: #2e7d32;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );

        btnNovoFeriado.setOnAction(e -> {

            CadastroFeriadoBoundary telaCadastroFeriado = new CadastroFeriadoBoundary(stage);

            Scene cadastroFeriadoScene = new Scene(telaCadastroFeriado.getRoot(), 900, 600);

            stage.setScene(cadastroFeriadoScene);


        });

        btnVoltar.setStyle(
            "-fx-background-color: #d32f2f;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );

        btnVoltar.setOnAction(e -> {

            DashboardChefeBoundary telaChefe = new  DashboardChefeBoundary(stage);

            Scene chefeScene = new Scene(telaChefe.getRoot(), 900, 600);
            
            stage.setScene(chefeScene);

        });

        HBox footer = new HBox(10);

        footer.setAlignment(
            Pos.CENTER_RIGHT
        );

        footer.getChildren().addAll(
            btnNovoFeriado,
            btnVoltar
        );

        VBox card = new VBox(20);

        card.getChildren().addAll(
            lblTitulo,
            tabela,
            footer
        );

        card.setPadding(
            new Insets(25)
        );

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