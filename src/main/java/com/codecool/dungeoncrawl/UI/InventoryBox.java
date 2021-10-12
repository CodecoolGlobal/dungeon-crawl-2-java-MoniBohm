package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Actor;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.BodyArmor;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.HeadGear;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.LegArmor;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.HealthPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.ManaPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.weapon.MagicStaff;
import com.codecool.dungeoncrawl.logic.MapObject.items.weapon.Uzi;
import com.codecool.dungeoncrawl.util.Direction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class InventoryBox {
    Stage window;
    List<Item> inventory;
    private Cell cell;

    public void display(List<Item> inventory, Cell cell) {
        this.inventory = inventory;
        this.cell = cell;
        GridPane gridPane;
        GridPane ui;
        BorderPane borderPane;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Inventory");
        window.setMinWidth(400);

        Label label = new Label();
        label.setText("Inventory");

        gridPane = initGridPane();
        createManaButton(gridPane);
        createHealtbutton(gridPane);
        createHeadgearButtonIfExists(inventory, gridPane);
        createBodyArmorButtonIfExists(inventory, gridPane);
        createRightLegArmorButtonIfExists(inventory, gridPane);
        createLeftLegArmorButtonIfExists(inventory, gridPane);
        createUziButtonIfExists(inventory, gridPane);
        createStaffButtonIfExists(inventory, gridPane);

        ui = initUi();
        borderPane = initBorderPane(gridPane, ui);
        Button closeButton = initCloseButton();
        initVBox(label, closeButton);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        scene.setOnKeyPressed(this::onKeyPressed);
        window.showAndWait();

    }

    private void initVBox(Label label, Button closeButton) {
        VBox layout = new VBox(450);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
    }

    private Button initCloseButton() {
        Button closeButton = new Button("Close window");
        closeButton.setOnAction(e -> window.close());
        return closeButton;
    }

    private BorderPane initBorderPane(GridPane gridPane, GridPane ui) {
        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);    // puts ui to a right pane layout
        borderPane.setCenter(gridPane);
        return borderPane;
    }

    private GridPane initUi() {
        Label inventoryLabel;
        GridPane ui;
        inventoryLabel = new Label();
        inventoryLabel.setText(getNumberOfElements());
        ui = new GridPane();
        ui.setPrefWidth(300);   // inventory width
        ui.setPadding(new Insets(50));
        ui.add(new Label(""), 0, 0);
        ui.add(inventoryLabel, 1, 0);
        ui.setId("inventoryList");
        return ui;
    }

    private void createLeftLegArmorButtonIfExists(List<Item> inventory, GridPane gridPane) {
        if (inventory.stream().anyMatch(c -> c instanceof LegArmor)) {
            Image img = new Image("/items/rleg.png");
            ImageView view = new ImageView(img);
            Item invItem = null;
            for(Item item:inventory) {
                if (item instanceof LegArmor){
                    invItem = item;
                }
            }
            Button rLegArmor = new Button();
            rLegArmor.setGraphic(view);
            view.setFitHeight(80);
            view.setPreserveRatio(true);
            rLegArmor.setText("Drop\nBoots");
            gridPane.add(rLegArmor, 2, 3);
            Item finalInvItem = invItem;
            rLegArmor.setOnAction(event -> removeItem(finalInvItem));
        }
    }

    private void createRightLegArmorButtonIfExists(List<Item> inventory, GridPane gridPane) {
        if (inventory.stream().anyMatch(c -> c instanceof LegArmor)) {
            Image img = new Image("/items/lleg.png");
            ImageView view = new ImageView(img);

            Item invItem = null;
            for(Item item:inventory) {
                if (item instanceof LegArmor){
                    invItem = item;
                }
            }
            Button lLegArmor = new Button();
            lLegArmor.setGraphic(view);
            view.setFitHeight(80);
            view.setPreserveRatio(true);
            lLegArmor.setText("Drop\nBoots");
            gridPane.add(lLegArmor, 0, 3);
            Item finalInvItem = invItem;
            lLegArmor.setOnAction(event -> removeItem(finalInvItem));
        }
    }

    private void createBodyArmorButtonIfExists(List<Item> inventory, GridPane gridPane) {
        if (inventory.stream().anyMatch(c -> c instanceof BodyArmor)) {
            Image img = new Image("/items/body.png");
            ImageView view = new ImageView(img);
            Item invItem = null;
            for(Item item: inventory) {
                if (item instanceof BodyArmor){
                    invItem = item;
                }
            }
            Button bodyArmor = new Button();
            bodyArmor.setGraphic(view);
            view.setFitHeight(80);
            view.setPreserveRatio(true);
            bodyArmor.setText("Drop\nBody armor");
            gridPane.add(bodyArmor, 1, 1);
            Item finalInvItem = invItem;
            bodyArmor.setOnAction(event -> removeItem(finalInvItem));
        }
    }

    private void createUziButtonIfExists(List<Item> inventory, GridPane gridPane) {
        if (inventory.stream().anyMatch(c -> c instanceof Uzi)) {
            Image img = new Image("/items/uzi.png");
            ImageView view = new ImageView(img);
            Item invItem = null;
            for(Item item: inventory) {
                if (item instanceof Uzi){
                    invItem = item;
                }
            }
            Button uziButton = new Button();
            uziButton.setGraphic(view);
            view.setFitHeight(80);
            view.setPreserveRatio(true);
            uziButton.setText("Drop\nUZI");
            gridPane.add(uziButton, 0, 1);
            Item finalInvItem = invItem;
            uziButton.setOnAction(event -> removeItem(finalInvItem));
        }
    }

    private void createStaffButtonIfExists(List<Item> inventory, GridPane gridPane) {
        if (inventory.stream().anyMatch(c -> c instanceof MagicStaff)) {
            Image img = new Image("/items/magicstaff.png");
            ImageView view = new ImageView(img);
            Item invItem = null;
            for(Item item: inventory) {
                if (item instanceof MagicStaff){
                    invItem = item;
                }
            }
            Button bazookaButton = new Button();
            bazookaButton.setGraphic(view);
            view.setFitHeight(80);
            view.setPreserveRatio(true);
            bazookaButton.setText("Drop\nMagic Staff");
            gridPane.add(bazookaButton, 2, 1);
            Item finalInvItem = invItem;
            bazookaButton.setOnAction(event -> removeItem(finalInvItem));
        }
    }


    private void createHeadgearButtonIfExists(List<Item> inventory, GridPane gridPane) {
        if (inventory.stream().anyMatch(c -> c instanceof HeadGear)) {
            Item invItem = null;
            for(Item item: inventory) {
                if (item instanceof HeadGear){
                    invItem = item;
                }
            }
            initHeadgearButtonEventListener(gridPane, invItem);
        }
    }

    private void initHeadgearButtonEventListener(GridPane gridPane, Item invItem) {
        Image img = new Image("/items/helmet.png");
        ImageView view = new ImageView(img);
        Button helmet = new Button();
        helmet.setGraphic(view);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        helmet.setText("Drop\nHelmet");
        gridPane.add(helmet, 1, 0);
        Item finalInvItem = invItem;
        helmet.setOnAction(event -> removeItem(finalInvItem));
    }

    private void createManaButton(GridPane gridPane) {
            if (inventory.stream().anyMatch(c -> c instanceof ManaPotion)) {
            Image img = new Image("/items/mana.png");
            ImageView view = new ImageView(img);
            Button manaPotion = new Button();
            manaPotion.setGraphic(view);
            view.setFitHeight(80);
            view.setPreserveRatio(true);
            manaPotion.setText("Use\nMana");
            gridPane.add(manaPotion, 0, 0);
            manaPotion.setOnAction(event -> turnRandomEnemyToFire());
        }
    }

    private void createHealtbutton(GridPane gridPane) {
        if (inventory.stream().anyMatch(c -> c instanceof HealthPotion)) {
            Image img = new Image("/items/health.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(80);
            view.setPreserveRatio(true);
            Button healthPotion = new Button();
            healthPotion.setGraphic(view);
            healthPotion.setText("Use\nHealth potion");
            gridPane.add(healthPotion, 2, 0);
            healthPotion.setOnAction(event -> usePotion());
        }
    }

    private GridPane initGridPane() {
        GridPane gridPane;
        //Creating a Grid Pane
        gridPane = new GridPane();
        gridPane.setId("inventoryGrid");
        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(100);
        gridPane.setHgap(10);
        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinSize(800, 800);
        gridPane.setMaxSize(800, 800);
        gridPane.setMinWidth(800);
        gridPane.setMinHeight(800);
        gridPane.setMaxWidth(800);
        gridPane.setMaxHeight(800);
        gridPane.setPadding(new Insets(30, 80, 0, 30));
        return gridPane;
    }

    private void usePotion() {
        if (inventory.stream().anyMatch(c -> c instanceof HealthPotion)) {
            Item invItem = null;
            for(Item item:inventory) {
                if (item instanceof HealthPotion){
                    invItem = item;
                }
            }
            inventory.remove(invItem);
            cell.getActor().incrementHealth();
            window.close();
        }
    }

    private void turnRandomEnemyToFire() {
        if (inventory.stream().anyMatch(c -> c instanceof ManaPotion)) {
            Item invItem = null;
            for(Item item:inventory) {
                if (item instanceof ManaPotion){
                    invItem = item;
                }
            }
            inventory.remove(invItem);
            List<Actor> enemies = cell.getGameMap().getEnemies();
            Random rand = new Random();
            Actor randomEnemy = enemies.get(rand.nextInt(enemies.size()));
            randomEnemy.getCell().setActor(null);
            cell.getGameMap().removeKilledEnemyFromEnemies(randomEnemy);

            AlertBox.display("Mana used"," You used your magic\n  and sent an enemy\nto a random black hole","magic");
            window.close();
        }

    }

    private Set<String> createSetFromInventory() {
        List<String> nameOfElements = new ArrayList<>();
        for (Item inv : inventory) {
            String name = inv.toString();
            nameOfElements.add(name);
        }
        return Set.copyOf(nameOfElements);
    }

    private String getNumberOfElements(){
        Set<String> targetSet = createSetFromInventory();
        StringBuilder inventoryToString = new StringBuilder("");
        for(Object element : targetSet){
            int numberOfElement = (int) inventory.stream()
                    .filter(item1 -> item1.toString() == (element))
                    .count();
            inventoryToString.append(element).append(": ").append(numberOfElement).append("\n");
        }
        return  inventoryToString.toString();
    }


    private void onKeyPressed(KeyEvent keyEvent) { // key event
        switch (keyEvent.getCode()) {
            case I:
                window.close();
                break;
        }
    }
    private void removeItem(Item item){
        int dx;
        int dy;
        inventory.remove(item);
        for (int i = 0; i < 10; i++) {
            dx = Direction.getRandom().dx;
            dy = Direction.getRandom().dy;
            if (cell.isEmptyCell(cell.getNeighbor(dx, dy))){
                cell.getNeighbor(dx,dy).setItem(item);
                break;
            }
        }
        window.close();
    }
}
