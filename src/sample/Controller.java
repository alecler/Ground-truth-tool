package sample;

import com.opencsv.CSVReader;
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
    ObservableList list2 = FXCollections.observableArrayList("0", "1", "2");
    @FXML
    private ChoiceBox<String> cb;
    @FXML
    private ChoiceBox<String> cb2;
    @FXML
    private TextArea ta;
    @FXML
    private TextArea ta2;
    @FXML
    private TextField goodUrl;

    @FXML
    public void initialize() {
        cb.setItems(list);
        cb2.setItems(list2);
    }

    public void displayValue() throws IOException {
        File file = new File("src/output/terrain/Esperanto_and_Interlingua_1.csv");

        //BufferedReader br = new BufferedReader(new FileReader(file));


        String st;
        StringBuilder result = new StringBuilder();

        try {
            FileReader fr = new FileReader(file);
            CSVReader csvReader = new CSVReader(fr);
            String[] nextRecord;
            StringBuilder line = new StringBuilder();

            while ((nextRecord = csvReader.readNext()) != null){
                for (String cell : nextRecord) {
                    line.append(cell).append(" ");
                }
                result.append(line.toString()).append("\n");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }


        /*String st;
        String result = "";*/
       /* while ((st = br.readLine()) != null) {
            result += st + "\n";
        }*/

        String extractor = cb.getValue();
        String url = goodUrl.getText();
        List<String> liste_line = new ArrayList<String>();
        url = url.substring(30, url.length());
        if (extractor == null) {
            ta.setText("Select an extractor");
        } else {
            ta.setText("Vous avez choisi : " + extractor + "\n" + "Ouverture de la page : " + url);
            ta2.setText(result.toString());
        }


    }
}