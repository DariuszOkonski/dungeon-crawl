package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Ghost extends Actor implements IFightable, IMovable {
    private boolean isFighting = false;

    public Ghost(Cell cell) {
        super(cell);
        health = Utilities.GHOST_HEALTH;
        strikePower = Utilities.GHOST_STRIKE_POWER;
    }

    @Override
    public String getTileName() {
        return "ghost";
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
        int numberOfmoves = ThreadLocalRandom.current().nextInt(0, 4);
        for (int i = 0; i < numberOfmoves; i++) {
            moveGhost();
        }
    }

    private void moveGhost() {
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
        return "Ghost{" +
                "health=" + health +
                ", strikePower=" + strikePower +
                ", isFighting=" + isFighting +
                '}';
    }
}

