package model.objects.phisics;

import view.Block;

import java.awt.Point;

public class CoinCollection {

    public static boolean playerCoin(Point p, Block b) {
        return b.contains(p);
    }
}
