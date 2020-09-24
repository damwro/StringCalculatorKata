package damwro;

public class Calculator {

    public int add(String numbers) {
        if (numbers.equals("")) {
            return 0;
        }
        String[] splittedNumbers = numbers.split(",");
        if (splittedNumbers.length == 2) {
            return Integer.parseInt(splittedNumbers[0]) + Integer.parseInt(splittedNumbers[1]);
        }
        return Integer.parseInt(numbers);
    }

}
