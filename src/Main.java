import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InputException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение (подобно a + b, a - b, a * b, a / b) :");
        String input = scanner.nextLine();

        // допустимый операторы
        String[] arithmeticSigns = {"+", "-", "*", "/"};

        // давайте найдем оператор
        int indexof = -1;
        // давайте проверим, содержит ли выражение какие-либо требуемые операции
        // если да, то найдем знак, а точнее его положение в массиве
        for (int i = 0; i < arithmeticSigns.length; i = i + 1) {
            if (input.contains(arithmeticSigns[i])) {
                indexof = i;
                break;
            }
        }

        // допустимый операторы (+, -, /, *) не найдено
        if (indexof == -1) {
            throw new InputException("Строка не является математической операцией");
        }

        // давайте найдем числа

        // regex подобно "\\-", "\\+", "\\*", "\\/"
        String literalChar = "\\";
        String regEx = literalChar.concat(arithmeticSigns[indexof]);
        String[] explode = input.split(regEx);

        if (explode.length != 2) {
            throw new InputException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }


        if (!isInteger(explode[0]) || !isInteger(explode[1])) {
            throw new InputException("Строка не является числовое выражение");
        }

        int x = Integer.parseInt(explode[0]);
        int y = Integer.parseInt(explode[1]);

        // Калькулятор умеет работать только с целыми числами.
        if (!(x >= 1 && y >= 1 && x <= 10 && y <= 10)) {
            throw new InputException("Числа должны быть целыми числами от 1 до 10");
        }
        int output = 0;

        switch (arithmeticSigns[indexof]) {
            case "+":
                output = x + y;
                break;
            case "-":
                output = x - y;
                break;
            case "*":
                output = x * y;
                break;
            default:
                output = x / y;
                break;
        }

        System.out.println(output);


    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}