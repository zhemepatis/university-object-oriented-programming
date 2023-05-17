//package map;
//
//import main.KeyHandler;
//import main.Playground;
//import main.UI;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class WorldConstructor {
//    Playground gameView;
//    KeyHandler keyHandler;
//    public UI.SpriteSheet wrldSpriteSheet;
//
//    public int selectedTileNum = 3;
//    public int mapCols = 20;
//    public int mapRows = 20;
//    public int[][] newLayout;
//
//
//    public boolean SPRITESHEET_ON = true;
//
//    public WorldConstructor(Playground gameView, KeyHandler keyHandler) {
//        this.gameView = gameView;
//        this.keyHandler = keyHandler;
//        this.wrldSpriteSheet = new SpriteSheet("res/world/forest.gif", 16);
//
//        this.newLayout = new int[mapRows][mapCols];
//        initialiseLayout(newLayout, mapRows, mapCols);
//    }
//
//    public void initialiseLayout(int[][] layout, int dim1, int dim2) {
//        for(int i = 0; i < dim1; ++i) {
//            for(int j = 0; j < dim2; ++j) {
//                layout[i][j] = 0;
//            }
//        }
//    }
//
//    public void draw(Graphics2D g2) {
//        int mouseX = (gameView.getMousePosition() != null) ? gameView.getMousePosition().x : gameView.WINDOW_WIDTH;
//        int mouseY = (gameView.getMousePosition() != null) ? gameView.getMousePosition().y : gameView.WINDOW_HEIGHT;
//
//        drawConstructor(g2, mouseX, mouseY);
//
//        if(SPRITESHEET_ON)
//            drawSpritesheet(g2,  mouseX, mouseY);
//    }
//
//    void drawConstructor(Graphics2D g2, int mouseX, int mouseY) {
//        for(int i = 0; i < mapRows; ++i) {
//            for(int j = 0; j < mapCols; ++j) {
//                int currRectX = j * gameView.SCALED_TILE_SIZE/gameView.SCALE;
//                int currRectY = i * gameView.SCALED_TILE_SIZE/gameView.SCALE;
//
//                if(mouseX >= currRectX && mouseX <= currRectX + gameView.SCALED_TILE_SIZE/ gameView.SCALE
//                        && mouseY >= currRectY && mouseY <= currRectY + gameView.SCALED_TILE_SIZE/ gameView.SCALE) {
//                    g2.setColor(new Color(175, 175, 175, 127));
//                    g2.fillRect(currRectX, currRectY, gameView.SCALED_TILE_SIZE/ gameView.SCALE, gameView.SCALED_TILE_SIZE/ gameView.SCALE);
//                }
//
//                int currImgNum = newLayout[i][j];
//                g2.drawImage(spritesheet.getSubImg(currImgNum), currRectX, currRectY, gameView.TILE_SIZE, gameView.TILE_SIZE, null);
//
//                g2.setColor(new Color(175, 175, 175));
//                g2.setStroke(new BasicStroke(3));
//                g2.drawRect(currRectX, currRectY, gameView.TILE_SIZE, gameView.TILE_SIZE);
//            }
//        }
//
//
//        BufferedImage selectedTileImage = spritesheet.getSubImg(selectedTileNum);
//        g2.drawImage(selectedTileImage, mouseX, mouseY, gameView.SCALED_TILE_SIZE, gameView.SCALED_TILE_SIZE, null);
//    }
//
//    void drawSpritesheet(Graphics2D g2, int mouseX, int mouseY) {
//        int imgWidth = spritesheet.image.getWidth() * gameView.SCALE;
//        int imgHeight = spritesheet.image.getHeight() * gameView.SCALE;
//
//        g2.drawImage(spritesheet.image, 0, 0, imgWidth, imgHeight, null);
//
//        int imgCols = imgWidth / gameView.SCALED_TILE_SIZE;
//        int imgRows = imgHeight / gameView.SCALED_TILE_SIZE;
//
//        for(int i = 0; i < imgRows; ++i) {
//            for(int j = 0; j < imgCols; ++j) {
//                int currRectX = j * gameView.SCALED_TILE_SIZE;
//                int currRectY = i * gameView.SCALED_TILE_SIZE;
//
//                if(mouseX >= currRectX && mouseX <= currRectX + gameView.SCALED_TILE_SIZE
//                        && mouseY >= currRectY && mouseY <= currRectY + gameView.SCALED_TILE_SIZE) {
//                    g2.setColor(new Color(175, 175, 175, 127));
//                    g2.fillRect(currRectX, currRectY, gameView.SCALED_TILE_SIZE, gameView.SCALED_TILE_SIZE);
//                }
//
//                g2.setColor(new Color(175, 175, 175));
//                g2.setStroke(new BasicStroke(3));
//                g2.drawRect(currRectX, currRectY, gameView.SCALED_TILE_SIZE, gameView.SCALED_TILE_SIZE);
//            }
//        }
//    }
//}
//
