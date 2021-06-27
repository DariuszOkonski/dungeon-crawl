package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private Actor inventoryItem;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
        this.inventoryItem = null;
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
            System.out.println("Collect item?");

            inventoryItem = nextCell.getActor();
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
                &&(nextCell.getActor() == null) && (inventoryItem != null)) {


            if(inventoryItem instanceof Sword)
                cell.setActor(new Sword(cell));

            if(inventoryItem instanceof Key)
                cell.setActor(new Key(cell));

            nextCell.setActor(this);
            cell = nextCell;
            inventoryItem = null;
        }
    }

    private boolean isHeroHitTheWall(Cell nextCell) {
        return nextCell.getTileName() == CellType.WALL.getTileName();
    }

    private void addToInventory(Cell nextCell) {
        if(nextCell.getActor() == null)
            return;

        System.out.println("ADD TO INVENTORY");
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
