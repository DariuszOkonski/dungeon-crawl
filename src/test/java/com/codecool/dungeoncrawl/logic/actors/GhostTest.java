package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GhostTest {
    private Ghost ghost; // SUT
    private Cell cell; // mock

    @BeforeEach
    void setUp() {
        cell = Mockito.mock(Cell.class);
        ghost = new Ghost(cell);
    }

    @Test
    void getTileName() {
        var result = ghost.getTileName();
        Assertions.assertEquals("ghost", result);
    }

    @Test
    void isCharacterKilled() {
        ghost.health = 0;
        var result = ghost.isCharacterKilled();
        Assertions.assertEquals(true, result);
    }

    @Test
    void subtractHealth() {
        var health = ghost.getHealth();
        Assertions.assertEquals(Utilities.GHOST_HEALTH, ghost.getHealth());

        ghost.subtractHealth(Utilities.HERO_STRIKE_POWER);

        var expected = health - Utilities.HERO_STRIKE_POWER;
        var result = ghost.getHealth();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getMonsterStrikePower() {
        var result = ghost.getMonsterStrikePower();
        Assertions.assertEquals(Utilities.GHOST_STRIKE_POWER, result);
    }

    @Test
    void getIsFighting() {
        var result = ghost.getIsFighting();
        Assertions.assertEquals(false, result);
    }

    @Test
    void setFighting() {
        var isFighting = ghost.getIsFighting();
        Assertions.assertEquals(false, isFighting);

        ghost.setFighting();
        isFighting = ghost.getIsFighting();
        Assertions.assertEquals(true, isFighting);
    }

    @Test
    void setNotFighting() {
        ghost.setFighting();
        var isFighting = ghost.getIsFighting();
        Assertions.assertEquals(true, isFighting);

        ghost.setNotFighting();
        isFighting = ghost.getIsFighting();
        Assertions.assertEquals(false, isFighting);
    }

    @Disabled
    void move() {
        Assertions.fail();
    }

    @Test
    void testToString() {
        var result = ghost.toString();
        var expected = "Ghost{health="+Utilities.GHOST_HEALTH+", strikePower="+Utilities.GHOST_STRIKE_POWER+", isFighting=false}";
        Assertions.assertEquals(expected, result);
    }
}