package ru.mitzury.hw4;

public class MyApp {

    public static void main(String[] args) {

        User[] users = new User[] {
                new User("Иванов", "Иван", "Петрович", 2000, "ivan25@example.com"),
                new User("Петров", "Пётр", "Сергеевич", 2005, "petr18@example.com"),
                new User("Сидоров", "Андрей", "Иванович", 1989, "sid34@example.com"),
                new User("Алексеев", "Алексей", "Петрович", 1990, "alex45@example.com"),
                new User("Фёдоров", "Фёдор", "Александрович", 1920, "fedor59@example.com"),
                new User("Николаев", "Николай", "Фёдорович", 1964, "nik72@example.com"),
                new User("Кузнецов", "Максим", "Егорович", 1962, "kuz80@example.com"),
                new User("Соловьёв", "Олег", "Николаевич", 1904, "oleg27@example.com"),
                new User("Орлов", "Дмитрий", "Викторович", 1995, "orl63@example.com"),
                new User("Яковлев", "Антон", "Михайлович", 1998, "yak90@example.com")
        };

        for (int i = 0; i < users.length; i++) {
            int age = 2025 - users[i].birthYear;
            if (age > 40) {
                users[i].ListUsers();
                System.out.println();
            }
        }

        Box box = new Box(10, 20, 30, "красный");

        box.printInfo();
        box.open();
        box.putItem("Телефон");
        box.printInfo();
        box.removeItem();
        box.repaint("синий");
        box.close();

    }

    public static class User {
        private String surname;     // Фамилия
        private String name;        // Имя
        private String fname;       // Отчество
        private int birthYear;      // Год рождения
        private String email;       // Email

        // Конструктор
        public User(String surname, String name, String fname, int birthYear, String email) {
            this.surname = surname;
            this.name = name;
            this.fname = fname;
            this.birthYear = birthYear;
            this.email = email;
        }

        public void ListUsers() {
            System.out.println("ФИО: " + surname + " " + name + " " + fname);
            System.out.println("Год рождения: " + birthYear);
            System.out.println("e-mail: " + email);
        }
    }

    public static class Box {
        private final int width;
        private final int height;
        private final int depth;

        private String color;
        private boolean isOpen;
        private String item;


        public Box(int width, int height, int depth, String color) {
            this.width = width;
            this.height = height;
            this.depth = depth;
            this.color = color;
            this.isOpen = false;
            this.item = null;
        }
        public void open() {
            if (!isOpen) {
                isOpen = true;
                System.out.println("Коробка открыта.");
            } else {
                System.out.println("Коробка уже открыта.");
            }
        }
        public void close() {
            if (isOpen) {
                isOpen = false;
                System.out.println("Коробка закрыта.");
            } else {
                System.out.println("Коробка уже закрыта.");
            }
        }
        public void repaint(String newColor) {
            System.out.println("Коробка перекрашена: " + color + " → " + newColor);
            color = newColor;
        }
        public void putItem(String itemName) {
            if (!isOpen) {
                System.out.println("Нельзя положить предмет. Коробка закрыта.");
                return;
            }

            if (item != null) {
                System.out.println("В коробке уже лежит предмет: " + item);
                return;
            }

            item = itemName;
            System.out.println("Предмет \"" + itemName + "\" помещён в коробку.");
        }
        public void removeItem() {
            if (!isOpen) {
                System.out.println("Нельзя выкинуть предмет. Коробка закрыта.");
                return;
            }

            if (item == null) {
                System.out.println("В коробке нет предмета.");
                return;
            }

            System.out.println("Предмет \"" + item + "\" удалён из коробки.");
            item = null;
        }
        public void printInfo() {
            System.out.println("Коробка:");
            System.out.println("Размеры: " + width + " x " + height + " x " + depth);
            System.out.println("Цвет: " + color);
            System.out.println("Состояние: " + (isOpen ? "открыта" : "закрыта"));
            System.out.println("Предмет внутри: " + (item == null ? "нет" : item));
        }
    }
}