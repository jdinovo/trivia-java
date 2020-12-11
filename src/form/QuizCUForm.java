package form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    public QuizCUForm(boolean update) {
        titleField = new TextField();
        descriptionArea = new TextArea();
        titleLabel = new Label("Title");
        descriptionLabel = new Label("Description");
        descriptionArea.setPromptText("Enter a description of the quiz");
        descriptionArea.setMaxSize(400,200);
        descriptionArea.setWrapText(true);

        createButton = new Button(update ? "Update" : "Create");
        createButton.setPrefSize(400, 50);

        setPrefSize(400, 400);
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER_LEFT);

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
