package com.ayah.quizapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private TextView questionTextView;
    private ImageView questionImageView;
    private RadioGroup optionsRadioGroup;
    private RadioButton option1Radio, option2Radio, option3Radio, option4Radio;
    private Button nextButton;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int[] userAnswers;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionTextView = findViewById(R.id.questionTextView);
        questionImageView = findViewById(R.id.questionImageView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        option1Radio = findViewById(R.id.option1Radio);
        option2Radio = findViewById(R.id.option2Radio);
        option3Radio = findViewById(R.id.option3Radio);
        option4Radio = findViewById(R.id.option4Radio);
        nextButton = findViewById(R.id.nextButton);

        int questionCount = getIntent().getIntExtra("QUESTION_COUNT", 1);
        category = getIntent().getStringExtra("CATEGORY");

        questions = QuizManager.getQuestions(category, questionCount);
        userAnswers = new int[questions.size()];

        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = -1;
        }

        displayQuestion(currentQuestionIndex);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNextButton();
            }
        });
    }

    private void displayQuestion(int index) {
        Question currentQuestion = questions.get(index);
        questionTextView.setText(currentQuestion.getQuestionText());

        String[] options = currentQuestion.getOptions();
        option1Radio.setText(options[0]);
        option2Radio.setText(options[1]);
        option3Radio.setText(options[2]);
        option4Radio.setText(options[3]);

        optionsRadioGroup.clearCheck();

        if (userAnswers[index] != -1) {
            switch (userAnswers[index]) {
                case 0: option1Radio.setChecked(true); break;
                case 1: option2Radio.setChecked(true); break;
                case 2: option3Radio.setChecked(true); break;
                case 3: option4Radio.setChecked(true); break;
            }
        }

        if (index == questions.size() - 1) {
            nextButton.setText("Finish");
        } else {
            nextButton.setText("Next");
        }
    }

    private void handleNextButton() {
        int selectedId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            return;
        }

        int selectedIndex = -1;
        if (selectedId == R.id.option1Radio) selectedIndex = 0;
        else if (selectedId == R.id.option2Radio) selectedIndex = 1;
        else if (selectedId == R.id.option3Radio) selectedIndex = 2;
        else if (selectedId == R.id.option4Radio) selectedIndex = 3;

        userAnswers[currentQuestionIndex] = selectedIndex;

        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);
        } else {
            showResults();
        }
    }

    private void showResults() {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (userAnswers[i] == questions.get(i).getCorrectAnswerIndex()) {
                score++;
            }
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.size());
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
        finish();
    }
}
