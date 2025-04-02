package com.example.bank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.R;

public class MainActivity extends AppCompatActivity {

    private EditText etAccountNumber;
    private RadioButton rbChecking, rbSavings;
    private TextView tvBalanceDisplay;
    private Button btnCreate, btnBalance, btnDeposit, btnWithdraw;

    private boolean accountCreated = false;
    private String accountNumber = "";
    private String accountType = "";
    private double balance = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAccountNumber = findViewById(R.id.etAccountNumber);
        rbChecking = findViewById(R.id.rbChecking);
        rbSavings = findViewById(R.id.rbSavings);
        tvBalanceDisplay = findViewById(R.id.tvBalanceDisplay);
        btnCreate = findViewById(R.id.btnCreate);
        btnBalance = findViewById(R.id.btnBalance);
        btnDeposit = findViewById(R.id.btnDeposit);
        btnWithdraw = findViewById(R.id.btnWithdraw);

        btnCreate.setOnClickListener(v -> createAccount());
        btnBalance.setOnClickListener(v -> checkBalance());
        btnDeposit.setOnClickListener(v -> showTransactionDialog(true));
        btnWithdraw.setOnClickListener(v -> showTransactionDialog(false));
    }

    private void createAccount() {
        String accNumber = etAccountNumber.getText().toString().trim();
        if (accNumber.isEmpty()) {
            Toast.makeText(this, "Please enter an account number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!rbChecking.isChecked() && !rbSavings.isChecked()) {
            Toast.makeText(this, "Please select an account type", Toast.LENGTH_SHORT).show();
            return;
        }

        accountNumber = accNumber;
        accountType = rbChecking.isChecked() ? "Checking" : "Savings";
        balance = 0.0;
        accountCreated = true;

        Toast.makeText(this, accountType + " account created successfully", Toast.LENGTH_SHORT).show();
        tvBalanceDisplay.setText("Account created. Initial balance: $0.00");
    }

    private void checkBalance() {
        if (!accountCreated) {
            Toast.makeText(this, "Please create an account first", Toast.LENGTH_SHORT).show();
            return;
        }

        tvBalanceDisplay.setText("Current balance: $" + String.format("%.2f", balance));
    }

    private void showTransactionDialog(boolean isDeposit) {
        if (!accountCreated) {
            Toast.makeText(this, "Please create an account first", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(isDeposit ? "Deposit Amount" : "Withdraw Amount");

        final EditText input = new EditText(this);
        input.setHint("Enter amount");
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String amountStr = input.getText().toString().trim();
            if (amountStr.isEmpty()) {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                Toast.makeText(this, "Amount must be greater than zero", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isDeposit) {
                deposit(amount);
            } else {
                withdraw(amount);
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void deposit(double amount) {
        balance += amount;
        Toast.makeText(this, "$" + String.format("%.2f", amount) + " deposited successfully", Toast.LENGTH_SHORT).show();
        tvBalanceDisplay.setText("New balance: $" + String.format("%.2f", balance));
    }

    private void withdraw(double amount) {
        if (balance < amount) {
            Toast.makeText(this, "Insufficient balance", Toast.LENGTH_SHORT).show();
            return;
        }

        balance -= amount;
        Toast.makeText(this, "$" + String.format("%.2f", amount) + " withdrawn successfully", Toast.LENGTH_SHORT).show();
        tvBalanceDisplay.setText("New balance: $" + String.format("%.2f", balance));
    }
}
