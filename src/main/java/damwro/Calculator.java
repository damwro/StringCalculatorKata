package damwro;

import damwro.exception.NegativeNotAllowedException;
import damwro.exception.NoNumberAfterSeparatorException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    private String numbersToCalculate;

    public int add(String numbers) throws NoNumberAfterSeparatorException, NegativeNotAllowedException {

        if (checkEmptyString(numbers)) {
            return 0;
        }
        checkIsNumberAfterSeparator(numbers);

        String[] separators = getSeparators(numbers);

        return sumNumbers(numbersToCalculate, separators);
    }

    private String[] getSeparators(String numbers) {
        String[] separators;
        if (numbers.substring(0, 2).equals("//")) {
            separators = prepareCustomSeparators(numbers);
            numbersToCalculate = numbers.substring(numbers.indexOf("\n") + 1);
        } else {
            separators = new String[2];
            separators[0] = ",";
            separators[1] = "\n";
            numbersToCalculate = numbers;
        }
        return separators;
    }

    private String[] prepareCustomSeparators(String numbers) {
        String[] separators;
        String allSeparators = numbers.substring(2, numbers.indexOf("\n"));
        Object[] withoutEmptyFirstElement = Arrays.stream(allSeparators.split("\\["))
                .filter(s -> !s.isEmpty())
                .toArray();
        String[] delimiters = Arrays.copyOf(withoutEmptyFirstElement, withoutEmptyFirstElement.length, String[].class);
        separators = new String[delimiters.length];

        for (int i = 0; i < delimiters.length; i++) {
            separators[i] = delimiters[i].replace("]", "");
        }
        return separators;
    }

    private int sumNumbers(String numbers, String[] separator) throws NegativeNotAllowedException {
        String[] splittedNumbers = numbers.split(Arrays.toString(separator));
        String[] withoutEmptyValues = removeEmptyValues(splittedNumbers);
        validateNegatives(withoutEmptyValues);
        int result = 0;
        for (String number : withoutEmptyValues) {
            result = sumBelow1000(result, number);
        }
        return result;
    }

    private String[] removeEmptyValues(String[] splittedNumbers) {
        Object[] objects = Arrays.stream(splittedNumbers)
                .filter(s -> !s.isEmpty())
                .toArray();
        return Arrays.copyOf(objects, objects.length, String[].class);
    }

    private int sumBelow1000(int result, String number) {
        if (Integer.parseInt(number) <= 1000) {
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
        return numbers.equals("") || numbers.isEmpty();
    }

}
