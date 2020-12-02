package evaluation;

public class Error {

    private int lineNumber;
    private String line1;
    private String line2;

    public Error(int i, String l1, String l2){
        lineNumber = i;
        line1 = l1;
        line2 = l2;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }
}
