package com.mickenator.javaBGameProject;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.mickenator.javaBGameProject.Colors.*;

public class Game {

    Scanner sc = new Scanner(System.in);
    Player player = new Player();
    Monster monster = new Monster();
    PlayerTests test = new PlayerTests();
    Shop shop = new Shop();
    WriteScoreFile wf = new WriteScoreFile();


    /* TODO - G - Klasser: Player,Monster,ICombat,Game, Enhetstester - CHECK!
     * TODO - VG - Shop(Dont work!), Potion(Works), WriteScoreFile(Works), Färger(Works), Felhantering(Works) - Fördjupande Enhetstester(No!) - Nice try
     * TODO - ICombat - CHECK!
     * TODO - Enhetstester G JUnit 3 @Test: Kolla om spelare kan lvla - (CHECK!), kan förlora - (CHECK!), kolla om skada = som spelaren kan skada - (CHECK!).
     * TODO - Felhantering - Appen ska inte kunna krasha under spelets gång - CHECK!
     */

    private void welcome() {
        System.out.println("\n\t\t- mickenator production presents -\n\t# ~~~{ SAVING THE VALUES OF JAVA! }~~~ #\n\t\t- < Developer: Mikael Hjelm > -\n");
        System.out.println("------------------ THE STORY BEGINS! ------------------\n");
        System.out.println("STI: Welcome to the world of Java young adventurer..ehm..I didn't catch your name?!");
        player.setName(player.createPlayer());
        System.out.println("STI: Yeye, whatever. Listen.. an adventure is awaiting you. Wild ugly foes will try to " +
                "harm you while you explore this forsaken world. \nThe worse of them all is called Krillenator, " +
                "he is corrupting this world and makes too high demands to our citizens.\n" +
                "Grab your sword " + player.getName() + "! Fight your way and defeat this Demon! Free the world of Java " +
                "and defend our values of KISS!\nSTI: Keep it simple stupid!!!");
        System.out.println();
    }

    private void run() {

        while (true) {
            System.out.println("-----------------");
            System.out.println("(1) Explore world");
            System.out.println("(2) Player stats");
            System.out.println("(3) Game instruction");
            System.out.println("(4) Potion Shop");
            System.out.println("(5) Exit game");
            System.out.println("-----------------");
            System.out.println("Choose an option: ");

            try {

                int selectOption = sc.nextInt();

                while (selectOption < 1 || 5 < selectOption) {
                    System.out.println("Option entered invalid, please enter a number between 1 - 5");
                    selectOption = sc.nextInt();
                }

                if (selectOption == 5) {
                    quit();
                    break;
                }

                this.gameMenu(selectOption);

            } catch (InputMismatchException e) {
                System.out.println("Invalid user input, please enter choose between option 1 - 5");
            }

            // ********* DEATH! *********                                                                                 -----> KLAR!
            //IF PLAYER DIE!

            if (player.isDead()) {
                test.PlayerTest();
                System.out.println();
                wf.createFile();
                wf.writeToFile();
                System.out.println("\nYou been killed! Game Over!");
                break;

            }
        }
    }

    // Skapar metod med Switch i body för att skapa mer interering för spelare.
    public void gameMenu(int selectOption) {
        switch (selectOption) {
            case 1:
                this.exploreWorld();            // Type 1 - randomise exploring event, until forced to face monster.
                break;
            case 2:
                this.playerStats();             // Type 2 - To see player stats.
                break;
            case 4:
                this.potionShop();              // Type 4 - To Potion Shop
                break;
            case 3:
                this.displayGameInstruction();  // Type 3 - Take us to Game instructions.
                break;
            default:
                break;                          // Type 4 - default take us to quit() game!
        }
    }

    // VISUAL COMBAT MENU
    private void combatMenu() {

        while (true) {
            // Visual choice in COMBAT MENU!
            System.out.println("(1) Fight!\n(2) Status check\n(3) Use Health Potion\n(4) Flee battle\nChoose an option:\n");
            // Felhantering - COMBAT MENU
            try {

                int selectOption = sc.nextInt();
                // While input lower than 1 or higher than 3, Alert and give new try until correct input is made.
                while (selectOption < 1 || 4 < selectOption) {
                    System.out.println("Option entered invalid, please enter a number between 1 - 4");
                    selectOption = sc.nextInt();
                }

                // Connect Switch with COMBAT MENU
                this.playerCombatAct(selectOption);
                // When MisMatch input, catch and Alert user for valid choices!
            } catch (InputMismatchException e) {
                System.out.println("Invalid user input, please enter number between option 1 - 4");
            }

            // ********* DEATH! *********                                                                         -----> KLAR!
            //IF PLAYER DIE! DRY behövs för att funka 100%!
            if (player.isDead()) {
                break;
            }

            //IF MONSTER DIE!
            if (monster.isDead()) {
                break;
            }
        }
    }

