package com.example.expensetrackerapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ExpenseActivity extends AppCompatActivity {

    private TextView expenseListTextView;
    private DatabaseHelper databaseHelper;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        EdgeToEdge.enable(this);

        home = findViewById(R.id.home);
        expenseListTextView = findViewById(R.id.text1);
        databaseHelper = new DatabaseHelper(this);

        home.setOnClickListener(v -> {
            Intent i = new Intent(ExpenseActivity.this, MainActivity.class);
            startActivity(i);
        });

        updateExpenseData();

        Button addExpenseButton = findViewById(R.id.addexpense);
        addExpenseButton.setOnClickListener(v -> {
            Intent intent = new Intent(ExpenseActivity.this, AddExpenseActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateExpenseData();
    }

    private void updateExpenseData() {
        Cursor cursor = databaseHelper.getAllExpenses();
        StringBuilder expenseList = new StringBuilder();
        while (cursor.moveToNext()) {
            double amount = cursor.getDouble(cursor.getColumnIndexOrThrow("amount"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            String timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
            expenseList.append("Amount: ").append(amount).append(", Description: ").append(description).append(", Time: ").append(timestamp).append("\n");
        }
        expenseListTextView.setText(expenseList.toString());
}
}