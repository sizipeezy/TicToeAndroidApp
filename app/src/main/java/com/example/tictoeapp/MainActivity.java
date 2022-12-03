package com.example.tictoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button [] buttons = new Button[9];
    private  Button resetGame;

    private  int playerOneScoreCount, playerTwoScoreCount, rountCount;
    boolean isActive;

    //p1 => 0
    //p2 => 1
    //empty => 2
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions = {
            {0, 1, 2}, {3,4,5}, {6,7,8}, //rows
            {0, 3, 6}, {1,4,7}, {2,5,8},//columns
            {0, 4, 8}, {2,4, 6} //cross
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);

        resetGame = (Button)  findViewById(R.id.resetGame);

        for (int i =0; i < buttons.length; i++) {
            String buttonId = "btn_" + i;
            int resourceId = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceId);
            buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Log.i("test", "button is clicked");
    }
}