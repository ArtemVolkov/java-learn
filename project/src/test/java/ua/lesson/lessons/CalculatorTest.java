package ua.lesson.lessons;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void add() throws Exception {
        Calculator calculator=new Calculator();
        calculator.add(1,1,2);
        assertEquals(4, calculator.getResult());
    }

    @Test
    public void subtraction() throws Exception{
        Calculator calc=new Calculator();
        calc.subtraction(1,1);
        assertEquals(0,calc.getResult());
    }

    @Test (expected = UserException.class)
    public void addException() throws Exception{
        Calculator calculator=new Calculator();
        calculator.add();
        assertEquals(0, calculator.getResult());
    }

}