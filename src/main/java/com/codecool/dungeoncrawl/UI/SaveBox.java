package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.model.GameState;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class SaveBox {
   static Boolean answer;

    public static Boolean display(List<GameState> gameStates){
        System.out.println(gameStates);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Save games");
        window.setMinWidth(350);
        window.setMinHeight(350);
        int counter = 0;
        VBox layout = new VBox(10);
        Button savedGameBtn;
        for(GameState state: gameStates){
            savedGameBtn = new Button();
            savedGameBtn.setText(state.getSavedAt().toString() + " " + state.getPlayer().getPlayerName() + " " + state.getMapFilename());
            savedGameBtn.setFont(Font.font("Verdana"));
            savedGameBtn.setId("savedGameBtn");
            layout.getChildren().add(savedGameBtn);
            counter++;
        }

        Button yesBtn = new Button("Save");
        yesBtn.setFont(Font.font("Verdana"));
        Button noBtn = new Button("Cancel");
        noBtn.setFont(Font.font("Verdana"));

        yesBtn.setOnAction( e -> {
            answer = true;
            window.close();
        });

        noBtn.setOnAction( e -> {
            answer = false;
            window.close();
        });
        layout.getChildren().addAll(yesBtn, noBtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();

        return answer;


    }
}
