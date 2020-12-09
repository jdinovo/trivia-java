package panes;

import form.CategoryChoice;
import form.QuizCUForm;
import javabean.Quiz;
import javabean.QuizQuestion;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import main.CurrentUser;
import tables.QuestionQuizRelationTable;
import tables.QuizQuestionTable;
import tables.QuizTable;
import tabs.NewQuizTab;

import java.util.ArrayList;


public class QuizCUPane extends BorderPane {

    // access tables
    private QuizTable quizTable;
    private QuizQuestionTable quizQuestionTable;
    private QuestionQuizRelationTable questionQuizRelationTable;

    // information
    private ArrayList<QuizQuestion> questions;

    // gui items
    private ListView<QuizQuestion> questionListView;
    private QuizCUForm form;

    public QuizCUPane() {

        // db access
        quizTable = new QuizTable();
        quizQuestionTable = new QuizQuestionTable();
        questionQuizRelationTable = new QuestionQuizRelationTable();

        // info
        questions = quizQuestionTable.getAllQuizQuestions();

        // gui
        form = new QuizCUForm("Create");

        questionListView = new ListView<>();
        questionListView.setItems(FXCollections.observableArrayList(questions));


        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(form, questionListView);
        setCenter(hBox);
        setAlignment(hBox, Pos.CENTER);

        form.getCreateButton().setOnAction(e -> {
            String title = form.getTitleField().getText().trim();
            String description = form.getdescriptionArea().getText().trim();

            if (!title.isEmpty() && !description.isEmpty()) {
                quizTable.createQuiz(new Quiz(title, description));
                QuizViewPane.refreshTable();
                NewQuizTab.closeInstance();
            }



        });




    }

}
