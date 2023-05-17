package main;

//import map.WorldConstructor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public Playground gameView;

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(Playground gameView) {
        this.gameView = gameView;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(keyCode == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(keyCode == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(keyCode == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(gameView.gameState == gameView.WORLD_CONSTRUCTOR) {
            if(keyCode == KeyEvent.VK_Q) {
                gameView.mapEditor.SPRITE_SHEET_ON = !gameView.mapEditor.SPRITE_SHEET_ON;
            }
            if(keyCode == KeyEvent.VK_S) {
                String path;
                if(gameView.mapEditor.NEW_LAYOUT) {
                    path = gameView.mapEditor.layout.generateNewFilename();
                    gameView.mapEditor.NEW_LAYOUT = false;
                }
                else {
                    path = gameView.mapEditor.layoutsPaths[gameView.mapEditor.currLayout];
                }

                gameView.mapEditor.layout.saveLayout(path);
                gameView.mapEditor.loadLevels();
            }
            if(keyCode == KeyEvent.VK_G) {
                gameView.mapEditor.MAZE_MODE = !gameView.mapEditor.MAZE_MODE;
            }
            if (keyCode == KeyEvent.VK_J) {
                gameView.mapEditor.previousLayout();
                gameView.mapEditor.NEW_LAYOUT = false;
            }
            if (keyCode == KeyEvent.VK_K) {
                gameView.mapEditor.nextLayout();
                gameView.mapEditor.NEW_LAYOUT = false;
            }
            if (keyCode == KeyEvent.VK_N) {
                gameView.mapEditor.createNewLayout();
                gameView.mapEditor.NEW_LAYOUT = true;
            }
            if(keyCode == KeyEvent.VK_W) {
                gameView.mapEditor.STARTING_POSITION_SELECTION = !gameView.mapEditor.STARTING_POSITION_SELECTION;
                gameView.mapEditor.FINISH_POSITION_SELECTION = false;
                gameView.mapEditor.RESPONDABLE_TILE_PLACING = false;
                gameView.mapEditor.ARTEFACT_PLACING = false;
            }
            if(keyCode == KeyEvent.VK_E) {
                gameView.mapEditor.FINISH_POSITION_SELECTION = !gameView.mapEditor.FINISH_POSITION_SELECTION;;
                gameView.mapEditor.STARTING_POSITION_SELECTION = false;
                gameView.mapEditor.RESPONDABLE_TILE_PLACING = false;
                gameView.mapEditor.ARTEFACT_PLACING = false;
            }
            if(keyCode == KeyEvent.VK_R) {
                gameView.mapEditor.RESPONDABLE_TILE_PLACING = !gameView.mapEditor.RESPONDABLE_TILE_PLACING;
                gameView.mapEditor.STARTING_POSITION_SELECTION = false;
                gameView.mapEditor.FINISH_POSITION_SELECTION = false;
                gameView.mapEditor.ARTEFACT_PLACING = false;
            }
            if(keyCode == KeyEvent.VK_A) {
                gameView.mapEditor.ARTEFACT_PLACING = !gameView.mapEditor.ARTEFACT_PLACING;
                gameView.mapEditor.STARTING_POSITION_SELECTION = false;
                gameView.mapEditor.FINISH_POSITION_SELECTION = false;
                gameView.mapEditor.RESPONDABLE_TILE_PLACING = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(keyCode == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(keyCode == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(keyCode == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
