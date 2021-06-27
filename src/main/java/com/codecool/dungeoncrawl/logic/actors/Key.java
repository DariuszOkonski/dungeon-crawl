package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Actor implements ICollectable {
    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}

