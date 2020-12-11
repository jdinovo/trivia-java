package tabs;

import javabean.Quiz;
import javafx.scene.control.Tab;
import panes.QuizCUPane;

public class EditQuizTab extends Tab {
    private static EditQuizTab tab;

    private EditQuizTab(Quiz quiz) {
        this.setText("Edit Quiz");

        this.setContent(new QuizCUPane(quiz));
        this.setOnClosed(e-> {
            tab = null;
        });
    }

    public static EditQuizTab getInstance(Quiz quiz) {
        if(tab == null) {
            tab = new EditQuizTab(quiz);
        } else {
            tab.setContent(new QuizCUPane(quiz));
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
