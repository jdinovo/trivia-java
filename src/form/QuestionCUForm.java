package form;

import javabean.Difficulty;
import javabean.QuestionAnswer;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

import static main.Const.BODY_FONT;


public class QuestionCUForm extends VBox {

    private Label questionLabel, categoryLabel, subcategoryLabel, difficultylabel;
    private TextArea questionArea;
    private ListView<QuestionAnswer> answerListView;
    private Map<String, List<String>> categoryMap;

    private Button createButton;

    private ComboBox<String> comboCategory;
    private ComboBox<String> comboSubcategory;
    private ComboBox<Difficulty> comboDifficulty;


    public QuestionCUForm(boolean update) {

        questionLabel = new Label("Question");
        questionLabel.setFont(BODY_FONT);
        categoryLabel = new Label("Category");
        categoryLabel.setFont(BODY_FONT);
        subcategoryLabel = new Label("Subcategory");
        subcategoryLabel.setFont(BODY_FONT);
        difficultylabel = new Label("Difficulty");
        difficultylabel.setFont(BODY_FONT);
        categoryMap = CategoryChoice.getCategoryModel();

        comboCategory = new ComboBox<>();
        comboSubcategory = new ComboBox<>();
        comboDifficulty = new ComboBox<>();

        subcategoryLabel.setVisible(false);
        comboSubcategory.setVisible(false);

        comboDifficulty.setItems(FXCollections.observableArrayList(Difficulty.values()));
        comboDifficulty.setValue(Difficulty.NORMAL);

        //category ComboBox
        //Set the drop down menu to the categoryMap's key values
        comboCategory.setItems(FXCollections.observableArrayList(categoryMap.keySet()));
        comboCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            List<String> subCategories = categoryMap.get(newValue);
            comboSubcategory.setValue("");
            if (subCategories != null && subCategories.size() > 0) {
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

        comboCategory.setMaxWidth(400);
        comboSubcategory.setMaxWidth(400);
        comboDifficulty.setMaxWidth(400);

        questionArea = new TextArea();
        questionArea.setPromptText("Enter the question");
        questionArea.setMaxSize(400,200);
        questionArea.setWrapText(true);

        createButton = new Button(update ? "Update Question" : "Create Question");
        createButton.setStyle("-fx-base: " + (update ? "rgb(200,100,0);" : "rgb(0,130,0);") + " -fx-text-fill: rgb(255,255,255);");
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

    public ListView<QuestionAnswer> getAnswerListView() {
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
