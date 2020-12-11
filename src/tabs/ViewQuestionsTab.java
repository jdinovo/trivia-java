package tabs;


import javafx.scene.control.Tab;
import panes.QuestionViewPane;

public class ViewQuestionsTab extends Tab {
    private static ViewQuestionsTab tab;

    private ViewQuestionsTab() {
        this.setText("View Questions");

        this.setContent(new QuestionViewPane());
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
