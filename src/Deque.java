import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    
    private Node first;
    private Node last;
    private int numOfItems;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        numOfItems = 0;
    }
    
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return numOfItems == 0;
    }

    // return the number of items on the deque
    public int size() {
        return numOfItems;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        if (oldFirst == null) last = first;
        first.item = item;
        first.prev = null;
        first.next = oldFirst;
        if (oldFirst != null) oldFirst.prev = first;
        numOfItems++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        if (oldLast == null) first = last;
        last.item = item;
        last.prev = oldLast;
        if (oldLast != null) oldLast.next = last;
        last.next = null;
        numOfItems++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (numOfItems == 0) throw new java.util.NoSuchElementException();
        numOfItems--;
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
            return item;
        }
        first.prev = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (numOfItems == 0) throw new java.util.NoSuchElementException();
        numOfItems--;
        Item item = last.item;
        last = last.prev;
        if (last == null) {
            first = null;
            return item;
        }
        last.next = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> i = new Deque<Integer>();
        i.addFirst(10);
        i.addLast(8);
        i.addLast(6);
        i.addFirst(12);
        i.addFirst(14);
        StdOut.println(i.removeLast());
        StdOut.println(i.removeFirst());
        i.removeFirst();
        i.removeFirst();
        i.removeFirst();
        i.addLast(7);
        StdOut.println(i.removeFirst());
        
        StdOut.println();
        StdOut.println();
        
        for (Integer s : i) {
            StdOut.println(s);
        }
        
        StdOut.println(i.size());
        StdOut.println(i.isEmpty());
    }

}