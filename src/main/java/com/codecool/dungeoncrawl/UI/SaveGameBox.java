package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.model.GameState;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class SaveGameBox {
   static String answer = null;

    public static String display(List<GameState> gameStates){
        System.out.println(gameStates);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Save games");
        window.setMinWidth(350);
        window.setMinHeight(350);
        int counter = 1;
        VBox layout = new VBox(10);
        RadioButton savedGameBtn;

        Label label1 = new Label("Name for save:");
        TextField textField = new TextField ();

        layout.getChildren().addAll(label1, textField);


        for(GameState state: gameStates){
            savedGameBtn = new RadioButton();
            savedGameBtn.setText(
                    "#" + counter + " | "
                    + state.getSaveName() + " | "
                    + state.getSavedAt().toString() + " "
                    + state.getPlayer().getPlayerName() + " "
                    + state.getMapFilename());

            savedGameBtn.setFont(Font.font("Verdana"));
            savedGameBtn.setId("savedGameBtn");
            layout.getChildren().add(savedGameBtn);
            counter++;
        }

        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font("Verdana"));
        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font("Verdana"));

        saveButton.setOnAction( e -> {
            answer = textField.getText();
            if (!answer.equals("")){
                //TODO check if save game with same name as input exists
                window.close();
            }

        });

        cancelButton.setOnAction( e -> {
            answer = null;
            window.close();
        });

        layout.getChildren().addAll(saveButton, cancelButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
