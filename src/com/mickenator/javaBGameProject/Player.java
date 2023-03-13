package com.mickenator.javaBGameProject;

import java.util.Random;
import java.util.Scanner;

import static com.mickenator.javaBGameProject.Colors.*;

public class Player implements ICombat {

    Scanner myScan = new Scanner(System.in);
    Random rand = new Random();

    // (2.1) Player Variables - Egenskaper som ökar vid lvl.
    private String name;
    private int strength = 1;       // styrka påverkar dmg ------------------------------------------------------------> calculateDamage(int strenght) KLAR!
    private int intelligence = 1;   // intelligence ökar % till dubbel dmg --------------------------------------------> doubleDamage() KLAR!
    private int agility = 1;        // agility ökar chansen att fly battle/dodge --------------------------------------> playerDodge() KLAR!
    private int health = 300;       // HP blir +25 hp högre för varje lvl ---------------------------------------------> playerLevelUp() KLAR!
    private int experience = 0;     // Varje 100 poäng == level +1
    private int level = 1;          // Varje level ökar abilitys (styrka, intelligence & agility) med +5.
    private int baseDamage = 5;    // Är till för att kalkylera hur mycket skada spelaren tillför vid varje attack ---> KLAR! ? org b-dmg = 25
    private int totalExperience;
    private int maxHealth = 300;
    private int coins;

    // (2.2) Player Constructors
    Player() {}
    Player(String name, int strength, int intelligence, int agility, int health, int experience, int level, int baseDamage) {
        setName(name);
        setStrength(strength);
        setIntelligence(intelligence);
        setAgility(agility);
        setHealth(health);
        setExperience(experience);
        setLevel(level);
        setBaseDamage(baseDamage);
    }

    // Hanterar health each round in battle()                                                                   -------> KLART!
    void addToTotalPlayerHealth(int damage) { health -= damage;}

    int getTotalPlayerHealth() { adjustDeathHealthToZero();return health;}

    // Metod att skapa spelaren!                                                                                -------> KLART!
    public String createPlayer() {
        String playerName = myScan.next();
        return playerName;
    }

    // Caclculate Exp to manage exp to store. And check amountLevels!                                           -------> KLART!
    public int calculateLevel(int xp) { // Exempel: monsterExp 150.

        setTotalExperience(getTotalExperience() + xp);  // tar in pExp + mExp för att kunna presentera total exp under spelets gång. 0 + 150xp = 150 totalGamexp
        int totalXp = xp + getExperience();             // lagrar mExp + nuvarande pExp i totalexp. 150mExp + 0pExp = 150 Exp
        if (totalXp < 100) {                            // OM totalxp(mExp + pExp = 150 Exp) e mindre än 100.  150+0 < 100 = false! If körs inte. till -> Rad 60.
            setExperience(totalXp);                     // uppdatera pExp med totalxp(mExp + pExp).
            return 0;                                   // return 0. OM if sats Rad 55 = true!
        }

        int remainder = totalXp % 100;                  // totalxp(mExp + pExp = 150 Exp) ~ 150 % 100 = remainder (överbliven exp) alltså 50 Exp

        int amountLevels = (totalXp - remainder)/100;   // Hur många lvlar ja får (150 - 50) / 100 = 1
        //                     alltså -> 100 / 100 = 1
        setExperience(remainder);                       // sätter in remainder (överbliven exp) i pExp. Mao har jag 50 pExp nu.

        return amountLevels; // return lvl 1 tex        // amountLevels = 1
    }

    // Vid levelUP - samtliga stats adderas på.                                                                 -------> KLART  -> FÖRSTÅ!
    public void playerLevelUp(int amountLevels) {       // amountLevels = 1

        if (amountLevels > 0) {                         // OM amountLevels(1) > 0 == LevelUp!
            setLevel(getLevel() + amountLevels );       // uppdatera plvl + antal levels(1)

            //setMaxHealth(getMaxHealth() + 25 * amountLevels); // Healar till fullt vid lvl + 25 hp per lvl.
            //setHealth(getMaxHealth());

            setHealth(getHealth() + 25 * amountLevels); // uppdatera pHealth + 25 health * AmountLevels(1)
            setBaseDamage(getBaseDamage() + 2 * amountLevels);
            setStrength(getStrength() + 2 * amountLevels);
            setIntelligence(getIntelligence() + 2 * amountLevels);
            setAgility(getAgility() + 2 * amountLevels);

            System.out.format("\n\t\t%s***%s %sLevel Up!%s %s- Congratz! ***%s\n\tYou've reached %slevel: %d%s with %s%d%s exp points to next level" +
                            "\n\t%sHealth: %d%s\n\t%sBasedamage: %d%s\n\t%sStrength: %d%s\n\t%sIntelligence: %d%s\n\t%sAgility: %d%s\n",
                    YELLOW_BRIGHT,RESET,CYAN_BRIGHT,RESET,YELLOW_BRIGHT,RESET,CYAN_BRIGHT,level,RESET,WHITE_BRIGHT,experience,RESET,GREEN_BRIGHT,health,RESET,RED_BRIGHT,baseDamage,RESET,BLUE_BRIGHT,strength,RESET,YELLOW_BRIGHT,intelligence,RESET,PURPLE_BRIGHT,agility,RESET);
        }
    }

