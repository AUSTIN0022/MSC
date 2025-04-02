// ResultActivity.xml

package com.example.calculation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewOperation;
    private TextView textViewResult;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewOperation = findViewById(R.id.textViewOperation);
        textViewResult = findViewById(R.id.textViewResult);
        buttonBack = findViewById(R.id.buttonBack);

        // Get data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String operation = extras.getString("OPERATION");
            double number1 = extras.getDouble("NUMBER1");
            double number2 = extras.getDouble("NUMBER2");
            double result = extras.getDouble("RESULT");

            if (operation.equals("Power")) {
                textViewOperation.setText(number1 + " raised to the power of " + number2);
            } else {
                textViewOperation.setText("Average of " + number1 + " and " + number2);
            }

            textViewResult.setText("Result: " + result);
        }

        // Back button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}