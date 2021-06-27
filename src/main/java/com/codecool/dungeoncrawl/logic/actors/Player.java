package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private final List<ICollectable> inventoryList = new ArrayList<>();
//    private Button pickUpItemButton;

    public Player(Cell cell) {
        super(cell);
    }

    public List<ICollectable> getInventoryList() {
        return inventoryList;
    }

    public void addToInventory() {
        this.inventoryList.add((ICollectable) tempInventoryItem);
        tempInventoryItem = null;
        this.pickUpItemButton.setDisable(true);
    }

    public void addPickuButton(Button pickUpItem) {
        this.pickUpItemButton = pickUpItem;
    }

    public String getTileName() {
        return "player";
    }
}
