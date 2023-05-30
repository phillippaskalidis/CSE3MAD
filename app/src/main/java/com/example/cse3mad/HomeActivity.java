package com.example.cse3mad;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    private String name, activityIntensityString;

    private TextView helloText;
    private TextView overviewText;
    private ProgressBar caloriesProgressBar;
    private ProgressBar waterProgressBar;
    private ProgressBar activityProgressBar;

    private Double height, weight,  goalWeight ,
            requiredCalories, proteinGoal, fatGoal, carbsGoal, calorieDeficit, waterGoal;
    private int age, intensity;
    private boolean buildMuscle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Retrieve the name from the intent extras
        Intent intent = getIntent();
        name = intent.getStringExtra("name");




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
        caloriesProgressBar.setProgress(30);
        waterProgressBar.setProgress(40);
        activityProgressBar.setProgress(80);

        // Find the buttons by their IDs
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        // Set click listeners for the buttons
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UpdateWater.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UpdateCalorie.class);
                startActivity(intent);
            }
        });
    }


    public void setActivityIntensity() {
        if (Objects.equals(activityIntensityString, "High")) {
            intensity = 3;
        } else if (Objects.equals(activityIntensityString, "Moderate")) {
            intensity = 2;
        } else {
            intensity = 1;
        }
    }

    public void CalculateWater() {
        waterGoal = (35 * weight);
    }

    public void CalculateActivityGoal() {
        //goal activity is just the calories that need to be burnt everyday depending on goal
        requiredCalories = (66 + (6.2 * weight) + (12.7 * height) - (6.67 * age));
        //Check if the goal is to lose weight and build muscle

        if ((goalWeight < weight) && buildMuscle) {
            calorieDeficit = (requiredCalories * 0.20);
        }
        // Check if the goal is to gain weight and build muscle

        else if ((goalWeight > weight) && buildMuscle) {
            calorieDeficit = requiredCalories * 0.10;
        }
        // Check if the goal is to lose weight without building muscle
        //calories that need to be burned  Calorie Deficit = GoalCalorie * 0.25
        // GA = Deficit
        else if ((goalWeight < weight) && !buildMuscle) {
            calorieDeficit = (requiredCalories * 0.25);
        }
        // Check if the goal is to gain weight without building muscle
        //calories that need to be burned  Calorie Deficit = GoalCalorie * 0.05
        // GA = Deficit
        else {
            calorieDeficit = (requiredCalories * 0.05);
        }
    }

    public void CalculateGoal() {

        // Calculate the required calories for each day
        requiredCalories = (66 + (6.2 * weight) + (12.7 * height) - (6.67 * age));

        // Check if the goal is to lose weight and build muscle
        if ((goalWeight < weight) && buildMuscle) {
            // Set the macros for losing weight and building muscle
            proteinGoal = 0.3 * requiredCalories;
            fatGoal = 0.3 * requiredCalories;
            carbsGoal = 0.4 * requiredCalories;
        }
        // Check if the goal is to gain weight and build muscle
        else if ((goalWeight > weight) && buildMuscle) {
            // Set the macros for gaining weight and building muscle
            proteinGoal = 0.4 * requiredCalories;
            fatGoal = 0.2 * requiredCalories;
            carbsGoal = 0.4 * requiredCalories;
        }
        // Check if the goal is to lose weight without building muscle
     //   else if ((goalWeight < weight) && !buildMuscle) {
            // Set the macros for losing weight without building muscle
       //     proteinGoal = 0.35 * requiredCalories;
        //    fatGoal = 0.25 * requiredCalories;
        //    carbsGoal = 0.4 * requiredCalories;
        //}
        // Check if the goal is to gain weight without building muscle
      //  else // ((goalWeight > weight) && !buildMuscle)
        // Set the macros for gaining weight without building muscle
     //   {
          //  proteinGoal = 0.3 * requiredCalories;
          //  fatGoal = 0.35 * requiredCalories;
          //  carbsGoal = 0.35 * requiredCalories;
      //  }

        // Adjust the calorie goal based on the intensity of exercise
        if (intensity == 2) {
            requiredCalories += 200; // High intensity: add 200 calories
        } else if (intensity == 1) {
            requiredCalories += 100; // Medium intensity: add 100 calories
        }

    }
}
