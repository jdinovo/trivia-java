package form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static main.Const.BODY_FONT;

public class AnswerCUForm extends VBox {
    private Label answerLabel, correctLabel;
    private TextArea answerArea;
    private CheckBox correctCheckBox;
    private Button addButton;

    public AnswerCUForm(boolean update) {
        answerLabel = new Label("Answer");
        answerLabel.setFont(BODY_FONT);
        correctLabel = new Label("Correct");
        correctLabel.setFont(BODY_FONT);

        answerArea = new TextArea();

        correctCheckBox = new CheckBox();

        addButton = new Button(update ? "Update Answer" : "Add Answer");
        addButton.setStyle("-fx-base: " + (update ? "rgb(200,100,0);" : "rgb(0,130,0);") + " -fx-text-fill: rgb(255,255,255);");

        answerArea.setPromptText("Enter an answer");
        answerArea.setMaxSize(400,200);
        answerArea.setWrapText(true);

        addButton.setPrefSize(400, 50);

        setPrefSize(400, 400);
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
