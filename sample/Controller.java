package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class Controller {

    ObservableList<String> extractorTypeList = FXCollections.observableArrayList("Python","Java : HTML","Java : WikiText");

    @FXML
    private ChoiceBox extractorTypeBox;
    @FXML
    private void initialize() {
        extractorTypeBox.setItems(extractorTypeList);
    }
}
