package view.app;

import io.resources.AppFileReader;
import view.LevelsMenu;
import view.gamestates.LevelStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Game {

    public static JFrame frame;
    public static JComboBox levelSelector;

    public static void main(String[] args){
        frame = new JFrame("platformer");
        frame.setSize(900, 450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AppFileReader.setLevelSelector();
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(new GamePanel(), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        levelSelector.addActionListener(event -> {
            LevelStates.loadPathM = "res/Maps/map" + (levelSelector.getSelectedIndex() + 1) + ".map";
            LevelStates.nameMap = "Level " + (levelSelector.getSelectedIndex() + 1);
        });

        levelSelector.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    LevelsMenu.openMenu();
                    levelSelector.setEnabled(false);
                    levelSelector.setVisible(false);

                }
            }

            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {}
        });
    }

    public static void frameClose(){
        frame.dispose();
    }
    public static void frameShow(){
        frame.setVisible(true);
    }
}
