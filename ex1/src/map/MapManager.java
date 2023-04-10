package map;

import main.Playground;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MapManager {
    Playground gameView;
    public MapLayout layout;

    final String LAYOUTS_FOLDER_PATH = "./res/world/layouts/";
    String[] layoutsPaths;
    int currLayout = 0;

    public MapManager(Playground gameView) {
        this.gameView = gameView;

        loadLevels();
        if (layoutsPaths.length != 0) {
            this.layout = new MapLayout(layoutsPaths[currLayout]);
        }
        else {
            gameView.gameState = gameView.WORLD_CONSTRUCTOR;
        }
    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < layout.rows; ++i) {
            for(int j = 0; j < layout.cols; ++j) {
                int playerWorldX = gameView.player.worldX;
                int playerWorldY = gameView.player.worldY;
                int playerScreenX = gameView.player.screenX;
                int playerScreenY = gameView.player.screenY;

                int tileWorldX = j * gameView.SCALED_TILE_SIZE;
                int tileWorldY = i * gameView.SCALED_TILE_SIZE;

                if(tileWorldX + gameView.SCALED_TILE_SIZE > playerWorldX - playerScreenX
                        && tileWorldX - gameView.SCALED_TILE_SIZE < playerWorldX + playerScreenX
                        && tileWorldY + gameView.SCALED_TILE_SIZE > playerWorldY - playerScreenY
                        && tileWorldY - gameView.SCALED_TILE_SIZE < playerWorldY + playerScreenY) {
                    int tileScreenX = tileWorldX - playerWorldX + playerScreenX;
                    int tileScreenY = tileWorldY - playerWorldY + playerScreenY;

                    BufferedImage currTileImg = layout.spriteSheet.getTile(layout.firstLayer[i][j].tileNum);
                    g2.drawImage(currTileImg, tileScreenX, tileScreenY, gameView.SCALED_TILE_SIZE, gameView.SCALED_TILE_SIZE, null);

                    if(layout.firstLayer[i][j].hasArtefact) {
                        BufferedImage artefactImg = layout.artefactSpriteSheet.getTile(layout.artefactSpriteSheet.DEFAULT_TILE_NUM);
                        g2.drawImage(artefactImg, tileScreenX, tileScreenY, gameView.SCALED_TILE_SIZE, gameView.SCALED_TILE_SIZE, null);
                    }
                }
            }
        }
    }

    void loadLevels() {
        File layoutsFolder = new File(LAYOUTS_FOLDER_PATH);
        File[] layouts = layoutsFolder.listFiles();
        int fileNum = layouts.length;

        layoutsPaths = new String[fileNum];
        for (int i = 0; i < fileNum; ++i)
            layoutsPaths[i] = layouts[i].getPath();
    }

    public void nextLayout() {
        ++currLayout;

        if(currLayout == layoutsPaths.length) {
            gameView.gameState = gameView.GAME_END;
            return;
        }

        layout.loadLayout(layoutsPaths[currLayout]);
        System.out.println(layout.startTileCol + " " + layout.startTileRow);
        gameView.player.worldX = layout.startTileCol * gameView.SCALED_TILE_SIZE;
        gameView.player.worldY = layout.startTileRow * gameView.SCALED_TILE_SIZE;
    }
}
