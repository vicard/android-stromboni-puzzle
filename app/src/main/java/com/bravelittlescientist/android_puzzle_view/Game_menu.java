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




    public void launchChat(View v){
        final CharSequence myList[] = { "2", "3", "4","5","6" };
        // Intent i = new Intent(this, PuzzleActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choix du nombre de pièces");
        builder.setSingleChoiceItems(myList,-1,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                setGameDifficulty(myList[arg1].toString());

            }
        });
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getBaseContext(), PuzzleActivity.class);
                Bundle extras = new Bundle();
                extras.putString("chosenFile","chat.jpg");
                extras.putString("gameDifficulty",getGameDifficulty());
                i.putExtras(extras);
                startActivity(i);
                finish();
            }
        });

        builder.show();

    }
    public void launchCheval(View v){
        final CharSequence myList[] = { "2", "3", "4","5","6" };
        // Intent i = new Intent(this, PuzzleActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choix du nombre de pièces");
        builder.setSingleChoiceItems(myList,-1,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                setGameDifficulty(myList[arg1].toString());

            }
        });
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getBaseContext(), PuzzleActivity.class);
                Bundle extras = new Bundle();
                extras.putString("chosenFile","cheval.jpg");
                extras.putString("gameDifficulty",getGameDifficulty());
                i.putExtras(extras);
                startActivity(i);
                finish();
            }
        });

        builder.show();

    }
    public void launchChien(View v){
        final CharSequence myList[] = { "2", "3", "4","5","6" };
        // Intent i = new Intent(this, PuzzleActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choix du nombre de pièces");
        builder.setSingleChoiceItems(myList,-1,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                setGameDifficulty(myList[arg1].toString());

            }
        });
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getBaseContext(), PuzzleActivity.class);
                Bundle extras = new Bundle();
                extras.putString("chosenFile","chien.jpg");
                extras.putString("gameDifficulty",getGameDifficulty());
                i.putExtras(extras);
                startActivity(i);
                finish();
            }
        });

        builder.show();

    }
    public void launchOiseau(View v){
        final CharSequence myList[] = { "2", "3", "4","5","6" };
        // Intent i = new Intent(this, PuzzleActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choix du nombre de pièces");
        builder.setSingleChoiceItems(myList,-1,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                setGameDifficulty(myList[arg1].toString());

            }
        });
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getBaseContext(), PuzzleActivity.class);
                Bundle extras = new Bundle();
                extras.putString("chosenFile","elephant.jpg");
                extras.putString("gameDifficulty",getGameDifficulty());
                i.putExtras(extras);
                startActivity(i);

                finish();

            }
        });

        builder.show();

    }

    public void launchChoice(View v) {
        final CharSequence myList[] = { "2", "3", "4","5","6" };
        // Intent i = new Intent(this, PuzzleActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choix du nombre de pièces");
        builder.setSingleChoiceItems(myList,-1,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                setGameDifficulty(myList[arg1].toString());

            }
        });
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getBaseContext(), FileExplore.class);
                Bundle extras = new Bundle();
                extras.putString("gameDifficulty",getGameDifficulty());
                i.putExtras(extras);
                startActivity(i);
                finish();
            }
        });
        builder.show();

    }

    public void launchCanard(View v) {
        final CharSequence myList[] = { "2", "3", "4","5","6" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choix du nombre de pièces");
        builder.setSingleChoiceItems(myList,-1,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                setGameDifficulty(myList[arg1].toString());

            }
        });
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getBaseContext(), PuzzleActivity.class);
                Bundle extras = new Bundle();
                extras.putString("chosenFile","canard.jpg");
                extras.putString("gameDifficulty",getGameDifficulty());
                i.putExtras(extras);
                startActivity(i);

                finish();
            }
        });



        builder.show();
    }

}
