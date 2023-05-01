package stackCalculator;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import static java.lang.Double.parseDouble;

public class CalculationContext {
    private Stack<Double> stack = new Stack<Double>();
    private Map<String, Double> vars = new HashMap<String, Double>();
    private static Logger log = LogManager.getLogger(CalculationContext.class);

    public void setVariable(String name, Double value) {
        vars.put(name, value);
    }

    public Double peek() throws EmptyStackException {
        log.info("Trying to get stack top");
        if (stack.isEmpty()) {
            log.error("Stack's empty");
            throw new EmptyStackException();
        }
        log.info("peek is successful");
        return stack.peek();
    }

    public Double pop() throws EmptyStackException {
        log.info("trying to pop stack head");
        if (stack.isEmpty()){
            log.error("failed to pop!");
            throw new EmptyStackException();
        }
        log.info("pop is successful.");
        return stack.pop();
    }

    public void push(Double num) {
        log.info("trying to push");
        stack.push(num);
        log.info("push is successful");
    }

    public Double getNumber(String str) throws IllegalArgumentException {
        Double number;

        try {
            log.info("Trying to parse a number");
            number = parseDouble(str);
            log.info("Successfully parsed a number");
        } catch (NumberFormatException ex) {
            log.info("Parse failed, it isn't a number, trying to parse a variable");
            number = vars.get(str);
            if (number == null){
                log.error("Parse failed. Passed string doesn't correspond to either a variable or a number.");
                throw new IllegalArgumentException("Illegal symbol");
            }
            log.info("Successfully parsed a variable");
        }

        return number;
    }
}
