import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String str1 = "Alphabet uses diacritics (ĉ, ĝ, ĥ, ĵ, ŝ, ŭ) does not use diacritics ";
        String str2 = "Alphabet uses diacritics (ĉ, ĝ, ĥ, ĵ, ŝ, ŭ) does not use diacritics ";

        boolean equal = str1.equalsIgnoreCase(str2);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");

        for (int i = 0; i < 4; i++) {
            List sub=list.subList(0,2);
            List two=new ArrayList<String>(sub);
            sub.clear();
            System.out.println(two);
        }







    }
}
