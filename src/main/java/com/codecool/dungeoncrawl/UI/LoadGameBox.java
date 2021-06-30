package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.model.GameState;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class LoadGameBox {

    static int saveFileId;
    private static Label chosenGame = new Label();
    public static List<String> saveNames = new ArrayList<>();
    private static ToggleGroup group = new ToggleGroup();
    private static VBox centerLayout;
    private static Button loadButton;
    private static Button cancelButton;

    public static int display(List<GameState> gameStates){
        Stage window = new Stage();
        int counter = 1;

        VBox basicLayout = new VBox(10);


        setupWindow(window);
        basicLayout.setId("layout");
        basicLayout.setAlignment(Pos.CENTER);
        generateTopLabel(basicLayout);
        generateSavedGamesToCenterLayout(gameStates, counter, basicLayout);
        createButtonsForSave(basicLayout);

        Scene scene = new Scene(basicLayout);
        scene.getStylesheets().add("style.css");

        loadButton.setOnAction( e -> {
            if(!chosenGame.getText().equals("")){
                boolean answer = ConfirmBox.display("Load game?","Are you sure you want to load save game:\n" + chosenGame.getText());
                if (answer){
                    window.close();
                }
            }
        });

        cancelButton.setOnAction( e -> {
            saveFileId = 0;
            window.close();
        });

        window.setScene(scene);
        window.showAndWait();

        return saveFileId;
    }

    private static void generateTopLabel(VBox basicLayout) {
        VBox topLayout = new VBox(10);
        Label label = new Label("Chosen File: ");
        label.setId("message");
        topLayout.getChildren().addAll(label, chosenGame);
        basicLayout.getChildren().add(topLayout);
        topLayout.setAlignment(Pos.CENTER);
    }

    private static void generateSavedGamesToCenterLayout(List<GameState> gameStates, int counter, VBox basicLayout) {
        centerLayout = new VBox(10);
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

    private static void setupWindow(Stage window) {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Load game");
        window.setMinWidth(500);
        window.setMinHeight(400);
    }

    private static void generateSavedGamesList(List<GameState> gameStates, int counter, VBox layout) {
        RadioButton savegameFileButton;
        for(GameState state: gameStates){
            saveNames.add(state.getSaveName());
            savegameFileButton = new RadioButton();
            savegameFileButton.setText(
                    "#" + counter + " | "
                            + state.getSaveName() + " | "
                            + state.getSavedAt().toString() + " "
                            + state.getMapFilename());

            savegameFileButton.setFont(Font.font("Verdana"));
            savegameFileButton.setId("savedGameBtn");
            savegameFileButton.setToggleGroup(group);
            layout.getChildren().add(savegameFileButton);
            savegameFileButton.setOnAction( e -> {
                    chosenGame.setText(state.getSaveName());
                    saveFileId = state.getId();
            });
            counter++;
        }
    }

    private static void createButtonsForSave(VBox basicLayout) {
        loadButton = new Button("Load");
        loadButton.setFont(Font.font("Verdana"));
        cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font("Verdana"));
        basicLayout.getChildren().addAll(loadButton, cancelButton);

    }
}
