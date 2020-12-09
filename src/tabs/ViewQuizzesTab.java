package tabs;

import javafx.scene.control.Tab;
import panes.QuizViewPane;

public class ViewQuizzesTab extends Tab {
    private static ViewQuizzesTab tab;

    private ViewQuizzesTab() {
        this.setText("View Quizzes");

        this.setContent(new QuizViewPane());
        this.setOnClosed(e-> {
            tab = null;
        });
    }

    public static ViewQuizzesTab getInstance() {
        if(tab == null) {
            tab = new ViewQuizzesTab();
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
