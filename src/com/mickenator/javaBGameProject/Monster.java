package com.mickenator.javaBGameProject;

import java.util.Random;

import static com.mickenator.javaBGameProject.Colors.*;

// (3). Starta Monster klass
public class Monster implements ICombat {

    Random random = new Random();
    Player player = new Player();

    // (3.1). instans variablar för Monster.
    private String name;        // Monster unika namn
    private int experience;     // experience monster get baserat på monstertyp
    private int health;         // Liv varierar baserat på monster. Classmate(code-worm)100hp, Calminder(Dragon)500hp, Krille(Boss) 1000hp
    private int baseDamage;     // Är till för att kalkylera hur mycket skada monster tillför vid varje attack
    private int deathCount;

    // (3.2). Konstruktor.
    public Monster() {}
    public Monster(String name, int experience, int health, int baseDamage) {
        setName(name);
        setExperience(experience);
        setHealth(health);
        setBaseDamage(baseDamage);
    }

    // Hanterar health each round in battle()
    void addToTotalMonsterHealth(int damage) { health -= damage;}
    int getTotalMonsterHealth() {adjustDeathHealthToZero();return health;}

    // Väljer monster i switch med hjälp av randomGenerator() som randomise fram ett nummer mellan 1- 3.
    public void generateMonsterList() {

        // Skapar metod med Switch i body för att skapa mer interering för spelare.
        switch (randomGenerator()) {
            case 1:
                codeMaggot();
                break;
            case 2:
                calminder();
                break;
            case 3:
                krillenator();
                break;
            default:
                break;
        }
    }
    // MONSTER DAMAGE!
    int dmgGenerator() {
        int damage;
        int noDamage;
        if (player.playerDodge()) { // If (e alltid original) - Träff!
            damage = baseDamage + random.nextInt(5,16); // org 5,16.
            return damage;
        } else {                    // else - Om dodge
            noDamage = 0;
            System.out.format("\n\t* You managed to %sDODGE%s counter attack!! *\n",PURPLE_BRIGHT,RESET);
            return noDamage;
        }
    }

    // MONSTER METODER - med Detaljer för enskilt monster man möter!
    void codeMaggot() {
        name = "Code-maggot";
        health = 100;   // org 100
        experience = 50; // if health < 1 { +=experice + playerExperience}
        baseDamage = 0; // org 0
        System.out.format("\t%s---------------------------------------%s" +
                        "%n\t# Your up against a %s%s%s #%n" + "\t%s---------------------------------------%s%n" +
                        "%n\t%s%s%s got %s%dhp%s and make %s5-15 dmg%s each turn%n%n",
                YELLOW_BRIGHT,RESET,RED,getName(),RESET,YELLOW_BRIGHT,RESET,RED,getName(),RESET,GREEN,health,RESET,RED,RESET);
    }
    void calminder() {
        name = "Calminder(Dragon)";
        health = 300;   // org 500
        experience = 100;
        baseDamage = 10; // org 10
        System.out.format("\t%s---------------------------------------%s" +
                        "%n\t# Your up against a %s%s%s #%n" + "\t%s---------------------------------------%s%n" +
                        "%n\t%s%s%s got %s%dhp%s and make %s5-15 dmg%s each turn%n%n",
                YELLOW_BRIGHT,RESET,RED,getName(),RESET,YELLOW_BRIGHT,RESET,RED,getName(),RESET,GREEN,health,RESET,RED,RESET);
    }
    void krillenator() {
        name = "krillenator(Boss)";
        health = 500;   // org 1000
        experience = 150;
        baseDamage = 20; // org 20
        System.out.format("\t%s---------------------------------------%s" +
                        "%n\t# Your up against a %s%s%s #%n" + "\t%s---------------------------------------%s%n" +
                        "%n\t%s%s%s got %s%dhp%s and make %s5-15 dmg%s each turn%n%n",
                YELLOW_BRIGHT,RESET,RED,getName(),RESET,YELLOW_BRIGHT,RESET,RED,getName(),RESET,GREEN,health,RESET,RED,RESET);
    }
    // Count monster deaths.
    void checkDefeatCount() {
        if (isDead()) {
            setDeathCount(getDeathCount() +1);
        }
    }

    // ********** ABSTRACT METHODS FROM ICombat! **********

    // Attack method used in fight() for Monster
    @Override
    public int attack() {
        if (isDead()) {
            return 0;
        } else {
            return dmgGenerator();
        }
    }
    // randomGenerator() randomise Monster to fight()
    @Override
    public int randomGenerator() {
        int roll;
        roll = random.nextInt(1,4);
        return roll;
    }

    // Check if Monster isAlive
    @Override
    public boolean isDead()  {return health < 1;}

    // Adjusts health so Monster health never go below zero HP
    @Override
    public void adjustDeathHealthToZero() {
        if (isDead()) {
            setHealth(0);
        }
    }

    // Show Monster current status in combatMenu - Status check (HP).
    @Override
    public void getStatus() {System.out.format("\n\t%s%s%s current health HP: %s%d%s.%n%n",
            RED,getName(),RESET, GREEN,getHealth(), RESET);}

    // (3.3). Setters & Getters
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getExperience() {return experience;}

    public void setExperience(int experience) {this.experience = experience;}

    public int getHealth() {return health;}

    public void setHealth(int health) {this.health = health;}

    public int getBaseDamage() {return baseDamage;}

    public void setBaseDamage(int baseDamage) {this.baseDamage = baseDamage;}

    public int getDeathCount() {return deathCount;}

    public void setDeathCount(int deathCount) {this.deathCount = deathCount;}
}
