package model;

import java.util.Arrays;

public class TrueFalseQuestion extends Question {
    public TrueFalseQuestion(String questionText, String correctAnswer) {
        super(questionText, Arrays.asList("True", "False"), correctAnswer);
    }
}
