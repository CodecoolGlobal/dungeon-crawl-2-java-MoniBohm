package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CheatMenu {

    public static void display(Player player){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Cheat Menu");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("Choose an option");
        label.setId("confirmmsg");

        Button infiniteLive = new Button("IDDQD");
        Button allStats = new Button("IDKFA");

        infiniteLive.setOnAction( e -> {
            player.setHealth(1000);
            window.close();
        });

        allStats.setOnAction( e -> {
            player.setArmor(1000);
            player.setDamage(1000);
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll( label, infiniteLive, allStats);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
    }
}
