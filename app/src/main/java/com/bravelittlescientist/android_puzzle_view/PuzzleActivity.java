package com.bravelittlescientist.android_puzzle_view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PuzzleActivity extends Activity {

    private PuzzleCompactSurface puzzleSurface;

    private static final String TAG = "PuzzleActivity";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle config = ExampleJigsawConfigurations.getRcatKittenExample();

        puzzleSurface = new PuzzleCompactSurface(this);
        JigsawPuzzle jigsawPuzzle = new JigsawPuzzle(this, config);
        puzzleSurface.setPuzzle(jigsawPuzzle);

        setContentView(puzzleSurface);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent i2 = new Intent(this, LaunchActivity.class);
        startActivity(i2);
        System.exit(0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        puzzleSurface.getThread().saveState(outState);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
