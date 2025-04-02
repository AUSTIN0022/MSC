package com.example.armstrong;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText numberInput;
    private Button checkButton;
    private TextView resultLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberInput = findViewById(R.id.numberInput);
        checkButton = findViewById(R.id.checkButton);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkArmstrong();
            }
        });
    }

    private void checkArmstrong() {

        String input = numberInput.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int number = Integer.parseInt(input);

            if (isArmstrong(number)) {
                Toast.makeText(this, number + " is an Armstrong number!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, number + " is Not an Armstrong number!", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter a valid integer.");
        }
    }

    private boolean isArmstrong(int num) {
        
        String numStr = String.valueOf(num);
        int n = numStr.length(); // Number of digits
        int sum = 0;
        int originalNumber = num;

        while (num > 0) {
            int digit = num % 10;
            sum += Math.pow(digit, n);
            num /= 10;
        }

        return sum == originalNumber;
    }
}