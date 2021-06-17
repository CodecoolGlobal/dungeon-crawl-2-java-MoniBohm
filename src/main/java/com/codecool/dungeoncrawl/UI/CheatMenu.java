package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Coin;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class CheatMenu {

    public static final int INCREASE_VALUE = 1000;
    public static final int MIN_HEALTH = 5;

    public static void display(Player player){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Cheat Menu");
        window.setMinWidth(350);
        window.setMinHeight(350);
        Label label = new Label();
        label.setText("Choose an option");
        label.setId("confirmmsg");

        Button infiniteLive = new Button("IDDQD ++Health++");
        Button allStats = new Button("IDKFA ++Damage++");
        Button allKeysAndCoins = new Button("IDBEHOLDA ++Keys&coins++");
        Button setHealthToMin = new Button("Dont Push!");

        infiniteLive.setOnAction( e -> {
            player.setCheatHealth(INCREASE_VALUE);
            window.close();
        });

        setHealthToMin.setOnAction( e -> {
            player.setCheatHealthToMin(MIN_HEALTH);
            window.close();
        });

        allKeysAndCoins.setOnAction( e -> {
            player.getInventory().removeIf(element -> element instanceof Key);
            Key.count =0;
            for (int i = 0; i < 10; i++) {
                Key key = new Key(player.getCell());
                player.getInventory().add(key);
            }
            for (int i = 0; i < 10; i++) {
                Coin coin = new Coin(player.getCell());
                player.getInventory().add(coin);
            }
            window.close();
        });

        allStats.setOnAction( e -> {
            player.setArmor(INCREASE_VALUE);
            player.setDamage(INCREASE_VALUE);
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll( label, infiniteLive, allStats, allKeysAndCoins, setHealthToMin);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
    }
}
