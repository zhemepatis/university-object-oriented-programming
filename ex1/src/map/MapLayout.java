package map;

import main.SpriteSheet;

import java.io.*;
import java.util.Scanner;

public class MapLayout {
    public int rows, cols;
    public Tile[][] firstLayer;
    public SpriteSheet spriteSheet = new SpriteSheet("./res/world/sprite.png", 24, 24, 1);
    public SpriteSheet artefactSpriteSheet = new SpriteSheet("./res/world/artefact_sprite.png", 32, 32, 1);

    public int startTileRow, startTileCol;
    public int finishTileRow, finishTileCol;
    public int artifactNum = 0;

    public MapLayout(String path) {
        loadLayout(path);
    }

    public MapLayout(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        firstLayer = new Tile[rows][cols];

        this.startTileRow = 1;
        this.startTileCol = 1;
        this.finishTileRow = rows-2;
        this.finishTileCol = cols-2;

        initLayout();
    }

    public void loadLayout(String path) {
        try {
            File inFile = new File(path);
            Scanner inReader = new Scanner(inFile);

            // loading player starting position
            String[] metaDataLine = inReader.nextLine().split(";");
            startTileRow = Integer.parseInt(metaDataLine[0]);
            startTileCol = Integer.parseInt(metaDataLine[1]);

            // loading finish tile
            metaDataLine = inReader.nextLine().split(";");
            finishTileRow = Integer.parseInt(metaDataLine[0]);
            finishTileCol = Integer.parseInt(metaDataLine[1]);

            // loading map dimensions
            metaDataLine = inReader.nextLine().split(";");
            rows = Integer.parseInt(metaDataLine[0]);
            cols = Integer.parseInt(metaDataLine[1]);

            // loading map layout
            firstLayer = new Tile[rows][cols];

            for(int i = 0; i < rows; ++i) {
                String[] line = inReader.nextLine().split(";");
                for(int j = 0; j < cols; ++j) {
                    int currTileNum = Integer.parseInt(line[j]);
                    firstLayer[i][j] = new Tile(currTileNum);
                }
            }

            while(inReader.hasNextLine()) {
                metaDataLine = inReader.nextLine().split(";");
                int artifactRow = Integer.parseInt(metaDataLine[0]);
                int artifactCol = Integer.parseInt(metaDataLine[1]);

                firstLayer[artifactRow][artifactCol].hasArtefact = true;
                artifactNum++;
            }

            inReader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void initLayout() {
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                firstLayer[i][j] = new Tile(spriteSheet.DEFAULT_TILE_NUM+1);
                if(i == 0 || j == 0 || i == rows-1 || j == cols-1)
                    firstLayer[i][j].isRespondable = true;
            }
        }
    }

    public void saveLayout(String path) {
        File outFile;
         try {
            outFile = new File(path);
            outFile.createNewFile();

            FileWriter writer = new FileWriter(path);

            // printing layout meta data
            writer.write(startTileRow + ";" + startTileCol + ";" + '\n');
            writer.write(finishTileRow + ";" + finishTileCol + ";" + '\n');
            writer.write(rows + ";" + cols + ";" + '\n');

            // printing layout
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    int currTileNum = (firstLayer[i][j].isRespondable) ? -1*(firstLayer[i][j].tileNum+1) : firstLayer[i][j].tileNum+1;
                    writer.write(currTileNum + ";");
                }
                writer.write('\n');
            }

             for (int i = 0; i < rows; ++i) {
                 for (int j = 0; j < cols; ++j) {
                     if (firstLayer[i][j].hasArtefact) {
                         writer.write(i + ";" + j + ";");
                         writer.write('\n');
                     }
                 }
             }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String generateNewFilename() {
        String path = "./res/world/layouts/";

        File folder = new File(path);
        File[] filesList = folder.listFiles();

        if(filesList.length == 0) {
            path += "000" + ".csv";
            return path;
        }

        String lastFileName = filesList[filesList.length-1].getName().substring(0, 3);
        if(Integer.parseInt(lastFileName) < 10)
            path += "00" + (Integer.parseInt(lastFileName)+1) + ".csv";
        else if(Integer.parseInt(lastFileName) < 100) {
            path += "0" + (Integer.parseInt(lastFileName)+1) + ".csv";
        }

        return path;
    }
}
