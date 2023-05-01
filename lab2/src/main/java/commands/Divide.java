package commands;

import stackCalculator.CalculationContext;

public class Divide implements AbstractCommand {
    public void execute(CalculationContext context, String[] arguments) throws Throwable {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("Incorrect arguments number");
        }

        Double dividend = context.pop();
        Double divisor = context.pop();

        if (divisor.equals(Double.valueOf(0.0))) {
            throw new Throwable("Trying to divide by zero");
        }

        context.push(dividend/divisor);
    }

    public String getName() {
        return "Divide";
    }
}
