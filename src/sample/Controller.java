package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    ObservableList list = FXCollections.observableArrayList("Java - Wikitext", "Java - HTML", "Python - HTML");
    @FXML
    private ChoiceBox<String> cb;
    @FXML
    private TextArea ta;
    @FXML
    private TextArea ta2;
    @FXML
    private TextField goodUrl;

    @FXML
    public void initialize() {
        cb.setItems(list);
    }

    public void displayValue() throws IOException {
        File file = new File("D:\\test.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        String result = "";
        while ((st = br.readLine()) != null) {
            result += st + "\n";
        }
        String extractor = cb.getValue();
        String url = goodUrl.getText();
        List<String> liste_line = new ArrayList<String>();
        url = url.substring(30, url.length());
        if (extractor == null) {
            ta.setText("Select an extractor");
        } else {
            ta.setText("Vous avez choisi : " + extractor + "\n" + "Ouverture de la page : " + url);
            ta2.setText(result);
        }


    }
}