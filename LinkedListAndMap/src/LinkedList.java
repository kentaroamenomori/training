import java.util.NoSuchElementException;

public class LinkedList<E> {

    private Node firstNode;
    private int length = 0;

    boolean isEmpty() {
        return length == 0;
    }

    int size() {
        return length;
    }

    void add(E newElement) {
        length++;

        var newNode = new Node();
        newNode.element = newElement;

        if (firstNode == null) {
            firstNode = newNode;
            firstNode.previous = firstNode;
            firstNode.next = firstNode;
            return;
        }

        var lastNode = firstNode.previous;
        newNode.next = firstNode;
        newNode.previous = lastNode;
        lastNode.next = newNode;
        firstNode.previous = newNode;
    }

    E get(int i) throws Exception {
        if (i >= length || i < 0) {
            throw new IndexOutOfBoundsException();
        }

        var fetchedNode = firstNode;
        if (i < length / 2) {
            while (i > 0) {
                i--;
                fetchedNode = fetchedNode.next;
            }
        } else {
            i = length - i;
            while (i > 0) {
                i--;
                fetchedNode = fetchedNode.previous;
            }
        }
        return fetchedNode.element;
    }

    void remove() {
        if (length == 0) {
            return;
        }

        length--;
        if (length == 0) {
            firstNode = null;
            return;
        }

        var lastNode = firstNode.previous;
        firstNode = firstNode.next;
        lastNode.next = firstNode;
        firstNode.previous = lastNode;
    }

    Iterator iterator() {
        return new Iterator();
    }

    private class Node {
        Node next;
        Node previous;
        E element;
    }

    class Iterator {
        private Node currentNode = firstNode;
        private int iteration = 0;

        private Iterator() {};

        boolean hasNext() {
            return iteration < length;
        }

        E next() throws Exception {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            iteration++;
            currentNode = currentNode.next;
            return currentNode.previous.element;
        }
    }
}
