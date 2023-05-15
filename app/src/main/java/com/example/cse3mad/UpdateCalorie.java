package com.example.cse3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UpdateCalorie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_calorie);

        Button doneBtn = findViewById(R.id.Done_Button);
        Button cancelBtn = findViewById(R.id.Cancel_Button);


        //doneBtn will save all the changes made to then display on first activity
        doneBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                //Toast will let the user know when the changes have been updated
                Toast.makeText(UpdateCalorie.this, " Calories updated! ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    }
