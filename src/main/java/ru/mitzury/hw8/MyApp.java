package ru.mitzury.hw8;

public class MyApp {

    // ===== Исключение: неверный размер массива =====
    static class AppArraySizeException extends RuntimeException {
        public AppArraySizeException(String message) {
            super(message);
        }
    }

    // ===== Исключение: неверные данные в ячейке =====
    static class AppArrayDataException extends RuntimeException {
        public AppArrayDataException(int row, int col, String value) {
            super("Invalid data at [" + row + "][" + col + "]: '" + value + "'");
        }
    }

    // ===== Логика обработки массива =====
    static int sumArray(String[][] array) {
        validateSize(array);

        int sum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException(i, j, array[i][j]);
                }
            }
        }

        return sum;
    }

    private static void validateSize(String[][] array) {
        if (array.length != 4) {
            throw new AppArraySizeException("Array must be 4x4");
        }

        for (String[] row : array) {
            if (row.length != 4) {
                throw new AppArraySizeException("Array must be 4x4");
            }
        }
    }

    public static void main(String[] args) {
        String[][] data = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16","23"}
        };

        try {
            int result = sumArray(data);
            System.out.println("Sum = " + result);
        } catch (AppArraySizeException | AppArrayDataException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
