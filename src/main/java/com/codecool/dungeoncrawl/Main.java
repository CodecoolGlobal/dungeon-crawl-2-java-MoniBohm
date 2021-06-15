package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.UI.ConfirmBox;
import com.codecool.dungeoncrawl.UI.InventoryBox;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.util.Direction;
import javafx.application.Application;
import javafx.geometry.Bounds;
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
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    public static boolean isNextMap;
    public static boolean isPreviousMap;
    int currentMap = 0;
    List<String> nameOfFiles = setMapNames();
    Stage window;
    String mapFilename = nameOfFiles.get(currentMap);
    GameMap map = MapLoader.loadMap(mapFilename);
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
        generateScene(primaryStage);
    }

    private void generateScene(Stage primaryStage) {
        window = primaryStage;
//        window.setMaxWidth(1500);
//        window.setMaxHeight(1500);
//        window.setMinWidth(500);
//        window.setMinHeight(500);
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);   // inventory width
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
        scene.getStylesheets().add("style.css");
        ui.setId("rightbar");
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

                canvas.getLayoutX();
                System.out.println("bounds = " +canvas.getWidth());

                map.getPlayer().initMove(Direction.UP.dx, Direction.UP.dy);
                ChangeMapIfTrue();
                refresh();
                break;
            case DOWN:
                map.getPlayer().initMove(Direction.DOWN.dx, Direction.DOWN.dy);
                ChangeMapIfTrue();
                refresh();
                break;
            case LEFT:
                map.getPlayer().initMove(Direction.LEFT.dx, Direction.LEFT.dy);
                ChangeMapIfTrue();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().initMove(Direction.RIGHT.dx, Direction.RIGHT.dy);
                ChangeMapIfTrue();
                refresh();
                break;
            case I:
                InventoryBox ibox = new InventoryBox();
                ibox.display(map.getPlayer().getInventory());
                refresh();
                break;
        }
    }

    private void ChangeMapIfTrue() {
        if (isNewMap()) {
            initNewMap();
        }
    }

    private boolean isNewMap(){
        return isNextMap || isPreviousMap;
    }

    public void initNewMap(){
        generateMapFileName();
        generateMap();
    }

    public void generateMapFileName(){
        if (isNextMap){
            if(currentMap < nameOfFiles.size()-1){
                currentMap++;
                mapFilename = nameOfFiles.get(currentMap);
                isNextMap = false;
            }
        }
        if (isPreviousMap){
            if(currentMap > 0){
                currentMap--;
                mapFilename = nameOfFiles.get(currentMap);
                isPreviousMap = false;
            }
        }
    }

    private List<String> setMapNames() {
        List<String> nameOfFiles = new ArrayList();
        nameOfFiles.add("/map1.txt");
        nameOfFiles.add("/map2.txt");
        nameOfFiles.add("/map3.txt");
        nameOfFiles.add("/map4.txt");
        return nameOfFiles;
    }

    public void generateMap() {
        map = MapLoader.loadMap(mapFilename);
        canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        refresh();
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
