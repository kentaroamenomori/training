public class BinaryTreeMap<K extends Comparable<K>, V> {

    private Node root;
    private int numberOfNodes = 0;
    private LinkedList<V> valuesList = new LinkedList<>();

    boolean isEmpty() {
        return numberOfNodes == 0;
    }

    int size() {
        return numberOfNodes;
    }

    void put(K key, V value) {
        valuesList.add(value);

        if (root == null) {
            root = new Node();
            root.key = key;
            root.value = value;
            return;
        }

        var addedNode = new Node();
        addedNode.key = key;
        addedNode.value = value;

        var parent = root;
        while (true) {
            if (key == parent.key) {
                parent.value = addedNode.value;
                return;
            }

            if (key.compareTo(parent.key) < 0) {
                if (parent.left == null) {
                    parent.left = addedNode;
                    break;
                }
                parent = parent.left;
            } else {
                if (parent.right == null) {
                    parent.right = addedNode;
                    break;
                }
                parent = parent.right;
            }
        }
        numberOfNodes++;
    }

    V get(K key) {
        if (root == null) {
            return null;
        }

        var parent = root;
        while (true) {
            if (key == parent.key) {
                return parent.value;
            }

            if (key.compareTo(parent.key) < 0) {
                if (parent.left == null) {
                    break;
                }
                parent = parent.left;
            } else {
                if (parent.right == null) {
                    break;
                }
                parent = parent.right;
            }
        }
        return null;
    }

     // abstract collection
    LinkedList<V> values() {
        return valuesList;
    }

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
    }
}
