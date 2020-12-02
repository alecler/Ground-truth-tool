package evaluation;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    public HashMap<Integer, String> fileToMap(String file){

        HashMap<Integer, String> map = new HashMap<Integer, String>();
        int k =0;

        try {

            FileReader fr = new FileReader(file);
            CSVReader csvReader = new CSVReader(fr);
            String[] nextRecord;
            StringBuilder line = new StringBuilder();

            while ((nextRecord = csvReader.readNext()) != null){
                for (String cell : nextRecord) {
                    line.append(cell).append(" ");
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
                        e.getValue().equals(m2.get(e.getKey()))
                );
    }

    private boolean compareCSV(HashMap<Integer, String> m1, HashMap<Integer, String> m2){

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
    //a.replaceAll("\\s+","").equalsIgnoreCase(b.replaceAll("\\s+",""))
    //e.getValue().replaceAll("\\t"," ").equalsIgnoreCase(m2.get(e.getKey()))

    @Test
    public void isSame(){

        String file1Path ="src/output/terrain/Esperanto_and_Interlingua_1.csv";
        String file2Path = "src/output/generer/Comparison_between_Esperanto_and_Interlingua_0.csv";
        TestFonction t = new TestFonction();

        HashMap<Integer, String> mp1 = t.fileToMap(file1Path);
        HashMap<Integer, String> mp2 = t.fileToMap(file2Path);

        assertTrue(t.compareCSV(mp1,mp2));
    }

    @Test
    public void testCountRows1(){
        String filePath ="src/output/terrain/Esperanto_and_Interlingua_1.csv";

        int actual = countRows(filePath);

        assertEquals(13,actual);

    }

    @Test
    public void testCountRows2(){
        String filePath ="src/output/generer/Comparison_between_Esperanto_and_Interlingua_0.csv";

        int actual = countRows(filePath);

        assertEquals(13,actual);

    }

    @Test
    public void testCountCols1(){
        String filePath ="src/output/terrain/Esperanto_and_Interlingua_1.csv";

        int actual = countCols(filePath);

        assertEquals(3,actual);

    }

    @Test
    public void testCountCols2(){
        String filePath ="src/output/generer/Comparison_between_Esperanto_and_Interlingua_0.csv";

        int actual = countCols(filePath);

        assertEquals(3,actual);

    }

}
