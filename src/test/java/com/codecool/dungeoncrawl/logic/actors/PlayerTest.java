package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = Mockito.mock(Cell.class);
        player = new Player(cell);
    }

    @Test
    void getInventoryList() {
        var result = player.getInventoryList();
        Assertions.assertNotNull(result);
    }

    @Disabled
    void addToInventory() {
    }

    @Disabled
    void getElementsFromGameMap() {
    }

    @Test
    void getTileName() {
        var result = player.getTileName();
        Assertions.assertEquals("player", result);
    }

    @Disabled
    void move() {
    }

    @Test
    void testToString() {
        var result = player.toString();
        var expected = "Player{" +
                "health="+ Utilities.HERO_HEALTH +
                ", strikePower="+Utilities.HERO_STRIKE_POWER+", " +
                "inventoryList="+player.getInventoryList()+", " +
                "tempInventoryItem="+null+"}";
        Assertions.assertEquals(expected, result);
    }
}