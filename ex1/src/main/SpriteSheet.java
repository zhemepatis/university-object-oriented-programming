package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class SpriteSheet {
    public BufferedImage img;

    public final int DEFAULT_TILE_NUM;
    public int TILE_WIDTH, TILE_HEIGHT;
    public int IMG_HEIGHT, IMG_WIDTH;
    public int IMG_ROWS, IMG_COLS;
    BufferedImage[][] tileArr;

    public SpriteSheet(String path, int tileWidth, int tileHeight, int defaultTile) {
        try {
            this.img = ImageIO.read(new FileInputStream(path));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.TILE_WIDTH = tileWidth;
        this.TILE_HEIGHT= tileHeight;
        this.IMG_HEIGHT = img.getHeight();
        this.IMG_WIDTH = img.getWidth();
        this.IMG_ROWS = IMG_HEIGHT/TILE_HEIGHT;
        this.IMG_COLS = IMG_WIDTH/TILE_WIDTH;
        this.DEFAULT_TILE_NUM = defaultTile-1;

        loadTiles();
    }

    public BufferedImage getTile(int tileNum) {
        int tileRow = tileNum/IMG_COLS;
        int tileCol = tileNum%IMG_COLS;

        return tileArr[tileRow][tileCol];
    }

    void loadTiles() {
        tileArr = new BufferedImage[IMG_ROWS][IMG_COLS];

        for(int i = 0; i < IMG_ROWS; ++i) {
            for(int j = 0; j < IMG_COLS; ++j) {
                tileArr[i][j] = getSubImg(i * IMG_COLS + j);
            }
        }
    }

    BufferedImage getSubImg(int num) {
        int subImgX = num % IMG_COLS * TILE_WIDTH;
        int subImgY = num / IMG_COLS * TILE_HEIGHT;

        return img.getSubimage(subImgX, subImgY, TILE_WIDTH, TILE_HEIGHT);
    }
}
