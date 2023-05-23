package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity  extends TicTacToe {

    EditText et_p1;
    EditText et_p2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_p1 = findViewById(R.id.et_p1);
        et_p2 = findViewById(R.id.et_p2);

        Button start = findViewById(R.id.button);
        start.setOnClickListener(view -> {
            Intent intent = new Intent(this, TicTacToe.class);
            Toast.makeText(getApplicationContext(), "Game Start!", Toast.LENGTH_SHORT).show();
             intent.putExtra("p1",et_p1.getText().toString());
             intent.putExtra("p2",et_p2.getText().toString());
            startActivity(intent);
        });
    }
}