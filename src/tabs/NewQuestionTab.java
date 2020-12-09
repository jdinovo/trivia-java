package tabs;

import javafx.scene.control.Tab;
import panes.QuestionCUPane;

public class NewQuestionTab extends Tab {
    private static NewQuestionTab tab;

    /**
     *
     * Constructor for NewQuestionTab
     *
     */
    private NewQuestionTab() {
        this.setText("New Question");
        //Execute the function to bring the GUI to the NewQuestionTab
        this.setContent(new QuestionCUPane());
        this.setOnClosed(e-> {
            tab = null;
        });
    }

    /**
     *
     * NewQuestionTab getInstance method
     * @return NewQuestionTab()
     *
     */
    public static NewQuestionTab getInstance() {
        if(tab == null) {
            tab = new NewQuestionTab();
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
