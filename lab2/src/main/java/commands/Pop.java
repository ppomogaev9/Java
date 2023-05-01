package commands;

import java.util.EmptyStackException;
import stackCalculator.CalculationContext;

public class Pop implements AbstractCommand {
    public void execute(CalculationContext context, String[] arguments) throws EmptyStackException, IllegalArgumentException {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("Incorrect arguments number");
        }

        context.pop();
    }

    public String getName() {
        return "Pop";
    }
}