    // SWITCH COMBAT MENU
    public void playerCombatAct(int combatMenu) {
        // Skapar metod med Switch i body för att skapa mer interering för spelare.
        switch (combatMenu) {
            case 1:
                this.fight();
                break;
            case 2:
                player.getStatus();
                monster.getStatus();
                break;
            case 3:
                this.potionHeal();
                break;
            case 4:
                if (player.playerFlee()) {
                    run();
                    break;
                } else {
                    fight();
                }
                break;
            default:
                break;
        }
    }





    private void potionShop() { // Finns i GameMenu

        System.out.println("\nGeorge the shopkeeper: Hello adventurer & welcome to my shop!" +
                "\nI offer you 3x health potion in exchange for 10 currency coins? \n");
        while (true) {
            System.out.println("George the shopkeeper: So we got a deal? \n(1) - Yes  \n(2) - No?");
            try {

                int selectOption = sc.nextInt();
                // While input lower than 1 or higher than 2, Alert and give new try until correct input is made.
                while (selectOption < 1 || 2 < selectOption) {
                    System.out.println("Option entered invalid, please enter a number between 1 - 2");
                    selectOption = sc.nextInt();
                }

                // Connect Switch with Potion Menu
                this.potionShopOptions(selectOption);
                // When MisMatch input, catch and Alert user for valid choices!
            } catch (InputMismatchException e) {
                System.out.println("Invalid user input, please enter number between option 1 - 2");
            }
            break;
        }
    }

    // Skapar metod med Switch i body för att skapa mer interering för spelare.
    public void potionShopOptions(int potionMenu) {
        switch (potionMenu) {
            case 1:
                shop.purchasePotion();                                                                      // Type 1 - Buy Potion
                break;
            case 2:
                System.out.println("George the shopkeeper: No..? Museum, museum, always the same! Bye!\n"); // Type 2 - Dont buy
                break;
            default:
                break;
        }
    }





    private void exploreWorld() {

        int roll = player.randomGenerator();
        if (roll == 1) {
            System.out.println("\n\t# Exploring #\n\tWalking..walkning..\n");
        } else if (roll == 2) {
            System.out.format("\n\t# Exploring #\n\tWalking..walkning.. wait, a foe is closing up ahead! %sPREPARE FOR COMBAT!%s\n",
                    RED,RESET);
            monster.generateMonsterList();
            combatMenu();
        } else {
            System.out.println("\n\t# Exploring #\n\tWalking..A monster watch from the distance and try to flee from you\n");
        }
    }

    // when potionHeal() is used I drink health potion to get health.
    private void potionHeal() {

        if (shop.getHealthPotionAmount() > 0) {
            player.setHealth(shop.getHealPotionHealAmount() + player.getHealth());
            shop.setHealthPotionAmount(shop.getHealthPotionAmount()-1);
            System.out.println("\t>" + player.getName() + " drink a health potion, healing you for " + shop.getHealPotionHealAmount() + " health points!"
                    + "\n\t> You now have " + player.getHealth() + "hp."
                    + "\n\t> You have " + shop.getHealthPotionAmount() + " health potions left.\n");
        } else {
            System.out.println("\t> You have no health potions left! You might loot more for defeating enemies!\n");
        }
    }

    private void playerStats() {

        System.out.format("""

                        \t# YOUR CURRENT STATS: #

                        \t%sLevel: %d%s (with a total of %s%d exp%s points)
                        \t%sHealth: %d%s
                        \t%sBasedamage: %d%s
                        \t%sIntelligence: %d%s
                        \t%sstrength: %d%s
                        \t%sAgility: %d%s
                        \t%n\t%sCoins: %d%s
                        \t%sHealth potions: %d%s%n""",
                CYAN_BRIGHT, player.getLevel(), RESET, WHITE_BRIGHT,player.getTotalExperience(),RESET,GREEN_BRIGHT, player.getHealth(), RESET, RED_BRIGHT, player.getBaseDamage(), RESET, YELLOW, +
                        player.getIntelligence(), RESET, BLUE_BRIGHT, player.getStrength(), RESET, PURPLE_BRIGHT, player.getAgility(), RESET, YELLOW_BRIGHT, player.getCoins(), RESET,RED,shop.getHealthPotionAmount(),RESET);
        System.out.println("\tDefeated: " + monster.getDeathCount() + " monsters in fights.\n");
    }

