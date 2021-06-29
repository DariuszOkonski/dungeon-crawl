package com.codecool.dungeoncrawl.logic.actors;

public interface IFightable {
    boolean isCharacterKilled();
    void subtractHealth(int strikePower);
    int getHealth();
    int getMonsterStrikePower();

    boolean getIsFighting();
    void setFighting();
    void setNotFighting();
}
