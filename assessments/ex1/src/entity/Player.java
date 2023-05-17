package entity;

import main.KeyHandler;
import main.Playground;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    Playground gameView;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    int animationTick = 0;
    int spriteNum = 0;

    public Player(Playground gameView, KeyHandler keyHandler) {
        this.gameView = gameView;
        this.keyHandler = keyHandler;

        screenX = gameView.WINDOW_WIDTH/2 - gameView.SCALED_TILE_SIZE/2;
        screenY = gameView.WINDOW_HEIGHT/2 - gameView.SCALED_TILE_SIZE/2;

        worldX = gameView.mapManager.layout.startTileCol * gameView.SCALED_TILE_SIZE;
        worldY = gameView.mapManager.layout.startTileRow * gameView.SCALED_TILE_SIZE;

        collisionArea = new Rectangle(gameView.SCALED_TILE_SIZE/4, gameView.SCALED_TILE_SIZE/2, gameView.SCALED_TILE_SIZE/2,gameView.SCALED_TILE_SIZE/2);

        velocity = 5;
    }

    public void update() {
        // checking direction
        if(keyHandler.upPressed)
            direction = 1;
        else if(keyHandler.downPressed)
            direction = 2;
        else if(keyHandler.leftPressed)
            direction = 3;
        else if(keyHandler.rightPressed)
            direction = 4;
        else
            direction = 0;

        // checking collision
        collisionOn = false;
        gameView.cd.check(this);

        // moving character if possible
        if(!collisionOn) {
            switch (direction) {
                case 1:
                    worldY -= velocity;
                    break;
                case 2:
                    worldY += velocity;
                    break;
                case 3:
                    worldX -= velocity;
                    break;
                case 4:
                    worldX += velocity;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case 0:
                spriteNum = spriteNum >= idleAnimation.IMG_COLS ? 0 : spriteNum;
                image = idleAnimation.getTile(spriteNum);
                break;
            case 1:
                spriteNum = spriteNum >= upAnimation.IMG_COLS ? 0 : spriteNum;
                image = upAnimation.getTile(spriteNum);
                break;
            case 2:
                spriteNum = spriteNum >= downAnimation.IMG_COLS ? 0 : spriteNum;
                image = downAnimation.getTile(spriteNum);
                break;
            case 3:
                spriteNum = spriteNum >= leftAnimation.IMG_COLS ? 0 : spriteNum;
                image = leftAnimation.getTile(spriteNum);
                break;
            case 4:
                spriteNum = spriteNum >= rightAnimation.IMG_COLS ? 0 : spriteNum;
                image = rightAnimation.getTile(spriteNum);
                break;
        }
        g2.drawImage(image, screenX, screenY, gameView.SCALED_TILE_SIZE, gameView.SCALED_TILE_SIZE, null);

        if(animationTick > 8) {
            animationTick = 0;
            spriteNum++;
        }
        animationTick++;
    }
}
