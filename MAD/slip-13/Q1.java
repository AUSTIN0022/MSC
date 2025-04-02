package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, addressEditText, mobileEditText, ageEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.edit_name);
        addressEditText = findViewById(R.id.edit_address);
        mobileEditText = findViewById(R.id.edit_mobile);
        ageEditText = findViewById(R.id.edit_age);
        registerButton = findViewById(R.id.btn_register);

        
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndRegister();
            }
        });
    }

    private void validateAndRegister() {

        String name = nameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String ageStr = ageEditText.getText().toString().trim();

        // name
        if (name.isEmpty()) {
            nameEditText.setError("Name is required");
            nameEditText.requestFocus();
            return;
        }

        // address
        if (address.isEmpty()) {
            addressEditText.setError("Address is required");
            addressEditText.requestFocus();
            return;
        }

        //  mobile number
        if (mobile.isEmpty()) {
            mobileEditText.setError("Mobile number is required");
            mobileEditText.requestFocus();
            return;
        } else if (mobile.length() != 10) {
            mobileEditText.setError("Mobile number must be 10 digits");
            mobileEditText.requestFocus();
            return;
        } else {
            try {
                Long.parseLong(mobile); // Check if input is numeric
            } catch (NumberFormatException e) {
                mobileEditText.setError("Mobile number must contain only digits");
                mobileEditText.requestFocus();
                return;
            }
        }

        // age
        if (ageStr.isEmpty()) {
            ageEditText.setError("Age is required");
            ageEditText.requestFocus();
            return;
        } else {
            try {
                int age = Integer.parseInt(ageStr);
                if (age <= 0 || age > 120) {
                    ageEditText.setError("Please enter a valid age");
                    ageEditText.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                ageEditText.setError("Age must be a number");
                ageEditText.requestFocus();
                return;
            }
        }

        String message = "Registration successful!\n" +
                "Name: " + name + "\n" +
                "Address: " + address + "\n" +
                "Mobile: " + mobile + "\n" +
                "Age: " + ageStr;

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        clearFields();
    }

    private void clearFields() {
        nameEditText.setText("");
        addressEditText.setText("");
        mobileEditText.setText("");
        ageEditText.setText("");
        nameEditText.requestFocus();
    }
}