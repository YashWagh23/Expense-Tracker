package com.example.expensetrackerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddIncomeActivity extends AppCompatActivity {

    private EditText amountEditText;
    private EditText descriptionEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addincome);
        EdgeToEdge.enable(this);

        amountEditText = findViewById(R.id.amount);
        descriptionEditText = findViewById(R.id.reason);
        databaseHelper = new DatabaseHelper(this);

        Button addIncomeButton = findViewById(R.id.inc_btn);
        addIncomeButton.setOnClickListener(v -> {
            try {
                double amount = Double.parseDouble(amountEditText.getText().toString());
                String description = descriptionEditText.getText().toString();
                if (amount > 0 && !description.isEmpty()) {
                    databaseHelper.addIncome(amount, description);
                    Toast.makeText(AddIncomeActivity.this, "Income added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddIncomeActivity.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(AddIncomeActivity.this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
            }
   });
}
}