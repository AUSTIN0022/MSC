package com.example.sdcardwriter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private EditText dataEditText;
    private TextView statusTextView;
    private Button writeButton, clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataEditText = findViewById(R.id.editText);
        statusTextView = findViewById(R.id.statusTextView);
        writeButton = findViewById(R.id.writeButton);
        clearButton = findViewById(R.id.clearButton);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    writeDataToSDCard();
                } else {
                    requestPermission();
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataEditText.setText("");
                statusTextView.setText("");
            }
        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                writeDataToSDCard();
            } else {
                Toast.makeText(this, "Permission Denied! Cannot write to SD Card.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void writeDataToSDCard() {
        String data = dataEditText.getText().toString();
        if (data.isEmpty()) {
            Toast.makeText(this, "Please enter data to write", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            
            File externalDir;

            // if external storage is available
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                externalDir = new File(getExternalFilesDir(null), "SDCardWriter");
            } else {
                Toast.makeText(this, "External Storage not available", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!externalDir.exists()) {
                boolean dirCreated = externalDir.mkdirs();
                if (!dirCreated) {
                    Toast.makeText(this, "Failed to create directory", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // Create & write to file
            File file = new File(externalDir, "data.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();

            statusTextView.setText("Data Written in SDCARD");
            Toast.makeText(this, "Data written to " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error writing to SD Card: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}