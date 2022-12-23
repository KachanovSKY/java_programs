package view;

import view.gamestates.GameState;
import view.gamestates.GameStateManager;
import view.constructor.LevelBoxConstructor;
import io.resources.Images;
import java.awt.*;
import java.awt.event.KeyEvent;


public class LevelsMenu extends GameState {

    public static LevelBoxConstructor levelSelector;

    public LevelsMenu(GameStateManager gsm) {
        super(gsm);
        levelSelector = new LevelBoxConstructor();
    }

    @Override
    public void init() {}

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Images.background, 0, 0, null);
        levelSelector.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            Coin.countCoin = 0;
            openMenu();
        }
    }

    public static void openMenu(){
        gsm.states.push(new Menu(gsm));
    }

    @Override
    public void keyReleased(int k) {
    }
}
