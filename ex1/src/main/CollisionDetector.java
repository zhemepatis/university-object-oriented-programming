package main;

import entity.Entity;
import map.MapLayout;

public class CollisionDetector {
    Playground gameView;
    MapLayout layout;

    public CollisionDetector(Playground gameView) {
        this.gameView = gameView;
        this.layout = gameView.mapManager.layout;
    }

    public void check(Entity entity) {
        int topBorderRow = entity.worldY + gameView.SCALED_TILE_SIZE - entity.collisionArea.height;
        int bottomBorderRow = entity.worldY + gameView.SCALED_TILE_SIZE;
        int leftBorderCol = entity.worldX + entity.collisionArea.x;
        int rightBorderCol = entity.worldX + gameView.SCALED_TILE_SIZE - entity.collisionArea.x;

        switch (entity.direction) {
            case 1 -> {
                topBorderRow = (topBorderRow - entity.velocity) / gameView.SCALED_TILE_SIZE;
                leftBorderCol = leftBorderCol / gameView.SCALED_TILE_SIZE;
                rightBorderCol = rightBorderCol / gameView.SCALED_TILE_SIZE;
                if (layout.firstLayer[topBorderRow][leftBorderCol].isRespondable ||
                        layout.firstLayer[topBorderRow][rightBorderCol].isRespondable)
                    entity.collisionOn = true;
            }
            case 2 -> {
                bottomBorderRow = (bottomBorderRow + entity.velocity) / gameView.SCALED_TILE_SIZE;
                leftBorderCol = leftBorderCol / gameView.SCALED_TILE_SIZE;
                rightBorderCol = rightBorderCol / gameView.SCALED_TILE_SIZE;
                if (layout.firstLayer[bottomBorderRow][leftBorderCol].isRespondable ||
                        layout.firstLayer[bottomBorderRow][rightBorderCol].isRespondable)
                    entity.collisionOn = true;
            }
            case 3 -> {
                leftBorderCol = (leftBorderCol - entity.velocity) / gameView.SCALED_TILE_SIZE;
                topBorderRow = topBorderRow / gameView.SCALED_TILE_SIZE;
                bottomBorderRow = bottomBorderRow / gameView.SCALED_TILE_SIZE;
                if (layout.firstLayer[topBorderRow][leftBorderCol].isRespondable ||
                        layout.firstLayer[bottomBorderRow][leftBorderCol].isRespondable)
                    entity.collisionOn = true;
            }
            case 4 -> {
                rightBorderCol = (rightBorderCol + entity.velocity) / gameView.SCALED_TILE_SIZE;
                topBorderRow = topBorderRow / gameView.SCALED_TILE_SIZE;
                bottomBorderRow = bottomBorderRow / gameView.SCALED_TILE_SIZE;
                if (layout.firstLayer[topBorderRow][rightBorderCol].isRespondable ||
                        layout.firstLayer[bottomBorderRow][rightBorderCol].isRespondable)
                    entity.collisionOn = true;
            }
        }
    }

    public void checkIfFinish(Entity player) {
        int topBorderRow = (player.worldY + player.collisionArea.y) / gameView.SCALED_TILE_SIZE;
        int bottomBorderRow = (player.worldY + gameView.SCALED_TILE_SIZE) / gameView.SCALED_TILE_SIZE;
        int leftBorderCol = (player.worldX + player.collisionArea.x) / gameView.SCALED_TILE_SIZE;
        int rightBorderCol = (player.worldX + gameView.SCALED_TILE_SIZE - player.collisionArea.x) / gameView.SCALED_TILE_SIZE;

        if(gameView.artifactCount == gameView.mapManager.layout.artifactNum
                && (gameView.mapManager.layout.finishTileCol == leftBorderCol && gameView.mapManager.layout.finishTileRow == topBorderRow
                || gameView.mapManager.layout.finishTileCol == rightBorderCol && gameView.mapManager.layout.finishTileRow == topBorderRow
                || gameView.mapManager.layout.finishTileCol == rightBorderCol && gameView.mapManager.layout.finishTileRow == bottomBorderRow
                || gameView.mapManager.layout.finishTileCol == leftBorderCol && gameView.mapManager.layout.finishTileRow == bottomBorderRow)) {
            gameView.mapManager.nextLayout();
        }
    }

    public void checkIfArtifact(Entity player) {
        int topBorderRow = (player.worldY + player.collisionArea.y) / gameView.SCALED_TILE_SIZE;
        int bottomBorderRow = (player.worldY + gameView.SCALED_TILE_SIZE) / gameView.SCALED_TILE_SIZE;
        int leftBorderCol = (player.worldX + player.collisionArea.x) / gameView.SCALED_TILE_SIZE;
        int rightBorderCol = (player.worldX + gameView.SCALED_TILE_SIZE - player.collisionArea.x) / gameView.SCALED_TILE_SIZE;

        if(gameView.mapManager.layout.firstLayer[topBorderRow][leftBorderCol].hasArtefact) {
            gameView.mapManager.layout.firstLayer[topBorderRow][leftBorderCol].hasArtefact = false;
            gameView.artifactCount++;
        }
        else if(gameView.mapManager.layout.firstLayer[topBorderRow][rightBorderCol].hasArtefact) {
            gameView.mapManager.layout.firstLayer[topBorderRow][rightBorderCol].hasArtefact = false;
            gameView.artifactCount++;
        }
        else if(gameView.mapManager.layout.firstLayer[bottomBorderRow][leftBorderCol].hasArtefact) {
            gameView.mapManager.layout.firstLayer[bottomBorderRow][leftBorderCol].hasArtefact = false;
            gameView.artifactCount++;
        }
        else if(gameView.mapManager.layout.firstLayer[bottomBorderRow][rightBorderCol].hasArtefact) {
            gameView.mapManager.layout.firstLayer[bottomBorderRow][rightBorderCol].hasArtefact = false;
            gameView.artifactCount++;
        }
    }
}
