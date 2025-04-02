package com.example.textformatter;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private TextView outputLabel;
    private RadioGroup colorRadioGroup;
    private CheckBox boldCheckBox, italicCheckBox, underlineCheckBox;
    private Button displayButton, clearButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        outputLabel = findViewById(R.id.outputLabel);
        colorRadioGroup = findViewById(R.id.colorRadioGroup);
        boldCheckBox = findViewById(R.id.boldCheckBox);
        italicCheckBox = findViewById(R.id.italicCheckBox);
        underlineCheckBox = findViewById(R.id.underlineCheckBox);
        displayButton = findViewById(R.id.displayButton);
        clearButton = findViewById(R.id.clearButton);
        exitButton = findViewById(R.id.exitButton);

        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFormattedText();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayFormattedText() {

        String name = nameEditText.getText().toString().trim();

        if (name.isEmpty()) {
            outputLabel.setText("Please enter your name");
            return;
        }

        String message = "Hello, " + name + "!";

        SpannableString spannableString = new SpannableString(message);

        // Check if underline is selected
        if (underlineCheckBox.isChecked()) {
            spannableString.setSpan(new UnderlineSpan(), 0, message.length(), 0);
        }

        // Set the formatted text to the label
        outputLabel.setText(spannableString);

        // bold and/or italic if selected
        int typefaceStyle = Typeface.NORMAL;
        if (boldCheckBox.isChecked() && italicCheckBox.isChecked()) {
            typefaceStyle = Typeface.BOLD_ITALIC;  // both
        } else if (boldCheckBox.isChecked()) {
            typefaceStyle = Typeface.BOLD;
        } else if (italicCheckBox.isChecked()) {
            typefaceStyle = Typeface.ITALIC;
        }
        outputLabel.setTypeface(null, typefaceStyle);

        // color based on  radio button
        int selectedColorId = colorRadioGroup.getCheckedRadioButtonId();
        if (selectedColorId == R.id.redRadioButton) {
            outputLabel.setTextColor(Color.RED);
        } else if (selectedColorId == R.id.greenRadioButton) {
            outputLabel.setTextColor(Color.GREEN);
        } else if (selectedColorId == R.id.blueRadioButton) {
            outputLabel.setTextColor(Color.BLUE);
        } else {
            outputLabel.setTextColor(Color.BLACK);
        }
    }

    private void clearAll() {
        nameEditText.setText("");

        outputLabel.setText("");

        outputLabel.setTypeface(null, Typeface.NORMAL);
        outputLabel.setTextColor(Color.BLACK);

        boldCheckBox.setChecked(false);
        italicCheckBox.setChecked(false);
        underlineCheckBox.setChecked(false);

        colorRadioGroup.clearCheck();
    }
}