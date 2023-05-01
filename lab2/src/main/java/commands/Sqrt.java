package commands;

import stackCalculator.CalculationContext;
import static java.lang.Math.sqrt;

public class Sqrt implements AbstractCommand {
    public void execute(CalculationContext context, String[] arguments) throws Throwable {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("Incorrect arguments number");
        }

        Double term = context.pop();

        if (term.compareTo(Double.valueOf(0.0)) < 0) {
            throw new Throwable("Square root from a negative number is undefined");
        }

        context.push(sqrt(term));
    }

    public String getName() {
        return "Sqrt";
    }
}