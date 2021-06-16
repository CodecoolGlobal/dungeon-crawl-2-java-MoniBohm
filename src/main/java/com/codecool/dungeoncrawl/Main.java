package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.UI.ConfirmBox;
import com.codecool.dungeoncrawl.UI.GameOverBox;
import com.codecool.dungeoncrawl.UI.InventoryBox;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;
import com.codecool.dungeoncrawl.util.Direction;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    public static final int PIXEL_OFFSET = 32;
    public static boolean isNextMap;
    public static boolean isPreviousMap;
    BorderPane borderPane;
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
    Label armorLabel = new Label();    // label for health
    Label damageLabel = new Label();    // label for health
    Label inventoryLabel = new Label(); // label for inventory

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        map.collectEnemies();

        window = primaryStage;
        window.setMaxWidth(1200);
        window.setMaxHeight(800);
        window.setMinWidth(1200);
        window.setMinHeight(800);

        GridPane ui = new GridPane();
        ui.setPrefWidth(1200);   // inventory width
        ui.setPrefHeight(100);   // inventory width
        ui.setPadding(new Insets(20));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label(" Armor: "), 2, 0);
        ui.add(armorLabel, 3, 0);
        ui.add(new Label(" Damage: "), 4, 0);
        ui.add(damageLabel,5 , 0);
        ui.add(new Label(" Inventory: press I "), 40, 0);
//        ui.add(new Label("Inventory: "), 0, 20);
//        ui.add(inventoryLabel, 1, 20);

        window.setOnCloseRequest(e -> {
            e.consume();
            closeApp();
        });

        borderPane = new BorderPane();  // borderPane layout
        BorderPane innerBorderPane = new BorderPane();  // borderPane layout

        innerBorderPane.setCenter(borderPane);

        innerBorderPane.setTop(ui);    // puts ui to a right pane layout
        borderPane.setCenter(canvas);
        ui.setId("topBar");

        Image overlayImg = new Image("file:./src/main/resources/overlay.png");
        ImageView overlay = new ImageView();
        overlay.setImage(overlayImg);
        HBox box = new HBox();
        box.getChildren().add(overlay);
        innerBorderPane.getChildren().add(box);

        Scene scene = new Scene(innerBorderPane); // creating the scene filling it with layout
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene); // put's the scene in main window
        refresh();  // printing
        scene.setOnKeyPressed(this::onKeyPressed); // Player movement - event listener

        primaryStage.setTitle("Free-range Chicken");
        primaryStage.show();
    }

    private void closeApp() {   // modal window for closing
        Boolean answer = ConfirmBox.display("Exit", "Do you want to exit?");
        if (answer) {
            window.close();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) { // key event
        boolean succesfullmove = false;
        switch (keyEvent.getCode()) {
            case UP:

                succesfullmove = map.getPlayer().initMove(Direction.UP.dx, Direction.UP.dy);
                if (succesfullmove) {
                    borderPane.setTranslateY(borderPane.getTranslateY()+PIXEL_OFFSET);
                }
                map.moveEnemies();
                ChangeMapIfTrue();
                refresh();
                break;
            case DOWN:

                succesfullmove = map.getPlayer().initMove(Direction.DOWN.dx, Direction.DOWN.dy);
                if (succesfullmove) {
                    borderPane.setTranslateY(borderPane.getTranslateY()-PIXEL_OFFSET);
                }
                map.moveEnemies();
                ChangeMapIfTrue();
                refresh();
                break;
            case LEFT:

                succesfullmove = map.getPlayer().initMove(Direction.LEFT.dx, Direction.LEFT.dy);
                if (succesfullmove) {
                    borderPane.setTranslateX(borderPane.getTranslateX()+ PIXEL_OFFSET);
                }
                map.moveEnemies();
                ChangeMapIfTrue();
                refresh();
                break;
            case RIGHT:

                succesfullmove =  map.getPlayer().initMove(Direction.RIGHT.dx, Direction.RIGHT.dy);
                if (succesfullmove) {
                    borderPane.setTranslateX(borderPane.getTranslateX()-PIXEL_OFFSET);
                }
                map.moveEnemies();
                ChangeMapIfTrue();
                refresh();
                break;
            case I:
                InventoryBox ibox = new InventoryBox();
                ibox.display(map.getPlayer().getInventory(), map.getPlayer().getCell());
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
        Key.count = 0;
        borderPane.setTranslateX(0);
        borderPane.setTranslateY(0);
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

        healthLabel.setText("" + map.getPlayer().getHealth() + " Hp "); // represents health
        damageLabel.setText("" + map.getPlayer().getDamage() + " Dp "); // represents health
        armorLabel.setText("" + map.getPlayer().getArmor() + " "); // represents health
//        inventoryLabel.setText("" + map.getPlayer().inventoryToString()); //represents inventory
    }
}
