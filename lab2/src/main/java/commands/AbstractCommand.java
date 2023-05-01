package commands;

import stackCalculator.CalculationContext;

public interface AbstractCommand {
    public void execute(CalculationContext context, String[] arguments) throws Throwable;

    public String getName();
}
