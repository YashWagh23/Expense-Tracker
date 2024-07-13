package com.example.expensetrackerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText amountEditText;
    private EditText descriptionEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);
        EdgeToEdge.enable(this);

        amountEditText = findViewById(R.id.am);
        descriptionEditText = findViewById(R.id.reason1);
        databaseHelper = new DatabaseHelper(this);

        Button addExpenseButton = findViewById(R.id.expense);
        addExpenseButton.setOnClickListener(v -> {
            try {
                double amount = Double.parseDouble(amountEditText.getText().toString());
                String description = descriptionEditText.getText().toString();
                if (amount > 0 && !description.isEmpty()) {
                    databaseHelper.addExpense(amount, description);
                    Toast.makeText(AddExpenseActivity.this, "Expense added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddExpenseActivity.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(AddExpenseActivity.this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
            }
   });
}
}