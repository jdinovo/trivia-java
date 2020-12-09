package panes;

import form.CategoryChoice;
import form.QuestionCUForm;
import form.QuizCUForm;
import javafx.scene.layout.BorderPane;
import tables.QuestionQuizRelationTable;
import tables.QuizAnswerTable;
import tables.QuizQuestionTable;

import java.util.List;
import java.util.Map;

public class QuestionCUPane extends BorderPane {



    // db access
    private QuizQuestionTable quizQuestionTable;
    private QuizAnswerTable quizAnswerTable;

    // gui
    private QuestionCUForm form;

    public QuestionCUPane() {

        // db access
        quizAnswerTable = new QuizAnswerTable();
        quizQuestionTable = new QuizQuestionTable();

        // info


        // gui
        form = new QuestionCUForm();

        setCenter(form);




    }
}
