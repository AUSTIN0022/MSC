package com.example.phonenumber;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText phoneNumberInput;
    private Button validateButton;
    private TextView resultTextView;

    // Valid area codes as specified
    private final Set<String> validAreaCodes = new HashSet<>(Arrays.asList(
            "040", "041", "050", "0400", "044"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        validateButton = findViewById(R.id.validateButton);
        resultTextView = findViewById(R.id.resultTextView);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatePhoneNumber();
            }
        });


    private void validatePhoneNumber() {
        String phoneNumber = phoneNumberInput.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            resultTextView.setText("Please enter a phone number");
            return;
        }
        // Remove any spaces, dashes, or parentheses for validation
        phoneNumber = phoneNumber.replaceAll("[\\s\\-\\(\\)]", "");

        if (isValidPhoneNumber(phoneNumber)) {
            resultTextView.setText("Valid phone number");
        } else {
            resultTextView.setText("Invalid phone number");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() < 9) { // Min 3 for area code + 6 for number
            return false;
        }
        if (phoneNumber.length() > 12) { // Max 4 for area code + 8 for number
            return false;
        }

        boolean hasValidAreaCode = false;
        for (String areaCode : validAreaCodes) {
            if (phoneNumber.startsWith(areaCode)) {
                hasValidAreaCode = true;

                int remainingDigits = phoneNumber.length() - areaCode.length();
                if (remainingDigits < 6 || remainingDigits > 8) {
                    return false;
                }

                break;
            }
        }

        return hasValidAreaCode;
    }
}