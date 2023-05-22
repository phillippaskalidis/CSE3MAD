package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateWater extends AppCompatActivity {

    private Button doneBtn, cancelBtn;
    private EditText ETWater;
    private String waterValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_water);

        doneBtn = findViewById(R.id.Done_Button);
        cancelBtn = findViewById(R.id.Cancel_Button);
        ETWater = findViewById(R.id.WaterInput);

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
