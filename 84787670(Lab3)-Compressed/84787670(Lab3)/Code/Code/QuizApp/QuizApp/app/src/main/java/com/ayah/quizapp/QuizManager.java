package com.ayah.quizapp;


import java.util.ArrayList;
import java.util.List;

public class QuizManager {

    public static List<Question> getQuestions(String category, int count) {
        List<Question> allQuestions = getAllQuestions();
        List<Question> categoryQuestions = new ArrayList<>();

        for (Question question : allQuestions) {
            if (question.getCategory().equals(category)) {
                categoryQuestions.add(question);
            }
        }

        return categoryQuestions.subList(0, Math.min(count, categoryQuestions.size()));
    }

    private static List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();

        // Canadian Capitals Questions
        questions.add(new Question(
                "What is the capital of Canada?",
                new String[]{"Toronto", "Vancouver", "Ottawa", "Montreal"},
                2,
                "ic_canada",
                "Canadian Capitals"
        ));

        questions.add(new Question(
                "What is the capital of Ontario?",
                new String[]{"Toronto", "Ottawa", "London", "Hamilton"},
                0,
                "ic_ontario",
                "Canadian Capitals"
        ));

        questions.add(new Question(
                "What is the capital of British Columbia?",
                new String[]{"Vancouver", "Victoria", "Burnaby", "Surrey"},
                1,
                "ic_bc",
                "Canadian Capitals"
        ));

        questions.add(new Question(
                "What is the capital of Quebec?",
                new String[]{"Montreal", "Quebec City", "Laval", "Gatineau"},
                1,
                "ic_quebec",
                "Canadian Capitals"
        ));

        // About Canada Questions
        questions.add(new Question(
                "What is Canada's national animal?",
                new String[]{"Moose", "Beaver", "Polar Bear", "Canadian Goose"},
                1,
                "ic_beaver",
                "About Canada"
        ));

        questions.add(new Question(
                "Which colors are in the Canadian flag?",
                new String[]{"Blue and White", "Red and White", "Red and Blue", "Green and White"},
                1,
                "ic_flag",
                "About Canada"
        ));

        questions.add(new Question(
                "What is Canada's national winter sport?",
                new String[]{"Skiing", "Snowboarding", "Ice Hockey", "Curling"},
                2,
                "ic_hockey",
                "About Canada"
        ));

        questions.add(new Question(
                "Which lake is entirely within Canada?",
                new String[]{"Lake Superior", "Great Bear Lake", "Lake Huron", "Lake Erie"},
                1,
                "ic_lake",
                "About Canada"
        ));

        // Canadian Mountains Questions
        questions.add(new Question(
                "What is the highest mountain in Canada?",
                new String[]{"Mount Logan", "Mount Robson", "Mount Columbia", "Mount Waddington"},
                0,
                "ic_mountain1",
                "Canadian Mountains"
        ));

        questions.add(new Question(
                "In which province is Whistler Mountain located?",
                new String[]{"Alberta", "British Columbia", "Ontario", "Quebec"},
                1,
                "ic_mountain2",
                "Canadian Mountains"
        ));

        questions.add(new Question(
                "Which mountain range runs through BC and Alberta?",
                new String[]{"Appalachians", "Rocky Mountains", "Coast Mountains", "Laurentians"},
                1,
                "ic_mountain3",
                "Canadian Mountains"
        ));

        questions.add(new Question(
                "What is the famous mountain on Canadian quarter?",
                new String[]{"Mount Edith Cavell", "The Lions", "Mount Temple", "Schooner Mountain"},
                0,
                "ic_mountain4",
                "Canadian Mountains"
        ));

        return questions;
    }
}
