package io.resources;

import model.objects.entities.Player;
import view.constructor.ConstructorMap;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppFileWriter {

    //Запись Рекорда
    static Date d = new Date();
    public static SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy kk:mm");// 25.02.2013 09:03);
    public static final String loadPathR = "res/Records/record.rec";


    public static void saveRecord(long timeRecord, String map){
        try(java.io.FileWriter writer = new java.io.FileWriter(loadPathR, true))
        {
            writer.write(format1.format(d));
            writer.append('|');
            writer.write(String.valueOf(timeRecord));
            writer.append(" sec");
            writer.append('|');
            writer.write(Player.recordMap);
            writer.append("\n");
            writer.flush();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void saveConstructorLevel(String name) {
        if (name.equals("")) {
            JOptionPane.showMessageDialog(new JFrame(), "Add map name.");
            return;
        }
        File newFile = new File("res/Maps/" + name + ".map");
        FileWriter writer = null;
        try {
            newFile.createNewFile();
            writer = new FileWriter(newFile, false);
            int[][] gameMap = ConstructorMap.level.getTileMap();
            writer.append(32 + "\n");
            writer.append(32 + "\n");

            for (int i = 0; i < gameMap.length; i++) {
                for (int j = 0; j < gameMap[i].length; j++) {
                    writer.append((gameMap[i][j]) + " ");
                }
                writer.append("\n");
            }
            writer.flush();
            ConstructorMap.Saved = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
