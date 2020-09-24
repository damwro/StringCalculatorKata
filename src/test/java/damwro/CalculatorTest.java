package damwro;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void shouldReturnZero(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.add(""), 0);
    }

}
