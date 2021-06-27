package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {

        Cell nextCell = null;
        try {
            nextCell = cell.getNeighbor(dx, dy);
        } catch (Exception ex) {
            return;
        }

        //TODO - drop comments
//        System.out.println("NextCell: " + nextCell);
//        System.out.println(nextCell.getTileName());
//        System.out.println(nextCell.getActor());
//        System.out.println(nextCell.getActor() instanceof Sword);
//        System.out.println(nextCell.getActor() instanceof Key);

        if(nextCell.getTileName() == CellType.WALL.getTileName())
            return;

        if((nextCell.getTileName() == CellType.FLOOR.getTileName())
                &&((nextCell.getActor() == null)
                || (nextCell.getActor() instanceof Sword)
                || (nextCell.getActor() instanceof Key)))
        {
            //TODO - add to inventory
            addToInventory(nextCell);

            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            return;
        }

        //TODO - fight with enemy or collect some items
//        System.out.println("============> fight with enemy or collect some items");
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

    private void addToInventory(Cell nextCell) {
        if(nextCell.getActor() == null)
            return;

        System.out.println("ADD TO INVENTORY");
    }
}
