package view;

import view.Coin;
import view.gamestates.GameState;
import io.resources.Images;

import java.awt.*;
import java.io.Serial;

public class Block extends Rectangle {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    public static final int blockSize = 64;

    public Block (int x, int y, int id){
        setBounds(x, y, blockSize, blockSize);
        this.id = id;
    }

    public void tick(){}
    public void draw (Graphics g) {
        g.setColor(new Color(14, 41, 75));
        if (id != 0) {
            if (id == 1)
                g.drawImage(Images.blocks[id - 1], x - (int) GameState.xOffset, y - (int) GameState.yOffset, width, height, null);
            else if (id == 2) {
                Coin tmpCoin = new Coin(x, y, id - 1);
                tmpCoin.draw(g);
            }
        }
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }
}
