package com.bravelittlescientist.android_puzzle_view;

import android.os.Bundle;

public final class ExampleJigsawConfigurations {

    public static Bundle getRcatKittenExample() {
        Bundle config = new Bundle();

        Bundle board = new Bundle();
        Bundle grid = new Bundle();
        Bundle frus = new Bundle();
        Bundle pieces = new Bundle();
        Bundle img = new Bundle();
        Bundle scores = new Bundle();

        board.putInt("w", 800);
        board.putInt("h", 600);
        board.putInt("minScale", 1);
        board.putInt("maxScale", 10);

        grid.putInt("x", 200);
        grid.putInt("y", 150);
        grid.putInt("ncols", 4); //Redimensionne automatiquement l'image en fonction du nombre de colonnes et de lignes
        grid.putInt("nrows", 3); //Redimensionne automatiquement l'image en fonction du nombre de colonnes et de lignes
        grid.putInt("cellw", 200);
        grid.putInt("cellh", 200);

        frus.putInt("x", 0);
        frus.putInt("y", 0);
        frus.putInt("scale", 1);
        frus.putString("w", null);
        frus.putString("h", null);


        img.putString("img_url", "http://ics.uci.edu/~tdebeauv/rCAT/diablo_1MB.jpg");
        img.putInt("img_local", R.drawable.chien);
        img.putInt("img_w", 1600);
        img.putInt("img_h", 1200);

        // Pieces
        Bundle p;
        String key;
        for (int h = 0; h < grid.getInt("nrows"); h++) {
            for (int w = 0; w < grid.getInt("ncols"); w++) {
                key = "piece_" + String.valueOf(w) + String.valueOf(h);
                p = new Bundle();
                p.putString("l", "-1");
                p.putString("pid", key);
                p.putBoolean("b", false);
                p.putInt("x", w*(img.getInt("img_w")/grid.getInt("ncols")));
                p.putInt("y", h*(img.getInt("img_h")/grid.getInt("nrows")));
                p.putInt("r", h);
                p.putInt("c", w);
                pieces.putBundle(key, p);
            }
        }

        config.putBundle("board", board);
        config.putBundle("grid", grid);
        config.putBundle("frus", frus);
        config.putBundle("pieces", pieces);
        config.putString("myId", "player1234-uid");
        config.putBundle("img", img);
        config.putBundle("scores", scores);

        return config;
    }

    private ExampleJigsawConfigurations () {
        throw new AssertionError();
    }
}
