package view;

import io.resources.AppFileReader;
import view.app.Game;
import view.app.GamePanel;
import view.constructor.ConstructorMap;
import view.gamestates.GameState;
import view.gamestates.GameStateManager;
import view.gamestates.LevelStates;
import io.resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu extends GameState {

    private final String[] options = {"Start", "Levels", "Records", "Construction", "Exit"};

    private int currentSelection = 0;

    public Menu(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {}

    @Override
    public void tick() {}

    @Override
    public void draw(Graphics g) {
        g.drawImage(Images.background, 0, 0, null);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Graduate", Font.PLAIN, 36));
        g.drawString("SQUARE ADVENTURE", GamePanel.WIDTH / 2 - 190, 80);

        for (int i = 0; i < options.length; i++){
            if (i == currentSelection)
                g.setColor(Color.WHITE);
            else
                g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Graduate", Font.PLAIN, 36));
            g.drawString(options[i], GamePanel.WIDTH / 2 - 100, 150 + i * 60);
        }
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_DOWN){
            currentSelection++;
            if(currentSelection >= options.length)
                currentSelection = 0;
        } else if(k == KeyEvent.VK_UP){
            currentSelection--;
            if(currentSelection < 0)
                currentSelection = options.length - 1;
        }
        if (k == KeyEvent.VK_ENTER){
            if (currentSelection == 0){

                //Start

                Coin.countCoin = 0;
                startGame();
            } else if (currentSelection ==1){

                //levels

                gsm.states.push(new LevelsMenu(gsm));
                Game.levelSelector.setEnabled(true);
                Game.levelSelector.setVisible(true);
            } else if (currentSelection ==2){

                //records

                gsm.states.push(new RecordsMenu(gsm));
            } else if (currentSelection ==3){

                //constructor

                new ConstructorMap();
                Game.frameClose();

            } else if (currentSelection ==4){
                System.exit(0);
            }
        }
        if (k == KeyEvent.VK_ESCAPE)
            System.exit(0);
    }

    public static void startGame(){
        gsm.states.push(new LevelStates(gsm));
    }

    @Override
    public void keyReleased(int k) {
    }
}
