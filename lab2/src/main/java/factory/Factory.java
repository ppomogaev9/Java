package factory;

import commands.AbstractCommand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Factory {
    private Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

    public Factory() throws ClassNotFoundException, IllegalArgumentException, FileNotFoundException {
        String configPath = "src/main/java/factory/config.txt";

        InputStream stream = new FileInputStream(configPath);

        Scanner scanner = new Scanner(stream).useDelimiter("\\A");

        String result = "";
        if (scanner.hasNext())
            result = scanner.next();

        String[] lines = result.split("\r\n");
        for (String line : lines) {
            String[] words = line.split(" ");

            if (words.length != 2){
                throw new IllegalArgumentException();
            }

            classMap.put(words[0], Class.forName(words[1]));
        }

        scanner.close();
    }
    public AbstractCommand create(String command) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> comClass = classMap.get(command);
        if (comClass == null) {
            throw new IllegalArgumentException(command + " is illegal command");
        }
        return (AbstractCommand)comClass.getDeclaredConstructor().newInstance();
    }

}
