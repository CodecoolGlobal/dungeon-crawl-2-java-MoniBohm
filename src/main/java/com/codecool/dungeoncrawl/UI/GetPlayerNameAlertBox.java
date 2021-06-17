package com.codecool.dungeoncrawl.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
        window.setTitle("Hi!");
        window.setMinWidth(250);

        Label label = new Label();
        label.setFont(Font.font("Verdana"));
        label.setId("alertmsg");
        Button closeButton = new Button("Close window");
        closeButton.setOnAction( e -> window.close());
        closeButton.setFont(Font.font("Verdana"));


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        final TextField name = new TextField();
        name.setPrefColumnCount(15);
        name.setPromptText("Enter your name: \n");
        GridPane.setConstraints(name, 0, 2);
        grid.getChildren().add(name);

        Button submit = new Button("Start");
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);
        submit.setOnAction(event -> {
                result.set(name.getText());
                window.close();
            });


        VBox layout = new VBox(10);
        layout.getChildren().addAll( label, closeButton);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(grid);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
        return result.toString();
    }

}
