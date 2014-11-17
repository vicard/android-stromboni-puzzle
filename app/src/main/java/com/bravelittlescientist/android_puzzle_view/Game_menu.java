package com.bravelittlescientist.android_puzzle_view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class Game_menu extends Activity {

    public String getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(String gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    private String gameDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        gameDifficulty="2";
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
        extras.putString("gameDifficulty",getGameDifficulty());
        i.putExtras(extras);
        startActivity(i);
        finish();
    }
    public void launchCheval(View v){
        Intent i = new Intent(this, PuzzleActivity.class);
        Bundle extras = new Bundle();
        extras.putString("chosenFile","cheval.jpg");
        extras.putString("gameDifficulty",getGameDifficulty());
        i.putExtras(extras);
        startActivity(i);
        finish();
    }
    public void launchChien(View v){
        Intent i = new Intent(this, PuzzleActivity.class);
        Bundle extras = new Bundle();
        extras.putString("chosenFile","chien.jpg");
        extras.putString("gameDifficulty",getGameDifficulty());
        i.putExtras(extras);
        startActivity(i);
        finish();
    }
    public void launchOiseau(View v){
        Intent i = new Intent(this, PuzzleActivity.class);
        Bundle extras = new Bundle();
        extras.putString("chosenFile","oiseau.jpg");
        extras.putString("gameDifficulty",getGameDifficulty());
        i.putExtras(extras);
        startActivity(i);

        finish();
    }

    public void launchChoice(View v) {
        Intent i = new Intent(this, FileExplore.class);
        Bundle extras = new Bundle();
        extras.putString("gameDifficulty",getGameDifficulty());
        i.putExtras(extras);
        startActivity(i);
        finish();
    }

    public void launchDifficulte(View v) {
        final CharSequence myList[] = { "2", "3", "4","5","6" };
        // Intent i = new Intent(this, PuzzleActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(myList,-1,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                setGameDifficulty(myList[arg1].toString());

            }
        });
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }

}
