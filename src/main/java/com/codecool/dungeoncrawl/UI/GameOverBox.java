package com.codecool.dungeoncrawl.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverBox {

    public static void display(){
    Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Game Over");
        window.setMinWidth(250);
        //Creating a Grid Pane
        BorderPane gridPane = new BorderPane();
        gridPane.setId("gameOver");

        //Setting size for the pane
        gridPane.setMinSize(600, 600);

        Label label = new Label();
        label.setText("Rip Chicken :(");


    Button closeButton = new Button("Close window");
        closeButton.setOnAction( e -> window.close());

    VBox layout = new VBox(10);
        layout.getChildren().addAll( label, closeButton);
        layout.setAlignment(Pos.CENTER);
        gridPane.setBottom(layout);
        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
}



}
