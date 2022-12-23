package view.constructor;

import view.LevelsMenu;
import view.app.Game;
import view.app.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class LevelBoxConstructor extends JComboBox {

    String[] maps = new File("res/Maps").list();

    public LevelBoxConstructor(){
        super();
        for (String iter: maps) {
            addItem(iter.substring(0, iter.length() - 4));
        }
        setBounds((GamePanel.WIDTH - 180) / 2 , (GamePanel.HEIGHT - 60) / 2 , 180, 30);
        setVisible(true);
        setEnabled(true);
        Game.frame.repaint();
    }

    public void draw(Graphics g){
        Game.frame.repaint();
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) LevelsMenu.openMenu();
    }
}
