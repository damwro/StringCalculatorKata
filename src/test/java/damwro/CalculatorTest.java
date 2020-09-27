package damwro;

import damwro.exception.NegativeNotAllowedException;
import damwro.exception.NoNumberAfterSeparatorException;
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
    public void shouldReturnZeroForEmptyString() throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
        //given
        //when
        //then
        assertEquals(calculator.add(""), 0);
    }

    @Test
    public void shouldReturnValueWhenGivenNumber() throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
        //given
        String value = "10";
        //when
        //then
        assertEquals(calculator.add(value), Integer.parseInt(value));
    }

    @Test
    public void shouldAddTwoNumbers() throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
        //given
        String value = "1,10";
        //when
        //then
        assertEquals(calculator.add(value), 11);
    }

    @Test
    public void shouldAllowAddMoreThanTwoNumbers() throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
        //given
        String values = "1,10,5,25,100";
        //when
        //then
        assertEquals(calculator.add(values), 141);
    }

    @Test
    public void shouldAllowAddNumbersSeparatedByNewLines() throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
        //given
        String values = "1\n2,3";
        //when
        //then
        assertEquals(calculator.add(values), 6);
    }

    @Test(expected = NoNumberAfterSeparatorException.class)
    public void shouldThrowExceptionWhenNoNumberAfterSeparator() throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
        //given
        String values = "1,\n";
        //when
        //then
        assertEquals(calculator.add(values), 3);
    }

    @Test
    public void shouldAcceptCustomDelimiters() throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
        //given
        String values = "//:\n1:2:3";
        //when
        //then
        assertEquals(calculator.add(values), 6);
    }

    @Test
    public void shouldAcceptCustomDelimiterAA() throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
        //given
        String values = "//AA\n1AA2AA15";
        //when
        //then
        assertEquals(calculator.add(values), 18);
    }

    @Test
    public void shouldHandleEmptyFirstLine() throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
        //given
        String values = "\n1,5,5";
        //when
        //then
        assertEquals(calculator.add(values), 11);
    }

    @Test(expected = NegativeNotAllowedException.class)
    public void shouldThrowNegativeNotAllowedException() throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
        //given
        String values = "1,-5,-6";
        //when
        //then
        assertEquals(calculator.add(values), 0);
    }

    @Test
    public void shouldIgnoreBiggerThan1000() throws NegativeNotAllowedException, NoNumberAfterSeparatorException {
        //given
        String values = "1,10,1001";
        //when
        //then
        assertEquals(calculator.add(values), 11);
    }

    @Test
    public void shouldAcceptLongerSeparators() throws NegativeNotAllowedException, NoNumberAfterSeparatorException {
        //given
        String values = "//[***]\n1***2***3";
        //when
        //then
        assertEquals(calculator.add(values), 6);
    }

    @Test
    public void shouldAcceptMultipleSeparators() throws NegativeNotAllowedException, NoNumberAfterSeparatorException {
        //given
        String values = "//[*][%]\n1*2%3";
        //when
        //then
        assertEquals(calculator.add(values), 6);
    }

    @Test
    public void shouldAllowMultipleLongerSeparators() throws NegativeNotAllowedException, NoNumberAfterSeparatorException {
        //given
        String values = "//[***][ABC]\n1ABC22***1000";
        //when
        //then
        assertEquals(calculator.add(values), 1023);
    }

}
