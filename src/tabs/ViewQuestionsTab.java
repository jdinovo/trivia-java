package tabs;


import javafx.scene.control.Tab;
import panes.QuestionCUPane;

public class ViewQuestionsTab extends Tab {
    private static ViewQuestionsTab tab;

    private ViewQuestionsTab() {
        this.setText("Edit Question");

        this.setContent(new QuestionCUPane());
        this.setOnClosed(e-> {
            tab = null;
        });
    }

    public static ViewQuestionsTab getInstance() {
        if(tab == null) {
            tab = new ViewQuestionsTab();
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
