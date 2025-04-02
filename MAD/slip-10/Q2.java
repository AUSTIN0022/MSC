package com.example.spinnerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText itemEditText;
    private Button addButton;
    private Button removeButton;
    private Spinner itemSpinner;
    private ArrayList<String> itemList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemEditText = findViewById(R.id.itemEditText);
        addButton = findViewById(R.id.addButton);
        removeButton = findViewById(R.id.removeButton);
        itemSpinner = findViewById(R.id.itemSpinner);

        // Initialize the item list and adapter
        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = itemEditText.getText().toString().trim();
                if (!newItem.isEmpty()) {
                    // Add item to list and notify adapter
                    itemList.add(newItem);
                    adapter.notifyDataSetChanged();
                    itemEditText.setText(""); 
                    Toast.makeText(MainActivity.this, "Item added: " + newItem, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter an item", Toast.LENGTH_SHORT).show();
                }
            }
        });


        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemList.size() > 0) {
                    String selectedItem = itemSpinner.getSelectedItem().toString();
                    itemList.remove(selectedItem);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Item removed: " + selectedItem, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No items to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Spinner item selection 
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
}