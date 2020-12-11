package form;

import javabean.QuizQuestion;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import tables.QuizQuestionTable;

import java.util.ArrayList;

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

        question = new QuizQuestion();
        questionListView = new ListView<>();
        quizQuestions = new ArrayList<>();
        audButtons = new AUDButtons();

        audButtons.getChildren().remove(audButtons.getUpdateButton());
        if (selectedList) {
            audButtons.getChildren().remove(audButtons.getAddButton());
            audButtons.getDeleteButton().setVisible(false);
        } else {
            quizQuestions = quizQuestionTable.getAllQuizQuestions();
            audButtons.getChildren().remove(audButtons.getDeleteButton());
        }


        refreshList();

        setPrefSize(200, 400);
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);

        getChildren().addAll(listLabel, questionListView, audButtons);


        questionListView.setOnMousePressed(e -> {
            question = questionListView.getSelectionModel().getSelectedItem();
            if (question != null) {
                audButtons.getDeleteButton().setVisible(true);
                audButtons.getAddButton().setVisible(true);
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
        audButtons.getAddButton().setVisible(false);
        audButtons.getDeleteButton().setVisible(false);
        refreshList();
    }

    public void refreshList() {
        questionListView.setItems(FXCollections.observableArrayList(quizQuestions));
        questionListView.refresh();
    }
}
