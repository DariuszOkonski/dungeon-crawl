package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Actor {
    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
