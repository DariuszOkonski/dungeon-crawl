package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;

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
    public String toString() {
        return "Ghost{" +
                "health=" + health +
                ", strikePower=" + strikePower +
                '}';
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
        System.out.println("GHOST IS MOVING");
    }
}

