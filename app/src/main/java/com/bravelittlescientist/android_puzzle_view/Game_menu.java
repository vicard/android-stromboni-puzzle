package com.bravelittlescientist.android_puzzle_view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Game_menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchChat(View v){
        Intent i = new Intent(this, PuzzleActivity.class);
        Bundle extras = new Bundle();
        extras.putString("chosenFile","chat.jpg");
        i.putExtras(extras);
        startActivity(i);
        finish();
    }
    public void launchCheval(View v){
        Intent i = new Intent(this, PuzzleActivity.class);
        Bundle extras = new Bundle();
        extras.putString("chosenFile","cheval.jpg");
        i.putExtras(extras);
        startActivity(i);
        finish();
    }
    public void launchChien(View v){
        Intent i = new Intent(this, PuzzleActivity.class);
        Bundle extras = new Bundle();
        extras.putString("chosenFile","chien.jpg");
        i.putExtras(extras);
        startActivity(i);
        finish();
    }
    public void launchOiseau(View v){
        Intent i = new Intent(this, PuzzleActivity.class);
        Bundle extras = new Bundle();
        extras.putString("chosenFile","oiseau.jpg");
        i.putExtras(extras);
        startActivity(i);

        finish();
    }

    public void launchChoice(View v) {
        // Intent i = new Intent(this, PuzzleActivity.class);
        Intent i = new Intent(this, FileExplore.class);
        startActivity(i);
        finish();
    }

}
