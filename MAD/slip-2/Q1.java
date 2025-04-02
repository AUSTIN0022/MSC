package com.example.perfectnumber;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText numberInput;
    private Button checkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberInput = findViewById(R.id.editTextNumber);
        checkButton = findViewById(R.id.buttonCheck);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPerfectNumber();
            }
        });
    }

    private void checkPerfectNumber() {
        String input = numberInput.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int number = Integer.parseInt(input);

            if (isPerfectNumber(number)) {
                Toast.makeText(this, number + " is a Perfect Number!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, number + " is NOT a Perfect Number!", Toast.LENGTH_LONG).show();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter a valid integer.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isPerfectNumber(int number) {
        if (number <= 0) {
            return false;
        }

        int sum = 0;
        // Find all divisors and sum them
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        // If sum of all divisors equals the number, it's a perfect number
        return sum == number;
    }
}