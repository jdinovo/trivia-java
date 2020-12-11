package panes.quiz;

import form.QuizAnswerButtons;
import javabean.QuestionResult;
import javabean.Quiz;
import javabean.QuizQuestion;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import main.Main;
import scenes.QuizResultsScene;

import java.util.ArrayList;

public class QuizPane extends BorderPane {

    private ArrayList<QuizQuestion> questions;
    private QuizAnswerButtons answerButtons;
    private int currentIndex;
    private QuizQuestion currentQuestion;
    private Button nextButton;
    private Quiz quiz;

    private ArrayList<QuestionResult> results;

    public QuizPane(Quiz quiz) {

        this.quiz = quiz;

        currentIndex = 0;

        questions = quiz.getQuestions(true);

        answerButtons = new QuizAnswerButtons();
        results = new ArrayList<>();

        // next Button
        nextButton = new Button("Next Question");
        nextButton.setOnAction(e -> {
            nextQuestion();
        });
        nextButton.setVisible(false);
        setRight(nextButton);

        // exit button
        Button quit = new Button("Quit Quiz");
        quit.setOnAction(e -> {
            Main.setMainScene();
        });
        setLeft(quit);

        nextQuestion();

        Button[] buttonArray = answerButtons.getAnswerButtons();

        for (int i = 0; i < buttonArray.length; i++) {
            final int finalI = i;
            buttonArray[i].setOnAction(e -> {
                answerButtons.disableButtons();
                answerButtons.setSelectedAnswer(finalI);
                answerButtons.highlightSelectedButton(finalI, answerButtons.getSelectedAnswer().isCorrect());

                results.add(new QuestionResult(currentQuestion, answerButtons.getSelectedAnswer()));

                nextButton.setVisible(true);

            });
        }

        setBottom(answerButtons);

    }

    private void nextQuestion() {

        if (currentIndex < questions.size()) {
            currentQuestion = questions.get(currentIndex);
            currentIndex++;
            answerButtons.setAnswers(currentQuestion.getAnswers(true));
            nextButton.setVisible(false);
            if (currentIndex > questions.size() - 1) {
                nextButton.setText("View Results");
                nextButton.setOnAction(e -> Main.window.setScene(new QuizResultsScene(quiz, results)));
            }
        }
    }


}