    // Try to flee from battle - controlled by procent from playerFleeChance().                                  ------> KLART!
    public boolean playerFlee() {

        if (playerFleeChance()) {
            System.out.format("\n\tYou manage to %sflee%s the fight successfully!%n", PURPLE_BRIGHT,RESET);
            return true;
        } else {
            System.out.println("\n\tMonster catch up to you, better fight back!\n");
            return false;
        }
    }

    // Calculate damage - return Damage/doubleDamage to Monster                                                 -------> KLART!
    public int calculateDamage() {
        int damage = strength + baseDamage;
        if (doubleDamage()) { // If: True == dubble dmg
            return damage * 2;
        } else {              // Else: False == normal dmg
            return damage;}
    }

    // Did Player double damage with attack()?
    public boolean doubleDamage() { //                                                                            -----> KLART!
        int doubleDamageProcent = 5; // percent of double damage chance
        int extraDoubleDamageProcent = intelligence + doubleDamageProcent; //percent of double damage chance + intelligence
        boolean doubleDamage = rand.nextInt(100) > extraDoubleDamageProcent; // if randomInt(0-100) is less than intelli+5percent == True

        return doubleDamage;
    }

    // Did Player Dodge a monster Attack()?
    public boolean playerDodge(){ //                                                                              -----> KLART!
        int dodgeChancePercent = 35; // percent of dodge change
        int extraDodgeChanceProcent = agility + dodgeChancePercent;
        boolean dodgeChance = rand.nextInt(100) > extraDodgeChanceProcent;

        return dodgeChance;
    }

    // Did Player flee a monster fight()?
    public boolean playerFleeChance(){ //                                                                         -----> KLART!
        int fleeChancePercent = 35; // percent of flee change
        int extraFleeChanceProcent = agility + fleeChancePercent;
        boolean fleeChance = rand.nextInt(100) > extraFleeChanceProcent;

        return fleeChance;
    }

    // Coins for shop
    // coinDropChance from Monster.
    boolean coinDropChance() { // Skulle kunna vara currency eller money för shop - men hinner inte med! :/
        int coinChancePercent = 35; // percent of flee change
        int extraCoinDropChancePercent = getIntelligence() + coinChancePercent;
        boolean coinDropChance = rand.nextInt(100) > extraCoinDropChancePercent;

        return coinDropChance;
    }

    // Did Monster drop a coins?
    void coinDrop() {
        if (coinDropChance()) {
            setCoins(getCoins() + 10);
            System.out.println("\t # Monster dropped coins! (Coins might be handy in the Potion Shop) #");
            System.out.println("\t # You now have " + getCoins() + " coin(s). # ");
        }
    }

    // ********** ABSTRACT METHODS FROM ICombat! **********
    // randomGenerator() randomise events to exploreWorld()
    @Override
    public int randomGenerator() {
        Random random = new Random();
        int randomise;
        randomise = random.nextInt(1,4);
        return randomise;
    }

    // Attack method used in fight() for Monster
    @Override
    public int attack() {return calculateDamage();}

    // Check if Player isAlive
    @Override
    public boolean isDead()  {return health < 1;}

    // Justerar health så Player aldrig har minus HP
    @Override
    public void adjustDeathHealthToZero() {
        if (health < 1) {
            setHealth(0);
        }
    }

    // Show player current status in combatMenu - Status check (HP).                                            -------> KLAR!
    @Override
    public void getStatus() {
        System.out.format("\t# Combat Status #\n\t%s current health HP: %s%d%s.",
                getName(), GREEN_BRIGHT,getHealth(), RESET);
    }

    // (2.3). Setters & Getters
    public int getStrength() {return strength;}

    public void setStrength(int strength) {this.strength = strength;}

    public int getIntelligence() {return intelligence;}

    public void setIntelligence(int intelligence) {this.intelligence = intelligence;}

    public int getAgility() {return agility;}

    public void setAgility(int agility) {this.agility = agility;}

    public int getHealth() {return health;}

    public void setHealth(int health) {this.health = health;}

    public int getExperience() {return experience;}

    public void setExperience(int experience) {this.experience = experience;}

    public int getLevel() {return level;}

    public void setLevel(int level) {this.level = level;}

    public int getBaseDamage() {return baseDamage;}

    public void setBaseDamage(int baseDamage) {this.baseDamage = baseDamage;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getTotalExperience() {return totalExperience;}

    public void setTotalExperience(int totalExperience) {this.totalExperience = totalExperience;}

    public int getMaxHealth() {return maxHealth;}

    public void setMaxHealth(int maxHealth) {this.maxHealth = maxHealth;}

    public int getCoins() {return coins;}

    public void setCoins(int coins) {this.coins = coins;}
}

