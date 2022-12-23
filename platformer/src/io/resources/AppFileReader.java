package io.resources;

import view.ConstructorRedactor;
import view.app.Game;
import view.app.GamePanel;
import view.gamestates.LevelStates;
import model.objects.mapping.Level;
import view.Block;
import view.Coin;
import view.RecordsMenu;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class AppFileReader {


    public static final String loadPathR = "res/Records/record.rec";


    public static void readRecord(){
        try {
            int size = 0;
            File file = new File(loadPathR);
            java.io.FileReader fr = new java.io.FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                RecordsMenu.records.add(size,line);
                line = reader.readLine();
                size++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Block[][] loadMap(String path) throws IOException {
        Scanner reader = new Scanner(new FileReader(new File(path)));
        try {
            Level.width = Integer.parseInt(reader.nextLine());
            Level.height = Integer.parseInt(reader.nextLine());

            Block[][] blocks = new Block[Level.height][Level.width];
            for (int y = 0; y < Level.height; y++) {
                for(int x = 0; x < Level.width; x++){
                    if (reader.hasNextInt()) {
                        int tmp = reader.nextInt();
                        if (tmp == 0) {
                            blocks[y][x] = new Block(x * Block.blockSize, y * Block.blockSize, 0);
                        }else if (tmp == 1) {
                            blocks[y][x] = new Block(x * Block.blockSize, y * Block.blockSize, tmp);
                        }else if (tmp == 2){
                            Coin.countCoin++;
                            blocks[y][x] = new Block(x * Block.blockSize, y * Block.blockSize, tmp);
                        }else if (tmp == 3){
                            LevelStates.xPosPlayer = x;
                            LevelStates.yPosPlayer = y;
                            blocks[y][x] = new Block(x * Block.blockSize, y * Block.blockSize, 0);
                        }
                    }
                }
            }
            return blocks;

        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void loadLevel(String name) throws FileNotFoundException {
        Scanner reader = new Scanner(new FileReader(new File("res/Maps/" + name + ".map")));
        try {
            int width = Integer.parseInt(reader.nextLine());
            int height = Integer.parseInt(reader.nextLine());

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (reader.hasNextInt()) {
                        ConstructorRedactor.gameMap[i][j] = reader.nextInt();
                    }
                }
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    public static void setLevelSelector(){
        String[] maps = new File("res/Maps").list();
        Game.levelSelector = new JComboBox();
        for (String iter: maps) {
            Game.levelSelector.addItem(iter.substring(0, iter.length() - 4));
        }
        Game.levelSelector.setBounds((GamePanel.WIDTH - 180) / 2 , (GamePanel.HEIGHT - 60) / 2 , 180, 30);
        Game.levelSelector.setVisible(false);
        Game.levelSelector.setEnabled(false);
        Game.frame.add(Game.levelSelector);
        Game.frame.repaint();
    }
}
