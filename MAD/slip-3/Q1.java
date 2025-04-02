package com.example.armstrong;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        resultLabel = findViewById(R.id.resultLabel);

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
            resultLabel.setText("Please enter a number");
            return;
        }

        try {
            int number = Integer.parseInt(input);

            if (isArmstrong(number)) {
                resultLabel.setText(number + " is an Armstrong number!");
            } else {
                resultLabel.setText(number + " is NOT an Armstrong number!");
            }

        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter a valid integer.");
        }
    }

    private boolean isArmstrong(int num) {
        
        String numStr = String.valueOf(num);
        int n = numStr.length();
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