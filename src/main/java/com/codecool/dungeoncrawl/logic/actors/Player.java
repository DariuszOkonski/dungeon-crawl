package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor implements IMovable {
    private final List<ICollectable> inventoryList = new ArrayList<>();
    private Button pickUpItemButton;
    private Actor tempInventoryItem;
    private Actor tempOpenedDoor;
    private Label enemyHealthLabel;
    private Label enemyStrikePowerLabel;
    private boolean isUsingWeapon = false;

    public Player(Cell cell) {
        super(cell);
        health = Utilities.HERO_HEALTH;
        strikePower = Utilities.HERO_STRIKE_POWER;
        this.tempInventoryItem = null;
        this.tempOpenedDoor = null;
    }

    public List<ICollectable> getInventoryList() {
        return inventoryList;
    }

    public void addToInventory() {
        this.inventoryList.add((ICollectable) tempInventoryItem);
        tempInventoryItem = null;
        this.pickUpItemButton.setDisable(true);
    }

    public void addPickUpButton(Button pickUpItem, Label enemyHealthLabel, Label enemyStrikePowerLabel) {
        this.pickUpItemButton = pickUpItem;
        this.enemyHealthLabel = enemyHealthLabel;
        this.enemyStrikePowerLabel = enemyStrikePowerLabel;
    }

    public String getTileName() {
        return "player";
    }

    public void move(int dx, int dy) {

        Cell nextCell = getNextCell(dx, dy);

        // nextCell out of board
        if(nextCell == null)
            return;

        if(didCharacterHitTheWall(nextCell))
            return;

        heroStepOffItemWithoutCollecting(nextCell);

        movingAroundTheBoardOnTheFloor(nextCell);

        canGoThroughDoor(nextCell);

        isItemToCollect(nextCell);

        setEnemyLabels("-", "-");

        fightEnemy(nextCell);


//        System.out.println(nextCell);
//        System.out.println(nextCell.getActor() instanceof OpenedDoor);

    }

    private void fightEnemy(Cell nextCell) {
        if(nextCell.getActor() instanceof IFightable) {
//            System.out.println("===========> fight");

            var monster = (IFightable) nextCell.getActor();
//            System.out.println(monster);
//            System.out.println(this);

            for (var sword: inventoryList) {
                if(sword instanceof Sword && !isUsingWeapon) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Use Weapon");
                    alert.setContentText("You have strong sword, do you want to use it?");
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == ButtonType.OK) {
                            strikePower = Utilities.SWORD_STRIKE_POWER;
                            inventoryList.remove(sword);
                            isUsingWeapon = true;
                        }
                    });
                    break;
                }
            }

            monster.subtractHealth(this.strikePower);
            this.subtractHealthFromHero(monster.getMonsterStrikePower());
            setEnemyLabels(Integer.toString(monster.getHealth()), Integer.toString(monster.getMonsterStrikePower()));

            if(monster.isCharacterKilled()){
//                System.out.println("Enemy is dead");
                setEnemyLabels("0", "-");
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;

                resetUsingWeapon();
            }

            if(this.isHeroKilled()) {
//                System.out.println("You are dead");
                health = 0;
                cell.setActor(null);
            }


//            System.out.println("===========> after hit");
//            System.out.println(monster);
//            System.out.println(this);


        }
    }

    private void resetUsingWeapon() {
        isUsingWeapon = false;
        strikePower = Utilities.HERO_STRIKE_POWER;
    }

    private void setEnemyLabels(String level, String strikePower) {
        this.enemyHealthLabel.setText(level);
        this.enemyStrikePowerLabel.setText(strikePower);
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
        if(tempOpenedDoor != null) {
            cell.setActor(tempOpenedDoor);
            nextCell.setActor(this);
            cell = nextCell;
            tempOpenedDoor = null;
            return;
        }

        if((nextCell.getTileName() == CellType.FLOOR.getTileName())
                &&(nextCell.getActor() == null))
        {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;

            resetUsingWeapon();
            return;
        }
    }

    private void canGoThroughDoor(Cell nextCell) {
        if(nextCell.getActor() instanceof OpenedDoor) {
            allowedToGoThroughDoor(nextCell);
            return;
        }

        if(nextCell.getActor() instanceof ClosedDoor) {
            System.out.println("CLOSED DOOR ===================");

            for (var key: inventoryList) {
                if (key instanceof Key) {
                    inventoryList.remove(key);
                    allowedToGoThroughDoor(nextCell);
                    return;
                }
            }

            noKeyInformation();
            return;
        }
    }

    private void noKeyInformation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("NO KEY");
        alert.setContentText("You have no key in your inventory to get through");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("No Key");
            }
        });
    }

    private void allowedToGoThroughDoor(Cell nextCell) {
        tempOpenedDoor = new OpenedDoor(nextCell);

        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
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

    private Cell getNextCell(int dx, int dy) {
        try {
            Cell nextCell = cell.getNeighbor(dx, dy);
            return nextCell;
        } catch (Exception ex) {
            return null;
        }
    }

    private boolean isHeroKilled() {
        return health <= 0;
    }

    private void subtractHealthFromHero(int strikePower) {
        health -= strikePower;
    }

    @Override
    public String toString() {
        return "Player{" +
                "health=" + health +
                ", strikePower=" + strikePower +
                ", inventoryList=" + inventoryList +
                ", tempInventoryItem=" + tempInventoryItem +
                '}';
    }

}
