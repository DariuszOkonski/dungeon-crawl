package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor implements IFightable {
    public Skeleton(Cell cell) {
        super(cell);
        health = Utilities.SKELETON_HEALTH;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}

