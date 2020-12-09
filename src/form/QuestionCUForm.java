package form;

import javabean.Difficulty;
import javabean.QuizAnswer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

import static main.Const.BODY_FONT;
import static main.Const.TEXTFIELD_WIDTH_SIZE;


public class QuestionCUForm extends VBox {

    private Label questionLabel, categoryLabel, subcategoryLabel, difficultylabel;
    private TextArea questionArea;
    private ListView<QuizAnswer> answerListView;
    private Map<String, List<String>> categoryMap;

    private Button createButton;

    private ComboBox<String> comboCategory;
    private ComboBox<String> comboSubcategory;
    private ComboBox<Difficulty> comboDifficulty;


    public QuestionCUForm(String buttonLabel) {

        questionLabel = new Label("Question");
        categoryLabel = new Label("Category");
        subcategoryLabel = new Label("Subcategory");
        difficultylabel = new Label("Difficulty");
        categoryMap = CategoryChoice.getCategoryModel();

        //Brand label
        Label brandText = new Label("Brand:");
        brandText.setFont(BODY_FONT);

        comboCategory = new ComboBox<>();
        comboSubcategory = new ComboBox<>();
        comboDifficulty = new ComboBox<>();

        subcategoryLabel.setVisible(false);
        comboSubcategory.setVisible(false);

        comboDifficulty.setItems(FXCollections.observableArrayList(Difficulty.values()));
        comboDifficulty.setValue(Difficulty.NORMAL);

        //category ComboBox
        comboCategory.setMaxWidth(TEXTFIELD_WIDTH_SIZE);
        //Set the drop down menu to the categoryMap's key values
        comboCategory.setItems(FXCollections.observableArrayList(categoryMap.keySet()));
        comboCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            List<String> subCategories = categoryMap.get(newValue);
            comboSubcategory.setValue("");
            if (subCategories.size() > 0) {
                comboSubcategory.setItems(FXCollections.observableList(subCategories));
                subcategoryLabel.setVisible(true);
                comboSubcategory.setVisible(true);
            } else {
                subcategoryLabel.setVisible(false);
                comboSubcategory.setVisible(false);
            }
        });
        comboCategory.setVisibleRowCount(5);
        comboSubcategory.setVisibleRowCount(5);

        comboSubcategory.setMaxWidth(TEXTFIELD_WIDTH_SIZE);

        questionArea = new TextArea();
        questionArea.setPromptText("Enter the question");
        questionArea.setMaxSize(200,200);
        questionArea.setWrapText(true);

        createButton = new Button(buttonLabel);
        createButton.setPrefSize(400, 50);

        setPrefSize(400, 400);
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);

        getChildren().addAll(questionLabel, questionArea, difficultylabel, comboDifficulty, categoryLabel, comboCategory, subcategoryLabel, comboSubcategory, createButton);

    }

    public ComboBox<Difficulty> getComboDifficulty() {
        return comboDifficulty;
    }

    public TextArea getQuestionArea() {
        return questionArea;
    }

    public ListView<QuizAnswer> getAnswerListView() {
        return answerListView;
    }

    public Map<String, List<String>> getCategoryMap() {
        return categoryMap;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public ComboBox<String> getComboCategory() {
        return comboCategory;
    }

    public ComboBox<String> getComboSubcategory() {
        return comboSubcategory;
    }
}
