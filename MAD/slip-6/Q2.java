package com.example.location;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button searchbtn = findViewById(R.id.button);
        EditText location = findViewById(R.id.location);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locationName = location.getText().toString();
                if (!locationName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter a Location", Toast.LENGTH_SHORT).show();
                }
                searchLocation(locationName);
            }
        });
    }
    private void searchLocation(String locationName) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+Uri.encode(locationName));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if(mapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(mapIntent);
        }
        else{
            Uri webUri = Uri.parse("https://www.google.com/maps/search/"+Uri.encode(locationName));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
            startActivity(webIntent);
        }
    }
}