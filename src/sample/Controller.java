package sample;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import evaluation.TestFonction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    private GridPane monGridPane;

    @FXML
    public void initialize() {
        cb.setItems(list);
        cb2.setItems(list2);
    }

    private void charger(){

        HashMap<Integer, String> file= new TestFonction().fileToMap("src/output/terrain/Esperanto_and_Interlingua_1.csv");

        int lines = file.size();
        int cols = file.get(0).split("####").length;

        monGridPane.getChildren().clear();

        for (int i=0; i<lines;i++){
            String[] fields = file.get(i).split("####");
            for (int j=0;j<cols;j++){
                TextField champ = new TextField();
                champ.setPrefWidth(250.0);
                champ.setPrefHeight(100.0);

                champ.setText(fields[j]);
                monGridPane.add(champ,j,i);
            }
        }



    }

    public void saveAsList(){

        int rows = monGridPane.getRowCount();
        int cols = monGridPane.getColumnCount();
        System.out.println("lignes = "+rows + " colonnes = "+cols);

        List<String> list = new ArrayList<>();
        List<List<String>> listCSV = new ArrayList<>();

        for (Node n: monGridPane.getChildren()){
            TextField txt = (TextField)n;
            list.add(txt.getText());
        }

        for (int i = 0; i < rows; i++) {
            List sub=list.subList(0,3);
            List two=new ArrayList<String>(sub);
            sub.clear();
            listCSV.add(two);
        }

        saveCSV(listCSV);
    }

    public void saveCSV(List<List<String>> data){

        try {
            CSVWriter writer = new CSVWriter(new FileWriter("./test.csv", false));


            for(List<String> l: data){
                String[] array = new String[l.size()];
                l.toArray(array);
                writer.writeNext(array);
            }

            writer.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
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
            /*ta2.setText(result.toString());*/

            charger();
        }


    }
}