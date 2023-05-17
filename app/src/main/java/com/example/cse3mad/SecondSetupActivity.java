package com.example.cse3mad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

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

        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Home activity
                Intent intent = new Intent(SecondSetupActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
