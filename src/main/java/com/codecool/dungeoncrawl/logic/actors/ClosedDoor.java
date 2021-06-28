package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class ClosedDoor extends Actor {
    public ClosedDoor(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "closed door";
    }
}
