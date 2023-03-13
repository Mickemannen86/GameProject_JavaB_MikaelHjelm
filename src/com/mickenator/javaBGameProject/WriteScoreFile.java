package com.mickenator.javaBGameProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteScoreFile {

    Player player = new Player();
    Monster monster = new Monster();
    Shop shop = new Shop();

    private String path = "C:\\Users\\Micke\\Desktop\\JAVA22-mapp\\myWriteFileLocation\\playerSummary.txt";

    // Create File
    public void createFile() { // Skulle kunna slippa if sats å lägga in 'throws IOException'
        File myFile = new File("playerSummary.txt");
        //File myFileWindowsPath = new File(path + "playerSummary.txt");
        //File myFileMacLinuxPath = new File("testFile.txt");

        // myFileWindowsPath.getAbsolutePath(); Alt

        try {

            if (myFile.createNewFile()) {            //if (myFileWindowsPath.createNewFile()) {
                System.out.println("Created new file " + myFile.getName());
            } else {
                System.out.println("Something went wrong!");
            }
        } catch (IOException e) {
            e.printStackTrace();  // alt throw new RunTimeException(e);
        }
    }

    // Write file
    public void writeToFile() {

        try {

            FileWriter myFileWriter = new FileWriter("playerSummary.txt");  // (path + "playerSummary.txt");

            myFileWriter.write("\n" + player.getName() + " died with honor! You reached level (" + player.getLevel() +
                    ") with a total of " + player.getTotalExperience() + " experience points.");
            myFileWriter.write("\nDefeated " + monster.getDeathCount() + " monsters in battle."); //  monster Count
            myFileWriter.write("\nPotions used: " + shop.getHealthPotionAmount()); // Using total of 30 potions
            myFileWriter.write("\nLegends never dies, You reach further next time!");
            myFileWriter.close();
            // myFileWriter.flush(); crashar för ja lägger flush EFTER close.
            System.out.println("Edited file succesfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
