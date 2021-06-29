package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label strikePowerLabel = new Label();
    Label enemyHealthLabel = new Label("-");
    Label enemyStrikePowerLabel = new Label("-");
    Label inventoryList = new Label();
    Button pickUpItemButton = new Button();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        setHealthLabels(ui);

        setHeroStrikePowerLabels(ui);

        setPickUpItemButton(ui);

        setEnemyHealthLabels(ui);

        setEnemyStrikePowerLabels(ui);

        setInventoryListLabels(ui);

        map.getPlayer().getElementsFromGameMap(pickUpItemButton, enemyHealthLabel, enemyStrikePowerLabel, map.getMovableList());
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void setPickUpItemButton(GridPane ui) {
        pickUpItemButton.setDisable(true);
        pickUpItemButton.setText("Pick Up Item");

        pickUpItemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                map.getPlayer().addToInventory();
                setInventoryItems();
            }
        });


        ui.add(pickUpItemButton, 0, 3);
    }

    private void setInventoryListLabels(GridPane ui) {
        Label inventoryListText = new Label("Inventory List:");
        inventoryListText.setFont(new Font(Utilities.FONT_SIZE_MEDIUM));
        inventoryListText.setTextFill(Color.GREEN);

        ui.add(inventoryListText, 0, 6);
        ui.add(inventoryList, 0, 7);
    }

    private void setEnemyStrikePowerLabels(GridPane ui) {
        Label enemyStrikePowerText = new Label("Enemy Strike Power: ");
        enemyStrikePowerText.setFont(new Font(Utilities.FONT_SIZE_SMALL));
        enemyStrikePowerText.setTextFill(Color.RED);
        ui.add(enemyStrikePowerText, 0, 5);

        enemyStrikePowerLabel.setFont(new Font(Utilities.FONT_SIZE_SMALL));
        ui.add(enemyStrikePowerLabel, 1, 5);
    }

    private void setEnemyHealthLabels(GridPane ui) {
        Label enemyHealthText = new Label("Enemy Health: ");
        enemyHealthText.setFont(new Font(Utilities.FONT_SIZE_SMALL));
        enemyHealthText.setTextFill(Color.RED);

        ui.add(enemyHealthText, 0, 4);
        enemyHealthLabel.setFont(new Font(Utilities.FONT_SIZE_SMALL));
        ui.add(enemyHealthLabel, 1, 4);
    }

    private void setHeroStrikePowerLabels(GridPane ui) {
        Label strikePowerText = new Label("Hero Strike Power: ");
        strikePowerText.setFont(new Font(Utilities.FONT_SIZE_SMALL));
        strikePowerText.setTextFill(Color.GREEN);
        ui.add(strikePowerText, 0 , 2);

        strikePowerLabel.setText(getPlayerStrikePower());
        strikePowerLabel.setFont(new Font(Utilities.FONT_SIZE_SMALL));
        ui.add(strikePowerLabel, 1, 2);
    }

    private void setHealthLabels(GridPane ui) {
        Label healthText = new Label("Health: ");
        healthText.setFont(new Font(Utilities.FONT_SIZE_LARGE));
        healthText.setTextFill(Color.GREEN);
        ui.add(healthText, 0, 0);

        healthLabel.setFont(new Font(Utilities.FONT_SIZE_LARGE));
        ui.add(healthLabel, 1, 0);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
//        System.out.println(keyEvent.getCode());
        if(map.getPlayer() == null)
            return;

        switch (keyEvent.getCode()) {
            case W:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case S:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case A:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case D:
                map.getPlayer().move(1,0);
                refresh();
                break;
        }

        moveEnemies();

        if(isEndGame()) {
            endGame();
        }
    }

    private void moveEnemies() {
        for (var monster: map.getMovableList()) {
            monster.move(0, 0);
        }
    }

    private void endGame() {
        map.setPlayer(null);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("GAME OVER");
        alert.setContentText("You have lost this game");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.exit(0);
            }
        });
    }

    private boolean isEndGame() {
        return map.getPlayer().getHealth() <= 0;
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        setInventoryItems();
        strikePowerLabel.setText(getPlayerStrikePower());
    }

    private String getPlayerStrikePower() {
        return Integer.toString(map.getPlayer().getStrikePower());
    }

    private void setInventoryItems() {
        var tempInventoryList = map.getPlayer().getInventoryList();

        String tempInventoryItems = "";
        for (var item: tempInventoryList) {
            tempInventoryItems += item.getTileName() + "\n";
        }
        inventoryList.setText(tempInventoryItems);
    }
}
