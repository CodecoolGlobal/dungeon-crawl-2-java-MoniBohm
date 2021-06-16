package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.util.Direction;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.io.File;
import java.util.List;

public class InventoryBox {
    Stage window;

    public void display(List inventory) {

        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Inventory");
        window.setMinWidth(350);

        Label label = new Label();
        label.setText("Inventory");

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();
        gridPane.setId("pane");

        //Setting size for the pane
        gridPane.setMinSize(400, 400);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Button text1 = new Button();
        text1.setText("Use Mana");
        Button text2 = new Button();
        text2.setText("Drop Helmet");
        Button text3 = new Button();
        text3.setText("Use Health potion");
        Button text4 = new Button();
        text4.setText("Drop body armour");
        Button text5 = new Button();
        text5.setText("Drop Left leg armour");
        Button text6 = new Button();
        text6.setText("Drop bottom armour");
        Button text7 = new Button();
        text7.setText("Drop Right leg armour");


        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(25);
        gridPane.setHgap(25);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(text1, 0, 0);
        gridPane.add(text2, 1, 0);
        gridPane.add(text3, 2, 0);
        gridPane.add(text4, 1, 1);
        gridPane.add(text5, 0, 3);
        gridPane.add(text6, 1, 2);
        gridPane.add(text7, 2, 3);
        gridPane.setGridLinesVisible(true);

        Button closeButton = new Button("Close window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(350);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        scene.setOnKeyPressed(this::onKeyPressed);
        window.showAndWait();

    }

    private void onKeyPressed(KeyEvent keyEvent) { // key event
        switch (keyEvent.getCode()) {
            case I:
                window.close();
                break;
        }
    }
}
