package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PitbullTest {
    private Pitbull pitbull;
    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = Mockito.mock(Cell.class);
        pitbull = new Pitbull(cell);
    }

    @Test
    void getTileName() {
        var result = pitbull.getTileName();
        Assertions.assertEquals("pitbull", result);
    }

    @Test
    void isCharacterKilled() {
        var result = pitbull.isCharacterKilled();
        Assertions.assertEquals(false, result);

        pitbull.health = 0;
        result = pitbull.isCharacterKilled();
        Assertions.assertEquals(true, result);
    }

    @Test
    void subtractHealth() {
        var result = pitbull.getHealth();
        Assertions.assertEquals(Utilities.PITBULL_HEALTH, result);

        pitbull.subtractHealth(Utilities.HERO_STRIKE_POWER);
        result = pitbull.getHealth();
        var expected = Utilities.PITBULL_HEALTH - Utilities.HERO_STRIKE_POWER;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getMonsterStrikePower() {
        var result = pitbull.getMonsterStrikePower();
        var expected = Utilities.PITBULL_STRIKE_POWER;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getIsFighting() {
        var result = pitbull.getIsFighting();
        Assertions.assertEquals(false, result);
    }

    @Test
    void setFighting() {
        var isFighting = pitbull.getIsFighting();
        Assertions.assertEquals(false, isFighting);

        pitbull.setFighting();
        isFighting = pitbull.getIsFighting();
        Assertions.assertEquals(true, isFighting);
    }

    @Test
    void setNotFighting() {
        pitbull.setFighting();
        var isFighting = pitbull.getIsFighting();
        Assertions.assertEquals(true, isFighting);

        pitbull.setNotFighting();
        isFighting = pitbull.getIsFighting();
        Assertions.assertEquals(false, isFighting);
    }

    @Disabled
    void move() {
        Assertions.fail();
    }

    @Test
    void testToString() {
        var result = pitbull.toString();
        var expected = "Pitbull{health="+Utilities.PITBULL_HEALTH+", strikePower="+Utilities.PITBULL_STRIKE_POWER+", isFighting=false}";
        Assertions.assertEquals(expected, result);
    }
}