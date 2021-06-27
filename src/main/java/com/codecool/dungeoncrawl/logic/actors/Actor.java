package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor implements Drawable {
    private Cell cell;
    protected Actor tempInventoryItem;
    private int health = 10;
    protected Button pickUpItemButton;

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

        isItemToCollect(nextCell);

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

    private void isItemToCollect(Cell nextCell) {
        if(nextCell.getActor() instanceof ICollectable) {
            tempInventoryItem = nextCell.getActor();
            this.pickUpItemButton.setDisable(false);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;

            // addToInventory method is called from Main and is defined in Player class
        }
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

    private Cell getNextCell(int dx, int dy) {
        try {
            Cell nextCell = cell.getNeighbor(dx, dy);
            return nextCell;
        } catch (Exception ex) {
            return null;
        }
    }

}
