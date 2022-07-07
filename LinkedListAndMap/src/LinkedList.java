import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

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
     * 指定した要素を末尾に追加する。
     * @param e
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
     * @param i 
     * @return インデックスに該当する要素
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
     * インデックスで指定した要素を削除する。
     * @param index 
     * @return 削除された要素
     */
    @Override
    public E remove(final int index) {
        if (length == 0) {
            return null;
        } else if (length == 1) {
            var previousElement = firstNode.element;
            firstNode = null;
            return previousElement;
        }

        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 走査中のノードを格納
        var fetchedNode = firstNode;
        // 走査用のインデックス
        int i = index;
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

        var previousNode = fetchedNode.previous;
        var nextNode = fetchedNode.next;

        previousNode.next = nextNode;
        nextNode.previous = previousNode;

        // firstNodeが削除された場合
        if (index == 0) {
            firstNode = nextNode;
        }

        length--;

        return fetchedNode.element;
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

        @Override
        public boolean hasNext() {
            return iteration < length;
        }

        @Override
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

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
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
