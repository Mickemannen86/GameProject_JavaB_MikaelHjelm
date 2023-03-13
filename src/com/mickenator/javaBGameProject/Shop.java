package com.mickenator.javaBGameProject;

public class Shop extends Potion {

    int healthPotionAmount = 3;                 // How many pots


    public void purchasePotion() {

        if (player.getCoins() >= 10) {
            player.setCoins(player.getCoins() - 10);
            setHealthPotionAmount(getHealthPotionAmount() + 3);
            System.out.println("George the shopkeeper: Here is your merch (You see " + healthPotionAmount + "x Health potions)");
            System.out.println("George the shopkeeper: Wise deal adventurer, your new potions might save your life in a critical situation\n");
        } else
            System.out.println("\t * You dont have enough currency. You might loot more for defeating enemies! *\n");

    }
    // Här ska de avslutas
}
