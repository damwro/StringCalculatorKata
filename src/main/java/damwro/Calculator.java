package damwro;

import damwro.exception.NoNumberAfterSeparatorException;

public class Calculator {

    public int add(String numbers) throws NoNumberAfterSeparatorException {
        if (numbers.equals("")) {
            return 0;
        }
        if (numbers.endsWith(",\n")) {
            throw new NoNumberAfterSeparatorException("not need to prove it");
        }
        String[] splittedNumbers = numbers.split(",|\\n");
        int result = 0;
        for (String number : splittedNumbers) {
            result += Integer.parseInt(number);
        }
        return result;
    }

}
