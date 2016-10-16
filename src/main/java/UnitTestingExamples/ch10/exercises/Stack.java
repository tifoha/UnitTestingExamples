package UnitTestingExamples.ch10.exercises;

import java.util.NoSuchElementException;

/**
 * Created by Vitaly on 15.10.2016.
 */
public class Stack<E> {
    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head;
    private int size;

    public E pop() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return popValue();
    }

    private E popValue() {
        size--;
        Node<E> node = head;
        head = node.next;
        return node.value;
    }

    public int size() {
        return size;
    }

    public void push(E element) {
        pushValue(element);
    }

    private void pushValue(E element) {
        Node<E> node = new Node<E>(element, head);
        head = node;
        size++;
    }
}
