package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {
    private Key key; //SUT
    private Cell cell; //mock

    @BeforeEach
    void setUp() {
        cell = Mockito.mock(Cell.class);
        key = new Key(cell);
    }

    @Test
    void getTileName() {
        var result = key.getTileName();
        Assertions.assertEquals("key", result);
    }
}