package map;

public class Tile {
    public int tileNum;
    public boolean isRespondable;
    public boolean hasArtefact = false;

    public Tile(int tileNum) {
        this.tileNum = Math.abs(tileNum)-1;
        this.isRespondable = tileNum < 0;
    }
}
