package com.example.palindrome;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText getnum;
    private Button checkButton;
    private TextView resultLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getnum = findViewById(R.id.getnum);
        checkButton = findViewById(R.id.checkButton);
        resultLabel = findViewById(R.id.resultLabel);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPalindrome();
            }
        });
    }

    private void checkPalindrome() {

        String input = getnum.getText().toString().trim();

        if (input.isEmpty()) {
            resultLabel.setText("Please enter a number");
            return;
        }

        try {
            if (isPalindrome(input)) {
                resultLabel.setText(input + " is a Palindrome number!");
            } else {
                resultLabel.setText(input + " is NOT a Palindrome number!");
            }

        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    private boolean isPalindrome(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
}