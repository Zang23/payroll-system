package edu.folhaPgto.boundary;

import edu.folhaPgto.entity.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DashboardChefeBoundary {

    private VBox root = new VBox();

    // Dados temporários
    private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(
            new Funcionario(1l, "João Silva", "joao@email.com", "123", "11999999999", "funcionario"),
            new Funcionario(2l, "Maria Santos", "maria@email.com", "123", "11988888888", "funcionario"),
            new Funcionario(3l, "Pedro Oliveira", "pedro@email.com", "123", "11977777777", "funcionario")
    );

    public DashboardChefeBoundary() {

        root.setStyle("-fx-background-color: #f5f5f5;");
        root.setPadding(new Insets(30));

        // ================= TITULO =================
        Label lblTitulo = new Label("Dashboard do Chefe");

        lblTitulo.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;"
        );

        // ================= TABELA =================
        TableView<Funcionario> tabela = new TableView<>();

        tabela.setItems(funcionarios);

        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // ================= COLUNA ID =================
        TableColumn<Funcionario, Long> colId = new TableColumn<>("ID");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        // ================= COLUNA NOME =================
        TableColumn<Funcionario, String> colNome = new TableColumn<>("Nome");

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        // ================= COLUNA EMAIL =================
        TableColumn<Funcionario, String> colEmail = new TableColumn<>("Email");

        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // ================= COLUNA TELEFONE =================
        TableColumn<Funcionario, String> colTelefone = new TableColumn<>("Telefone");

        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        // ================= COLUNA TIPO =================
        TableColumn<Funcionario, String> colTipo = new TableColumn<>("Tipo");

        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        // ================= COLUNA AÇÕES =================
        TableColumn<Funcionario, Void> colEditar = new TableColumn<>("Editar");

colEditar.setCellFactory(param -> new TableCell<>() {

    private final Button btnEditar = new Button("Editar");

    {

        btnEditar.setStyle(
                "-fx-background-color: #1976d2;" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;" +
                "-fx-font-weight: bold;"
        );

        btnEditar.setOnAction(event -> {

            Funcionario funcionario = getTableView()
                    .getItems()
                    .get(getIndex());

            editarFuncionario(funcionario);

        });

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
TableColumn<Funcionario, Void> colExcluir = new TableColumn<>("Excluir");

colExcluir.setCellFactory(param -> new TableCell<>() {

    private final Button btnExcluir = new Button("Excluir");

    {

        btnExcluir.setStyle(
                "-fx-background-color: #d32f2f;" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;" +
                "-fx-font-weight: bold;"
        );

        btnExcluir.setOnAction(event -> {

            Funcionario funcionario = getTableView()
                    .getItems()
                    .get(getIndex());

            excluirFuncionario(funcionario);

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
TableColumn<Funcionario, Void> colVer = new TableColumn<>("Ver");

colVer.setCellFactory(param -> new TableCell<>() {

    private final Button btnVer = new Button("Ver");

    {

        btnVer.setStyle(
                "-fx-background-color: #1976d2;" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;" +
                "-fx-font-weight: bold;"
        );

        btnVer.setOnAction(event -> {

            Funcionario funcionario = getTableView()
                    .getItems()
                    .get(getIndex());

            verFuncionario(funcionario);

        });

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
            colTipo,
            colEditar,
            colExcluir,
            colVer
        );  

        VBox.setVgrow(tabela, Priority.ALWAYS);

        // ================= CARD =================
        VBox card = new VBox(20);

        card.getChildren().addAll(lblTitulo, tabela);

        card.setPadding(new Insets(25));

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;"
        );

        // ================= ROOT =================
        root.getChildren().add(card);

    }

    // ================= METODO EDITAR =================
    private void editarFuncionario(Funcionario funcionario) {

        System.out.println(
                "Editar funcionario: " + funcionario.getId()
        );

    }

    // ================= METODO EXCLUIR =================
    private void excluirFuncionario(Funcionario funcionario) {

        System.out.println(
                "Excluir funcionario: " + funcionario.getId()
        );

        funcionarios.remove(funcionario);

    }

    // ================= METODO VER =================
    private void verFuncionario(Funcionario funcionario) {

        System.out.println(
                "Visualizando funcionario: " + funcionario.getId()
        );

    }

    // ================= GET ROOT =================
    public Parent getRoot() {

        return root;

    }

}