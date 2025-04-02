package com.example.alertbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button showAlertButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        showAlertButton = findViewById(R.id.showAlertButton);
        showAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }
    private void showAlertDialog() {
        
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alert Dialog Example");
        builder.setMessage("This is a simple Alert Dialog demonstration. Would you like to proceed?");

        // icon for the dialog (optional)
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked Yes", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked No", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        
        builder.setCancelable(false);
        
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}