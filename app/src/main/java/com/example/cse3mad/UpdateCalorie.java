package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCalorie extends AppCompatActivity {

    public void CalculateMacros(int MealCalories, int MealCarbs, int MealProtein, int MealFat)
    {
        //Getting the ratio from each category from the meal
        int ProteinRatio = MealProtein/100;
        int FatRatio = MealFat/100;
        int CarbsRatio = MealCarbs/100;


        // Get macro total from the meal
        int PResult = MealCalories * ProteinRatio;
        int FResult = MealCalories * FatRatio;
        int CResult = MealCalories * CarbsRatio;


        // this will be assigned to the variable of another activity but will save in this for now
        int RequiredProtein = 0;
        int RequiredFat = 0;
        int RequiredCarbs = 0;
        int RequiredCalories =0;

        // minus the macro from that meal from the required macro amount for that day
        int Protein = RequiredProtein - PResult;
        int Fat = RequiredFat - FResult;
        int Carbs = RequiredCarbs - CResult;
        int Calories = RequiredCalories - MealCalories;





    }
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

        //convert ET to Int to use in method

        int PInput;

        //doneBtn will save all the changes made to then display on first activity
        doneBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {







                //save the new calculated variable into the variable in the main



                //Toast will let the user know when the changes have been updated
                Toast.makeText(UpdateCalorie.this, " Calories updated! ", Toast.LENGTH_SHORT).show();
            }

        });


    }

    //Method to calculate the macros of each meal that will then

    }
