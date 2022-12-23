package view;

import io.resources.Images;
import model.objects.entities.Player;
import model.objects.mapping.Level;
import view.app.GamePanel;

import java.awt.*;
import java.time.Instant;

public class DrawGraphics {

    public static void drawScore(Graphics g) {
        String text = "Score: " + Player.getScore();
        g.setColor(new Color(30, 201, 139));
        g.setFont(new Font("Graduate", Font.PLAIN, 25));
        Rectangle rect = new Rectangle(GamePanel.WIDTH / 2 - 100, 150, GamePanel.WIDTH, GamePanel.HEIGHT);
        int x = rect.x + GamePanel.WIDTH / 2 - 60;
        int y = rect.y + GamePanel.HEIGHT / 2 ;
        g.drawString(text, x, y);
    }

    public static void drawFinalTitle(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Graduate", Font.BOLD, 36));
        g.drawString("LEVEL COMPLITE!", GamePanel.WIDTH / 2 - 190, GamePanel.HEIGHT / 2);
    }

    public static void playerDraw(Graphics g , Player pl) {

        g.drawImage(Images.player, (int) pl.pGetX(), (int)pl.pGetY(), (int) pl.pGetWidth(), (int) pl.pGetHeight(), null);
        if (!pl.recordedST){
            pl.startTime = Instant.now();
            pl.recordedST = true;
        }
    }

    public static void drawLevel(Graphics g, Level lvl) {
        Block[][] blocks = lvl.getBlocks();
        for (Block[] block : blocks) {
            for (int j = 0; j < block.length; j++) {
                block[j].draw(g);
            }
        }
    }
}
