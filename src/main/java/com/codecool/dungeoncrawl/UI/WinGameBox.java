package com.codecool.dungeoncrawl.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WinGameBox {


    public static void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Winner, Winner, Chicken Dinner");
        window.setMinWidth(250);
        BorderPane gridPane = new BorderPane();
        gridPane.setId("winGame");

        gridPane.setMinSize(600, 600);

        Label label = new Label();
        label.setText("Congratulations!\nYou have beaten the evil KFC!");


        Button closeButton = new Button("Close window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        gridPane.setBottom(layout);
        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
    }

}

