package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.model.GameState;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SaveGameBox {

    static String saveNameFromUser = null;

    private static TextField textField;

    public static List<String> saveNames = new ArrayList<>();

    public static String display(List<GameState> gameStates){
        Stage window = new Stage();
        int counter = 1;
        setupWindow(window);

        VBox basicLayout = new VBox(10);
        basicLayout.setId("layout");
        basicLayout.setAlignment(Pos.CENTER);
        textField = generateTopTextField(basicLayout);

        generateSavedGamesToCenterLayout(gameStates, counter, basicLayout);
        createButtonsForSave(window, basicLayout, textField);

        Scene scene = new Scene(basicLayout);
        scene.getStylesheets().add("style.css");

        window.setScene(scene);
        window.showAndWait();

        return saveNameFromUser;

    }

    private static void generateSavedGamesToCenterLayout(List<GameState> gameStates, int counter, VBox basicLayout) {
        VBox centerLayout = new VBox(10);
        centerLayout.setId("centerLayout");
        ScrollPane sp = new ScrollPane();
        sp.setId("scrollPane");
        generateSavedGamesList(gameStates, counter, centerLayout);
        sp.setContent(centerLayout);
        sp.setVmax(500);
        sp.setPrefSize(300, 300);
        basicLayout.getChildren().add(sp);
        centerLayout.setAlignment(Pos.CENTER);
        sp.setFitToWidth(true);
    }

    private static TextField generateTopTextField(VBox basicLayout) {
        VBox topLayout = new VBox(10);
        Label label = new Label("Please enter a name for save file\nor choose a file to overwrite:");
        label.setId("message");
        TextField textField = new TextField ();
        textField.setId("textField");
        topLayout.getChildren().addAll(label, textField);
        basicLayout.getChildren().add(topLayout);
        topLayout.setAlignment(Pos.CENTER);
        return textField;
    }

    private static void setupWindow(Stage window) {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Save games");
        window.setMinWidth(500);
        window.setMinHeight(400);
    }

    private static void generateSavedGamesList(List<GameState> gameStates, int counter, VBox layout) {
        Button savedGameBtn;
        for(GameState state: gameStates){
            saveNames.add(state.getSaveName());
            savedGameBtn = new Button();
            String time = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(state.getSavedAt());
            savedGameBtn.setText(
                    "#" + counter + " | "
                    + state.getSaveName() + " | "
                    + time);

            savedGameBtn.setFont(Font.font("Verdana"));
            savedGameBtn.setId("savedGameBtn");
            layout.getChildren().add(savedGameBtn);
            savedGameBtn.setOnAction( e -> {
                saveNameFromUser = state.getSaveName();
                textField.setText(state.getSaveName());
            });
            counter++;
        }
    }

    private static void createButtonsForSave(Stage window, VBox basicLayout, TextField textField) {
        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font("Verdana"));
        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font("Verdana"));
        basicLayout.getChildren().addAll(saveButton, cancelButton);

        saveButton.setOnAction( e -> {
            saveNameFromUser = textField.getText();
            if (!saveNameFromUser.equals("")){
                if (saveNames.contains(saveNameFromUser)){
                    Boolean answer = ConfirmBox.display("Overwrite","Do you want to overwrite the save file named: "+ saveNameFromUser + "?");
                    if (answer) { window.close(); }
                }else{ window.close(); }
            }

        });

        cancelButton.setOnAction( e -> {
            saveNameFromUser = null;
            window.close();
        });
    }
}
