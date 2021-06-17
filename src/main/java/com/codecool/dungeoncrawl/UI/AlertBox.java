package com.codecool.dungeoncrawl.UI;

import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {


    public static void display(String title, String msg){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);

        Label label = new Label();
        label.setText(msg);
        label.setFont(Font.font("Verdana"));
        label.setId("alertmsg");
        Button closeButton = new Button("Close window");
        closeButton.setOnAction( e -> window.close());
        closeButton.setFont(Font.font("Verdana"));

        VBox layout = new VBox(10);
        layout.getChildren().addAll( label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();


    }
}
