package commands;

import java.util.EmptyStackException;
import stackCalculator.CalculationContext;

public class Print implements AbstractCommand {
    public void execute(CalculationContext context, String[] arguments) throws EmptyStackException, IllegalArgumentException {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("Incorrect arguments number");
        }

        Double result = context.peek();
        result = Math.round(1000000 * result) / 1000000.0;
        System.out.println(result);
    }

    public String getName() {
        return "Print";
    }
}
