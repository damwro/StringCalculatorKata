package damwro;

import damwro.exception.NoNumberAfterSeparatorException;

public class Calculator {

    public int add(String numbers) throws NoNumberAfterSeparatorException {
        //default value
        String separator = ",|\\n";

        if (checkEmptyString(numbers)) {
            return 0;
        }
        checkIsNumberAfterSeparator(numbers);

        if (numbers.substring(0, 2).equals("//")) {
            separator = numbers.substring(2, numbers.indexOf("\n"));
            numbers = numbers.substring(numbers.indexOf("\n") + 1);
        }

        return sumNumbers(numbers, separator);
    }

    private int sumNumbers(String numbers, String separator) {
        String[] splittedNumbers = numbers.split(separator);
        int result = 0;
        for (String number : splittedNumbers) {
            result += Integer.parseInt(number);
        }
        return result;
    }

    private void checkIsNumberAfterSeparator(String numbers) throws NoNumberAfterSeparatorException {
        if (numbers.endsWith(",\n")) {
            throw new NoNumberAfterSeparatorException("not need to prove it");
        }
    }

    private boolean checkEmptyString(String numbers) {
        return numbers.equals("");
    }

}
