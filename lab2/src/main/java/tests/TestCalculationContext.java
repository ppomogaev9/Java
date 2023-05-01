package tests;

import org.junit.*;

import stackCalculator.CalculationContext;

import java.util.EmptyStackException;
import java.util.ArrayList;
import java.util.List;

public class TestCalculationContext {
    @Test
    public void testPushPop() {
        CalculationContext context = new CalculationContext();

        context.push(12.0);
        Double num = context.pop();

        Assert.assertTrue(num.equals(12.0));
    }

    @Test
    public void testPopFromEmptyStack () {
        CalculationContext context = new CalculationContext();

        boolean Thrown = false;
        try {
            context.pop();
        } catch (EmptyStackException ex) {
            Thrown = true;
        }

        Assert.assertTrue(Thrown);

        Thrown = false;
        try {
            context.push(5.0);
            context.pop();
            context.pop();
        } catch (EmptyStackException ex) {
            Thrown = true;
        } catch (Throwable ex) {
            Assert.fail("Not expected exception in testPopFromEmptyStack, message: " + ex.getMessage());
        }

        Assert.assertTrue(Thrown);
    }

    @Test
    public void testSetVariable() {
        CalculationContext context = new CalculationContext();
        
        try {
            context.setVariable("a", 1.0);
            context.push(context.getNumber("a"));
            context.setVariable("b", 2.0);
            context.push(context.getNumber("b"));
        } catch (Throwable ex) {
            Assert.fail(ex.getMessage());
        }

        List<Double> actual = new ArrayList<>();
        try {
            actual.add(context.pop());
            actual.add(context.pop());
        } catch (EmptyStackException ex) {
            Assert.fail("Stack is empty yet it shouldn't be so, message: " + ex.getMessage());
        }

        List<Double> expected = new ArrayList<>();
        expected.add(2.0);
        expected.add(1.0);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetNumber() {
        CalculationContext context = new CalculationContext();

        Double number;
        try {
            number = context.getNumber("1.322");
            Assert.assertEquals("incorrect parse", number, Double.valueOf(1.322));
        } catch (IllegalArgumentException ex) {
            Assert.fail("in testGetNumber function getNumber threw an exception " + ex.getMessage() + " but it shouldn't");
        }

        boolean Throws = false;
        try {
            context.getNumber("a");
        } catch (IllegalArgumentException ex) {
            Throws = true;
        }
        Assert.assertTrue("getNumber does not throw an IllegalArgumentException exception " +
                "in case of an attempt to get a non-existent variable", Throws);

        context.setVariable("bcd", 12.322);
        number = context.getNumber("bcd");
        Assert.assertEquals("getNumber and setVariable doesn't adjust each other", number, Double.valueOf(12.322));
    }

}
