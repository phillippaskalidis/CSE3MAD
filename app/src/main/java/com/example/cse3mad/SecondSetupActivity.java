package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class SecondSetupActivity extends AppCompatActivity {

    private RadioGroup radioGroupBuildMuscle, radioGroupLoseFat;
    private Spinner spinnerActivityLevel, spinnerActivityFrequency;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize views
        radioGroupBuildMuscle = findViewById(R.id.radioGroupBuildMuscle);
        radioGroupLoseFat = findViewById(R.id.radioGroupLoseFat);
        spinnerActivityLevel = findViewById(R.id.spnActivityLevel);
        spinnerActivityFrequency = findViewById(R.id.spnActivityFrequency);
        buttonDone = findViewById(R.id.btnDone);

        // Set click listener for Done button
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from views
                String buildMuscle = ((RadioButton) findViewById(radioGroupBuildMuscle.getCheckedRadioButtonId())).getText().toString();
                String loseFat = ((RadioButton) findViewById(radioGroupLoseFat.getCheckedRadioButtonId())).getText().toString();
                String activityLevel = spinnerActivityLevel.getSelectedItem().toString();
                String activityFrequency = spinnerActivityFrequency.getSelectedItem().toString();

                // Create intent to return data to previous activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("buildMuscle", buildMuscle);
                resultIntent.putExtra("loseFat", loseFat);
                resultIntent.putExtra("activityLevel", activityLevel);
                resultIntent.putExtra("activityFrequency", activityFrequency);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
