package com.ayah.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner questionCountSpinner;
    private Spinner categorySpinner;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionCountSpinner = findViewById(R.id.questionCountSpinner);
        categorySpinner = findViewById(R.id.categorySpinner);
        startButton = findViewById(R.id.startButton);

        Integer[] questionCounts = {1, 2, 3, 4};
        ArrayAdapter<Integer> countAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, questionCounts);
        countAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        questionCountSpinner.setAdapter(countAdapter);

        String[] categories = {"Canadian Capitals", "About Canada", "Canadian Mountains"};
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int questionCount = (Integer) questionCountSpinner.getSelectedItem();
                String category = (String) categorySpinner.getSelectedItem();

                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("QUESTION_COUNT", questionCount);
                intent.putExtra("CATEGORY", category);
                startActivity(intent);
            }
        });
    }
}