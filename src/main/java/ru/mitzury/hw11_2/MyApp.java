package ru.mitzury.hw11_2;

import ru.mitzury.hw11.SearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApp {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        SearchTree<Integer> tree = new BinarySearchTree<>(list);

        System.out.println(tree.find(6));   // 6
        System.out.println(tree.find(100)); // null
        System.out.println(tree.getSortedList()); // [1..9]
    }
}
