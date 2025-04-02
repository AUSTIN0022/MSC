package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText displayEditText;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnEquals, btnClear;

    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operation = "";
    private boolean isOperationClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the display
        displayEditText = findViewById(R.id.displayEditText);

        // Initialize number buttons
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        // Initialize operation buttons
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnClear = findViewById(R.id.btnClear);

        // Set click listeners for number buttons
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        // Set click listeners for operation buttons
        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        // Handle number buttons
        if (viewId == R.id.btn0) {
            appendToDisplay("0");
        } else if (viewId == R.id.btn1) {
            appendToDisplay("1");
        } else if (viewId == R.id.btn2) {
            appendToDisplay("2");
        } else if (viewId == R.id.btn3) {
            appendToDisplay("3");
        } else if (viewId == R.id.btn4) {
            appendToDisplay("4");
        } else if (viewId == R.id.btn5) {
            appendToDisplay("5");
        } else if (viewId == R.id.btn6) {
            appendToDisplay("6");
        } else if (viewId == R.id.btn7) {
            appendToDisplay("7");
        } else if (viewId == R.id.btn8) {
            appendToDisplay("8");
        } else if (viewId == R.id.btn9) {
            appendToDisplay("9");
        }
        // Handle operation buttons
        else if (viewId == R.id.btnAdd) {
            performOperation("+");
        } else if (viewId == R.id.btnSubtract) {
            performOperation("-");
        } else if (viewId == R.id.btnMultiply) {
            performOperation("*");
        } else if (viewId == R.id.btnDivide) {
            performOperation("/");
        } else if (viewId == R.id.btnEquals) {
            calculateResult();
        } else if (viewId == R.id.btnClear) {
            clearDisplay();
        }
    }

    private void appendToDisplay(String value) {
        if (isOperationClicked) {
            displayEditText.setText(value);
            isOperationClicked = false;
        } else {
            String currentText = displayEditText.getText().toString();
            if (currentText.equals("0")) {
                displayEditText.setText(value);
            } else {
                displayEditText.setText(currentText + value);
            }
        }
    }

    private void performOperation(String op) {
        try {
            firstNumber = Double.parseDouble(displayEditText.getText().toString());
            operation = op;
            isOperationClicked = true;
        } catch (Exception e) {
            displayEditText.setText("Error");
        }
    }

    private void calculateResult() {
        if (!operation.isEmpty()) {
            try {
                secondNumber = Double.parseDouble(displayEditText.getText().toString());
                double result = 0;

                switch (operation) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber == 0) {
                            displayEditText.setText("Cannot divide by zero");
                            return;
                        }
                        result = firstNumber / secondNumber;
                        break;
                }

                // Format result to avoid unnecessary decimal places
                if (result == (long) result) {
                    displayEditText.setText(String.valueOf((long) result));
                } else {
                    displayEditText.setText(String.valueOf(result));
                }

                // Reset operation
                operation = "";

            } catch (Exception e) {
                displayEditText.setText("Error");
            }
        }
    }

    private void clearDisplay() {
        displayEditText.setText("0");
        firstNumber = 0;
        secondNumber = 0;
        operation = "";
        isOperationClicked = false;
    }
}