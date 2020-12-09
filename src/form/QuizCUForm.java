package form;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class QuizCUForm extends VBox {

    private Label titleLabel, descriptionLabel;
    private TextField titleField;
    private TextArea descriptionArea;
    private Button createButton;

    public QuizCUForm(String buttonLabel) {
        titleField = new TextField();
        descriptionArea = new TextArea();
        titleLabel = new Label("Title");
        descriptionLabel = new Label("Description");
        descriptionArea.setPromptText("Enter a description of the quiz");
        descriptionArea.setMaxWidth(200);
        descriptionArea.setMaxSize(200,200);
        descriptionArea.setWrapText(true);

        createButton = new Button(buttonLabel);

        getChildren().addAll(titleLabel, titleField, descriptionLabel, descriptionArea, createButton);

    }

    public TextField getTitleField() {
        return titleField;
    }

    public TextArea getdescriptionArea() {
        return descriptionArea;
    }

    public Button getCreateButton() {
        return createButton;
    }
}
