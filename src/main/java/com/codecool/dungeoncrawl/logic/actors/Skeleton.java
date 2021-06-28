package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor implements IFightable {
    public Skeleton(Cell cell) {
        super(cell);
        health = Utilities.SKELETON_HEALTH;
        strikePower = Utilities.SKELETON_STRIKE_POWER;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public String toString() {
        return "Skeleton{" +
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
}

