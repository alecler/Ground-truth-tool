package evaluation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Compare {

    TestFonction testF;

    @BeforeEach
    void setUp(){
        testF = new TestFonction();
    }

    @Test
    public void isSame(){

        String file1Path ="src/output/generer/Comparison_between_Esperanto_and_Interlingua_0.csv";
        String file2Path = "test.csv";

        int file1Lines = testF.countRows(file1Path);
        int file2Lines = testF.countRows(file2Path);

        int file1Cols = testF.countCols(file1Path);
        int file2Cols = testF.countCols(file2Path);

        assertEquals(file1Lines,file2Lines);
        assertEquals(file1Cols,file2Cols);


        HashMap<Integer, String> mp1 = testF.fileToMap(file1Path);
        HashMap<Integer, String> mp2 = testF.fileToMap(file2Path);

        assertTrue(testF.compareCSVStrict(mp1,mp2));
    }

    @Test
    public void testCountRows1(){
        String filePath ="src/output/terrain/Esperanto_and_Interlingua_1.csv";

        int actual = testF.countRows(filePath);

        assertEquals(13,actual);

    }

    @Test
    public void testCountRows2(){
        String filePath ="src/output/generer/Comparison_between_Esperanto_and_Interlingua_0.csv";

        int actual = testF.countRows(filePath);

        assertEquals(13,actual);

    }

    @Test
    public void testCountCols1(){
        String filePath ="src/output/terrain/Esperanto_and_Interlingua_1.csv";

        int actual = testF.countCols(filePath);

        assertEquals(3,actual);

    }

    @Test
    public void testCountCols2(){
        String filePath ="src/output/generer/Comparison_between_Esperanto_and_Interlingua_0.csv";

        int actual = testF.countCols(filePath);

        assertEquals(3,actual);

    }
}
