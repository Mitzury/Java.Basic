package ru.mitzury.hw11;


import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> implements SearchTree<T> {

    private final Node<T> root;
    private final List<T> sortedList;

    public BinarySearchTree(List<T> sortedList) {
        this.sortedList = new ArrayList<>(sortedList);
        this.root = buildTree(this.sortedList, 0, this.sortedList.size() - 1);
    }

    // Построение дерева из отсортированного списка
    private Node<T> buildTree(List<T> list, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        Node<T> node = new Node<>(list.get(mid));

        node.left = buildTree(list, left, mid - 1);
        node.right = buildTree(list, mid + 1, right);

        return node;
    }

    // Публичный метод поиска
    @Override
    public T find(T element) {
        return findRecursive(root, element);
    }

    // Рекурсивный поиск
    private T findRecursive(Node<T> node, T element) {
        if (node == null) {
            return null;
        }

        int cmp = element.compareTo(node.value);

        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return findRecursive(node.left, element);
        } else {
            return findRecursive(node.right, element);
        }
    }

    @Override
    public List<T> getSortedList() {
        return new ArrayList<>(sortedList);
    }

    // Внутренний класс узла дерева
    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
        }
    }
}
