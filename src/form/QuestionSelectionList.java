package form;

import javabean.QuizQuestion;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import tables.QuizQuestionTable;

import java.util.ArrayList;

import static main.Const.BODY_FONT;

public class QuestionSelectionList extends VBox {

    private ListView<QuizQuestion> questionListView;
    private ArrayList<QuizQuestion> quizQuestions;
    private QuizQuestion question;
    private AUDButtons audButtons;
    private QuizQuestionTable quizQuestionTable;
    private Label listLabel;

    public QuestionSelectionList(String label, boolean selectedList) {
        quizQuestionTable = new QuizQuestionTable();
        listLabel = new Label(label);
        listLabel.setFont(BODY_FONT);

        question = new QuizQuestion();
        questionListView = new ListView<>();
        quizQuestions = new ArrayList<>();
        audButtons = new AUDButtons();

        audButtons.getChildren().remove(audButtons.getUpdateButton());
        if (selectedList) {
            audButtons.getChildren().remove(audButtons.getCreateButton());
            audButtons.getDeleteButton().setText("Remove");
            audButtons.getDeleteButton().setVisible(false);
        } else {
            quizQuestions = quizQuestionTable.getAllQuizQuestions();
            audButtons.getChildren().remove(audButtons.getDeleteButton());
        }

        questionListView.setCellFactory(param -> new ListCell<QuizQuestion>(){
            @Override
            protected void updateItem(QuizQuestion item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // set width
                    setPrefWidth(param.getWidth() - 10);
                    // set wrapping
                    setWrapText(true);
                    setText(item.toString());
                }
            }
        });

        refreshList();

        setPrefSize(400, 400);
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);

        getChildren().addAll(listLabel, questionListView, audButtons);


        questionListView.setOnMousePressed(e -> {
            question = questionListView.getSelectionModel().getSelectedItem();
            if (question != null) {
                audButtons.getDeleteButton().setVisible(true);
                audButtons.getCreateButton().setVisible(true);
            }
        });


    }

    public QuizQuestion getSelectedQuestion() {
        return question;
    }

    public ArrayList<QuizQuestion> getQuizQuestions() {
        return quizQuestions;
    }

    public ListView<QuizQuestion> getQuestionListView() {
        return questionListView;
    }

    public AUDButtons getAudButtons() {
        return audButtons;
    }

    public void addQuestion(QuizQuestion quizQuestion) {
        this.quizQuestions.add(quizQuestion);
        refreshList();
    }

    public void removeQuestion(QuizQuestion quizQuestion) {
        quizQuestions.remove(quizQuestion);
        audButtons.getCreateButton().setVisible(false);
        audButtons.getDeleteButton().setVisible(false);
        refreshList();
    }

    public void refreshList() {
        questionListView.setItems(FXCollections.observableArrayList(quizQuestions));
        questionListView.refresh();
    }
}
