package commands;

import stackCalculator.CalculationContext;

public class Push implements AbstractCommand {
    public void execute(CalculationContext context, String[] arguments) throws IllegalArgumentException {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("Incorrect arguments number");
        }

        double num = context.getNumber(arguments[1]);

        context.push(num);
    }

    public String getName() {
        return "Push";
    }
}
