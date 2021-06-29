package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;

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
        System.out.println("PITBULL IS MOVING ============");
    }
}
