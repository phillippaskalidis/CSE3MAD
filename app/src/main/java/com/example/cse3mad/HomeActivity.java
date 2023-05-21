


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

    private Button nextPageButton;
    private TextView helloText;
    private TextView overviewText;
    private ProgressBar caloriesProgressBar;
    private ProgressBar waterProgressBar;
    private ProgressBar activityProgressBar;

    private Double height, weight,  goalWeight ,  requiredCalories, proteinGoal, fatGoal, carbsGoal; ;
    private int age, intensity ;
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
        caloriesProgressBar.setProgress(70);
        waterProgressBar.setProgress(50);
        activityProgressBar.setProgress(80);



        // Find the nextPageButton by its ID
        nextPageButton = findViewById(R.id.nextPageButton);
        nextPageButton.setOnClickListener(v -> {
            // Retrieve the input values from the intent extras
            Intent intent1 = getIntent();
            String name = intent1.getStringExtra("name");
            String dob = intent1.getStringExtra("dob");
            String heightString = intent1.getStringExtra("height");
            String weightString = intent1.getStringExtra("weight");
            String goalWeightString = intent1.getStringExtra("goalWeight");
            buildMuscle = intent1.getBooleanExtra("buildMuscle", false);
            activityIntensityString = intent1.getStringExtra("activityLevel");

            weight = Double.parseDouble(weightString);
            height = Double.parseDouble(heightString);
            goalWeight = Double.parseDouble(goalWeightString);
            age = Integer.parseInt(dob) ;


            setActivityIntensity();
            CalculateActivityGoal();
            // Start HomeActivityTwo
            Intent homeTwoIntent = new Intent(HomeActivity.this, HomeActivityTwo.class);
            // Pass the input values to the Home activity
            homeTwoIntent.putExtra("name", name);
            homeTwoIntent.putExtra("dob", dob);
            homeTwoIntent.putExtra("height", height);
            homeTwoIntent.putExtra("weight", weight);
            startActivity(homeTwoIntent);

            // Start HomeActivityTwo
            Intent updateCalorieIntent = new Intent(HomeActivity.this, HomeActivityTwo.class);
            // Pass the input values to the Home activity
            homeTwoIntent.putExtra("RequiredCalories", requiredCalories);
            startActivity(updateCalorieIntent);

        });
    }


    public void setActivityIntensity()
    {
        if(Objects.equals(activityIntensityString, "High"))
        {
            intensity = 3;
        } else if(Objects.equals(activityIntensityString, "Moderate"))
        {
            intensity = 2;
        } else
        {
            intensity = 1;
        }
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
        else if ((goalWeight < weight) && !buildMuscle) {
            // Set the macros for losing weight without building muscle
             proteinGoal = 0.35 * requiredCalories;
             fatGoal = 0.25 * requiredCalories;
             carbsGoal = 0.4 * requiredCalories;
        }
        // Check if the goal is to gain weight without building muscle
        else // ((goalWeight > weight) && !buildMuscle)
            // Set the macros for gaining weight without building muscle
        {
            proteinGoal = 0.3 * requiredCalories;
            fatGoal = 0.35 * requiredCalories;
            carbsGoal = 0.35 * requiredCalories;
        }

        // Adjust the calorie goal based on the intensity of exercise
        if (intensity == 2) {
            requiredCalories += 200; // High intensity: add 200 calories
        } else if (intensity == 1) {
            requiredCalories += 100; // Medium intensity: add 100 calories
        }

    }
}
