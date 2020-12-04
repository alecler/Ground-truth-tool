package sample;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import evaluation.TestFonction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
    ObservableList list = FXCollections.observableArrayList("Java - Wikitext", "Java - HTML", "Python - HTML");
    @FXML
    private ChoiceBox<String> cb;
    @FXML
    private ChoiceBox<String> cb2;
    @FXML
    private TextArea ta;
    @FXML
    private TextField goodUrl;
    @FXML
    private GridPane monGridPane;

    @FXML
    public void initialize() {
        cb.setItems(list);
    }

    private void charger(String path) {

        HashMap<Integer, String> file = new TestFonction().fileToMap(path);

        int lines = file.size();
        int cols = file.get(0).split("####").length;

        monGridPane.getChildren().clear();

        for (int i = 0; i < lines; i++) {
            String[] fields = file.get(i).split("####");
            for (int j = 0; j < cols; j++) {
                TextField champ = new TextField();
                champ.setPrefWidth(250.0);
                champ.setPrefHeight(100.0);


                if (j<= fields.length-1) {
                    champ.setText(fields[j]);
                }else {
                    champ.setText(" ");
                }


                monGridPane.add(champ, j, i);
            }
        }
    }

    public void ListerRepertoire() {
        if (cb.getValue() == "Java - Wikitext") {
            File repertoire = new File("./outputJava/wikitext");
            ObservableList list1 = FXCollections.observableArrayList();
            String liste[] = repertoire.list();

            if (liste != null) {
                for (int i = 0; i < liste.length; i++) {
                    String url = liste[i].substring(0, liste[i].length() - 4);
                    String url1 = goodUrl.getText();
                    url1 = url1.substring(30, url1.length());
                    if (url.contains(url1)) {
                        list1.add(url);
                    }
                }
            } else {
                System.err.println("Nom de repertoire invalide");
            }
            cb2.setItems(list1);
        } else {
            File repertoire = new File("./outputJava/html");
            ObservableList list1 = FXCollections.observableArrayList();
            String liste[] = repertoire.list();

            if (liste != null) {
                for (int i = 0; i < liste.length; i++) {
                    String url = liste[i].substring(0, liste[i].length() - 4);
                    String url1 = goodUrl.getText();
                    url1 = url1.substring(30, url1.length());
                    if (url.contains(url1)) {
                        list1.add(url);
                    }
                }
            } else {
                System.err.println("Nom de repertoire invalide");
            }
            cb2.setItems(list1);
        }
    }

    public void saveAsList() {

        int rows = monGridPane.getRowCount();
        int cols = monGridPane.getColumnCount();
        System.out.println("lignes = " + rows + " colonnes = " + cols);

        List<String> list = new ArrayList<>();
        List<List<String>> listCSV = new ArrayList<>();

        for (Node n : monGridPane.getChildren()) {
            TextField txt = (TextField) n;
            list.add(txt.getText());
        }

        for (int i = 0; i < rows; i++) {
            List sub = list.subList(0, cols);
            List two = new ArrayList<String>(sub);
            sub.clear();
            listCSV.add(two);
        }

        saveCSV(listCSV);
    }

    public void saveCSV(List<List<String>> data) {

        try {
            String name = (new Timestamp(System.currentTimeMillis()).getTime())+ "_finalCSV.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir") + "/generate/"+name, false));


            for (List<String> l : data) {
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

        String st;
        StringBuilder result = new StringBuilder();

        try {
            FileReader fr = new FileReader(file);
            CSVReader csvReader = new CSVReader(fr);
            String[] nextRecord;
            StringBuilder line = new StringBuilder();

            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    line.append(cell).append(" ");
                }
                result.append(line.toString()).append("\n");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        String extractor = cb.getValue();
        String url = goodUrl.getText();
        List<String> liste_line = new ArrayList<String>();
        url = url.substring(30, url.length());
        if (extractor == null) {
            ta.setText("Select an extractor");
        } else {
            ta.setText("Vous avez choisi : " + extractor + "\n" + "Ouverture de la page : " + url);
        }
    }

    public void displayExtractedFile() throws IOException {
        String extractor = cb.getValue();
        String url = goodUrl.getText();
        String dir = "outputPython/";
        String name = getNameFromUrl(url);
        String format = "html";

        switch (extractor) {
            case "Python - HTML":
                dir = "outputPython/";
                break;
            case "Java - HTML":
                dir = System.getProperty("user.dir") + "/outputJava/html/";
                format = "html";
                break;
            case "Java - Wikitext":
                dir = System.getProperty("user.dir") + "/outputJava/wikitext/";
                format = "wikitext";
                break;
        }

        this.extraction(url, format);

        String path = dir + name + "_1.csv";
        File file = new File(path);

        String st;
        StringBuilder result = new StringBuilder();

        try {
            FileReader fr = new FileReader(file);
            CSVReader csvReader = new CSVReader(fr);
            String[] nextRecord;
            StringBuilder line = new StringBuilder();

            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    line.append(cell).append(" ");
                }
                result.append(line.toString()).append("\n");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        List<String> liste_line = new ArrayList<String>();
        url = url.substring(30, url.length());
        if (extractor == null) {
            ta.setText("Select an extractor");
        } else {
            ta.setText("Vous avez choisi : " + extractor + "\n" + "Ouverture de la page : " + url);
            /*ta2.setText(result.toString());*/

            charger(path);
        }
        ListerRepertoire();

    }

    private String getNameFromUrl(String url) {
        String str = url;
        str = str.replace("https://en.wikipedia.org/wiki/", "");
        str = str.replace("https://fr.wikipedia.org/wiki/", "");
        str = str.replace("en.wikipedia.org/wiki/", "");
        str = str.replace("fr.wikipedia.org/wiki/", "");
        return str;
    }

    private void extraction(String url, String format) {
        Process mProcess = null;
        try {
            Process process = Runtime.getRuntime().exec("java -jar " + System.getProperty("user.dir") + "/src/sample/extracteurJava.jar " + format + " " + url);
            mProcess = process;
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if (mProcess != null) {
            InputStream stdout = mProcess.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println("stdout: " + line);
                }
            } catch (IOException e) {
                System.out.println("Exception in reading output" + e.toString());
            }
        }
    }

    public void displayCSV() {
        String path;
        if (cb.getValue() == "Java - WikiText") {
            path = "./outputJava/wikitext/";
        }
        else {
            path = "./outputJava/html/";
        }
        path += cb2.getValue() + ".csv";
        File file = new File(path);

        StringBuilder result = new StringBuilder();

        try {
            FileReader fr = new FileReader(file);
            CSVReader csvReader = new CSVReader(fr);
            String[] nextRecord;
            StringBuilder line = new StringBuilder();

            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    line.append(cell).append(" ");
                }
                result.append(line.toString()).append("\n");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        charger(path);
    }
}

/*
--module-path
        "/home/sadou/Vidéos/javafx-sdk-11.0.2/lib/"
        --add-modules=javafx.controls,javafx.fxml*/
