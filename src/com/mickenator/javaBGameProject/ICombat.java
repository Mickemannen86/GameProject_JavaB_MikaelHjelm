package com.mickenator.javaBGameProject;

public interface ICombat {

    int attack();
    int randomGenerator();
    boolean isDead();
    void adjustDeathHealthToZero();
    void getStatus();

}
