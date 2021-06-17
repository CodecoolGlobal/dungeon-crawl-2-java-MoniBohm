package com.codecool.dungeoncrawl.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class GetPlayerNameAlertBox {

    public static String display(){
        Stage window = new Stage();
        AtomicReference<String> result = new AtomicReference<>("");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Start New Game");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("Enter your name please : ");
        label.setFont(Font.font("Verdana"));
        label.setId("alertmsg");

        final TextField name = new TextField();
        name.setPrefColumnCount(15);

        Button submit = new Button("Start");
        submit.setOnAction(event -> {
                result.set(name.getText());
                window.close();
            });


        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(label);
        layout.getChildren().add(name);
        layout.getChildren().add(submit);


        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
        return result.toString();
    }

}
