package com.example.tictoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        rountCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        isActive = true;
    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }

        String buttonId = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonId.substring(buttonId.length() - 1, buttonId.length()));

        if(isActive){
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer] = 0;
        }
        else {
            ((Button) view).setText("O");
            ((Button) view).setTextColor(Color.parseColor("#70FFEA"));
            gameState[gameStatePointer] = 1;
        }

        rountCount++;

        if(checkWinner()){
                if(isActive){
                    playerOneScoreCount++;
                    updatePlayerScore();
                    Toast.makeText(this, "Player one Won!", Toast.LENGTH_SHORT).show();
                    playerAgain();
                }
                else{
                    playerTwoScoreCount++;
                    updatePlayerScore();
                    Toast.makeText(this, "Player two Won!", Toast.LENGTH_SHORT).show();
                    playerAgain();
                }
        }else if(rountCount == 9){
            playerAgain();
            Toast.makeText(this, "No Winner!", Toast.LENGTH_SHORT).show();
        }else{
            isActive = !isActive;
        }

        if(playerOneScoreCount > playerTwoScoreCount){
            playerStatus.setText("Player one is Winning!");
        }
        else if(playerTwoScoreCount > playerOneScoreCount){
            playerStatus.setText("Player two is Winning!");
        }
        else{
            playerStatus.setText("");
        }

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });

    }

    public boolean checkWinner(){
        boolean isWinner = false;

        for (int [] winningPoistion : winningPositions){
            if(gameState[winningPoistion[0]] == gameState[winningPoistion[1]]
                    && gameState[winningPoistion[1]] == gameState[winningPoistion[2]]
                         && gameState[winningPoistion[0]] != 2)
            {
                    isWinner =true;
            }

        }
        return isWinner;
    }

    public  void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));

    }

    public  void playerAgain() {
        rountCount =0 ;
        isActive = true;

        for (int i = 0; i< buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }
}