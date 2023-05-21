package com.example.cse3mad;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {
    private TextView helloText;
    private TextView overviewText;
    private ProgressBar caloriesProgressBar;
    private ProgressBar waterProgressBar;
    private ProgressBar activityProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Retrieve the input values from the intent extras
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String dob = intent.getStringExtra("dob");
        String height = intent.getStringExtra("height");
        String weight = intent.getStringExtra("weight");
        double goalWeight = intent.getDoubleExtra("goalWeight", 0.0);
        boolean buildMuscle = intent.getBooleanExtra("buildMuscle", false);
        int activityIntensity = intent.getIntExtra("activityIntensity", 0);

        // Find the welcomeText TextView by its ID
        helloText = findViewById(R.id.helloText);

        // Update the welcome message with the user's name
        String welcomeMessage = "Hello, " + name;
        helloText.setText(welcomeMessage);

        // Find views by their ids
        helloText = findViewById(R.id.helloText);
        overviewText = findViewById(R.id.overview);
        caloriesProgressBar = findViewById(R.id.caloriesProgressBar);
        waterProgressBar = findViewById(R.id.waterProgressBar);
        activityProgressBar = findViewById(R.id.activityProgressBar);

        // Set progress values for the progress bars
        caloriesProgressBar.setProgress(70);
        waterProgressBar.setProgress(50);
        activityProgressBar.setProgress(80);

        // Call the CalculateGoal method if input values are valid
        if (validateInput(weight, height, dob)) {
            double weightValue = Double.parseDouble(weight);
            double heightValue = Double.parseDouble(height);
            int dobValue = Integer.parseInt(dob);
            CalculateGoal(weightValue, heightValue, dobValue, goalWeight, buildMuscle, activityIntensity);
        } else {
            Toast.makeText(this, "Invalid input. Please enter valid weight, height, and date of birth.", Toast.LENGTH_SHORT).show();
        }

        // Set up the buttons
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UpdateActivityIntent = new Intent(HomeActivity.this, UpdateActivity.class);
                startActivity(UpdateActivityIntent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UpdateWaterIntent = new Intent(HomeActivity.this, UpdateWater.class);
                startActivity(UpdateWaterIntent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UpdateCalorieIntent = new Intent(HomeActivity.this, UpdateCalorie.class);
                startActivity(UpdateCalorieIntent);
            }
        });
    }

    private boolean validateInput(String weight, String height, String dob) {
        return !TextUtils.isEmpty(weight) && !TextUtils.isEmpty(height) && !TextUtils.isEmpty(dob);
    }

    public void CalculateGoal(double weight, double height, int age, double goalWeight, boolean buildMuscle, int intensity) {
        // Calculate the required calories for each day
        double requiredCalories = 66 + (6.2 * weight) + (12.7 * height) - (6.67 * age);

        // Check if the goal is to lose weight and build muscle
        if (goalWeight < weight && buildMuscle) {
            // Set the macros for losing weight and building muscle
            double proteinGoal = 0.3 * requiredCalories;
            double fatGoal = 0.3 * requiredCalories;
            double carbsGoal = 0.4 * requiredCalories;
        }
        // Check if the goal is to gain weight and build muscle
        else if (goalWeight > weight && buildMuscle) {
            // Set the macros for gaining weight and building muscle
            double proteinGoal = 0.4 * requiredCalories;
            double fatGoal = 0.2 * requiredCalories;
            double carbsGoal = 0.4 * requiredCalories;
        }
        // Check if the goal is to lose weight without building muscle
       // else if (goalWeight < weight && !buildMuscle) {
            // Set the macros for losing weight without building muscle
       //     double proteinGoal = 0.35 * requiredCalories;
       //     double fatGoal = 0.25 * requiredCalories;
      //      double carbsGoal = 0.4 * requiredCalories;
     //   }
        // Check if the goal is to gain weight without building muscle
       // else if (goalWeight > weight && !buildMuscle) {
            // Set the macros for gaining weight without building muscle
        //    double proteinGoal = 0.3 * requiredCalories;
       //     double fatGoal = 0.35 * requiredCalories;
       //     double carbsGoal = 0.35 * requiredCalories;
      //  }

        // Adjust the calorie goal based on the intensity of exercise
     //   if (intensity == 2) {
       //     requiredCalories += 200; // High intensity: add 200 calories
     //   } else if (intensity == 1) {
       //     requiredCalories += 100; // Medium intensity: add 100 calories
     //   }

        // Start UpdateCalorie activity
        Intent updateCalorieIntent = new Intent(HomeActivity.this, UpdateCalorie.class);
        // Pass the required calories to the UpdateCalorie activity
        updateCalorieIntent.putExtra("requiredCalories", requiredCalories);
        startActivity(updateCalorieIntent);
    }
}
