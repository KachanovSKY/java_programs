package view.gamestates;

import model.objects.entities.Player;
import model.objects.mapping.Level;
import io.resources.Images;
import view.DrawGraphics;

import java.awt.*;
import java.io.IOException;

public class LevelStates extends GameState{

    private Player player;
    private Level map;
    public static String loadPathM = "res/Maps/map1.map";
    public static String nameMap = "Level 1";
    public static int xPosPlayer = 0;
    public static int yPosPlayer = 0;
    public LevelStates(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        player = new Player(30, 30);
        try {
            map = new Level(loadPathM);
            Player.setRecordMap(nameMap);

        } catch (IOException e) {
            e.printStackTrace();
        }

        xOffset = -(428 - (64 * xPosPlayer));//Смещение на 1 блок - 64 ед. КООРДИНАТА 0
        yOffset = -(224 - (64 * yPosPlayer));//Смещение на 1 блок - 64 ед. КООРДИНАТА 0
    }

    @Override
    public void tick() throws InterruptedException {
        player.tick(map.getBlocks());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Images.background, 0, 0, null);
        DrawGraphics.playerDraw(g, player);
        DrawGraphics.drawLevel(g, map);
        DrawGraphics.drawScore(g);
        if (player.finalTitles){
            DrawGraphics.drawFinalTitle(g);
            player.off = true;
        }
    }

    @Override
    public void keyReleased(int k) {
        player.keyReleased(k);
    }

    @Override
    public void keyPressed(int k) {
        player.keyPessed(k);
    }
}
