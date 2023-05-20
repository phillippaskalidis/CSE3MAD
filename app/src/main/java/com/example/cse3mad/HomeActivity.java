package com.example.cse3mad;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private String nameInput;

    private TextView helloText;
    private TextView overviewText;
    private ProgressBar caloriesProgressBar;
    private ProgressBar waterProgressBar;
    private ProgressBar activityProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Retrieve the name from the intent extras
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

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

        // Call the CalculateGoal method
        CalculateGoal(70.0, 183.0, 20, 65.0, true, 1);

        // Find the nextPageButton by its ID
        Button nextPageButton = findViewById(R.id.nextPageButton);
        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the input values from the intent extras
                Intent intent = getIntent();
                String name = intent.getStringExtra("name");
                String dob = intent.getStringExtra("dob");
                String height = intent.getStringExtra("height");
                String weight = intent.getStringExtra("weight");

                // Start HomeActivityTwo
                Intent homeTwoIntent = new Intent(HomeActivity.this, HomeActivityTwo.class);
                // Pass the input values to the Home activity
                homeTwoIntent.putExtra("name", name);
                homeTwoIntent.putExtra("dob", dob);
                homeTwoIntent.putExtra("height", height);
                homeTwoIntent.putExtra("weight", weight);
                startActivity(homeTwoIntent);
            }
        });
    }

    public void CalculateActivityGoal()
    {
        //goal actvity is just the calories that need to be burnt everyday depending on goal

        //Check if the goal is to lose weight and build muscle
        //calories that need to be burned  Calorie Defiect = GoalCalorie * 0.20
        // GA = Defict

        // Check if the goal is to gain weight and build muscle
        //calories that need to be burned  Calorie Defiect = GoalCalorie * 0.10
        // GA = Defict

        // Check if the goal is to lose weight without building muscle
        //calories that need to be burned  Calorie Defiect = GoalCalorie * 0.25
        // GA = Defict

        // Check if the goal is to gain weight without building muscle
        //calories that need to be burned  Calorie Defiect = GoalCalorie * 0.05
        // GA = Defict
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
        else if (goalWeight < weight && !buildMuscle) {
            // Set the macros for losing weight without building muscle
            double proteinGoal = 0.35 * requiredCalories;
            double fatGoal = 0.25 * requiredCalories;
            double carbsGoal = 0.4 * requiredCalories;
        }
        // Check if the goal is to gain weight without building muscle
        else if (goalWeight > weight && !buildMuscle) {
            // Set the macros for gaining weight without building muscle
            double proteinGoal = 0.3 * requiredCalories;
            double fatGoal = 0.35 * requiredCalories;
            double carbsGoal = 0.35 * requiredCalories;
        }

        // Adjust the calorie goal based on the intensity of exercise
        if (intensity == 2) {
            requiredCalories += 200; // High intensity: add 200 calories
        } else if (intensity == 1) {
            requiredCalories += 100; // Medium intensity: add 100 calories
        }

    }
}
