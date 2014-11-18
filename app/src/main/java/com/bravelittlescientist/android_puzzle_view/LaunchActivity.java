package com.bravelittlescientist.android_puzzle_view;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.io.IOException;


public class LaunchActivity extends Activity {

    // Animation
    Animation animFadein;
    private TextView title ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        title = (TextView) findViewById(R.id.title);
        // load the animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        title.startAnimation(animFadein);


    }

    public void launchPuzzle(View v) {
       // Intent i = new Intent(this, PuzzleActivity.class);
        Intent i = new Intent(this, Game_menu.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}
