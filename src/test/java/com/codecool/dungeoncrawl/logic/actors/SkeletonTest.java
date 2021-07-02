package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Utilities;
import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SkeletonTest {
    private Skeleton skeleton;
    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = Mockito.mock(Cell.class);
        skeleton = new Skeleton(cell);
    }

    @Test
    void getTileName() {
        var result = skeleton.getTileName();
        Assertions.assertEquals("skeleton", result);
    }

    @Test
    void isCharacterKilled() {
        var result = skeleton.isCharacterKilled();
        Assertions.assertEquals(false, result);

        skeleton.health = 0;
        result = skeleton.isCharacterKilled();
        Assertions.assertEquals(true, result);
    }

    @Test
    void subtractHealth() {
        var result = skeleton.getHealth();
        Assertions.assertEquals(Utilities.SKELETON_HEALTH, result);

        skeleton.subtractHealth(Utilities.HERO_STRIKE_POWER);
        result = skeleton.getHealth();
        var expected = Utilities.SKELETON_HEALTH - Utilities.HERO_STRIKE_POWER;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getMonsterStrikePower() {
        var result = skeleton.getMonsterStrikePower();
        var expected = Utilities.SKELETON_STRIKE_POWER;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getIsFighting() {
        var result = skeleton.getIsFighting();
        Assertions.assertEquals(false, result);
    }

    @Test
    void setFighting() {
        var isFighting = skeleton.getIsFighting();
        Assertions.assertEquals(false, isFighting);

        skeleton.setFighting();
        isFighting = skeleton.getIsFighting();
        Assertions.assertEquals(true, isFighting);
    }

    @Test
    void setNotFighting() {
        skeleton.setFighting();
        var isFighting = skeleton.getIsFighting();
        Assertions.assertEquals(true, isFighting);

        skeleton.setNotFighting();
        isFighting = skeleton.getIsFighting();
        Assertions.assertEquals(false, isFighting);
    }

    @Test
    void testToString() {
        var result = skeleton.toString();
        var expected = "Skeleton{" +
                "health="+Utilities.SKELETON_HEALTH+", " +
                "strikePower="+Utilities.SKELETON_STRIKE_POWER+"}";
        Assertions.assertEquals(expected, result);
    }
}