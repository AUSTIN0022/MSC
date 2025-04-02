// SecondActivity.java


package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLifecycle";
    private TextView statusTextView;
    private StringBuilder lifecycleLog;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        statusTextView = findViewById(R.id.text_lifecycle_status);
        backButton = findViewById(R.id.btn_back);

        lifecycleLog = new StringBuilder();

        logLifecycleEvent("onCreate");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        Log.d(TAG, "SecondActivity: " + event);

        lifecycleLog.append("SecondActivity: ")
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