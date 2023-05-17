package map;

import main.Playground;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MapEditor {
    Playground gameView;
    public MapLayout layout;

    public final String LAYOUTS_FOLDER_PATH = "./res/world/layouts/";
    public int currLayout = 0;
    public String[] layoutsPaths;

    public int selectedTileNum;
    public int leftX, rightX, topY, bottomY;

    public final int LAYOUT_TILE_SIZE = 32;
    public final int SPRITE_SHEET_TILE_SIZE = 64;
    final int OPACITY_MODE = 90;

    public boolean SPRITE_SHEET_ON = true;
    public boolean STARTING_POSITION_SELECTION = false;
    public boolean FINISH_POSITION_SELECTION = false;
    public boolean RESPONDABLE_TILE_PLACING = false;
    public boolean NEW_LAYOUT = false;
    public boolean ARTEFACT_PLACING = false;
    public boolean MAZE_MODE = true;

    int mouseX, mouseY;

    public MapEditor(Playground gameView) {
        this.gameView = gameView;

        loadLevels();
        if(layoutsPaths.length == 0) {
            createNewLayout();
            NEW_LAYOUT = true;
        }
        else {
            this.layout = new MapLayout(layoutsPaths[layoutsPaths.length-1]);
        }

        this.leftX = gameView.WINDOW_WIDTH/2 - layout.cols * LAYOUT_TILE_SIZE / 2;
        this.rightX = this.leftX + layout.cols * LAYOUT_TILE_SIZE;
        this.topY = gameView.WINDOW_HEIGHT/2 - layout.rows * LAYOUT_TILE_SIZE / 2;
        this.bottomY = this.topY + layout.rows * LAYOUT_TILE_SIZE;
    }

    public void draw(Graphics2D g2) {
        Point mouse = gameView.getMousePosition();
        this.mouseX = (mouse != null) ? mouse.x : gameView.WINDOW_WIDTH;
        this.mouseY = (mouse != null) ? mouse.y : gameView.WINDOW_HEIGHT;

        drawConstructor(g2);

        if(SPRITE_SHEET_ON)
            drawSpriteSheet(g2);
    }

    void drawConstructor(Graphics2D g2) {
        for(int i = 0; i < layout.rows; ++i) {
            for(int j = 0; j < layout.cols; ++j) {
                int currRectX = leftX + j * LAYOUT_TILE_SIZE;
                int currRectY = topY + i * LAYOUT_TILE_SIZE;

                int currImgNum = layout.firstLayer[i][j].tileNum;
                g2.drawImage(layout.spriteSheet.getTile(currImgNum), currRectX, currRectY, LAYOUT_TILE_SIZE, LAYOUT_TILE_SIZE, null);

                hoverTile(g2, currRectX, currRectY, LAYOUT_TILE_SIZE);

                g2.setColor(new Color(175, 175, 175));
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(currRectX, currRectY, LAYOUT_TILE_SIZE, LAYOUT_TILE_SIZE);

                if(MAZE_MODE) {
                    if(layout.finishTileRow == i && layout.finishTileCol == j) {
                        g2.setColor(new Color(253, 84, 84, OPACITY_MODE));
                        g2.fillRect(currRectX, currRectY, LAYOUT_TILE_SIZE, LAYOUT_TILE_SIZE);
                    }
                    else if(layout.startTileRow == i && layout.startTileCol == j) {
                        g2.setColor(new Color(74, 243, 85, OPACITY_MODE));
                        g2.fillRect(currRectX, currRectY, LAYOUT_TILE_SIZE, LAYOUT_TILE_SIZE);
                    }
                    else if(layout.firstLayer[i][j].hasArtefact) {
                        g2.setColor(new Color(246, 222, 101, OPACITY_MODE));
                        g2.fillRect(currRectX, currRectY, LAYOUT_TILE_SIZE, LAYOUT_TILE_SIZE);
                    }
                    else if(layout.firstLayer[i][j].isRespondable) {
                        g2.setColor(new Color(101, 101, 246, OPACITY_MODE));
                        g2.fillRect(currRectX, currRectY, LAYOUT_TILE_SIZE, LAYOUT_TILE_SIZE);
                    }
                }
            }
        }

        // drawing selected tile
        BufferedImage selectedTileImage = layout.spriteSheet.getTile(selectedTileNum);
        g2.drawImage(selectedTileImage, mouseX, mouseY, SPRITE_SHEET_TILE_SIZE, SPRITE_SHEET_TILE_SIZE, null);

        if(FINISH_POSITION_SELECTION)
            g2.setColor(new Color(253, 84, 84, OPACITY_MODE));
        else if(STARTING_POSITION_SELECTION)
            g2.setColor(new Color(74, 243, 85, OPACITY_MODE));
        else if (RESPONDABLE_TILE_PLACING)
            g2.setColor(new Color(101, 101, 246, OPACITY_MODE));
        else if (ARTEFACT_PLACING)
            g2.setColor(new Color(246, 222, 101, OPACITY_MODE));
        else {
            g2.setColor(new Color(0, 0, 0, 0));
        }
        g2.fillRect(mouseX, mouseY, SPRITE_SHEET_TILE_SIZE, SPRITE_SHEET_TILE_SIZE);
    }

    void drawSpriteSheet(Graphics2D g2) {
        int imgWidth = SPRITE_SHEET_TILE_SIZE*layout.spriteSheet.IMG_COLS;
        int imgHeight = SPRITE_SHEET_TILE_SIZE*layout.spriteSheet.IMG_ROWS;

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, imgWidth, imgHeight);

        g2.drawImage(layout.spriteSheet.img, 0, 0, imgWidth, imgHeight, null);

        int imgCols = imgWidth / SPRITE_SHEET_TILE_SIZE;
        int imgRows = imgHeight / SPRITE_SHEET_TILE_SIZE;

        for(int i = 0; i < imgRows; ++i) {
            for(int j = 0; j < imgCols; ++j) {
                int currRectX = j * SPRITE_SHEET_TILE_SIZE;
                int currRectY = i * SPRITE_SHEET_TILE_SIZE;

                hoverTile(g2, currRectX, currRectY, SPRITE_SHEET_TILE_SIZE);

                g2.setColor(new Color(175, 175, 175));
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(currRectX, currRectY, SPRITE_SHEET_TILE_SIZE, SPRITE_SHEET_TILE_SIZE);
            }
        }
    }

    void hoverTile(Graphics2D g2, int currRectX, int currRectY, int tileSize) {
        if (mouseX >= currRectX && mouseX <= currRectX + tileSize && mouseY >= currRectY && mouseY <= currRectY + tileSize) {
            g2.setColor(new Color(175, 175, 175, 127));
            g2.fillRect(currRectX, currRectY, tileSize, tileSize);
        }
    }

    public void loadLevels() {
        File layoutsFolder = new File(LAYOUTS_FOLDER_PATH);
        File[] layouts = layoutsFolder.listFiles();
        int fileNum = layouts.length;

        layoutsPaths = new String[fileNum];
        for (int i = 0; i < fileNum; ++i)
            layoutsPaths[i] = layouts[i].getPath();
    }

    public void nextLayout() {
        currLayout = ((currLayout + 1) == layoutsPaths.length) ? 0 : currLayout + 1;
        layout.loadLayout(layoutsPaths[currLayout]);
    }

    public void previousLayout() {
        currLayout = ((currLayout - 1) < 0) ? layoutsPaths.length - 1  : currLayout - 1;
        layout.loadLayout(layoutsPaths[currLayout]);
    }

    public void createNewLayout() {
        layout = new MapLayout(20, 20);
    }
}
