package com.mickenator.javaBGameProject;

import java.util.Random;

public class Potion {

    Player player = new Player();
    Random random = new Random();

    int healthPotionAmount = 3;                 // How many pots
    int healPotionHealAmount = 30;              // Amount of heal
    int healthPotionDropChancePercent = 50;     // Percent chance of drop

    // healthPotionDropChance from Monster.
    boolean healthPotionDropChance() { // Skulle kunna vara currency eller money för shop - men hinner inte med! :/

        int extraHealthPotionDropChancePercent = player.getIntelligence() + healthPotionDropChancePercent;
        boolean potionDropChance = random.nextInt(100) > extraHealthPotionDropChancePercent;

        return potionDropChance;
    }

    // Did Monster drop a health potion?
    void potionDrop() {             // Skulle kunna vara currency eller money för shop - men hinner inte med! :/
        if (healthPotionDropChance()) {
            setHealthPotionAmount(getHealthPotionAmount() + 5);    //;healthPotionAmount++; // set get +1
            System.out.println("\t # Monster dropped a health potion! #");
            System.out.println("\t # You now have " + healthPotionAmount + " health potion(s). # ");
        }
    }

    public int getHealthPotionAmount() {return healthPotionAmount;}

    public void setHealthPotionAmount(int healthPotionAmount) {this.healthPotionAmount = healthPotionAmount;}

    public int getHealPotionHealAmount() {return healPotionHealAmount;}

    public void setHealPotionHealAmount(int healPotionHealAmount) {this.healPotionHealAmount = healPotionHealAmount;}

}

