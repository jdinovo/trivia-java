package tabs;

import javafx.scene.control.Tab;
import panes.QuestionCUPane;

public class EditQuestionTab extends Tab {
    private static EditQuestionTab tab;

    private EditQuestionTab() {
        this.setText("Edit Question");

        this.setContent(new QuestionCUPane());
        this.setOnClosed(e-> {
            tab = null;
        });
    }

    public static EditQuestionTab getInstance() {
        if(tab == null) {
            tab = new EditQuestionTab();
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
