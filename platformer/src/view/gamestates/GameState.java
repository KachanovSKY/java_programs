package view.gamestates;

import java.awt.*;

public abstract class GameState {

    protected static GameStateManager gsm; // состояние программы
    public static double xOffset; // Смещение
    public static double yOffset;

    public GameState(GameStateManager gsm){
        this.gsm = gsm;
        this.xOffset = 0;
        this.yOffset = 0;

        init();
    }

    public abstract void init();
    public abstract void tick() throws InterruptedException;
    public abstract void draw(Graphics g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
}
