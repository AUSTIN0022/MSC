package com.example.numberinput;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Number1, Number2;
    private Button buttonSubmit;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Number1 = findViewById(R.id.editTextNumber1);
        Number2 = findViewById(R.id.editTextNumber2);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewResult = findViewById(R.id.textViewResult);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processNumbers();
            }
        });
    }

    private void processNumbers() {
        try {
            String input1 = Number1.getText().toString().trim();
            String input2 = Number2.getText().toString().trim();

            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            int number1 = Integer.parseInt(input1);
            int number2 = Integer.parseInt(input2);

            if (number1 > 20 && number2 > 20) {
                Number1.setText("");
                Number2.setText("");

                Toast.makeText(MainActivity.this, "Both numbers are greater than 20. Please enter new numbers.", Toast.LENGTH_LONG).show();
                textViewResult.setText("Input rejected! Both numbers cannot be greater than 20.");
            } else {
                String resultText = "Numbers accepted: " + number1 + " and " + number2;
                textViewResult.setText(resultText);
            }
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}