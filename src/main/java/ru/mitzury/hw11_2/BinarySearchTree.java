package ru.mitzury.hw11_2;

import ru.mitzury.hw11.SearchTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> implements SearchTree<T> {

    private Node<T> root;

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
        }
    }

    public BinarySearchTree(List<T> sortedList) {
        if (sortedList == null || sortedList.isEmpty())
            throw new IllegalArgumentException("List is empty");

        this.root = build(sortedList, 0, sortedList.size() - 1);
    }

    private Node<T> build(List<T> list, int left, int right) {
        if (left > right)
            return null;

        int mid = (left + right) / 2;

        Node<T> node = new Node<>(list.get(mid));
        node.left = build(list, left, mid - 1);
        node.right = build(list, mid + 1, right);

        return node;
    }

    @Override
    public T find(T element) {
        return findRecursive(root, element);
    }

    private T findRecursive(Node<T> node, T element) {
        if (node == null)
            return null;

        int cmp = element.compareTo(node.value);

        if (cmp == 0)
            return node.value;
        if (cmp < 0)
            return findRecursive(node.left, element);
        else
            return findRecursive(node.right, element);
    }

    @Override
    public List<T> getSortedList() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<T> node, List<T> result) {
        if (node == null)
            return;

        inOrder(node.left, result);
        result.add(node.value);
        inOrder(node.right, result);
    }
}