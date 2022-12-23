package view.constructor;

import io.resources.AppFileWriter;
import view.ConstructorRedactor;
import view.LevelsMenu;
import view.app.Game;
import view.gamestates.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;


public class ConstructorMap extends JFrame {
    private JPanel ConstructorMap;
    private JButton SaveButton;
    private JTextField LevelName;
    private JComboBox Selector;
    private JComboBox items;
    private JPanel levelField;
    private JButton exitButton;
    public static ConstructorRedactor level;
    public static boolean Saved = false;
    private String[] maps = new File("res/Maps").list();


    public ConstructorMap() {
        this.setTitle("constructor");
        this.setSize(900, 630);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocation(
                (Toolkit.getDefaultToolkit().getScreenSize().width - 900 )/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - 630 )/2
        );

        this.setResizable(false);
        this.setContentPane(ConstructorMap);
        createSelector();
        Selector.addActionListener(event -> {
            int flag = 1;
            if (!Saved)
                flag = JOptionPane.showConfirmDialog(new JFrame(), "Save changes in this map?");
            int selectedIndex = Selector.getSelectedIndex();
            if (flag < 2) {
                if (flag == 0)
                    AppFileWriter.saveConstructorLevel(LevelName.getText());
                if (selectedIndex != 0) {
                    try {
                        String path = maps[selectedIndex - 1].substring(0, maps[selectedIndex - 1].length() - 4);
                        level.paintLevel(path);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    LevelName.setText(maps[selectedIndex - 1].substring(0, maps[selectedIndex - 1].length() - 4));
                    levelField = level;
                    Saved = false;
                }
                else {
                    LevelName.setText("New Map");
                    level.clearMap();
                    levelField = level;
                    Saved = false;
                }
            }
        });

        SaveButton.addActionListener(event -> {
            AppFileWriter.saveConstructorLevel(LevelName.getText());
            Game.levelSelector.addItem(LevelName.getText());
        });

        exitButton.addActionListener(event -> {
            int flag = 1;
            if (!Saved)
                flag = JOptionPane.showConfirmDialog(new JFrame(), "Save changes before exit?");
            if (flag == 0) {
                AppFileWriter.saveConstructorLevel(LevelName.getText());
                if (Saved) {
                    Game.frameShow();
                    this.dispose();
                }
            }
            else if (flag == 1) {
                Game.frameShow();
                this.dispose();
            }
        });

        items.addActionListener(event -> {
            level.setTile(items.getSelectedIndex());
            levelField = level;
        });

        LevelName.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    int flag = 1;
                    if (!Saved)
                        flag = JOptionPane.showConfirmDialog(new JFrame(), "Save changes before exit?");
                    if (flag == 0) {
                        AppFileWriter.saveConstructorLevel(LevelName.getText());
                        if (Saved) {
                            Game.frameShow();
                            dispose();
                        }
                    }
                    else if (flag == 1) {
                        Game.frameShow();
                        dispose();
                    }
                }
            }

            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {}
        });

        this.setVisible(true);
    }

    private void createSelector(){
        Selector.addItem("New Map");
        for (String iter: maps) {
            Selector.addItem(iter.substring(0, iter.length() - 4));
        }
    }

    private void createUIComponents() {
        level = new ConstructorRedactor();
        level.setLocation(55, 80);
        level.setSize(900, 630);
        level.setVisible(true);
        levelField = level;
    }
}
