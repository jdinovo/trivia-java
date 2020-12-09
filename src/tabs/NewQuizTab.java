package tabs;

import javafx.scene.control.Tab;
import panes.QuizCUPane;

/**
 *
 * NewQuizTab is a tab that contains the GUI for creating a new quiz
 * It is a singleton class
 *
 */
public class NewQuizTab extends Tab {
    private static NewQuizTab tab;

    /**
     *
     * Constructor for NewQuizTab
     *
     */
    private NewQuizTab() {
        this.setText("New Quiz");
        //Execute the function to bring the GUI to the NewQuizTab
        this.setContent(new QuizCUPane());
        this.setOnClosed(e-> {
            tab = null;
        });
    }

    /**
     *
     * NewQuizTab getInstance method
     * @return NewQuizTab()
     *
     */
    public static NewQuizTab getInstance() {
        if(tab == null) {
            tab = new NewQuizTab();
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
