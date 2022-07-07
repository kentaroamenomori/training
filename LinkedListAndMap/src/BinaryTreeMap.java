import java.util.Map;
import java.util.Set;

public class BinaryTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> root;
    private int numberOfNodes = 0;
    private ValuesLinkedList valuesList = new ValuesLinkedList();

    public boolean isEmpty() {
        return numberOfNodes == 0;
    }

    public int size() {
        return numberOfNodes;
    }

    /**
     * keyとvalueのペアを追加する。
     * @param key
     * @param value
     * @return 追加されたkeyに格納されていたvalueを返す。呼び出し時点でkeyがなければnull、あれば対応するvalueを返す
     */
    @Override
    public V put(K key, V value) {
        var newNode = new Node<K, V>(key, value);

        if (root == null) {
            root = newNode;
            numberOfNodes++;
            valuesList.addNewValue(value);
            return null;
        }

        // 現時点で走査中のノードを格納する
        var fetchedNode = root;
        while (true) {
            // 既に同じキーが存在している場合はvalueを更新する
            if (key.compareTo(fetchedNode.key) == 0) {
                var oldValue = fetchedNode.value;
                fetchedNode.value = newNode.value;
                return oldValue;

            } else if (key.compareTo(fetchedNode.key) < 0) {
                // 空いているノードに辿り着いた時点で格納
                if (fetchedNode.left == null) {
                    fetchedNode.left = newNode;
                    break;
                }
                fetchedNode = fetchedNode.left;

            } else if (key.compareTo(fetchedNode.key) > 0) {
                // 空いているノードに辿り着いた時点で格納
                if (fetchedNode.right == null) {
                    fetchedNode.right = newNode;
                    break;
                }
                fetchedNode = fetchedNode.right;
            }
        }

        numberOfNodes++;
        valuesList.addNewValue(value);
        return null;
    }

    /**
     * 指定したkeyでvalueを取得する。
     * @param key
     * @return 指定したkeyの要素がなければnull、あればvalueを返す
     */
    public V get(K key) {
        if (root == null) {
            return null;
        }

        // 現時点で走査中のノードを格納する
        var fetchedNode = root;
        while (true) {
            // キーを発見した場合はそのvalueを返す
            if (key.compareTo(fetchedNode.key) == 0) {
                return fetchedNode.value;

            } else if (key.compareTo(fetchedNode.key) < 0) {
                if (fetchedNode.left == null) {
                    break; // 該当するキーが存在しない
                }
                fetchedNode = fetchedNode.left;

            } else if (key.compareTo(fetchedNode.key) > 0) {
                if (fetchedNode.right == null) {
                    break; // 該当するキーが存在しない
                }
                fetchedNode = fetchedNode.right;
            }
        }
        return null; // キーがない場合はnullを返す
    }

    /**
     * valueのみを格納したLinkedListを返す。mapへの要素追加はListに反映される。
     * List側からの中身の変更は不可。
     * @return valueを格納したLinkedList
     */
    public ValuesLinkedList values() {
        return valuesList;
    }

    private static class Node<K extends Comparable<K>, V> {
        final K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public class ValuesLinkedList extends LinkedList<V> {
        // mapとの同期が大変なのでaddをprivateにして外部から呼び出せないようにする。
        private void addNewValue(V value) {
            super.add(value);
        }

        @Override
        public boolean add(V newElement) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V remove(int i) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
    }

    @Override
    public void clear() {
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public V get(Object key) {
        return null;
    }
}
