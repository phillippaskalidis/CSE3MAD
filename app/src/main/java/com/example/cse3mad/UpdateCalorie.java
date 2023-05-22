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
    private EditText ETCalo, ETProtein, ETCarbs, ETFat;
    private String caloriesValue, proteinValue, carbsValue, fatValue;
    private Double requiredProtein, requiredFat, requiredCarbs, requiredCalories,
            Protein, Fat, Carbs, Calories, CaloriesInput, PInput, CInput, FInput;

    public void CalculateMacros(Double MealCalories, Double MealCarbs, Double MealProtein, Double MealFat) {
        Double ProteinRatio = MealProtein / 100;
        Double FatRatio = MealFat / 100;
        Double CarbsRatio = MealCarbs / 100;

        Double PResult = MealCalories * ProteinRatio;
        Double FResult = MealCalories * FatRatio;
        Double CResult = MealCalories * CarbsRatio;

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
        ETFat = findViewById(R.id.CInput);
        ETCarbs = findViewById(R.id.FInput);

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
                caloriesValue = ETCalo.getText().toString().trim();
                proteinValue = ETProtein.getText().toString().trim();
                carbsValue = ETCarbs.getText().toString().trim();
                fatValue = ETFat.getText().toString().trim();

                if (caloriesValue.isEmpty() || proteinValue.isEmpty() || carbsValue.isEmpty() || fatValue.isEmpty()) {
                    Toast.makeText(UpdateCalorie.this, "Please enter values for all macros", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    CaloriesInput = Double.parseDouble(caloriesValue);
                    PInput = Double.parseDouble(proteinValue);
                    CInput = Double.parseDouble(carbsValue);
                    FInput = Double.parseDouble(fatValue);

                    Intent HomeIntent = getIntent();
                    String requiredCaloriesString = HomeIntent.getStringExtra("requiredCalories");
                    String requiredCarbsString = HomeIntent.getStringExtra("requiredCarbs");
                    String requiredProteinString = HomeIntent.getStringExtra("requiredProtein");
                    String requiredFatString = HomeIntent.getStringExtra("requiredFat");

                    requiredCalories = Double.parseDouble(requiredCaloriesString);
                    requiredCarbs = Double.parseDouble(requiredCarbsString);
                    requiredFat = Double.parseDouble(requiredFatString);
                    requiredProtein = Double.parseDouble(requiredProteinString);

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
