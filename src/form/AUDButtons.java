package form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class AUDButtons extends ButtonGroup {

    public AUDButtons() {
        Button createButton = getCreateButton();
        Button updateButton = getUpdateButton();
        Button deleteButton = getDeleteButton();

        createButton.setText("Add");
        updateButton.setText("Edit");
        deleteButton.setText("Delete");

        createButton.setPrefSize(100, 50);
        updateButton.setPrefSize(100, 50);
        deleteButton.setPrefSize(100, 50);

        getChildren().addAll(createButton, updateButton, deleteButton);

        setPrefSize(315, 50);
    }
}
