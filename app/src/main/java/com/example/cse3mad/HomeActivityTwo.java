package com.example.cse3mad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        // Retrieve the name from the intent extras
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        // Find the welcomeText TextView by its ID
        TextView helloText = findViewById(R.id.helloText);

        // Update the welcome message with the user's name
        String welcomeMessage = "Hello, " + name;
        helloText.setText(welcomeMessage);

        // Set up the buttons
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UpdateActivityIntent = new Intent(HomeActivityTwo.this, UpdateActivity.class);
                startActivity(UpdateActivityIntent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UpdateWaterIntent = new Intent(HomeActivityTwo.this, UpdateWater.class);
                startActivity(UpdateWaterIntent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UpdateCalorieIntent = new Intent(HomeActivityTwo.this, UpdateCalorie.class);
                startActivity(UpdateCalorieIntent);
            }
        });
    }
}
