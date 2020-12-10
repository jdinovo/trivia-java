package form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class AUDButtons extends HBox {
    private final Button addButton, updateButton, deleteButton;

    public AUDButtons() {

        addButton = new Button("Add");
        updateButton = new Button("Edit");
        deleteButton = new Button("Del");

        addButton.setStyle("-fx-base: rgb(0,130,0); -fx-text-fill: rgb(255,255,255);");
        updateButton.setStyle("-fx-base: rgb(200,100,0); -fx-text-fill: rgb(255,255,255);");
        deleteButton.setStyle("-fx-base: rgb(180,0,0); -fx-text-fill: rgb(255,255,255);");

        addButton.setPrefSize(50, 50);
        updateButton.setPrefSize(50, 50);
        deleteButton.setPrefSize(50, 50);

        setAlignment(Pos.CENTER);
        setSpacing(5);
        setPadding(new Insets(5));
        getChildren().addAll(addButton, updateButton, deleteButton);

        setPrefSize(170, 50);
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
