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
        String welcomeMessage = "Hello, " + name ;
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
}

