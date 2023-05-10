package com.example.cse3mad;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare your UI components here
    private EditText nameInput;
    private EditText dobInput;
    private EditText heightInput;
    private EditText weightInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your UI components here
        nameInput = findViewById(R.id.nameInput);
        dobInput = findViewById(R.id.dobInput);
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the next button click here
                // Navigate to the next screen
                Button nextButton = findViewById(R.id.nextButton);
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get the user input
                        String name = nameInput.getText().toString();
                        String dob = dobInput.getText().toString();
                        String height = heightInput.getText().toString();
                        String weight = weightInput.getText().toString();

                        // Create an Intent to start the SecondSetupActivity
                        Intent intent = new Intent(MainActivity.this, SecondSetupActivity.class);

                        // Pass the user input to the SecondSetupActivity
                        intent.putExtra("name", name);
                        intent.putExtra("dob", dob);
                        intent.putExtra("height", height);
                        intent.putExtra("weight", weight);

                        // Start the SecondSetupActivity
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
