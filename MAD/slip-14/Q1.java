package com.example.factorial;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    private EditText numberEditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberEditText = findViewById(R.id.numberEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFactorial();
            }
        });
    }

    private void calculateFactorial() {

        String inputStr = numberEditText.getText().toString().trim();

        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int number = Integer.parseInt(inputStr);

            if (number > 50) {
                Toast.makeText(this, "Please enter a number less than or equal to 50", Toast.LENGTH_SHORT).show();
                return;
            }

            if (number < 0) {
                Toast.makeText(this, "Factorial is not defined for negative numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            BigInteger factorial = calculateFactorialBigInteger(number);

            String resultText = number + "! = " + factorial.toString();
            resultTextView.setText(resultText);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private BigInteger calculateFactorialBigInteger(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}