package com.example.tictactoe;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class TicTacToe extends AppCompatActivity  {

    TextView tv_vs;
    String tv,tv_s;
    TextView tv_status;
    //    0 - x
//    1 - o
//    2 - null
    boolean gameActive = true;
    int activePlayer = 0;
    int [] gameStatus = {2,2,2,2,2,2,2,2,2};
    int count = 0;
    int flag = 0;
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void onTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(gameStatus[tappedImage]==2){
            count++;
            gameStatus[tappedImage] = activePlayer;
            if(activePlayer==0){
                activePlayer = 1;
                img.setImageResource(R.drawable.x);
                tv_s= getIntent().getStringExtra("p2") +" your turn - Tap to play :)";
            }
            else{
                activePlayer = 0;
                img.setImageResource(R.drawable.o);
                tv_s= getIntent().getStringExtra("p1") +" your turn - Tap to play :)";
            }
            tv_status = findViewById(R.id.tv_status);
            tv_status.setText(tv_s);
        }
        for(int[] winPosition : winPositions) {
            if (gameStatus[winPosition[0]] == gameStatus[winPosition[1]] && gameStatus[winPosition[1]] == gameStatus[winPosition[2]] && gameStatus[winPosition[0]] != 2) {
                flag = 1;
                String winStr;
                gameActive = false;
                if (gameStatus[winPosition[0]] == 0)
                    winStr = "congratulations to " + getIntent().getStringExtra("p1") + " - you won the game!!!";
                else
                    winStr = "congratulations to " + getIntent().getStringExtra("p2") + " - you won the game!!!";
                tv_status = findViewById(R.id.tv_status);
                tv_status.setText(winStr);
                Toast.makeText(getApplicationContext(), "To restart the game - tap the restart button.", Toast.LENGTH_LONG).show();
            }
        }
        if(count==9 && flag==0)
        {
            tv_status = findViewById(R.id.tv_status);
            Toast.makeText(getApplicationContext(), "Tap restart button to restart the game.", Toast.LENGTH_LONG).show();
            String md = "Game draw !!!";
            tv_status.setText(md);
        }
    }
    public void gameReset(View view){
        tv_vs = findViewById(R.id.tv_vs);
        tv =getIntent().getStringExtra("p1")+" vs "+getIntent().getStringExtra("p2");
        tv_vs.setText(tv);

        if(activePlayer==0) {
            activePlayer = 1;
            tv_status = findViewById(R.id.tv_status);
            tv_s = getIntent().getStringExtra("p2") + " your turn - tap to play :)";
        }
        else {
            activePlayer = 0;
            tv_status = findViewById(R.id.tv_status);
            tv_s = getIntent().getStringExtra("p1") + " your turn - tap to play :)";
        }
        tv_status.setText((tv_s));
        count = 0;
        flag = 0;
        gameActive = true;
        gameStatus = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        ((ImageView)findViewById(R.id.iv0)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv1)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv2)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv3)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv4)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv5)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv6)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv7)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv8)).setImageResource(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        tv_vs = findViewById(R.id.tv_vs);
        tv =getIntent().getStringExtra("p1")+" vs "+getIntent().getStringExtra("p2");
        tv_vs.setText(tv);
        tv_status = findViewById(R.id.tv_status);
        tv_s = getIntent().getStringExtra("p1")+" your turn - tap to play :)";
        tv_status.setText((tv_s));
        Intent backbitten = new Intent(this,MainActivity.class);
        Button back = findViewById(R.id.b_back);
        back.setOnClickListener(view -> startActivity((backbitten)));
        Button restart = findViewById(R.id.b_restart);
        restart.setOnClickListener(this::gameReset);
    }
}