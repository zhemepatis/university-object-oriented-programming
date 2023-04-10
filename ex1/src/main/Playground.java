package main;

import entity.Player;
import map.MapEditor;
import map.MapManager;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

public class Playground extends JPanel implements Runnable {
    // --- GRAPHIC SETTINGS ---
    public final int TILE_SIZE = 16;
    public final int SCALE = 3;
    public final int SCALED_TILE_SIZE = TILE_SIZE * SCALE;

    final int MAX_WINDOW_COL_NUM = 20;
    final int MAX_WINDOW_ROW_NUM = 15;
    public final int WINDOW_WIDTH = MAX_WINDOW_COL_NUM * SCALED_TILE_SIZE;
    public final int WINDOW_HEIGHT = MAX_WINDOW_ROW_NUM * SCALED_TILE_SIZE;

    final int FPS = 60;

    // --- GAME STATE ---
    final int LEVEL_RUNNING = 2;
    public final int WORLD_CONSTRUCTOR = 3;
    public final int GAME_END = 4;
    public int gameState = 3;

    public int artifactCount = 0;

    // --- OTHER ---
    Thread gameThread;  // needed for making game loop
    KeyHandler keyHandler = new KeyHandler(this);
    MouseHandler mouseHandler = new MouseHandler(this);

    public MapManager mapManager = new MapManager(this);
    public CollisionDetector cd = new CollisionDetector(this);
    public Player player = new Player(this, keyHandler);
    MapEditor mapEditor = new MapEditor(this);

    public Playground() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)); // sets panel size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000/FPS;
        double delta = 0;
        long lastTimeDrawn = System.currentTimeMillis();
        long currTime;

        // game loop
        while(gameThread != null) {
            currTime = System.currentTimeMillis();
            delta += (currTime - lastTimeDrawn) / drawInterval;
            lastTimeDrawn = currTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        switch(gameState) {
            case LEVEL_RUNNING -> {
                player.update();
                cd.checkIfFinish(player);
                cd.checkIfArtifact(player);

            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        switch(gameState) {
            case LEVEL_RUNNING:
                mapManager.draw(g2);
                player.draw(g2);
                break;
            case WORLD_CONSTRUCTOR:
                mapEditor.draw(g2);
                break;
            case GAME_END:
                String msg = "Congratulations! You finished the game!";
                msg = msg.toUpperCase();
                g2.setFont(new Font("Arial", BOLD, 24));
                g2.setColor(Color.white);
                g2.drawString(msg, WINDOW_WIDTH/2 - g2.getFontMetrics().stringWidth(msg)/2, WINDOW_HEIGHT/2);
                break;
        }

        g2.dispose();
    }
}
