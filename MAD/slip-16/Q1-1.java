// SecondActivity.java
package com.example.hellointent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        messageTextView = findViewById(R.id.text_message);

        Intent intent = getIntent();

        if (intent.hasExtra("message")) {
            String message = intent.getStringExtra("message");
            messageTextView.setText("Received: " + message);
        }
    }
}