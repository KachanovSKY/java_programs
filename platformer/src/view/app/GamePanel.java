package view.app;

import view.gamestates.GameStateManager;
import io.resources.Images;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 900;
    public static final int HEIGHT = 450;

    private Thread thread;
    private boolean isRunning = false;
    private int FPS = 60;
    private long targetTime = 1000/FPS;

    private GameStateManager gsm;

    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        addKeyListener(this);
        setFocusable(true);

        new Images();
        start();
    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run(){
        long start, elapsed, wait;

        gsm = new GameStateManager();
        while(isRunning){
            start = System.nanoTime();

            try {
                tick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();

            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            // если он делает это за меньшее время чем мы хотим (меньше 60 фпс) - будем ждать
            // чтобы перс не двигался быстрей на лучших компах
            if (wait <= 0) {
                wait = 5;
            }
            try {
                Thread.sleep(wait);
            }catch(Exception e){
                e.printStackTrace();
            }
         }
    }

    public void tick() throws InterruptedException {
        gsm.tick();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.clearRect(0, 0, WIDTH, HEIGHT);
        gsm.draw(g);

    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());

    }


}
