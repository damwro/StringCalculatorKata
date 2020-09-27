package damwro;

import damwro.exception.NegativeNotAllowedException;
import damwro.exception.NoNumberAfterSeparatorException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public int add(String numbers) throws NoNumberAfterSeparatorException, NegativeNotAllowedException {
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

    private int sumNumbers(String numbers, String separator) throws NegativeNotAllowedException {
        String[] splittedNumbers = numbers.split(separator);
        validateNegatives(splittedNumbers);
        int result = 0;
        for (String number : splittedNumbers) {
            if (number.isEmpty()) {
                number = "0";
            }
            result += Integer.parseInt(number);
        }
        return result;
    }

    private void validateNegatives(String[] splittedNumbers) throws NegativeNotAllowedException {
        List<String> negatives = Arrays.stream(splittedNumbers)
                .filter(s -> Integer.parseInt(s) < 0)
                .collect(Collectors.toList());
        if (!negatives.isEmpty()) {
            throw new NegativeNotAllowedException("Negative numbers are not allowed: " + negatives);
        }
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
