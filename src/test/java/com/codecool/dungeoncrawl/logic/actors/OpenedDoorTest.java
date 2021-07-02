package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class OpenedDoorTest {
    private OpenedDoor openedDoor;
    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = Mockito.mock(Cell.class);
        openedDoor = new OpenedDoor(cell);
    }

    @Test
    void getTileName() {
        var result = openedDoor.getTileName();
        Assertions.assertEquals("opened door", result);
    }
}