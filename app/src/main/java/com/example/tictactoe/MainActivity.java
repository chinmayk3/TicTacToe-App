package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = O, 1= X
    int activePlayer = 0;

    // 2 means unplayed position
    int[] state={2,2,2,2,2,2,2,2,2};

    //Winning possibilities
    int[][] winningPosition = {{0,1,2} ,{3,4,5},{6,7,8},{0,3,6}, {1,4,7},{2,5,8} ,{0,4,8}, {2,4,6}};

     LinearLayout mLayout;

     boolean gameisActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        mLayout = findViewById(R.id.playgainLayout);
        mLayout.setVisibility(View.GONE);

    }

    public void dropIn(View view){

        ImageView img = (ImageView) view;

        int tappedCounter = Integer.parseInt(img.getTag().toString());

        System.out.println(tappedCounter);

        if ( state[tappedCounter] == 2 && gameisActive ){

            state[tappedCounter] = activePlayer;
            img.setImageResource(R.drawable.o);
            if ( activePlayer == 0 ){
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
            }

            for (int[] winningPos: winningPosition){
                if (state[winningPos[0]] == state[winningPos[1]] &&
                        state[winningPos[1]] == state[winningPos[2]] &&
                        state[winningPos[0]] != 2){
                    System.out.println(state[winningPos[0]]);

                    gameisActive = false;

                    //Some one will win
                    String winner = "O";
                    if (state[winningPos[0]] == 0){
                        winner = "X";
                    }

                    //Setting Text
                    TextView mWinner = findViewById(R.id.winnerTextView);
                    mWinner.setText(winner+ " has won!!");

                    //Layout Show
                    mLayout.setVisibility(View.VISIBLE);
                }
            }
        } else {

            boolean gameOver = true;

            for (int countState: state){
                if (countState == 2) gameOver = false;
            }
            if (gameOver){
                //Setting Text
                TextView mWinner = findViewById(R.id.winnerTextView);
                mWinner.setText("It's a draw");
                //Layout Show
                mLayout.setVisibility(View.VISIBLE);
            }
        }

    }

    public void playAgain(View view){
        gameisActive = true;

        mLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < state.length; i++){
            state[i] = 2;
        }

        GridLayout mGridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < mGridLayout.getChildCount(); i++){
            ((ImageView)mGridLayout.getChildAt(i)).setImageResource(0);
        }
    }
}