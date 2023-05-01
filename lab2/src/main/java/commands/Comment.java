package commands;

import stackCalculator.CalculationContext;

public class Comment implements AbstractCommand {
    public void execute(CalculationContext ctx, String[] args) {}

    public String getName() {
        return "Comment";
    }
}