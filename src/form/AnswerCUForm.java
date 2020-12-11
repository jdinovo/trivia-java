package form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AnswerCUForm extends VBox {
    private Label answerLabel, correctLabel;
    private TextArea answerArea;
    private CheckBox correctCheckBox;
    private Button addButton;

    public AnswerCUForm(boolean update) {
        answerLabel = new Label("Answer");
        correctLabel = new Label("Correct");

        answerArea = new TextArea();

        correctCheckBox = new CheckBox();

        addButton = new Button(update ? "Update Answer" : "Add Answer");

        answerArea.setPromptText("Enter a description of the quiz");
        answerArea.setMaxSize(200,200);
        answerArea.setWrapText(true);

        addButton.setPrefSize(200, 50);

        setPrefSize(200, 400);
        setSpacing(10);
        setPadding(new Insets(10));

        setAlignment(Pos.CENTER_LEFT);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(correctLabel, correctCheckBox);
        hBox.setSpacing(10);

        getChildren().addAll(answerLabel, answerArea, hBox, addButton);
    }

    public TextArea getAnswerArea() {
        return answerArea;
    }

    public CheckBox getCorrectCheckBox() {
        return correctCheckBox;
    }

    public Button getAddButton() {
        return addButton;
    }
}
