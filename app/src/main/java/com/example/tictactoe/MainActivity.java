package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
//0-X
//1-O
    int activePlayer=0;
    int[] gameState ={2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
//    state meaning
//    0-X
//    1-0
//    2-Null
    int [][] winPositions = {
                             {0,1,2},{3,4,5},{6,7,8},
                             {0,3,6},{1,4,7},{2,5,8},
                             {0,4,8},{2,4,6}
                            };

    public void PlayerTap(View view)
    {      ImageView img = (ImageView) view;
           int tappedImage= Integer.parseInt(img.getTag().toString());
           if(!gameActive)
           { gameReset(view);
           }
           if(gameState[tappedImage]==2 & gameActive)
                {
                    gameState[tappedImage]=activePlayer;
                    img.setTranslationY(-1000f);
                    if(activePlayer==0)
                    {  img.setImageResource(R.drawable.kanta);
                       activePlayer=1;
                        TextView status =findViewById((R.id.status));
                        status.setText("O's turn -tap to play");
                    }
                    else
                    { img.setImageResource(R.drawable.zeroooo);
                        activePlayer=0;
                        TextView status =findViewById((R.id.status));
                        status.setText("X's turn -tap to play");
                    }
                    img.animate().translationYBy(1000f).setDuration(300);
                }

           // check if someone wins
        for(int[] winPosition: winPositions)
        {
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]]
            && gameState[winPosition[0]] !=2)
            {
             //someone one
             //find out who
                String winnerStr;
                gameActive=false;
             if(gameState[winPosition[0]]==0)   {
                 winnerStr=("X has won");
             }
             else{
                 winnerStr="O has win";}
             //update status bar
                TextView status =findViewById((R.id.status));
             // to give the alert
                new AlertDialog.Builder(this).setTitle(winnerStr).setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameReset(view);
                            }
                        })
                        .show();
                status.setText(" ");
            }
        }


    }

    public void gameReset(View view) {
        gameActive=true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status =findViewById((R.id.status));
        status.setText("X's turn -tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}