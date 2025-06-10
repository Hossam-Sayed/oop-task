package service;

import model.Question;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Quiz {
    private final List<Question> questions;
    private final Random random;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.random = new Random();
    }

    public void addQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Cannot add a null question.");
        }
        this.questions.add(question);
    }

    /**
     * Creates a random sub-quiz from the available questions.
     *
     * @param numberOfQuestions The number of random questions to include in the quiz.
     * @return A list of randomly selected questions.
     */
    public List<Question> createRandomQuiz(int numberOfQuestions) {
        if (numberOfQuestions <= 0) {
            throw new IllegalArgumentException("Number of questions must be positive.");
        }
        if (numberOfQuestions > questions.size()) {
            System.out.println("Warning: Requested more questions than available. Using all available questions.");
            numberOfQuestions = questions.size();
        }

        List<Question> shuffledQuestions = new ArrayList<>(questions);
        Collections.shuffle(shuffledQuestions, random);
        return shuffledQuestions.subList(0, numberOfQuestions);
    }

    /**
     * Calculates the score for a user based on their answers to a given set of questions.
     *
     * @param user          The user object to update the score for.
     * @param quizQuestions The list of questions presented to the user.
     * @param userResponses A list of user's answers, in the same order as quizQuestions.
     */
    public void calculateScore(User user, List<Question> quizQuestions, List<Integer> userResponses) {
        user.resetProgress(); // Reset user's score and answers
        int currentScore = 0;
        for (int i = 0; i < quizQuestions.size(); i++) {
            if (i < userResponses.size()) { // Ensure we don't go out of bounds
                Question question = quizQuestions.get(i);
                String userAnswer = question.getPossibleAnswers().get(userResponses.get(i) - 1);
                if (question.isCorrect(userAnswer)) {
                    currentScore++;
                }
                user.recordAnswer(userAnswer); // Record the answer in the user's history
            }
        }
        user.setScore(currentScore);
    }


    public List<Question> getAllQuestions() {
        return Collections.unmodifiableList(questions);
    }
}