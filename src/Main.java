import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Process mProcess = null;
        try {
            Process process = Runtime.getRuntime().exec("java -jar extracteurs/extracteurJava.jar wikitext https://en.wikipedia.org/wiki/France");
            mProcess = process;
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if (mProcess != null) {
            InputStream stdout = mProcess.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            String line;
            try{
                while((line = reader.readLine()) != null){
                    System.out.println("stdout: "+ line);
                }
            }catch(IOException e){
                System.out.println("Exception in reading output"+ e.toString());
            }
        }

    }
}
