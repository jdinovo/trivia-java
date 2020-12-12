package panes;

import database.DBConst;
import database.Database;
import database.FileProcessor;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import tabs.NewQuestionTab;
import tabs.NewQuizTab;
import tabs.ViewQuestionsTab;
import tabs.ViewQuizzesTab;

import java.util.Optional;

public class MainMenuPane extends BorderPane {

    private static final TabPane tabPane = new TabPane();

    public MainMenuPane() {

        // Access db instance
        Database db = Database.getInstance();

        // create menu bar
        MenuBar menu = new MenuBar();

        // create menu dropdowns
        Menu quizMenu = new Menu("Quiz");
        Menu createMenu = new Menu("Create");
        Menu importMenu = new Menu("Import/Export");

        //creating tabPane
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        //Create MenuItems for the create Tab
        MenuItem newQuiz = new MenuItem("New Quiz");
        newQuiz.setOnAction(e-> {
            NewQuizTab newQuizTab = NewQuizTab.getInstance();
            //if tab is not already open
            if(!tabPane.getTabs().contains(newQuizTab) && !tabPane.getTabs().contains(NewQuizTab.getInstance())) {
                tabPane.getTabs().add(newQuizTab);
                tabPane.getSelectionModel().select(newQuizTab);
            } else if(tabPane.getTabs().contains(NewQuizTab.getInstance())){
                tabPane.getSelectionModel().select(NewQuizTab.getInstance());
            } else {
                tabPane.getSelectionModel().select(newQuizTab);
            }
        });

        MenuItem newQuestion = new MenuItem("New Question");
        newQuestion.setOnAction(e-> {
            NewQuestionTab newQuestionTab = NewQuestionTab.getInstance();
            //if tab is not already open
            if(!tabPane.getTabs().contains(newQuestionTab) && !tabPane.getTabs().contains(NewQuestionTab.getInstance())) {
                tabPane.getTabs().add(newQuestionTab);
                tabPane.getSelectionModel().select(newQuestionTab);
            } else if(tabPane.getTabs().contains(NewQuestionTab.getInstance())){
                tabPane.getSelectionModel().select(NewQuestionTab.getInstance());
            } else {
                tabPane.getSelectionModel().select(newQuestionTab);
            }
        });

        // quiz menu items
        MenuItem viewQuizzes = new MenuItem("View Quizzes");
        viewQuizzes.setOnAction(e-> {
            ViewQuizzesTab viewQuizzesTab = ViewQuizzesTab.getInstance();
            //if tab is not already open
            if(!tabPane.getTabs().contains(viewQuizzesTab) && !tabPane.getTabs().contains(ViewQuizzesTab.getInstance())) {
                tabPane.getTabs().add(viewQuizzesTab);
                tabPane.getSelectionModel().select(viewQuizzesTab);
            } else if(tabPane.getTabs().contains(ViewQuizzesTab.getInstance())){
                tabPane.getSelectionModel().select(ViewQuizzesTab.getInstance());
            } else {
                tabPane.getSelectionModel().select(viewQuizzesTab);
            }
        });

        MenuItem viewQuestions = new MenuItem("View Questions");
        viewQuestions.setOnAction(e-> {
            ViewQuestionsTab viewQuestionsTab = ViewQuestionsTab.getInstance();
            //if tab is not already open
            if(!tabPane.getTabs().contains(viewQuestionsTab) && !tabPane.getTabs().contains(ViewQuestionsTab.getInstance())) {
                tabPane.getTabs().add(viewQuestionsTab);
                tabPane.getSelectionModel().select(viewQuestionsTab);
            } else if(tabPane.getTabs().contains(ViewQuestionsTab.getInstance())){
                tabPane.getSelectionModel().select(ViewQuestionsTab.getInstance());
            } else {
                tabPane.getSelectionModel().select(viewQuestionsTab);
            }
        });

        MenuItem importQuestions = new MenuItem("Import Questions");
        importQuestions.setOnAction(e-> {
            TextInputDialog inputDialog = new TextInputDialog("example_questions.json");
            inputDialog.setTitle("Import Questions");
            inputDialog.setContentText("Enter the file path: ");
            inputDialog.setHeaderText("Questions JSON File Path");

            Optional<String> result = inputDialog.showAndWait();


            result.ifPresent(path -> {
                // insert create default questions
                FileProcessor fp = new FileProcessor();
                fp.readQuestionsIntoDB(path);

                ViewQuestionsTab viewQuestionsTab = ViewQuestionsTab.getInstance();
                //if tab is not already open
                if (!tabPane.getTabs().contains(viewQuestionsTab) && !tabPane.getTabs().contains(ViewQuestionsTab.getInstance())) {
                    tabPane.getTabs().add(viewQuestionsTab);
                    tabPane.getSelectionModel().select(viewQuestionsTab);
                } else if (tabPane.getTabs().contains(ViewQuestionsTab.getInstance())) {
                    tabPane.getSelectionModel().select(ViewQuestionsTab.getInstance());
                } else {
                    tabPane.getSelectionModel().select(viewQuestionsTab);
                }
            });

        });


        // add menu items to menus
        createMenu.getItems().addAll(newQuiz, newQuestion);
        quizMenu.getItems().addAll(viewQuizzes, viewQuestions);
        importMenu.getItems().addAll(importQuestions);

        // add menus to menubar
        menu.getMenus().addAll(quizMenu, createMenu, importMenu);

        // add menubar, tabpane to pane
        this.setTop(menu);
        this.setCenter(tabPane);

        // show view quizzes by default
        viewQuizzes.fire();

    }

    public static TabPane getTabPane() {
        return tabPane;
    }
}
