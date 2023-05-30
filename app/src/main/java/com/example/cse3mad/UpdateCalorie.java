package com.example.cse3mad;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCalorie extends AppCompatActivity {

    private Button doneBtn, cancelBtn;
    private EditText ETCalo, ETProtein ,ETFat, ETCarbs;

    private String caloriesValue, proteinValue, carbsValue, fatValue;
    private int requiredProtein , requiredFat, requiredCarbs, requiredCalories ,
            Protein, Fat, Carbs, Calories, CaloriesInput, PInput, FInput,CInput;

    public void CalculateMacros(int MealCalories, int MealCarbs, int MealProtein, int MealFat) {
        int ProteinRatio = MealProtein / 100;
        int FatRatio = MealFat / 100;
        int CarbsRatio = MealCarbs / 100;

        int PResult = MealCalories * ProteinRatio;
        int FResult = MealCalories * FatRatio;
        int CResult = MealCalories * CarbsRatio;

        Protein = requiredProtein - PResult;
        Fat = requiredFat - FResult;
        Carbs = requiredCarbs - CResult;
        Calories = requiredCalories - MealCalories;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_calorie);

        doneBtn = findViewById(R.id.Done_Button);
        cancelBtn = findViewById(R.id.Cancel_Button);
        ETCalo = findViewById(R.id.CaloriesInput);
        ETProtein = findViewById(R.id.PInput);
        ETFat = findViewById(R.id.FInput);
        ETCarbs = findViewById(R.id.CInput);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(UpdateCalorie.this, HomeActivity.class);
                startActivity(cancel);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //.trim();
                caloriesValue = ETCalo.getText().toString().trim();
                proteinValue = ETProtein.getText().toString().trim();
                carbsValue = ETCarbs.getText().toString().trim();
                fatValue = ETFat.getText().toString().trim();

                if (caloriesValue.isEmpty() || proteinValue.isEmpty() || carbsValue.isEmpty() || fatValue.isEmpty()) {
                    Toast.makeText(UpdateCalorie.this, "Please enter values for all macros", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {

                    CaloriesInput = Integer.parseInt(caloriesValue);
                    PInput = Integer.parseInt(proteinValue);
                    CInput = Integer.parseInt(carbsValue);
                    FInput = Integer.parseInt(fatValue);

                    Intent HomeIntent = getIntent();
                    String requiredCaloriesString = HomeIntent.getStringExtra("RequiredCalories");
                    String requiredCarbsString = HomeIntent.getStringExtra("RequiredCarbs");
                    String requiredProteinString = HomeIntent.getStringExtra("RequiredProtein");
                    String requiredFatString = HomeIntent.getStringExtra("RequiredFat");

                    requiredCalories = 500;
                    requiredCarbs = 50;
                    requiredFat = 25;
                    requiredProtein = 25;

                    CalculateMacros(CaloriesInput, CInput, PInput, FInput);

                    Toast.makeText(UpdateCalorie.this, "Updated!", Toast.LENGTH_SHORT).show();

                    Intent updateCaloriesIntent = new Intent(UpdateCalorie.this, HomeActivity.class);
                    updateCaloriesIntent.putExtra("Protein", Protein);
                    updateCaloriesIntent.putExtra("Fat", Fat);
                    updateCaloriesIntent.putExtra("Carbs", Carbs);
                    updateCaloriesIntent.putExtra("Calories", Calories);

                    startActivity(updateCaloriesIntent);
                } catch (NumberFormatException e) {
                    Toast.makeText(UpdateCalorie.this, "Invalid macro values", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}

