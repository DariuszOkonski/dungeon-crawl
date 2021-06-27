package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor implements IFightable {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}

