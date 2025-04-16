package com.example.listviewapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText inputText, searchText;
    Button insertButton, deleteButton, searchButton;
    ListView listView;

    ArrayList<String> itemList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        searchText = findViewById(R.id.searchText);
        insertButton = findViewById(R.id.insertButton);
        deleteButton = findViewById(R.id.deleteButton);
        searchButton = findViewById(R.id.searchButton);
        listView = findViewById(R.id.listView);

        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        insertButton.setOnClickListener(v -> {
            String item = inputText.getText().toString();
            if (!item.isEmpty()) {
                itemList.add(item);
                adapter.notifyDataSetChanged();
                inputText.setText("");
            }
        });

        deleteButton.setOnClickListener(v -> {
            String item = inputText.getText().toString();
            if (itemList.contains(item)) {
                itemList.remove(item);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show();
            }
        });

        searchButton.setOnClickListener(v -> {
            String query = searchText.getText().toString();
            if (itemList.contains(query)) {
                int position = itemList.indexOf(query);
                listView.setSelection(position);
                Toast.makeText(this, "Item found at position: " + position, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
