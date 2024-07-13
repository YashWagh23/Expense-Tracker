package com.example.expensetrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView totalIncomeTextView;
    private TextView totalExpenseTextView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        totalIncomeTextView = findViewById(R.id.text);

        databaseHelper = new DatabaseHelper(this);

        updateTotals();

        Button incomeButton = findViewById(R.id.income);
        incomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, IncomeActivity.class);
            startActivity(intent);
        });

        Button expenseButton = findViewById(R.id.expense);
        expenseButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExpenseActivity.class);
            startActivity(intent);
        });
    }

    private void updateTotals() {
        double totalExpense = databaseHelper.getTotalExpenses();
        double totalIncome = databaseHelper.getTotalIncome();
        double totalBalance = totalIncome - totalExpense;

        if(totalBalance >= 0){
            totalIncomeTextView.setText("₹" + totalBalance);
        }
        else{
            totalIncomeTextView.setText("₹" + 0.0);
        }

}
}