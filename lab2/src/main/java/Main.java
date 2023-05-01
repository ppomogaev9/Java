import stackCalculator.Calculator;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream inFile;
            if (args.length == 0) {
                inFile = System.in;
            }
            else {
                inFile = new FileInputStream(new File(args[0]));
            }
            stackCalculator.Calculator calc = new Calculator(inFile);
            calc.Calculate();
        }
        catch (Throwable ex) {
            System.out.println("try-block in Main function threw exception " + ex.getMessage());
        }

    }
}