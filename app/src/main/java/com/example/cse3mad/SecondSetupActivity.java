package com.example.cse3mad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class SecondSetupActivity extends AppCompatActivity {
    private TextView goalWeightLabel, buildMuscleLabel, loseFatLabel, activityLevelLabel, activityFrequencyLabel;
    private EditText goalWeightInput;
    private RadioButton buildMuscleYes, buildMuscleNo, loseFatYes, loseFatNo;
    private Spinner activityLevelDropdown, activityFrequencyDropdown;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        goalWeightLabel = findViewById(R.id.goalWeightLabel);
        buildMuscleLabel = findViewById(R.id.buildMuscleLabel);
        loseFatLabel = findViewById(R.id.loseFatLabel);
        activityLevelLabel = findViewById(R.id.activityLevelLabel);
        activityFrequencyLabel = findViewById(R.id.activityFrequencyLabel);

        goalWeightInput = findViewById(R.id.goalWeightInput);

        buildMuscleYes = findViewById(R.id.buildMuscleYes);
        buildMuscleNo = findViewById(R.id.buildMuscleNo);

        loseFatYes = findViewById(R.id.loseFatYes);
        loseFatNo = findViewById(R.id.loseFatNo);

        activityLevelDropdown = findViewById(R.id.activityLevelDropdown);
        activityFrequencyDropdown = findViewById(R.id.activityFrequencyDropdown);

        // Set up the dropdown options for activity level
        ArrayAdapter<CharSequence> activityLevelAdapter = ArrayAdapter.createFromResource(
                this, R.array.activity_level_options, android.R.layout.simple_spinner_item);
        activityLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityLevelDropdown.setAdapter(activityLevelAdapter);

        // Set up the dropdown options for activity frequency
        ArrayAdapter<CharSequence> activityFrequencyAdapter = ArrayAdapter.createFromResource(
                this, R.array.activity_frequency_options, android.R.layout.simple_spinner_item);
        activityFrequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityFrequencyDropdown.setAdapter(activityFrequencyAdapter);

        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the input values from the intent extras
                Intent intent = getIntent();
                String name = intent.getStringExtra("name");
                String dob = intent.getStringExtra("dob");
                String height = intent.getStringExtra("height");
                String weight = intent.getStringExtra("weight");
                String goalWeight = goalWeightInput.getText().toString();
                boolean buildMuscle = buildMuscleYes.isChecked();
                boolean loseFat = loseFatYes.isChecked();
                String activityLevel = activityLevelDropdown.getSelectedItem().toString();
                String activityFrequency = activityFrequencyDropdown.getSelectedItem().toString();

                // Create an intent to start the Home activity
                Intent homeIntent = new Intent(SecondSetupActivity.this, HomeActivity.class);

                // Pass the input values to the Home activity
                homeIntent.putExtra("name", name);
                homeIntent.putExtra("dob", dob);
                homeIntent.putExtra("height", height);
                homeIntent.putExtra("weight", weight);
                homeIntent.putExtra("goalWeight", goalWeight);
                homeIntent.putExtra("buildMuscle", buildMuscle);
                homeIntent.putExtra("loseFat", loseFat);
                homeIntent.putExtra("activityLevel", activityLevel);
                homeIntent.putExtra("activityFrequency", activityFrequency);

                // Start the Home activity
                startActivity(homeIntent);
            }
        });
    }
}