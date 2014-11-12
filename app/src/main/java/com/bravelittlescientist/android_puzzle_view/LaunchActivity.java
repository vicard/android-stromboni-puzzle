package com.bravelittlescientist.android_puzzle_view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class LaunchActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    public void launchPuzzle(View v) {
        Intent i = new Intent(this, PuzzleActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}
