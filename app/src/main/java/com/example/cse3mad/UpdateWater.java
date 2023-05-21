package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import android.os.Bundle;
import android.widget.Toast;

public class UpdateWater extends AppCompatActivity {

    Button doneBtn , cancelBtn;
    Spinner waterSpinner;
    Double requiredWater , water;
    public void CalculateWater(Double waterInput)
    {
        // water represents the difference between required water and amount drank
        water = requiredWater - waterInput;
        //water will be used in the progress bar to visually represent the progress and be passed onto the home page

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_water);

        // declaring variables & Buttons and setting them to the relevant views by Id
        waterSpinner = findViewById(R.id.waterSpinner);
        doneBtn = findViewById(R.id.Done_Button);
        cancelBtn = findViewById(R.id.Cancel_Button);

        String[] waterOptions = getResources().getStringArray(R.array.amountOptions);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, waterOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        waterSpinner.setAdapter(adapter);


        Intent HomeIntent = getIntent();
        String waterGoalString = HomeIntent.getStringExtra("waterGoal");
        requiredWater = Double.parseDouble(waterGoalString);


        // cancel button will just go back to home page
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
                String WaterOption = waterSpinner.getSelectedItem().toString();
                double waterInput = Double.parseDouble(WaterOption);
                CalculateWater(waterInput);

                // Toast will let the user know when the changes have been updated
                Toast.makeText(UpdateWater.this, " updated! ", Toast.LENGTH_SHORT).show();

                //intent to go into home page after updating
                Intent updateWaterIntent = new Intent(UpdateWater.this, HomeActivity.class);

                // pass input onto home activity
                updateWaterIntent.putExtra("Water", water);
                startActivity(updateWaterIntent);
            }
        });
    }
}