package commands;

import java.util.EmptyStackException;
import stackCalculator.CalculationContext;

public class Plus implements AbstractCommand {
    public void execute(CalculationContext context, String[] arguments) throws EmptyStackException, IllegalArgumentException {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("Incorrect arguments number");
        }

        Double term1 = context.pop();
        Double term2 = context.pop();

        context.push(term1 + term2);
    }

    public String getName() {
        return "Plus";
    }
}