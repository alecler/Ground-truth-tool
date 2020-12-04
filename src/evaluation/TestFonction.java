package evaluation;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TestFonction {

    List<Error> errors;
    List<Boolean> checks;

    public TestFonction(){
        errors = new ArrayList<>();
        checks = new ArrayList<>();
    }

    public int countRows(String file){

        int rows = 0;

        try{
            FileReader fr = new FileReader(file);
            CSVReader csvReader = new CSVReader(fr);

            while (csvReader.readNext() != null){
                rows+=1;
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return rows;
    }

    public int countCols(String file){
        int cols = 0;

        try {
            FileReader fr = new FileReader(file);
            CSVReader csvReader = new CSVReader(fr);
            String[] line = csvReader.readNext();
            cols = line.length;
        }catch (Exception e){e.printStackTrace();}

        return cols;
    }

    public static HashMap<Integer, String> fileToMap(String file){

        HashMap<Integer, String> map = new HashMap<Integer, String>();
        int k =0;

        try {

            FileReader fr = new FileReader(file);
            CSVReader csvReader = new CSVReader(fr);
            String[] nextRecord;
            StringBuilder line = new StringBuilder();

            while ((nextRecord = csvReader.readNext()) != null){
                for (String cell : nextRecord) {
                    line.append(cell).append("####");
                }
                map.put(k, line.toString());
                line = new StringBuilder();
                k+=1;
            }

        }catch (Exception e){e.printStackTrace();}

        return map;
    }

    /**
     * Comoaraison strict
     * @param m1 file1
     * @param m2 file2
     * @return TRUE ou FALSE
     */
    public boolean compareCSVStrict(HashMap<Integer, String> m1, HashMap<Integer, String> m2){

        return m1.entrySet().stream()
                .allMatch(e ->
                        e.getValue().replaceAll(System.lineSeparator()," ").equalsIgnoreCase(m2.get(e.getKey()))
                );
    }

    public boolean compareCSV(HashMap<Integer, String> m1, HashMap<Integer, String> m2){

        boolean check;
        for (Map.Entry<Integer, String> entry1 : m1.entrySet()) {
            int key = entry1.getKey();
            String value1 = entry1.getValue();
            String value2 = m2.get(key);

            value1 = value1.replaceAll(System.lineSeparator()," ");

            check =value1.equalsIgnoreCase(value2);

            if (!check){

                errors.add(new Error(key, value1, value2));

            }else {
                checks.add(true);
            }


        }

        return checks.contains(true);
    }
}
