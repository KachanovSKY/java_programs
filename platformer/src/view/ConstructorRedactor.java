package view;

import io.resources.AppFileReader;
import io.resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class ConstructorRedactor extends JPanel{
    public static int[][] gameMap;
    private int[] playerPosition = {0, 0};
    private int selectedIcon;
    private boolean playerStatus = false;

    public static final int pictureSize = 16;
    public static final int windowSize = 512;
    private BufferedImage image;


    public ConstructorRedactor () {
        selectedIcon = 1;
        gameMap = new int[32][32];


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

        public void clearMap() {
            gameMap = new int[32][32];
            repaint();
        }

        public void setTile(int tile) {
            selectedIcon = tile+1;
        }

        public int[][] getTileMap() {
            return gameMap;
        }

    public void paintLevel(String name) throws FileNotFoundException {
        repaint();
        AppFileReader.loadLevel(name);
        repaint();
    }

        private void drawBackground(Graphics g) {
            g.setColor(new Color(45,155,240));
            g.fillRect(0, 0, 512, 512);
        }

    private void drawTiles(Graphics g) {
        for(int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                if (gameMap[i][j] == 1){
                    g.drawImage(Images.blockC, j * pictureSize,
                            i * pictureSize, Color.BLACK, null);
                } else if (gameMap[i][j] == 2){
                    g.drawImage(Images.coinC, j * pictureSize,
                            i * pictureSize, Color.BLACK, null);
                } else if (gameMap[i][j] == 3){
                    g.drawImage(Images.playerC, j * pictureSize,
                            i * pictureSize, Color.BLACK, null);
                } else if (gameMap[i][j] == 0){
                    g.drawImage(Images.clearIcon, j * pictureSize,
                            i * pictureSize, Color.BLACK, null);
                }
            }
        }
    }

    private void drawGrid(Graphics g) {
        int	borderLine = windowSize / 32 - 1;
        g.setColor(Color.WHITE);
        for (int i = 1; i < 32; i++) {
            g.drawLine(0,  (borderLine + 1) * i, windowSize, (borderLine + 1) * i);
            g.drawLine((borderLine + 1) * i, 0, (borderLine + 1) * i, windowSize);
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int i = (int) ((float) mouseEvent.getY() / windowSize * 32);
            int j = (int) ((float) mouseEvent.getX() / windowSize * 32);
            if (selectedIcon == 3) {
                if (!playerStatus) {
                    gameMap[i][j] = selectedIcon;
                    playerPosition[0] = i;
                    playerPosition[1] = j;
                    playerStatus = true;
                }
            }else
                gameMap[i][j] = selectedIcon;
            if(playerStatus){
                if (gameMap[playerPosition[0]][playerPosition[1]] != 3) {
                    playerPosition[0] = 0;
                    playerPosition[1] = 0;
                    playerStatus = false;
                }
            }
            repaint();
        } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            int i = (int) ((float) mouseEvent.getY() / windowSize * 32);
            int j = (int) ((float) mouseEvent.getX() / windowSize * 32);
            gameMap[i][j] = 0;
            repaint();
        }

    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, windowSize, windowSize);
        drawBackground(g);
        drawTiles(g);
        drawGrid(g);
    }
}
