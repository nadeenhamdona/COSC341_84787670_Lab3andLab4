package com.ayah.quizapp;

public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;
    private String imageName;
    private String category;

    public Question(String questionText, String[] options, int correctAnswerIndex,
                    String imageName, String category) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.imageName = imageName;
        this.category = category;
    }

    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
    public String getImageName() { return imageName; }
    public String getCategory() { return category; }
}
