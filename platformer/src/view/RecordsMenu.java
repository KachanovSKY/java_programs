package view;

import view.app.GamePanel;
import view.gamestates.GameState;
import view.gamestates.GameStateManager;
import io.resources.AppFileReader;
import io.resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class RecordsMenu extends GameState {

    
    
    public static List<String> records;
    private static boolean permission = true;

    public RecordsMenu(GameStateManager gsm) {
        super(gsm);
        records = new ArrayList<>(5);
        AppFileReader.readRecord();
    }

    @Override
    public void init() {}

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(Images.background, 0, 0, null);
        if (permission) {
            permission = false;
        }
        if (records.size() == 0) {
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Graduate", Font.BOLD, 24));
            g.drawString("NONE RECORDS", GamePanel.WIDTH / 2 - 190, 210);
        } else if (records.size() >= 5){
            int size = records.size();
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Graduate", Font.BOLD, 24));
            g.drawString("          Date:          Time:     Level:", GamePanel.WIDTH / 2 - 230, 70);

            for (int i = 0; i < 5; i++) {
                g.setColor(Color.DARK_GRAY);
                g.setFont(new Font("Graduate", Font.BOLD, 24));
                g.drawString(records.get(size - i - 1).replace("|", "   "), GamePanel.WIDTH / 2 - 230, 110 + i * 60);

            }

        } else {
            int size = records.size();
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Graduate", Font.BOLD, 24));
            g.drawString("          Date:          Time:     Level:", GamePanel.WIDTH / 2 - 230, 70);

            for (int i = 0; i < size; i++) {
                g.setColor(Color.DARK_GRAY);
                g.setFont(new Font("Graduate", Font.BOLD, 24));
                g.drawString(records.get(i).replace("|", "   "), GamePanel.WIDTH / 2 - 230, 110 + i * 60);
            }
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE){
            gsm.states.push(new Menu(gsm));
        }
    }

    @Override
    public void keyReleased(int k) {
    }
    
}

