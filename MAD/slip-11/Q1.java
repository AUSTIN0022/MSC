package com.example.calculation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private Button buttonPower, buttonAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonPower = findViewById(R.id.buttonPower);
        buttonAverage = findViewById(R.id.buttonAverage);

        // Set click listener for Power button
        buttonPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePower();
            }
        });

        // Set click listener for Average button
        buttonAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAverage();
            }
        });
    }

    private void calculatePower() {
        try {
            // Get input from EditText fields
            String input1 = editTextNumber1.getText().toString().trim();
            String input2 = editTextNumber2.getText().toString().trim();

            // Check if inputs are empty
            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse input to doubles
            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);

            // Calculate power
            double result = Math.pow(number1, number2);

            // Start ResultActivity with calculation result
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("OPERATION", "Power");
            intent.putExtra("NUMBER1", number1);
            intent.putExtra("NUMBER2", number2);
            startActivity(intent);

        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric)
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void calculateAverage() {
        try {
            // Get input from EditText fields
            String input1 = editTextNumber1.getText().toString().trim();
            String input2 = editTextNumber2.getText().toString().trim();

            // Check if inputs are empty
            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse input to doubles
            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);

            // Calculate average
            double result = (number1 + number2) / 2;

            // Start ResultActivity with calculation result
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("OPERATION", "Average");
            intent.putExtra("NUMBER1", number1);
            intent.putExtra("NUMBER2", number2);
            startActivity(intent);

        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric)
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}package com.example.calculation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private Button buttonPower, buttonAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonPower = findViewById(R.id.buttonPower);
        buttonAverage = findViewById(R.id.buttonAverage);

        // Set click listener for Power button
        buttonPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePower();
            }
        });

        // Set click listener for Average button
        buttonAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAverage();
            }
        });
    }

    private void calculatePower() {
        try {
            // Get input from EditText fields
            String input1 = editTextNumber1.getText().toString().trim();
            String input2 = editTextNumber2.getText().toString().trim();

            // Check if inputs are empty
            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse input to doubles
            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);

            // Calculate power
            double result = Math.pow(number1, number2);

            // Start ResultActivity with calculation result
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("OPERATION", "Power");
            intent.putExtra("NUMBER1", number1);
            intent.putExtra("NUMBER2", number2);
            startActivity(intent);

        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric)
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void calculateAverage() {
        try {
            // Get input from EditText fields
            String input1 = editTextNumber1.getText().toString().trim();
            String input2 = editTextNumber2.getText().toString().trim();

            // Check if inputs are empty
            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse input to doubles
            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);

            // Calculate average
            double result = (number1 + number2) / 2;

            // Start ResultActivity with calculation result
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("OPERATION", "Average");
            intent.putExtra("NUMBER1", number1);
            intent.putExtra("NUMBER2", number2);
            startActivity(intent);

        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric)
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}package com.example.calculation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private Button buttonPower, buttonAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonPower = findViewById(R.id.buttonPower);
        buttonAverage = findViewById(R.id.buttonAverage);

        // Set click listener for Power button
        buttonPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePower();
            }
        });

        // Set click listener for Average button
        buttonAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAverage();
            }
        });
    }

    private void calculatePower() {
        try {
            // Get input from EditText fields
            String input1 = editTextNumber1.getText().toString().trim();
            String input2 = editTextNumber2.getText().toString().trim();

            // Check if inputs are empty
            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse input to doubles
            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);

            // Calculate power
            double result = Math.pow(number1, number2);

            // Start ResultActivity with calculation result
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("OPERATION", "Power");
            intent.putExtra("NUMBER1", number1);
            intent.putExtra("NUMBER2", number2);
            startActivity(intent);

        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric)
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void calculateAverage() {
        try {
            // Get input from EditText fields
            String input1 = editTextNumber1.getText().toString().trim();
            String input2 = editTextNumber2.getText().toString().trim();

            // Check if inputs are empty
            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse input to doubles
            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);

            // Calculate average
            double result = (number1 + number2) / 2;

            // Start ResultActivity with calculation result
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("OPERATION", "Average");
            intent.putExtra("NUMBER1", number1);
            intent.putExtra("NUMBER2", number2);
            startActivity(intent);

        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric)
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}package com.example.calculation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private Button buttonPower, buttonAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonPower = findViewById(R.id.buttonPower);
        buttonAverage = findViewById(R.id.buttonAverage);

        // Set click listener for Power button
        buttonPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePower();
            }
        });

        // Set click listener for Average button
        buttonAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAverage();
            }
        });
    }

    private void calculatePower() {
        try {
            // Get input from EditText fields
            String input1 = editTextNumber1.getText().toString().trim();
            String input2 = editTextNumber2.getText().toString().trim();

            // Check if inputs are empty
            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse input to doubles
            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);

            // Calculate power
            double result = Math.pow(number1, number2);

            // Start ResultActivity with calculation result
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("OPERATION", "Power");
            intent.putExtra("NUMBER1", number1);
            intent.putExtra("NUMBER2", number2);
            startActivity(intent);

        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric)
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void calculateAverage() {
        try {
            // Get input from EditText fields
            String input1 = editTextNumber1.getText().toString().trim();
            String input2 = editTextNumber2.getText().toString().trim();

            // Check if inputs are empty
            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse input to doubles
            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);

            // Calculate average
            double result = (number1 + number2) / 2;

            // Start ResultActivity with calculation result
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("OPERATION", "Average");
            intent.putExtra("NUMBER1", number1);
            intent.putExtra("NUMBER2", number2);
            startActivity(intent);

        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric)
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}package com.example.calculation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Number1, Number2;
    private Button buttonPower, buttonAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Number1 = findViewById(R.id.editTextNumber1);
        Number2 = findViewById(R.id.editTextNumber2);
        buttonPower = findViewById(R.id.buttonPower);
        buttonAverage = findViewById(R.id.buttonAverage);


        buttonPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePower();
            }
        });

        buttonAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAverage();
            }
        });
    }

    private void calculatePower() {
        try {
            String input1 = Number1.getText().toString().trim();
            String input2 = Number2.getText().toString().trim();

            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);

            double result = Math.pow(number1, number2);

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("OPERATION", "Power");
            intent.putExtra("NUMBER1", number1);
            intent.putExtra("NUMBER2", number2);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void calculateAverage() {
        try {
            String input1 = Number1.getText().toString().trim();
            String input2 = Number2.getText().toString().trim();

            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            double number1 = Double.parseDouble(input1);
            double number2 = Double.parseDouble(input2);

            double result = (number1 + number2) / 2;

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("OPERATION", "Average");
            intent.putExtra("NUMBER1", number1);
            intent.putExtra("NUMBER2", number2);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}


/*
 <activity
            android:name=".ResultActivity"
            android:exported="false" />
*/