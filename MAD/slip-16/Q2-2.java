// SecondActivity.java

package com.example.playerinfo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView playerNameTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        playerNameTextView = findViewById(R.id.text_player_name);
        backButton = findViewById(R.id.btn_back);

        Intent intent = getIntent();

        if (intent.hasExtra("PLAYER_NAME")) {
            String playerName = intent.getStringExtra("PLAYER_NAME");
            playerNameTextView.setText(playerName);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}