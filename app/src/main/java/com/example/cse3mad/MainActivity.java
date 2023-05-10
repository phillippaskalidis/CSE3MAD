package com.example.cse3mad;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput, dobInput, heightInput, weightInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the input fields by their IDs
        nameInput = findViewById(R.id.nameInput);
        dobInput = findViewById(R.id.dobInput);
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);



        // Find the button by its ID and set a click listener
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start the second activity
                Intent intent = new Intent(MainActivity.this, SecondSetupActivity.class);

                // Put the input fields as extras in the intent
                intent.putExtra("name", nameInput.getText().toString());
                intent.putExtra("dob", dobInput.getText().toString());
                intent.putExtra("height", heightInput.getText().toString());
                intent.putExtra("weight", weightInput.getText().toString());

                // Start the second activity
                startActivity(intent);
            }
        });
    }
}
