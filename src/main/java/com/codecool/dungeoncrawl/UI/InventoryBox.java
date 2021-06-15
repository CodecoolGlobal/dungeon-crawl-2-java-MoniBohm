package com.codecool.dungeoncrawl.UI;

import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.List;

public class InventoryBox {


    public static void display(List inventory){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("title");
        window.setMinWidth(350);

        Label label = new Label();
        label.setText("Inventory");

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(400, 200);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Text text1 = new Text();
        text1.setText("Mana");
        Text text2 = new Text();
        text2.setText("Helmet");
        Text text3 = new Text();
        text3.setText("Health potion");
        Text text4 = new Text();
        text4.setText("body armour");
        Text text5 = new Text();
        text5.setText("Left leg armour");
        Text text6 = new Text();
        text6.setText("bottom armour");
        Text text7 = new Text();
        text7.setText("Right leg armour");

//        text1.setScaleY(10);
//        text2.setScaleY(10);
//        text3.setScaleY(10);
//        text4.setScaleY(10);
//        text5.setScaleY(10);


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
        closeButton.setOnAction( e -> window.close());

        VBox layout = new VBox(350);
        layout.getChildren().addAll( label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.showAndWait();


    }
}
