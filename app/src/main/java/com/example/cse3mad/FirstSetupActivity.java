package com.example.cse3mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FirstSetupActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText dobInput;
    private EditText heightInput;
    private EditText weightInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        nameInput = findViewById(R.id.name_input);
        dobInput = findViewById(R.id.dob_input);
        heightInput = findViewById(R.id.height_input);
        weightInput = findViewById(R.id.weight_input);

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                String dob = dobInput.getText().toString();
                float height = Float.parseFloat(heightInput.getText().toString());
                float weight = Float.parseFloat(weightInput.getText().toString());

                Intent intent = new Intent(FirstSetupActivity.this, SecondSetupActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("dob", dob);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                startActivity(intent);
            }
        });
    }
}
