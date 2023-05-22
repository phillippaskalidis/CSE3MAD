package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateWater extends AppCompatActivity {

    private Button doneBtn, cancelBtn;
    private EditText ETWater;
    private Spinner waterSpinner;
    private String waterValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_water);

        doneBtn = findViewById(R.id.Done_Button);
        cancelBtn = findViewById(R.id.Cancel_Button);
        ETWater = findViewById(R.id.WaterInput);
        waterSpinner = findViewById(R.id.waterSpinner);

        // Define the options for the spinner
        String[] waterOptions = {"500ml", "1L", "1.5L", "2L"};

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, waterOptions);

        // Set the layout style for the dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the spinner
        waterSpinner.setAdapter(adapter);

        // Set the spinner item selection listener
        waterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the selected water option
                String selectedWater = waterOptions[position];

                // You can perform any additional actions based on the selected item here
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle the case where no item is selected
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(UpdateWater.this, HomeActivity.class);
                startActivity(cancel);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waterValue = ETWater.getText().toString();

                if (waterValue.isEmpty()) {
                    Toast.makeText(UpdateWater.this, "Please enter a value for water intake", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double waterIntake = Double.parseDouble(waterValue.trim());

                    // Get the selected water option
                    String selectedWater = waterSpinner.getSelectedItem().toString();

                    // Update the waterIntake based on the selectedWater
                    if (selectedWater.equals("500 ml")) {
                        waterIntake = 0.5;
                    } else if (selectedWater.equals("1L")) {
                        waterIntake = 1.0;
                    } else if (selectedWater.equals("1.5L")) {
                        waterIntake = 1.5;
                    } else if (selectedWater.equals("2L")) {
                        waterIntake = 2.0;
                    }

                    Intent updateWaterIntent = new Intent(UpdateWater.this, HomeActivity.class);
                    updateWaterIntent.putExtra("WaterIntake", waterIntake);
                    startActivity(updateWaterIntent);
                } catch (NumberFormatException e) {
                    Toast.makeText(UpdateWater.this, "Invalid water intake value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
