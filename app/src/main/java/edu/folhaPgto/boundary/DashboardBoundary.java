package edu.folhaPgto.boundary;


import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class DashboardBoundary  {

     private BorderPane root = new BorderPane();

    public DashboardBoundary() {

        Label lblTitulo =
            new Label("Dashboard");

        root.setCenter(lblTitulo);

        BorderPane.setAlignment(lblTitulo, Pos.CENTER);
    }

    public Parent getRoot() {
        return root;
    }
    
}
