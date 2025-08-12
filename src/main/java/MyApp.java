import java.util.Scanner;

public class MyApp {

    // Задание 1.
    public static void greetings() {
        System.out.println("Hello\nWorld!\nFrom\nJava");
    }

    // Задание 2.
    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;
        if (sum >= 0) {
            System.out.println("Сумма положительна " + sum);
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // Задание 3.
    public static void selectColor() {
        int data = 8;
        if (data <= 10) {
            System.out.println("Красный");
        } else if (data > 10 && data <= 20) {
            System.out.println("Желтый");
        } else if (data > 20) {
            System.out.println("Зеленый");
        }
    }

    // Задание 4.
    public static void compareNumbers() {
        int a = 10;
        int b = 20;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // Задание 5
    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        int sum;
        if (increment == true) {
            sum = initValue + delta;
            System.out.println("Result " + sum);
        } else {
            sum = initValue - delta;
            System.out.println("Result " + sum);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер задания для просмотра от 1 до 5");

        while (true) {
            int a = sc.nextInt();
            switch (a) {
                case 1:
                    greetings();
                    break;
                case 2:
                    checkSign(a, 2, 3);
                    break;
                case 3:
                    selectColor();
                    break;
                case 4:
                    compareNumbers();
                    break;
                case 5:
                    addOrSubtractAndPrint(a, 2, false);
                    break;
                case 0:
                    System.exit(0);

                default:
                    System.out.println("<UNK> <UNK>");
                    break;
            }

        }


    }
}
