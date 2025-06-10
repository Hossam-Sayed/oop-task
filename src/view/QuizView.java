package view;

import model.Question;
import model.User;

import java.util.Scanner;

public class QuizView {
    private final Scanner scanner;

    public QuizView() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayQuestion(int questionNumber, Question question) {
        System.out.println("\n--- Question " + questionNumber + " ---");
        System.out.println(question);
    }

    public void displayScore(User user) {
        System.out.println("\n--- Quiz Finished! ---");
        System.out.println(user.getName() + ", your final score is: " + user.getScore() + " out of " + user.getUserAnswers().size());
        System.out.println("Thanks for playing!");
    }

    public void displayWelcomeMessage() {
        displayMessage("Welcome to the Command Line Quiz Application!");
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
