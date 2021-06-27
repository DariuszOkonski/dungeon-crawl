package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Actor implements ICollectable {
    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
