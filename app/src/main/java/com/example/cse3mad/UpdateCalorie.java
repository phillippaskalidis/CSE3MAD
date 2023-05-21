package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCalorie extends AppCompatActivity {

    private Button doneBtn , cancelBtn;

    // Values from layout
    private EditText ETCalo , ETProtein , ETCarbs, ETFat;
    // turn values into string value
    private String caloriesValue , proteinValue , carbsValue, fatValue;

    //the required protein is the goal
    private Double requiredProtein, requiredFat , requiredCarbs ,requiredCalories,
            Protein, Fat, Carbs, Calories, CaloriesInput, PInput , CInput, FInput ;


    //Method to calculate the macros of each meal that will then
    public void CalculateMacros(Double MealCalories, Double MealCarbs, Double MealProtein, Double MealFat)
    {
        //Getting the ratio from each category from the meal
        Double ProteinRatio = MealProtein/100;
        Double FatRatio = MealFat/100;
        Double CarbsRatio = MealCarbs/100;

        // Get macro total from the meal
        Double PResult = MealCalories * ProteinRatio;
        Double FResult = MealCalories * FatRatio;
        Double CResult = MealCalories * CarbsRatio;

        // minus the macro from that meal from the required macro amount for that day
        Protein = requiredProtein - PResult;
        Fat = requiredFat - FResult;
        Carbs = requiredCarbs - CResult;
        Calories = requiredCalories - MealCalories;

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
        CaloriesInput = Double.parseDouble(caloriesValue);
        PInput  = Double.parseDouble(proteinValue);
        CInput = Double.parseDouble(carbsValue);
        FInput = Double.parseDouble(fatValue);


        //get Data from intent
        Intent HomeIntent = getIntent();
        String requiredCaloriesString = HomeIntent.getStringExtra("requiredCalories");
        String requiredCarbsString = HomeIntent.getStringExtra("requiredCarbs");
        String requiredProteinString = HomeIntent.getStringExtra("requiredProtein");
        String requiredFatString = HomeIntent.getStringExtra("requiredFat");


        //save data to variables
        requiredCalories = Double.parseDouble(requiredCaloriesString);
        requiredCarbs = Double.parseDouble(requiredCarbsString);
        requiredFat =  Double.parseDouble(requiredFatString);
        requiredProtein  =  Double.parseDouble(requiredProteinString);

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
