package com.example.lightbulb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;
import android.widget.TextView;

import com.example.lightbulb.R;

public class MainActivity extends AppCompatActivity {

    private ImageView lightBulbImageView;
    private ToggleButton toggleButton;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightBulbImageView = findViewById(R.id.lightBulbImageView);
        toggleButton = findViewById(R.id.toggleButton);
        statusTextView = findViewById(R.id.statusTextView);

        lightBulbImageView.setImageResource(R.drawable.light_off);
        statusTextView.setText("Light is OFF");

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // When toggle is ON
                    lightBulbImageView.setImageResource(R.drawable.light_on);
                    statusTextView.setText("Light is ON");
                } else {
                    // When toggle is OFF
                    lightBulbImageView.setImageResource(R.drawable.light_off);
                    statusTextView.setText("Light is OFF");
                }
            }
        });
    }
}