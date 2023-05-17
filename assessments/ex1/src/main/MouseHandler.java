package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    Playground gameView;

    public MouseHandler(Playground gameView) {
        this.gameView = gameView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = (gameView.getMousePosition() != null) ? gameView.getMousePosition().x : gameView.WINDOW_WIDTH;
        int mouseY = (gameView.getMousePosition() != null) ? gameView.getMousePosition().y : gameView.WINDOW_HEIGHT;

        if(gameView.gameState == gameView.WORLD_CONSTRUCTOR) {
            int tileCol = (mouseX-gameView.mapEditor.leftX)/gameView.mapEditor.LAYOUT_TILE_SIZE;
            int tileRow = (mouseY-gameView.mapEditor.topY)/gameView.mapEditor.LAYOUT_TILE_SIZE;

            if(gameView.mapEditor.SPRITE_SHEET_ON) {
                if(mouseX >= 0 && mouseX <= gameView.mapEditor.layout.spriteSheet.IMG_COLS*gameView.mapEditor.SPRITE_SHEET_TILE_SIZE
                    && mouseY >= 0 && mouseY <= gameView.mapEditor.layout.spriteSheet.IMG_ROWS*gameView.mapEditor.SPRITE_SHEET_TILE_SIZE) {
                    int spriteSheetTileCol = mouseX/gameView.mapEditor.SPRITE_SHEET_TILE_SIZE;
                    int spriteSheetTileRow = mouseY/gameView.mapEditor.SPRITE_SHEET_TILE_SIZE;

                    gameView.mapEditor.selectedTileNum = spriteSheetTileRow * gameView.mapEditor.layout.spriteSheet.IMG_COLS + spriteSheetTileCol;
                } else {
                    gameView.mapEditor.SPRITE_SHEET_ON = false;
                }
            }
            if (mouseX >= gameView.mapEditor.leftX
                    && mouseX <= gameView.mapEditor.rightX
                    && mouseY >= gameView.mapEditor.topY
                    && mouseY <= gameView.mapEditor.bottomY) {

                if(tileCol != 0 && tileRow != 0
                    && tileRow != gameView.mapEditor.layout.rows-1
                    && tileCol != gameView.mapEditor.layout.cols-1) {
                    if(gameView.mapEditor.STARTING_POSITION_SELECTION) {
                        gameView.mapEditor.layout.startTileCol = tileCol;
                        gameView.mapEditor.layout.startTileRow = tileRow;
                        gameView.mapEditor.layout.firstLayer[tileRow][tileCol].isRespondable = false;
                        gameView.mapEditor.layout.firstLayer[tileRow][tileCol].hasArtefact = false;
                    }
                    else if(gameView.mapEditor.FINISH_POSITION_SELECTION) {
                        gameView.mapEditor.layout.finishTileCol = tileCol;
                        gameView.mapEditor.layout.finishTileRow = tileRow;
                        gameView.mapEditor.layout.firstLayer[tileRow][tileCol].isRespondable = false;
                        gameView.mapEditor.layout.firstLayer[tileRow][tileCol].hasArtefact = false;

                    }
                    else if(gameView.mapEditor.RESPONDABLE_TILE_PLACING) {
                        gameView.mapEditor.layout.firstLayer[tileRow][tileCol].isRespondable = true;
                        gameView.mapEditor.layout.firstLayer[tileRow][tileCol].hasArtefact = false;
                    }
                    else if(gameView.mapEditor.ARTEFACT_PLACING) {
                        gameView.mapEditor.layout.firstLayer[tileRow][tileCol].hasArtefact = true;
                        gameView.mapEditor.layout.firstLayer[tileRow][tileCol].isRespondable = false;
                    }
                    else {
                        gameView.mapEditor.layout.firstLayer[tileRow][tileCol].isRespondable = false;
                        gameView.mapEditor.layout.firstLayer[tileRow][tileCol].hasArtefact = false;
                    }
                }
                gameView.mapEditor.layout.firstLayer[tileRow][tileCol].tileNum = gameView.mapEditor.selectedTileNum;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
