package commands;

import stackCalculator.CalculationContext;
import static java.lang.Double.parseDouble;

public class Define implements AbstractCommand {
    public void execute(CalculationContext context, String[] arguments) throws Throwable {
        if (arguments.length != 3) {
            throw new IllegalArgumentException("Incorrect arguments number");
        }

        try {
            context.setVariable(arguments[1], parseDouble(arguments[2]));
        } catch (NumberFormatException ex) {
            throw new Throwable("Passed value inconvertible to double");
        }
    }

    public String getName() {
        return "Define";
    }
}
