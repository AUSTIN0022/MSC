package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLifecycle";
    private TextView statusTextView;
    private StringBuilder lifecycleLog;
    private Button launchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTextView = findViewById(R.id.text_lifecycle_status);
        launchButton = findViewById(R.id.btn_launch_second);

        lifecycleLog = new StringBuilder();

        logLifecycleEvent("onCreate");

        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        logLifecycleEvent("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logLifecycleEvent("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logLifecycleEvent("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logLifecycleEvent("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logLifecycleEvent("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logLifecycleEvent("onDestroy");
    }

    private void logLifecycleEvent(String event) {
        // Log to Android debug log
        Log.d(TAG, "MainActivity: " + event);

        // Add to our log StringBuilder
        lifecycleLog.append("MainActivity: ")
                .append(event)
                .append(" called at ")
                .append(java.text.DateFormat.getTimeInstance().format(new java.util.Date()))
                .append("\n");

        updateLifecycleDisplay();
    }

    private void updateLifecycleDisplay() {
        if (statusTextView != null) {
            statusTextView.setText(lifecycleLog.toString());
        }
    }
}