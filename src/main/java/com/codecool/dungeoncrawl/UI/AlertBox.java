package com.codecool.dungeoncrawl.UI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
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
        label.setId("message");
        Button closeButton = new Button("Close window");
        closeButton.setOnAction( e -> window.close());
        closeButton.setFont(Font.font("Verdana"));

        VBox layout = new VBox(10);
        layout.getChildren().addAll( label, closeButton);
        layout.setAlignment(Pos.CENTER);
        label.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
    }
    public static void display(String title, String msg, String pictureName){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);

        Label label = new Label();
        label.setText(msg);
        label.wrapTextProperty();
        label.setFont(Font.font("Verdana"));
        label.setAlignment(Pos.CENTER);
        label.setId("message");

        Image image = null;
        Button closeButton = new Button("Close window");
        closeButton.setOnAction( e -> window.close());
        closeButton.setFont(Font.font("Verdana"));
        if(pictureName.equals("load")){
            image = new Image("items/loading.gif");
        }
        if(pictureName.equals("loaded")){
            image = new Image("items/load.gif");
        }
        if(pictureName.equals("save")){
            image = new Image("items/save.gif");
        }
        if(pictureName.equals("magic")){
            image = new Image("items/magic.gif");
        }
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        VBox basicBox = new VBox(10);
        basicBox.getChildren().addAll( label, imageView, closeButton);
        basicBox.setId("layout");
        basicBox.setAlignment(Pos.CENTER);
        label.setAlignment(Pos.CENTER);

        Scene scene = new Scene(basicBox);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
    }
}
