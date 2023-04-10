package entity;

import main.SpriteSheet;

import java.awt.*;

public class Entity {
    public int worldX, worldY;
    public int velocity;
    public int direction = 0;   // 0 - idle, 1 - up, 2 - down, 3 - left, 4 - right

    public SpriteSheet leftAnimation = new SpriteSheet("./res/player/left.png", 16, 16, 1);
    public SpriteSheet rightAnimation = new SpriteSheet("./res/player/right.png", 16, 16, 1);
    public SpriteSheet upAnimation = new SpriteSheet("./res/player/up.png", 16, 16, 1);
    public SpriteSheet downAnimation = new SpriteSheet("./res/player/down.png", 16, 16, 1);
    public SpriteSheet idleAnimation = new SpriteSheet("./res/player/idle.png", 16, 16, 1);

    public Rectangle collisionArea;
    public boolean collisionOn = false;
}
