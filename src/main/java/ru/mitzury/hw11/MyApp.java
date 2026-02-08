package ru.mitzury.hw11;

import java.util.List;

public class MyApp {
        public static void main(String[] args) {
            List<Integer> sortedNumbers = List.of(1, 3, 5, 7, 9, 11, 13);

            SearchTree<Integer> tree = new BinarySearchTree<>(sortedNumbers);

            System.out.println(tree.find(7));   // 7
            System.out.println(tree.find(2));   // null
            System.out.println(tree.getSortedList()); // [1, 3, 5, 7, 9, 11, 13]
        }
}


