package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {

    private Button doneBtn , cancelBtn;
    private Spinner intensitySpinner , activitySpinner;

    private String DurationInput;
    private int  intensity, age;

    private Double Duration, weight, RequiredDeficit, CurrentActivity;

    private String activityType, activityIntensity;


    public void setActivityIntensity()
    {
        if(Objects.equals(activityIntensity, "High"))
        {
            intensity = 3;
        } else if(Objects.equals(activityIntensity, "Moderate"))
        {
            intensity = 2;
        } else
        {
            intensity = 1;
        }
    }
    public void CalculateCardio()
    {
        int MHR, HR,BPM;
        Double CaloriesPerMinute, CaloriesBurnt;

        //MHR stands for max heart rate, its calculated this way.
        MHR = 220 - age;

        // converting intensity from array string options to numbers for simplicity
        if(intensity == 1) {
            // HR stands for Heart Rate, it is set to a negative number as  the MHR is the max heart rate,
            // when Intensity is low the heart rate will be less than max by 100 BPM
            HR = -100;
            BPM = MHR + HR;
        } else if (intensity == 2) {
            HR = -70;
            BPM =  MHR + HR;
        } else {
            HR = -40;
            BPM =  MHR + HR;
        }

        //formula for amount of calories burned per minute
        CaloriesPerMinute =  (((((-20.4022 + (0.4472 * BPM)) - (0.1263 * weight)) + (0.074 * age) )/ 4.184));
        CaloriesBurnt= CaloriesPerMinute * Duration;

        // Current Activity will be used in the progress bar to track the progress
        CurrentActivity = (RequiredDeficit - CaloriesBurnt);

    }

    public void CalculateStrength()
    {
        Double totalWeight, CaloriesBurnt;

        // converting intensity from array string options to numbers for simplicity
        // Intensity for strength training would be the amount of additional weight being used
        if(intensity == 1) {
           // Intensity 1 would be low intensity meaning just using body weight
            totalWeight = weight;

        } else if (intensity == 2) {
            // Intensity 2 would be Moderate intensity meaning body weight plus Moderate weight which would be 50kgs
            int moderateWeight = 50;
            totalWeight = (weight + moderateWeight);

        } else {
            int highWeight = 80;
            totalWeight = (weight + highWeight);
        }

        //formula for strength training : [Minutes working out] × [bodyWeight in kg] × 0.0713
        CaloriesBurnt= (Duration * totalWeight * 0.0713);
        //Current Activity will be used in the progress bar to track the progress
        CurrentActivity = (RequiredDeficit - CaloriesBurnt);


    }

    public void CalculateActivity()
    {
        if (activityType == "Strength")
        {
            CalculateStrength();
        }
        else if (activityType == "Cardio")
        {
            CalculateCardio();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // declaring variables & Buttons and setting them to the relevant views by Id
        intensitySpinner = findViewById(R.id.IntensitySpinner);
        activitySpinner = findViewById(R.id.activityTypeSpinner);
        DurationInput  = findViewById(R.id.durationInput).toString();
        doneBtn = findViewById(R.id.Done_Button);
        cancelBtn = findViewById(R.id.Cancel_Button);

        String[] IntensityOptions = getResources().getStringArray(R.array.Intensity);
        ArrayAdapter IntensityAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, IntensityOptions);
        IntensityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        intensitySpinner.setAdapter(IntensityAdapter);

        String[] ActivityTypeOptions = getResources().getStringArray(R.array.activityType);
        ArrayAdapter ActivityTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ActivityTypeOptions);
        IntensityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        activitySpinner.setAdapter(ActivityTypeAdapter);


        //Duration = 40//Double.parseDouble(DurationInput);
        //activityType ;//activitySpinner.getSelectedItem().toString();
        //activityIntensity = High//intensitySpinner.getSelectedItem().toString();


        Intent getHomeIntent = getIntent();
        String ageData = getHomeIntent.getStringExtra("dob");
        String weightData = getHomeIntent.getStringExtra("weight");
        String RequiredDeficitData = getHomeIntent.getStringExtra("calorieDeficit");


        age = Integer.parseInt(ageData);
        weight = Double.parseDouble(weightData);
        RequiredDeficit = Double.parseDouble(RequiredDeficitData);

        //cancel button will just go back to home page
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(UpdateActivity.this, HomeActivity.class);
                startActivity(cancel);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String intensityOption = intensitySpinner.getSelectedItem().toString();
                int IntensityInput = Integer.parseInt(intensityOption);

                String ActivityOption = activitySpinner.getSelectedItem().toString();
                int ActivityInput = Integer.parseInt(intensityOption);

                //CalculateActivity();

                // Toast will let the user know when the changes have been updated
                Toast.makeText(UpdateActivity.this, " updated! ", Toast.LENGTH_SHORT).show();

                //intent to go into home page after updating
                Intent updateActivityIntent = new Intent(UpdateActivity.this, HomeActivity.class);

                // pass input onto home activity
                updateActivityIntent.putExtra("activityType", activityType);
                updateActivityIntent.putExtra("Intensity", intensity);
                updateActivityIntent.putExtra("CurrentActivity", CurrentActivity);
                startActivity(updateActivityIntent);
            }
        });
    }

}