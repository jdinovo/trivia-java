package form;

import javabean.QuestionAnswer;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class QuizAnswerButtons extends VBox {

    private Button[] answerButtons;
    private QuestionAnswer[] answers;
    private QuestionAnswer selectedAnswer;
    private boolean answerCorrect;

    public QuizAnswerButtons() {

        answerButtons = new Button[4];
        this.selectedAnswer = new QuestionAnswer();
        this.answerCorrect = false;
        this.answers = new QuestionAnswer[4];

        for (int i = 0; i < this.answerButtons.length; i++) {
            answerButtons[i] = new Button("");
            answerButtons[i].setPrefSize(512, 200);
            answerButtons[i].wrapTextProperty().setValue(true);
            answerButtons[i].textAlignmentProperty().set(TextAlignment.CENTER);
        }

        HBox row1 = new HBox();
        row1.getChildren().addAll(answerButtons[0], answerButtons[1]);

        HBox row2 = new HBox();
        row2.getChildren().addAll(answerButtons[2], answerButtons[3]);

        getChildren().addAll(row1, row2);

    }

    public void setAnswers(QuestionAnswer[] answers) {
        this.answers = answers;

        for (int i = 0; i < this.answers.length; i++) {
            answerButtons[i].setText(this.answers[i].getText());
        }

        resetButtonColours();
        enableButtons();
    }

    public QuestionAnswer getSelectedAnswer() {
        return this.selectedAnswer;
    }

    public Button[] getAnswerButtons() {
        return this.answerButtons;
    }

    public void setSelectedAnswer(int index) {
        this.selectedAnswer = answers[index];
    }

    public void disableButtons() {
        for (Button button : answerButtons) {
            button.setDisable(true);
        }
    }

    private void enableButtons() {
        for (Button button : answerButtons) {
            button.setDisable(false);
        }
    }

    private void resetButtonColours() {
        answerButtons[0].setStyle("-fx-font: 16 impact; -fx-base: rgb(200,0,0); -fx-text-fill: rgb(255,255,255);");
        answerButtons[1].setStyle("-fx-font: 16 impact; -fx-base: rgb(0,0,200); -fx-text-fill: rgb(255,255,255);");
        answerButtons[2].setStyle("-fx-font: 16 impact; -fx-base: rgb(200,140,0); -fx-text-fill: rgb(255,255,255);");
        answerButtons[3].setStyle("-fx-font: 16 impact; -fx-base: rgb(0,150,0); -fx-text-fill: rgb(255,255,255);");
    }

    public void highlightSelectedButton(int index, boolean correct) {
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setStyle("-fx-font: 16 impact; -fx-text-fill: rgb(255,255,255); -fx-base: " + (i == index ? (correct ? "rgb(0,150,0);" : "rgb(200,0,0);" ):  "rgb(150,150,150);"));
        }
    }






}
