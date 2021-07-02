package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ClosedDoorTest {
    private ClosedDoor closedDoor; // SUT
    private Cell cell; // mock

    @BeforeEach
    void setUp() {
        cell = Mockito.mock(Cell.class);
        closedDoor = new ClosedDoor(cell);
    }

    @Test
    void getTileName() {
        var result = closedDoor.getTileName();
        Assertions.assertEquals("closed door", result);
    }
}