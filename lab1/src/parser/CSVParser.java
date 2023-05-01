package parser;

import java.io.*;
import java.util.*;
import java.lang.Character;

public class CSVParser {
    private final String InputFilePath;
    private final String OutputFileName;

    public CSVParser(String InputFilePath, String OutputFilePath) {
        this.InputFilePath = InputFilePath;
        this.OutputFileName = OutputFilePath;
    }

    private Map<String, Integer> readWords() {
        Map<String, Integer> words = new HashMap<String, Integer>();
        Scanner scanner = null;
        try
        {
            //reader = new InputStreamReader(new FileInputStream(this.InputFilePath));
            scanner = new Scanner(new FileInputStream(this.InputFilePath)).useDelimiter("\r\n");


            while (scanner.hasNext()) {
                String line = scanner.next();

                StringBuilder Word = new StringBuilder("");
                for (int i = 1; i < line.length(); ++i) {
                    if (Character.isLetterOrDigit(line.charAt(i))) {
                        Word.append(line.charAt(i));
                    }

                    if ((Word.length() != 0 && i == line.length() - 1) ||
                            (!Character.isLetterOrDigit(line.charAt(i)) && Character.isLetterOrDigit(line.charAt(i - 1))))
                    {
                        if (!words.containsKey(Word.toString())) {
                            words.put(Word.toString(), 1);
                        } else {
                            words.put(Word.toString(), words.get(Word.toString()) + 1);
                        }

                        Word = new StringBuilder("");
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error while reading file:" + e.getLocalizedMessage());
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return words;
    }

    private static class WordFrequency {
        public String Word;
        public Integer Frequency;

        public WordFrequency(String word, Integer freq) {
            this.Frequency = freq;
            this.Word = word;
        }

        public String toString() {
            return Word + ": " + Frequency.toString();
        }
    }

    private List<WordFrequency> mapToList(Map<String, Integer> map) {
        List<WordFrequency> list = new ArrayList<WordFrequency>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(new WordFrequency(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    private static class FrequencyComparator implements Comparator<WordFrequency> {
        @Override
        public int compare(WordFrequency a, WordFrequency b) {
            return Integer.compare(b.Frequency, a.Frequency);
        }
    }

    private int getWordsCount(List<WordFrequency> list) {
        int sum = 0;
        for (WordFrequency entry : list) {
            sum += entry.Frequency;
        }
        return sum;
    }

    public void ProfileToCSV(List<WordFrequency> Profile, String FileName) {
        Profile.sort(new FrequencyComparator());
        int WordsCount = getWordsCount(Profile);

        Writer writer = null;
        try
        {
            writer = new OutputStreamWriter(new FileOutputStream(FileName));

            writer.write("Word,Frequency,Frequency(%)\n");

            for (WordFrequency entry : Profile) {
                writer.write(entry.Word + "," + entry.Frequency.toString() + "," + 100.0*entry.Frequency/WordsCount + "\n");
            }

        }
        catch (IOException e)
        {
            System.err.println("Error while writing in file:" + e.getLocalizedMessage());
        }
        finally
        {
            if (null != writer)
            {
                try
                {
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public void parse() {
        List<WordFrequency> Profile = new ArrayList<WordFrequency>(mapToList(readWords()));
        ProfileToCSV(Profile, this.OutputFileName);
    }
}
