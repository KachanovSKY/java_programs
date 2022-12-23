package io.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

public class Images {

    public static BufferedImage[] blocks;
    public static BufferedImage player, background, coin;
    public static BufferedImage backgroundRedactor, playerC, coinC, blockC, clearIcon;




    public Images(){

        blocks = new BufferedImage[1];
        File fileBlocks = new File("res/Blocks/stone.png");
        File fileBlocksC = new File("res/Blocks/stoneConstructor.png");
        File fileClearIcon= new File("res/Blocks/clearIcon.png");
        File filePlayer = new File("res/Players/ninja.png");
        File filePlayerC = new File("res/Players/ninjaConstructor.png");
        File fileBackground = new File("res/Background/rocks3.jpg");
        File fileBackgroundRedactor = new File("res/Background/monthRedactor.png");
        File fileCoin = new File("res/Coin/coin.png");
        File fileCoinC = new File("res/Coin/coinConstructor.png");


        try {
            player = ImageIO.read(filePlayer);
            playerC = ImageIO.read(filePlayerC);
            blocks[0] = ImageIO.read(fileBlocks);
            blockC = ImageIO.read(fileBlocksC);
            clearIcon = ImageIO.read(fileClearIcon);
            background = ImageIO.read(fileBackground);
            backgroundRedactor = ImageIO.read(fileBackgroundRedactor);
            coin = ImageIO.read(fileCoin);
            coinC = ImageIO.read(fileCoinC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
