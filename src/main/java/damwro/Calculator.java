package damwro;

public class Calculator {

    public int add(String numbers) {
        if (numbers.equals("")) {
            return 0;
        }
        String[] splittedNumbers = numbers.split(",");
        int result = 0;
        for (String number : splittedNumbers) {
            result += Integer.parseInt(number);
        }
        return result;
    }

}
