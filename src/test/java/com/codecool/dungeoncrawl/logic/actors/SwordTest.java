package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {
    private Sword sword;
    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = Mockito.mock(Cell.class);
        sword = new Sword(cell);
    }

    @Test
    void getTileName() {
        var result = sword.getTileName();
        Assertions.assertEquals("sword", result);
    }
}