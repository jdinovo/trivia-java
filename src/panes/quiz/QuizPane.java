package panes.quiz;

import form.QuizAnswerButtons;
import javabean.QuestionResult;
import javabean.Quiz;
import javabean.QuizQuestion;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.Construct;
import main.Main;
import scenes.QuizResultsScene;

import java.util.ArrayList;

import static main.Const.BODY_FONT;
import static main.Const.HEADER_FONT;

public class QuizPane extends BorderPane {

    private ArrayList<QuizQuestion> questions;
    private QuizAnswerButtons answerButtons;
    private int currentIndex;
    private QuizQuestion currentQuestion;
    private Button nextButton;
    private Quiz quiz;

    private Label questionLabel, categoryLabel;

    private ArrayList<QuestionResult> results;

    public QuizPane(Quiz quiz) {

        // set quiz
        this.quiz = quiz;

        // set base index
        currentIndex = 0;

        // get questions
        questions = quiz.getQuestions(true);

        // initialize gui
        answerButtons = new QuizAnswerButtons();
        results = new ArrayList<>();

        questionLabel = new Label();
        questionLabel.setAlignment(Pos.CENTER);
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(Construct.SCREEN_WIDTH >> 1);
        questionLabel.setFont(BODY_FONT);
        categoryLabel = new Label();
        categoryLabel.setFont(HEADER_FONT);

        VBox headerBox = new VBox();
        headerBox.getChildren().addAll(categoryLabel, questionLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setSpacing(10);

        setCenter(headerBox);

        // next Button
        nextButton = new Button("Next Question");
        nextButton.setOnAction(e -> {
            nextQuestion();
        });
        nextButton.setVisible(false);
        setRight(nextButton);

        // exit button
        Button quit = new Button("Exit Quiz");
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

            questionLabel.setText(currentQuestion.getText());
            categoryLabel.setText(currentQuestion.getCategory() + (currentQuestion.getSubcategory().isEmpty() ? "" : (": " + currentQuestion.getSubcategory())));

            nextButton.setVisible(false);
            if (currentIndex > questions.size() - 1) {
                nextButton.setText("View Results");
                nextButton.setOnAction(e -> Main.window.setScene(new QuizResultsScene(quiz, results)));
            }
        }
    }


}
