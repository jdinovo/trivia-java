package form;

import javabean.QuizAnswer;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;


public class QuestionCUForm extends GridPane {

    private Label questionLabel, categoryLabel, subcategoryLabel;
    private TextArea questionArea;
    private ListView<QuizAnswer> answerListView;
    private Map<String, List<String>> categoryMap;

    public QuestionCUForm() {

        questionLabel = new Label("Question");
        categoryLabel = new Label("Category");
        subcategoryLabel = new Label("Subcategory");
        categoryMap = CategoryChoice.getCategoryModel();

        questionArea = new TextArea();
        questionArea.setPromptText("Enter the question");
        questionArea.setMaxSize(200,200);
        questionArea.setWrapText(true);

    }
}
