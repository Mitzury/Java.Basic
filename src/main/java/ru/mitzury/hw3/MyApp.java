package ru.mitzury.hw3;

public class MyApp {

    public static int sumOfPositiveElements(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            for (int element : row) {
                if (element > 0) {
                    sum += element;
                }
            }
        }
        return sum;
    }

    public static void printSquare(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void zeroDiagonals(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            matrix[i][i] = 0;
            matrix[i][n - 1 - i] = 0;
        }
    }

    public static int findMax(int[][] array) {
        int max = array[0][0];
        for (int[] row : array) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    public static int sumSecondRow(int[][] array) {
        if (array.length < 2) {  // Проверяем, есть ли хотя бы 2 строки
            return -1;
        }

        int sum = 0;
        for (int value : array[1]) { // Берём строку с индексом 1
            sum += value;
        }
        return sum;
    }

    public static void main(String[] args) {

        System.out.println("Part 1");
        int[][] matrix = {
                {1, -3, 5},
                {0, 7, -2},
                {-1, 4, 6}
        };
        int result = sumOfPositiveElements(matrix);
        System.out.println("Сумма положительных элементов: " + result);

        System.out.println("Part 2");
        printSquare(5); // пример вызова

        System.out.println("Part 3");
        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        zeroDiagonals(matrix2);

        // Печать результата
        for (int[] row : matrix2) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }

        System.out.println("Part 4");
        int maxElement = findMax(matrix2);
        System.out.println("Максимальный элемент массива: " + maxElement);


        System.out.println("Part 5");
        int[][] matrixx = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrixy = {
                {10, 20, 30}
        };

        System.out.println("Сумма второй строки (matrix1): " + sumSecondRow(matrixx)); // 15
        System.out.println("Сумма второй строки (matrix2): " + sumSecondRow(matrixy)); // -1
    }
}