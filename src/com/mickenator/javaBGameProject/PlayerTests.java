package com.mickenator.javaBGameProject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTests {

    Player player = new Player();

    int strength = 5;
    int baseDamage = 2;
    int damage = strength * baseDamage;
    int levelUp = player.calculateLevel(199);


    @Test
    void PlayerTest() {
        player.setHealth(0);
        assertTrue(true,"Yes, we died!" + player.isDead());
        System.out.println("You dead! - test is " + player.isDead());

        assertEquals(10,strength * baseDamage);
        System.out.println("damage makes 10 damage? " + (damage==10));

        assertEquals(1, levelUp);
        System.out.println("Yes we level up, to " + levelUp);

    }

}
