package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor implements Drawable {
    private Cell cell;
    private Actor tempInventoryItem;
    private int health = 10;
    private Button pickUpItemButton;
    private final List<ICollectable> inventoryList = new ArrayList<>();

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
        this.tempInventoryItem = null;
    }

    public void move(int dx, int dy) {

        Cell nextCell = getNextCell(dx, dy);

        // nextCell out of board
        if(nextCell == null)
            return;

        if(isHeroHitTheWall(nextCell))
            return;

        heroStepOffItemWithoutCollecting(nextCell);

        movingAroundTheBoardOnTheFloor(nextCell);

        // collect an item logic
        if(nextCell.getActor() instanceof ICollectable) {
            tempInventoryItem = nextCell.getActor();
            this.pickUpItemButton.setDisable(false);

            System.out.println("Collect item?");

            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }


        if(nextCell.getActor() instanceof IFightable) {
            //TODO - fight with enemy or collect some items
            System.out.println("===========> fight");
        }

    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    private void movingAroundTheBoardOnTheFloor(Cell nextCell) {
        if((nextCell.getTileName() == CellType.FLOOR.getTileName())
                &&(nextCell.getActor() == null))
        {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    private void heroStepOffItemWithoutCollecting(Cell nextCell) {
        if((nextCell.getTileName() == CellType.FLOOR.getTileName())
                &&(nextCell.getActor() == null) && (tempInventoryItem != null)) {


            if(tempInventoryItem instanceof Sword)
                cell.setActor(new Sword(cell));

            if(tempInventoryItem instanceof Key)
                cell.setActor(new Key(cell));

            nextCell.setActor(this);
            cell = nextCell;
            tempInventoryItem = null;
            this.pickUpItemButton.setDisable(true);
        }
    }

    private boolean isHeroHitTheWall(Cell nextCell) {
        return nextCell.getTileName() == CellType.WALL.getTileName();
    }

    public void addToInventory() {
        System.out.println("ADD TO INVENTORY");
        this.inventoryList.add((ICollectable) tempInventoryItem);
        tempInventoryItem = null;
        this.pickUpItemButton.setDisable(true);
    }

    private Cell getNextCell(int dx, int dy) {
        try {
            Cell nextCell = cell.getNeighbor(dx, dy);
            return nextCell;
        } catch (Exception ex) {
            return null;
        }
    }

    public void addPickuButton(Button pickUpItem) {
        this.pickUpItemButton = pickUpItem;
    }
}