    private void displayGameInstruction() {

        System.out.println("\n\t# GAME INSTRUCTIONS: #\n" + "\n\tIn the World of Java, you try to save the lands from the evil creatures. Kill foes to lvl up and advance in skills to become stronger!\n");
        System.out.println("\tType(1) - Explore the world. If a vicious foe closing up to fight, You enter the Combat menu! ");
        System.out.println("\tType(2) - To get an overview over your current character level and abilities.");
        System.out.println("\tType(3) - To get here! - (Game instruction).");
        System.out.println("\tType(4) - To quit the game!");
        System.out.println("\n\tIn the world we got monsters with different difficulty. Code-Maggot(easy), Caliminder(immediate) & the ruler of these monsters, Krillenator(Boss).");
        System.out.println("\n\tI suggest to improve in levels & abilities by facing easier monsters before trying to defeat the demon Krillenator!");
        System.out.println("\n\tIf your health bar is empty, its Game Over!\n");

    }

    private void quit() {

        System.out.println("\n####################################");
        System.out.println("\tAre you sure you wanna quit?\n####################################");
        System.out.println("(1) NO! - take me back!");
        System.out.println("(2) YES - Im a quitter, exit the game!");
        System.out.println("Choose an option: ");
        String input = sc.next();

        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("Option entered invalid, please enter a number between 1 - 2");
            input = sc.next();
        }

        if (input.equals("1")) {
            System.out.println("You choose to continue your adventure!");
            run();
        }else if (input.equals("2")) {
            System.out.println("\n\t\t\t- Thanks for playing -\n\t# ~~~{ SAVING THE VALUES OF JAVA! }~~~ #\n\t\t- < Credits: Mikael Hjelm > -\n");
        }
    }

    private void fight() {

        boolean running = true;
        int playerRoundHealth = player.getHealth();                                  // PLAYER HP
        int monsterRoundHealth = monster.getHealth();                                // MONSTER HP
        int playerRoundDamage = player.attack();                                     // PLAYER DMG
        int monsterRoundDamage = monster.attack();                                   // MONSTER DMG

        // Health board!
        System.out.format("\t%s-----------------%s\n\t%s's HP: %s%d%s.%n",
                YELLOW_BRIGHT,RESET,player.getName(),GREEN_BRIGHT,playerRoundHealth,RESET); // player start HP

        System.out.format("\t%s's HP: %s%d%s.\n\t%s-----------------%s\n",
                monster.getName(),GREEN,monsterRoundHealth,RESET,YELLOW_BRIGHT,RESET);      // monster start HP

        // While loop 'running' kör fight round vid (True).
        while (running) {

            // Hanterar aktuellt HP för Player & Monster.
            monster.addToTotalMonsterHealth(playerRoundDamage);
            if (!monster.isDead()) {
                player.addToTotalPlayerHealth(monsterRoundDamage);
            }

            // Player Attack!
            System.out.format("\t%s Attacking!\n\t%s did %s%d damage%s to %s.\n\t" +
                            "%s have %s%d hp%s left.\n",
                    player.getName(), player.getName(), RED, playerRoundDamage, RESET, monster.getName(),
                    monster.getName(), GREEN, monster.getTotalMonsterHealth(), RESET);
            System.out.println();

            // Monster Attack!
            // OM MONSTER E DÖD
            if (monster.isDead()) {  // (1) - if MONSTER DEAD - Skriv ut monster dör, avsluta fight().
                System.out.format("\t%s%s%s died!\n",
                        RED,monster.getName(),RESET);

                // get EXP from monster & add to calculateLevel to check amountLevels to get from gained exp.
                int ExpToGetFromMonster = monster.getExperience();
                int amountLevels = player.calculateLevel(ExpToGetFromMonster);

                // Monster death message!
                System.out.format("\t%s gained %s%d%s exp and got a total of %s%d%s experience points\n",
                        player.getName(),WHITE_BRIGHT,ExpToGetFromMonster,RESET,WHITE_BRIGHT,player.getTotalExperience(),RESET);
                monster.checkDefeatCount();
                System.out.println("\tDefeated: " + monster.getDeathCount() + " monsters in fights.\n");

                // Health Potion / Coin(s) drop?
                shop.potionDrop();player.coinDrop();

                // Call playerLevelUp method() for Lvl notice and see gains in sout if True!
                player.playerLevelUp(amountLevels);

                running = false;      // (1) - if MONSTER DEAD - Exit fight() round, & since monster is dead also CombatMenu.

                // OM MONSTER LEVER
            } else {                  // (1) - else (MONSTER LIVE) - Print counter attack!

                System.out.format("\t%s Attacking!\n\t%s did %s%d damage%s to %s.\n\t" +
                                "%s have %s%d hp%s left.\n\t%s-----------------%s\n",
                        monster.getName(), monster.getName(), RED, monsterRoundDamage, RESET, player.getName(),
                        player.getName(), GREEN_BRIGHT, player.getTotalPlayerHealth(), RESET,YELLOW_BRIGHT,RESET);
                System.out.println();


                //While loop 'running' - Exit fight() round vid (false).
                running = false;     //  Exit loop fight() round.

            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.welcome();
        game.run();

    }

}
