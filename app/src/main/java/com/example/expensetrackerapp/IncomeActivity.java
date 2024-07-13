package com.example.expensetrackerapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class IncomeActivity extends AppCompatActivity {

    private TextView incomeListTextView;
    private DatabaseHelper databaseHelper;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        EdgeToEdge.enable(this);

        home = findViewById(R.id.home1);
        incomeListTextView = findViewById(R.id.text);
        databaseHelper = new DatabaseHelper(this);

        home.setOnClickListener(v -> {
            Intent i = new Intent(IncomeActivity.this, MainActivity.class);
            startActivity(i);
        });

        updateIncomeData();

        Button addIncomeButton = findViewById(R.id.addincome);
        addIncomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(IncomeActivity.this, AddIncomeActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateIncomeData();
    }

    private void updateIncomeData() {
        Cursor cursor = databaseHelper.getAllIncome();
        StringBuilder incomeList = new StringBuilder();
        while (cursor.moveToNext()) {
            double amount = cursor.getDouble(cursor.getColumnIndexOrThrow("amount"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            String timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
            incomeList.append("Amount: ").append(amount).append(", Description: ").append(description).append(", Time: ").append(timestamp).append("\n");
        }
        incomeListTextView.setText(incomeList.toString());
}
}