package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Pitbull extends Actor implements IFightable, IMovable {
    private boolean isFighting = false;

    public Pitbull(Cell cell) {
        super(cell);
        health = Utilities.PITBULL_HEALTH;
        strikePower = Utilities.PITBULL_STRIKE_POWER;
    }

    @Override
    public String getTileName() {
        return "pitbull";
    }

    @Override
    public boolean isCharacterKilled() {
        return health <= 0;
    }

    @Override
    public void subtractHealth(int strikePower) {
        health -= strikePower;
    }

    @Override
    public int getMonsterStrikePower() {
        return strikePower;
    }

    @Override
    public boolean getIsFighting() {
        return isFighting;
    }

    @Override
    public void setFighting() {
        isFighting = true;
    }

    @Override
    public void setNotFighting() {
        isFighting = false;
    }

    @Override
    public void move(int dx, int dy) {

        movePitbull();
    }

    private void movePitbull() {
        int dy;
        int dx;
        if(!isFighting) {
            dy = 0;
            dx = ThreadLocalRandom.current().nextInt(-1, 2);

            Cell nextCell = getNextCell(dx, dy);
            while (nextCell.getActor() != null) {
                dy = ThreadLocalRandom.current().nextInt(-1, 2);
                dx = ThreadLocalRandom.current().nextInt(-1, 2);
                nextCell = getNextCell(dx, dy);
            }

            // nextCell out of board
            if(nextCell == null || nextCell instanceof IFightable)
                return;

            if(didCharacterHitTheWall(nextCell))
                return;

            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    public String toString() {
        return "Pitbull{" +
                "health=" + health +
                ", strikePower=" + strikePower +
                ", isFighting=" + isFighting +
                '}';
    }
}
