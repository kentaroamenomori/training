public class BinaryTreeMap<K extends Comparable<K>, V> {

    private Node root;
    private int numberOfNodes = 0;
    private ValuesLinkedList valuesList = new ValuesLinkedList();

    boolean isEmpty() {
        return numberOfNodes == 0;
    }

    int size() {
        return numberOfNodes;
    }

    void put(K key, V value) {
        var newNode = new Node();
        newNode.key = key;
        newNode.value = value;

        if (root == null) {
            root = newNode;
            numberOfNodes++;
            valuesList.addNewValue(value);
            return;
        }

        var fetchedNode = root;
        while (true) {
            if (key.compareTo(fetchedNode.key) == 0) {
                fetchedNode.value = newNode.value;
                return;

            } else if (key.compareTo(fetchedNode.key) < 0) {
                if (fetchedNode.left == null) {
                    fetchedNode.left = newNode;
                    break;
                }
                fetchedNode = fetchedNode.left;

            } else if (key.compareTo(fetchedNode.key) > 0) {
                if (fetchedNode.right == null) {
                    fetchedNode.right = newNode;
                    break;
                }
                fetchedNode = fetchedNode.right;
            }
        }

        numberOfNodes++;
        valuesList.addNewValue(value);
    }

    V get(K key) {
        if (root == null) {
            return null;
        }

        var fetchedNode = root;
        while (true) {
            if (key.compareTo(fetchedNode.key) == 0) {
                return fetchedNode.value;

            } else if (key.compareTo(fetchedNode.key) < 0) {
                if (fetchedNode.left == null) {
                    break;
                }
                fetchedNode = fetchedNode.left;

            } else if (key.compareTo(fetchedNode.key) > 0) {
                if (fetchedNode.right == null) {
                    break;
                }
                fetchedNode = fetchedNode.right;
            }
        }
        return null;
    }

    ValuesLinkedList values() {
        return valuesList;
    }

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
    }

    public class ValuesLinkedList extends LinkedList<V> {
        // 外部からaddされたくない（同期が大変）のでaddをprivateにした別のメソッドに格納しておく
        private void addNewValue(V value) {
            super.add(value);
        }

        // 外部から要素を追加する場合はこれを利用する
        void add(K key, V value) {
            put(key, value);
        }

        // キーを自動生成してmapと同期させるのが大変なので例外を投げる
        @Override
        void add(V newElement) {
            throw new UnsupportedOperationException();
        }

        // map本体にremoveを実装していないので例外を投げる
        @Override
        void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
