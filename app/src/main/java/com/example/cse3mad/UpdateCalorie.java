package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCalorie extends AppCompatActivity {

    Button doneBtn , cancelBtn;
    EditText ETCalo , ETProtein , ETCarbs, ETFat;
    String caloriesValue , proteinValue , carbsValue, fatValue;
    // Input values that will go into method
    int CaloriesInput, PInput , CInput, FInput;
    int Protein, Fat, Carbs, Calories;


    //Method to calculate the macros of each meal that will then
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
        Protein = RequiredProtein - PResult;
        Fat = RequiredFat - FResult;
        Carbs = RequiredCarbs - CResult;
        Calories = RequiredCalories - MealCalories;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_calorie);

        // declaring variables & Buttons and setting them to the relevant views by Id
        doneBtn = findViewById(R.id.Done_Button);
        cancelBtn = findViewById(R.id.Cancel_Button);


        ETCalo = (EditText) findViewById(R.id.CaloriesInput);
        ETProtein = (EditText) findViewById(R.id.PInput);
        ETFat = (EditText) findViewById(R.id.CInput);
        ETCarbs = (EditText) findViewById(R.id.FInput);

        //get text from edit text
        caloriesValue  = ETCalo.getText().toString();
        proteinValue = ETProtein.getText().toString();
        carbsValue = ETCarbs.getText().toString();
        fatValue = ETFat.getText().toString();

        //turn the string to an Int to then use in the method to calculate the macros of that meal
        CaloriesInput = Integer.parseInt(caloriesValue);
        PInput  = Integer.parseInt(proteinValue);
        CInput = Integer.parseInt(carbsValue);
        FInput = Integer.parseInt(fatValue);


        // cancel button will just go back to home page
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(UpdateCalorie.this, HomeActivity.class);
                startActivity(cancel);

            }
        });



        //doneBtn will save all the changes made to then display on first activity
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CalculateMacros(CaloriesInput, CInput, PInput,FInput);
                //Toast will let the user know when the changes have been updated
                Toast.makeText(UpdateCalorie.this, "updated! ", Toast.LENGTH_SHORT).show();

                // intent to go into home page after updating
                Intent updateCaloriesIntent = new Intent(UpdateCalorie.this, HomeActivity.class);

                // pass input onto home activity
                updateCaloriesIntent.putExtra("Protein", Protein);
                updateCaloriesIntent.putExtra("Fat", Fat);
                updateCaloriesIntent.putExtra("Carbs", Carbs);
                updateCaloriesIntent.putExtra("Calories", Calories);
                startActivity(updateCaloriesIntent);

            }

        });


    }

    }
