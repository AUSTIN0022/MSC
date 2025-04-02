package com.example.stringoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private EditText editTextOutput;
    private RadioGroup radioGroupOperations;
    private Button buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        editTextOutput = findViewById(R.id.editTextOutput);
        radioGroupOperations = findViewById(R.id.radioGroupOperations);
        buttonClick = findViewById(R.id.buttonClick);

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performStringOperation();
            }
        });
    }

    private void performStringOperation() {

        String inputString = editTextInput.getText().toString().trim();

        if (inputString.isEmpty()) {
            Toast.makeText(this, "Please enter a string", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedRadioButtonId = radioGroupOperations.getCheckedRadioButtonId();

        if (selectedRadioButtonId == -1) {
            Toast.makeText(this, "Please select an operation", Toast.LENGTH_SHORT).show();
            return;
        }

        String result = "";
        if (selectedRadioButtonId == R.id.radioUppercase) {
            result = inputString.toUpperCase();
        } else if (selectedRadioButtonId == R.id.radioLowercase) {
            result = inputString.toLowerCase();
        } else if (selectedRadioButtonId == R.id.radioRight5) {
            if (inputString.length() >= 5) {
                result = inputString.substring(inputString.length() - 5);
            } else {
                result = inputString;
                Toast.makeText(this, "String has less than 5 characters", Toast.LENGTH_SHORT).show();
            }
        } else if (selectedRadioButtonId == R.id.radioLeft5) {
            if (inputString.length() >= 5) {
                result = inputString.substring(0, 5);
            } else {
                result = inputString;
                Toast.makeText(this, "String has less than 5 characters", Toast.LENGTH_SHORT).show();
            }
        }

        editTextOutput.setText(result);
    }
}