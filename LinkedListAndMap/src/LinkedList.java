import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {

    private Node<E> firstNode;
    private int length = 0;

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    /**
     * 指定した要素を末尾に追加する
     * @param e 指定する要素
     * @return 常にtrue（Listインターフェース的には変更があった場合にtrueを返す仕様っぽい）
     */
    @Override
    public boolean add(E e) {
        length++;

        var newNode = new Node<E>();
        newNode.element = e;

        if (firstNode == null) {
            firstNode = newNode;
            firstNode.previous = firstNode;
            firstNode.next = firstNode;
            return true;
        }

        // 循環リストになっているので、firstNode.previous、
        // lastNode.nextの参照先をnewNodeに指し変える
        var lastNode = firstNode.previous;

        newNode.next = firstNode;
        newNode.previous = lastNode;

        lastNode.next = newNode;
        firstNode.previous = newNode;
        return true;
    }

    /**
     * 指定したインデックスの要素を取得する。インデックスが
     * Listの範囲を超えている場合は例外を投げる。
     * インデックスが小さい場合は前から、大きい場合は後ろから走査する。
     * @param i インデックス
     * @return インデックスにある要素
     */
    @Override
    public E get(int i) {
        if (i >= length || i < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 走査中のノードを格納
        var fetchedNode = firstNode;
        // iが小さい場合は通常の順序で走査
        if (i < length / 2) {
            while (i > 0) {
                i--;
                fetchedNode = fetchedNode.next;
            }

        // iが大きい場合は逆順に走査
        } else {
            i = length - i;
            while (i > 0) {
                i--;
                fetchedNode = fetchedNode.previous;
            }
        }
        return fetchedNode.element;
    }

    /**
     * 最初の要素を削除する。
     * @return 変更があった場合はtrue、なかった場合（呼び出し時点で要素が0個）はfalseを返す
     */
    public boolean remove() {
        if (length == 0) {
            return false;
        }

        length--;
        if (length == 0) {
            firstNode = null;
            return true;
        }

        // 循環リスト
        // firstNodeをnewFirstNode(firstNode.next)に差し替える
        // lastNode.nextの参照先もnewFirstNodeに差し替える
        var newFirstNode = firstNode.next;
        var lastNode = firstNode.previous;
        
        lastNode.next = newFirstNode;
        newFirstNode.previous = lastNode;

        this.firstNode = newFirstNode;
        return true;
    }

    @Override
    public LinkedListIterator iterator() {
        return new LinkedListIterator();
    }

    private static class Node<E> {
        Node<E> next;
        Node<E> previous;
        E element;
    }

    public class LinkedListIterator implements Iterator<E> {
        private Node<E> currentNode = firstNode;
        private int iteration = 0;

        private LinkedListIterator() {};

        public boolean hasNext() {
            return iteration < length;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            iteration++;
            
            // currentNodeをその次のものに差し替え、
            // 差し替え前のcurrentNodeのelementを返す
            var oldCurrentNode = currentNode;
            this.currentNode = currentNode.next;
            return oldCurrentNode.element;
        }
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }    

    @Override
    public E[] toArray() {
        return null;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {
        
    }

    @Override
    public E set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }
}
