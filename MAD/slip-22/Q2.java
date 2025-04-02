package com.example.numericoperations;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText numberInput;
    private RadioGroup operationGroup;
    private RadioButton oddEvenRadio;
    private RadioButton positiveNegativeRadio;
    private RadioButton squareRadio;
    private Button calculateButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberInput = findViewById(R.id.numberInput);
        operationGroup = findViewById(R.id.operationGroup);
        oddEvenRadio = findViewById(R.id.oddEvenRadio);
        positiveNegativeRadio = findViewById(R.id.positiveNegativeRadio);
        squareRadio = findViewById(R.id.squareRadio);
        calculateButton = findViewById(R.id.calculateButton);
        resultText = findViewById(R.id.resultText);

        oddEvenRadio.setChecked(true);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation();
            }
        });
    }

    private void performOperation() {

        String inputStr = numberInput.getText().toString();

        if (inputStr.isEmpty()) {
            resultText.setText("Please enter a number");
            return;
        }

        try {
            int number = Integer.parseInt(inputStr);
            int selectedId = operationGroup.getCheckedRadioButtonId();

            if (selectedId == R.id.oddEvenRadio) {
                checkOddEven(number);
            } else if (selectedId == R.id.positiveNegativeRadio) {
                checkPositiveNegative(number);
            } else if (selectedId == R.id.squareRadio) {
                checkSquare(number);
            }
        } catch (NumberFormatException e) {
            resultText.setText("Invalid input. Please enter a valid number.");
        }
    }

    private void checkOddEven(int number) {
        if (number % 2 == 0) {
            resultText.setText("Ans : " + number + " is Even");
        } else {
            resultText.setText("Ans : " + number + " is Odd");
        }
    }

    private void checkPositiveNegative(int number) {
        if (number > 0) {
            resultText.setText("Ans : " + number + " is Positive");
        } else if (number < 0) {
            resultText.setText("Ans : " + number + " is Negative");
        } else {
            resultText.setText("Ans : " + number + " is Zero (neither positive nor negative)");
        }
    }

    private void checkSquare(int number) {
        double square = number * number;
        resultText.setText("Ans : " + number + " Square is " + square);
    }
}