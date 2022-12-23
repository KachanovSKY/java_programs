package model.objects.entities;

import io.resources.AppFileReader;
import view.app.GamePanel;
import view.gamestates.GameState;
import io.resources.AppFileWriter;
import view.Block;
import view.Coin;
import model.objects.phisics.CoinCollection;
import model.objects.phisics.Collision;
import view.LevelsMenu;
import view.Menu;


import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Player extends Rectangle {
    //Фиксация времени рекорда
    public static Instant startTime;
    public static Instant finishTime;

    //Фиксация карты рекорда
    public static String recordMap;

    public static int score;

    // Движение
    public boolean right = false, left = false, jumping = false, falling = false;
    private boolean topCollision = false;
    public boolean finalTitles = false;
    public boolean recordedST = false;
    public static boolean off = false;

    // Границы
    private final double x;
    private double y;
    private final int width;
    private final int height;

    // Скорость перемещения
    private final double moveSpeed = 2.5;

    // Скорость прыжка
    private final double jumpSpeed = 5.5;
    private double currentJumpSpeed = jumpSpeed;

    // Скорость падения
    private final double maxFallSpeed = 5;
    private double currentFallSpeed = 0.1;

    public Player(int width, int height){
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;
        this.width = width;
        this.height = height;
        score = 0;
    }

    public void tick(Block[][] b) throws InterruptedException {
        int iX = (int)x;
        int iY = (int)y;

        // Коллизия
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {

                if (b[i][j].getID() != 0 && b[i][j].getID() != 2){
                    //Право
                    if (Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset, iY + height + (int) GameState.yOffset - 1), b[i][j])) {
                        right = false;
                    }

                    //Лево
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX + (int) GameState.xOffset - 1, iY + height + (int) GameState.yOffset - 1), b[i][j])) {
                        left = false;
                    }

                    //Верх
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset + 1, iY + (int) GameState.yOffset + 1), b[i][j])
                            || Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset - 2, iY + (int) GameState.yOffset + 1), b[i][j])) {
                        jumping = false;
                        falling = true;
                    }

                    //Низ
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset + 2, iY + height + (int) GameState.yOffset + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset - 2, iY + height + (int) GameState.yOffset + 1), b[i][j])) {
                        y = b[i][j].getY() - height - GameState.yOffset; // чтобы небыло отскока
                        falling = false;
                        topCollision = true;
                    } else {
                        if (!topCollision && !jumping) {
                            falling = true;
                        }
                    }
                } else if (b[i][j].getID() == 2){

                    //Право
                    if (CoinCollection.playerCoin(new Point(iX + width - 15 + (int) GameState.xOffset, iY + (int) GameState.yOffset + 2), b[i][j])
                            || CoinCollection.playerCoin(new Point(iX + width - 15 + (int) GameState.xOffset, iY + height + (int) GameState.yOffset - 1), b[i][j])) {
                        b[i][j].setID(0);
                        this.addScore(1);
                    }

                    //Лево
                    else if (CoinCollection.playerCoin(new Point(iX + 14 + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset + 2), b[i][j])
                            || CoinCollection.playerCoin(new Point(iX + 14 + (int) GameState.xOffset - 1, iY + height + (int) GameState.yOffset - 1), b[i][j])) {
                        b[i][j].setID(0);
                        this.addScore(1);
                    }

                    //Верх
                    else if (CoinCollection.playerCoin(new Point(iX + (int) GameState.xOffset + 1, iY + (int) GameState.yOffset), b[i][j])
                            || CoinCollection.playerCoin(new Point(iX + width - 15 + (int) GameState.xOffset - 2 , iY + (int) GameState.yOffset), b[i][j])) {
                        b[i][j].setID(0);
                        this.addScore(1);
                    }

                    //Низ
                    else if (CoinCollection.playerCoin(new Point(iX + (int) GameState.xOffset + 2 , iY + 15 + (int) GameState.yOffset + 2), b[i][j])
                            || CoinCollection.playerCoin(new Point(iX + width + (int) GameState.xOffset - 2, iY + 15 + (int) GameState.yOffset + 2), b[i][j])) {
                        y = b[i][j].getY() - 15 - GameState.yOffset; // чтобы небыло отскока
                        b[i][j].setID(0);
                        this.addScore(1);
                    } else {
                        if (!topCollision && !jumping) {
                            falling = true;
                        }
                    }
                }
                if (score == Coin.countCoin){
                    boolean status = false;
                    finalTitles = true;
                    if (off){
                        finishTime = Instant.now();
                        AppFileWriter.saveRecord(Duration.between(startTime, finishTime).toSeconds(), this.recordMap);
                        left = false; right = false; falling = false; jumping = false;
                        status = true;
                        off = false;
                    }
                    if (status) {
                        TimeUnit.SECONDS.sleep(3);
                        Coin.countCoin = 0;
                        LevelsMenu.openMenu();
                    }
                }
            }
        }

        topCollision = false;

        if (right) {
            GameState.xOffset += moveSpeed;
        }
        if (left) {
            GameState.xOffset -= moveSpeed;
        }
        if (jumping){
            GameState.yOffset -= currentJumpSpeed;

            currentJumpSpeed -= .1;

            if(currentJumpSpeed <= 0){
                currentJumpSpeed = jumpSpeed;
                jumping = false;
                falling = true;
            }
        }
        if (falling){
            GameState.yOffset += currentFallSpeed;

            if (currentFallSpeed < maxFallSpeed) {
                currentFallSpeed += .1;
            }
        }

        if (!falling) {
            currentFallSpeed = .1;
        }
    }

    public double pGetX(){
        return x;
    }

    public double pGetY(){
        return y;
    }

    public double pGetWidth(){
        return width;
    }

    public double pGetHeight(){
        return height;
    }

    public void keyPessed(int k) {
        if(k == KeyEvent.VK_D) right = true;
        if(k == KeyEvent.VK_A) left = true;
        if(k == KeyEvent.VK_SPACE && !jumping && !falling) jumping = true;
        if(k == KeyEvent.VK_R) {
            Coin.countCoin = 0;
            Menu.startGame();
        }
        if (k == KeyEvent.VK_ESCAPE) LevelsMenu.openMenu();
    }

    public void keyReleased(int k) {
        if(k == KeyEvent.VK_D) right = false;
        if(k == KeyEvent.VK_A) left = false;
    }

    public static String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }


    public static void setRecordMap(String name) {
        recordMap = name;
    }
}
