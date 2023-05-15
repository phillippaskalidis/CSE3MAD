package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCalorie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_calorie);

        // declaring variables & Buttons and setting them to the relevant views by Id
        Button doneBtn = findViewById(R.id.Done_Button);
        Button cancelBtn = findViewById(R.id.Cancel_Button);
        EditText ETTotalCalories = (EditText) findViewById(R.id.eTTotalCal);
        EditText ETProtein = (EditText) findViewById(R.id.eTProtein);
        EditText ETFat = (EditText) findViewById(R.id.eTFat);
        EditText ETCarbs = (EditText) findViewById(R.id.eTCarbs);


        //doneBtn will save all the changes made to then display on first activity
        doneBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {


                //do some calculations


                //save the new calculated variable into the variable in the main



                //Toast will let the user know when the changes have been updated
                Toast.makeText(UpdateCalorie.this, " Calories updated! ", Toast.LENGTH_SHORT).show();
            }

        });


    }

    //Method to calculate the macros of each meal that will then
    public void CalculateMacros(String MealCalories, String MealCarbs, String MealProtein, String MealFat)
    {

    }
    }
