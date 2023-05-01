package stackCalculator;

import factory.Factory;
import commands.AbstractCommand;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.StreamCorruptedException;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.Scanner;

public class Calculator {
    CalculationContext context = new CalculationContext();
    Scanner scanner;
    Factory factory = new Factory();
    public static final String breakWord = ":q"; // для ввода с консоли
    private static Logger log = LogManager.getLogger(Calculator.class);

    public Calculator(InputStream inFile) throws ClassNotFoundException, StreamCorruptedException, FileNotFoundException {
        log.info("Initiating calculator");
        scanner = new Scanner(inFile);
    }

    public void Calculate() throws Throwable {
        log.info("//----------------------------------------//");
        log.info("Calculations started");

        while(scanner.hasNext()){
            log.info("Get a new line from input stream");

            String newLine = scanner.nextLine();
            if (newLine.equals(breakWord)) {
                log.info("Got break word, stopping calculations");
                break;
            }

            String[] arguments = newLine.split(" ");
            AbstractCommand command = factory.create(arguments[0]);
            log.info("Executing command " + command.getName());
            command.execute(context, arguments);
            log.info("Command " + command.getName() + " successfully executed");
        }

        log.info("Calculations ended");
        log.info("//----------------------------------------//");
    }
}
