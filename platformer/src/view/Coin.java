package view;

import view.gamestates.GameState;
import io.resources.Images;

import java.awt.*;
import java.io.Serial;

public class Coin extends Block {
    @Serial
    private static final long serialVersionUID = 1L;
    public static int countCoin = 0;
    private int id;
    public static final int coinSize = 30;

    public Coin (int x, int y, int id){
        super(x, y, id);
        setBounds(x, y, coinSize, coinSize);
        this.id = id;

    }

    public void tick(){}
    public void draw(Graphics g) {
        g.drawImage(Images.coin, (int) (x - (int) GameState.xOffset + 16), (int) (y - (int) GameState.yOffset + 30), coinSize, coinSize, null);
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }
}
