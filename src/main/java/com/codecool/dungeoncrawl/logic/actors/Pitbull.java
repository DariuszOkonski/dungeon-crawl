package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;

public class Pitbull extends Actor implements IFightable {
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
}
