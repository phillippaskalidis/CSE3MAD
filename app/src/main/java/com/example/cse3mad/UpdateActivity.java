package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    Button doneBtn , cancelBtn;
    Spinner intensitySpinner , activitySpinner;

    int Activity , Intensity;



    public void CalculateActivity(int IntensityInput, int ActivityInput)
    {



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // declaring variables & Buttons and setting them to the relevant views by Id
        intensitySpinner = findViewById(R.id.IntensitySpinner);
        activitySpinner = findViewById(R.id.activityTypeSpinner);

        doneBtn = findViewById(R.id.Done_Button);
        cancelBtn = findViewById(R.id.Cancel_Button);

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

                CalculateActivity(IntensityInput, ActivityInput);

                // Toast will let the user know when the changes have been updated
                Toast.makeText(UpdateActivity.this, " updated! ", Toast.LENGTH_SHORT).show();

                //intent to go into home page after updating
                Intent updateActivityIntent = new Intent(UpdateActivity.this, HomeActivity.class);

                // pass input onto home activity
                updateActivityIntent.putExtra("Activity", Activity);
                updateActivityIntent.putExtra("Intensity", Intensity);
                startActivity(updateActivityIntent);
            }
        });
    }

}