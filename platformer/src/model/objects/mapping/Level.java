package model.objects.mapping;

import io.resources.AppFileReader;
import view.Block;
import java.io.*;

public class Level {

    public static int width, height;

    public Block[][] blocks;
    String path = "res/Maps/map2.map";
    public Level(String loadPath) throws IOException {
        blocks = AppFileReader.loadMap(loadPath);
    }

    public Block[][] getBlocks() {
        return blocks;
    }
}
