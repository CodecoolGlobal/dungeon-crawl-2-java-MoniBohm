package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.UI.ConfirmBox;
import com.codecool.dungeoncrawl.UI.InventoryBox;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.util.Direction;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window;
    String mapFile1 = "/map.txt";
    String mapFile2 = "/map2.txt";
    GameMap map = MapLoader.loadMap(mapFile1);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();    // label for health
    Label inventoryLabel = new Label(); // label for inventory

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setMaxWidth(1500);
        window.setMaxHeight(1500);
        window.setMinWidth(500);
        window.setMinHeight(500);
        GridPane ui = new GridPane();
        ui.setPrefWidth(150);   // inventory width
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Inventory: "), 0, 20);
        ui.add(inventoryLabel, 1, 20);

        window.setOnCloseRequest(e -> {
            e.consume();
            closeApp();
        });

        BorderPane borderPane = new BorderPane();  // borderPane layout

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);    // puts ui to a right pane layout

        Scene scene = new Scene(borderPane); // creating the scene filling it with layout
        primaryStage.setScene(scene); // put's the scene in main window
        refresh();  // printing
        scene.setOnKeyPressed(this::onKeyPressed); // Player movement - eventlistener

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void closeApp() {   // modal window for closing
        Boolean answer = ConfirmBox.display("Exit", "Do you want to exit?");
        if (answer) {
            window.close();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) { // key event
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().initMove(Direction.UP.dx, Direction.UP.dy);
                refresh();
                break;
            case DOWN:
                map.getPlayer().initMove(Direction.DOWN.dx, Direction.DOWN.dy);
                refresh();
                break;
            case LEFT:
                map.getPlayer().initMove(Direction.LEFT.dx, Direction.LEFT.dy);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().initMove(Direction.RIGHT.dx, Direction.RIGHT.dy);
                refresh();
                break;
            case I:
                InventoryBox.display(map.getPlayer().getInventory());
                refresh();
                break;
        }
    }

    private void refresh() {        // This method responsible for printing
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y); // draws player on ui
                } else if (cell.getItem() != null) {        // draws items on ui
                        Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y); // draws empty on ui
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth()); // represents health
        inventoryLabel.setText("" + map.getPlayer().inventoryToString()); //represents inventory
    }
}
