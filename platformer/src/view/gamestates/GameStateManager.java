package view.gamestates;

import view.Menu;

import java.awt.*;
import java.util.Stack;


public class GameStateManager {

    public Stack<GameState> states;
    public GameStateManager(){
        states = new Stack<GameState>();
        states.push(new Menu(this));
    }

    public void tick() throws InterruptedException {
        states.peek().tick();
    }

    public void draw(Graphics g){
        states.peek().draw(g);
    }

    public void keyPressed(int k){
        states.peek().keyPressed(k);
    }

    public void keyReleased(int k){
        states.peek().keyReleased(k);
    }
}
