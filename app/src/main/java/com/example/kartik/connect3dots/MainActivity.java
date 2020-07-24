package com.example.kartik.connect3dots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int ActivePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;




    public void dropin(View view) {
        ImageView counter = (ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedcounter] == 2 && gameActive) {
            gameState[tappedcounter] = ActivePlayer;

            counter.setTranslationY(-1500);
            if (ActivePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                ActivePlayer = 1;


            } else {
                counter.setImageResource((R.drawable.red));
                ActivePlayer = 0;
            }


            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningposition : winningpositions) {


                if (gameState[winningposition[0]] == gameState[winningposition[1]] && gameState[winningposition[1]] == gameState[winningposition[2]] && gameState[winningposition[0]] != 2)


                {
                    gameActive = false;
                    String winner = "";
                    if (ActivePlayer == 1) {
                        winner = "Yellow";

                    } else {
                        winner = "Red";
                    }
                    Toast.makeText(this, winner + " has won.", Toast.LENGTH_SHORT).show();

                    Button PlayAgainButton = (Button) findViewById(R.id.PlayAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won.");
                    PlayAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);


                }

            }
        }
    }

        public void playagain(View view){
        Button PlayAgainButton = (Button) findViewById(R.id.PlayAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        PlayAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
            // do stuff with child view
        }

        for (int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        ActivePlayer = 0;
        gameActive = true;


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
