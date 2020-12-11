package tabs;

import javabean.QuizQuestion;
import javafx.scene.control.Tab;
import panes.QuestionCUPane;
import panes.QuizCUPane;

public class EditQuestionTab extends Tab {
    private static EditQuestionTab tab;

    private EditQuestionTab(QuizQuestion quizQuestion) {
        this.setText("Edit Question");

        this.setContent(new QuestionCUPane(quizQuestion));
        this.setOnClosed(e-> {
            tab = null;
        });
    }

    public static EditQuestionTab getInstance(QuizQuestion quizQuestion) {
        if(tab == null) {
            tab = new EditQuestionTab(quizQuestion);
        } else {
            tab.setContent(new QuestionCUPane(quizQuestion));
        }
        return tab;
    }

    public static void closeInstance() {
        if (tab != null) {
            tab.getTabPane().getTabs().remove(tab);
            tab = null;
        }
    }
}
