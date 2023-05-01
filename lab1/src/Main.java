import parser.CSVParser;

public class Main {
    public static void main(String[] args) {
        String InputFilePath = args[0];
        String OutputFilePath = args[1];
        CSVParser parser = new CSVParser(InputFilePath, OutputFilePath);
        parser.parse();
    }
}
