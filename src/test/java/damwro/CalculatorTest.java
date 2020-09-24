package damwro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void shouldReturnZeroForEmptyString() {
        //given
        //when
        //then
        assertEquals(calculator.add(""), 0);
    }

    @Test
    public void shouldReturnValueWhenGivenNumber() {
        //given
        String value = "10";
        //when
        //then
        assertEquals(calculator.add(value), Integer.parseInt(value));
    }

    @Test
    public void shouldAddTwoNumbers() {
        //given
        String value = "1,10";
        //when
        //then
        assertEquals(calculator.add(value), 11);
    }

    @Test
    public void shouldAllowAddMoreThanTwoNumbers() {
        //given
        String values = "1,10,5,25,100";
        //when
        //then
        assertEquals(calculator.add(values), 141);
    }

    @Test
    public void shouldAllowAddNumbersSeparatedByNewLines() {
        //given
        String values = "1\n2,3";
        //when
        //then
        assertEquals(calculator.add(values), 6);
    }
}
