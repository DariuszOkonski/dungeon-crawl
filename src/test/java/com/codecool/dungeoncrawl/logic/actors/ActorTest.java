package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ActorTest {
    private Player player; // SUT
    private Cell cell; // mock

    @BeforeEach
    void setUp() {
        cell = Mockito.mock(Cell.class);
        player = new Player(cell);
    }

    @Test
    void getStrikePower() {
        var strikePower = player.getStrikePower();
        Assertions.assertEquals(Utilities.HERO_STRIKE_POWER, strikePower);
    }

    @Test
    void getHealth() {
        var health = player.getHealth();
        Assertions.assertEquals(Utilities.HERO_HEALTH, health);
    }

    @Test
    void getCell() {

        var result = player.getCell();

        Assertions.assertNotNull(cell);
        Assertions.assertEquals(result, cell);
    }

    @Test
    void getX() {
        var result = player.getX();

        Assertions.assertEquals(0, result);
    }

    @Test
    void getY() {
        var result = player.getY();
        Assertions.assertEquals(0, result);
    }

    @Test
    void didCharacterHitTheWall_notHit() {
        var result = player.didCharacterHitTheWall(cell);
        Assertions.assertEquals(false, result);
    }

    @Test
    void getNextCell() {
        var tempCell = new Cell(null, 1, 1, CellType.FLOOR);
        Mockito.when(cell.getNeighbor(1,1))
                .thenReturn(null);

        var result = player.getNextCell(0,0);
        Assertions.assertEquals(null, result);
    }
}