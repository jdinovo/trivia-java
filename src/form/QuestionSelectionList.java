package form;

import javabean.QuizAnswer;
import javabean.QuizQuestion;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class QuestionSelectionList extends VBox {

    private ListView<QuizQuestion> questionListView;
    private ArrayList<QuizQuestion> quizQuestions;
    private QuizQuestion question;
    private AUDButtons audButtons;

    public QuestionSelectionList(boolean selectedList) {
        questionListView = new ListView<>();
        quizQuestions = new ArrayList<>();
        audButtons = new AUDButtons();
        audButtons.getChildren().remove(audButtons.getUpdateButton());
        if (selectedList) {
            audButtons.getChildren().remove(audButtons.getAddButton());
        }
        audButtons.getDeleteButton().setText("Remove");
        question = new QuizQuestion();

        refreshList();

        setPrefSize(200, 400);
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);

        getChildren().addAll(questionListView, audButtons);


        questionListView.setOnMousePressed(e -> {
            question = questionListView.getSelectionModel().getSelectedItem();
            if (question != null) {
                audButtons.getDeleteButton().setVisible(true);
            }
        });


        audButtons.getDeleteButton().setOnAction(e -> {
            quizQuestions.remove(question);
            audButtons.getDeleteButton().setVisible(false);
            refreshList();
        });


    }

    public ListView<QuizQuestion> getQuestionListView() {
        return questionListView;
    }

    public ArrayList<QuizQuestion> getQuizQuestions() {
        return quizQuestions;
    }

    public AUDButtons getAudButtons() {
        return audButtons;
    }

    public void refreshList() {
        questionListView.setItems(FXCollections.observableArrayList(quizQuestions));
        questionListView.refresh();
    }
}
