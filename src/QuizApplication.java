import model.Question;
import model.TrueFalseQuestion;
import model.User;
import model.MultipleChoiceQuestion;
import service.Quiz;
import view.QuizView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizApplication {
    private final Quiz quiz;
    private final QuizView view;

    public QuizApplication() {
        this.quiz = new Quiz();
        this.view = new QuizView();
        initializeQuestions(); // Populate the quiz with some questions
    }

    private void initializeQuestions() {
        // Example questions
        quiz.addQuestion(new TrueFalseQuestion("Is the Llama a mammal", "True"));
        quiz.addQuestion(new MultipleChoiceQuestion("What is the capital of France?",
                Arrays.asList("Paris", "London", "Berlin", "Rome"), "Paris"));
        quiz.addQuestion(new MultipleChoiceQuestion("Which planet is known as the Red Planet?",
                Arrays.asList("Mars", "Jupiter", "Venus", "Mercury"), "Mars"));
        quiz.addQuestion(new MultipleChoiceQuestion("What is 2 + 2?",
                Arrays.asList("3", "4", "5", "6"), "4"));
        quiz.addQuestion(new MultipleChoiceQuestion("Who painted the Mona Lisa?",
                Arrays.asList("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"), "Leonardo da Vinci"));
        quiz.addQuestion(new MultipleChoiceQuestion("What is the largest ocean on Earth?",
                Arrays.asList("Atlantic", "Indian", "Arctic", "Pacific"), "Pacific"));
    }

    public void start() {
        view.displayWelcomeMessage();
        String userName = view.getUserInput("Please enter your name: ");
        User currentUser = new User(userName);

        int numberOfQuestions = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                String numQuestionsStr = view.getUserInput("How many questions would you like to attempt? (Max " + quiz.getAllQuestions().size() + "): ");
                numberOfQuestions = Integer.parseInt(numQuestionsStr);
                if (numberOfQuestions > 0 && numberOfQuestions <= quiz.getAllQuestions().size()) {
                    isValidInput = true;
                } else {
                    view.displayMessage("Please enter a positive number up to " + quiz.getAllQuestions().size() + ".");
                }
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a number.");
            }
        }


        List<Question> currentQuizQuestions = quiz.createRandomQuiz(numberOfQuestions);
        List<Integer> userResponses = new ArrayList<>();

        for (int i = 0; i < currentQuizQuestions.size(); i++) {
            Question question = currentQuizQuestions.get(i);
            view.displayQuestion(i + 1, question);
            try {
                String userAnswerStr = view.getUserInput("Your answer (write the answer number): ");
                Integer userAnswer = Integer.parseInt(userAnswerStr);
                userResponses.add(userAnswer);
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a number.");
                i--;
            }
        }

        quiz.calculateScore(currentUser, currentQuizQuestions, userResponses);
        view.displayScore(currentUser);
        view.closeScanner();
    }

    public static void main(String[] args) {
        QuizApplication app = new QuizApplication();
        app.start();
    }
}