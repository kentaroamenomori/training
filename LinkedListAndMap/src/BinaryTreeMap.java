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
     * @param key Comparableのみ許容
     * @param value
     * @return 追加されたkeyに格納されていたvalueを返す。呼び出し時点でkeyがなければnull、あれば対応するvalueを返す
     */
    @Override
    public V put(K key, V value) {
        var newNode = new Node<K, V>();
        newNode.key = key;
        newNode.value = value;

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
                try {
                    return fetchedNode.value;
                } finally {
                    fetchedNode.value = newNode.value;
                }

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
     * @param key Comparableのみ許容
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
     * valueのみを格納したLinkedListを返す。LinkedListに対する変更はBinaryTreeMapにも反映される。
     * ただremoveが面倒くさいので現状はaddにしか対応していない。
     * @return valueを格納したLinkedList
     */
    public ValuesLinkedList values() {
        return valuesList;
    }

    private static class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
    }

    public class ValuesLinkedList extends LinkedList<V> {
        /**
         * mapとの同期が大変なのでaddをprivateにして外部から呼び出せないようにする。
         * @param value
         */
        private void addNewValue(V value) {
            super.add(value);
        }

        /**
         * 外部からListに値を追加する場合はこのメソッドを利用する。
         * mapにも同時に値が追加され、中身が同期されるようになっている。
         * @param key mapに追加するkey
         * @param value 
         * @return 変更があった場合はtrueが返る（毎回trueが返る）
         */
        public boolean add(K key, V value) {
            put(key, value);
            return true;
        }

        // addは呼び出されたくないので例外を投げるようにしておく
        @Override
        public boolean add(V newElement) {
            throw new UnsupportedOperationException();
        }

        // map本体にremoveを実装していないので例外を投げる
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
