package tabs;


import javafx.scene.control.Tab;

public class TakeQuizTab extends Tab {
    private static TakeQuizTab tab;

    private TakeQuizTab() {
        this.setText("Quiz");

//        this.setContent(new QuestionCUPane());
        this.setOnClosed(e-> {
            tab = null;
        });
    }

    public static TakeQuizTab getInstance() {
        if(tab == null) {
            tab = new TakeQuizTab();
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
