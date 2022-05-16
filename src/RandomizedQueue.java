import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int numOfItems;
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object [1];
         numOfItems = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return numOfItems == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return numOfItems;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (arr.length == numOfItems) resize(numOfItems*2);
        arr[numOfItems++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        if (numOfItems == arr.length/4) resize(arr.length/2);
        int position = StdRandom.uniform(numOfItems);
        Item item = arr[position];
        arr[position] = arr[--numOfItems];
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return arr[StdRandom.uniform(numOfItems)];
    }

    private void resize(int n) {
        Item[] copy  = (Item[]) new Object [n];
        for (int i = 0; i < numOfItems; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }
    
    private class RandomIterator implements Iterator<Item> {

        private Item[] copy;
        private int sz;
        
        public RandomIterator() {
            copy = (Item[]) new Object[numOfItems];
            for (int i = 0; i < numOfItems; i++) {
                copy[i] = arr[i];
            }
            sz = numOfItems;
        }
        public boolean hasNext() {
            return sz != 0;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            int position = StdRandom.uniform(sz);
            Item item = copy[position];
            copy[position] = copy[--sz];
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        s.enqueue("Stephen");
        s.enqueue("Chidiebere");
        s.enqueue("Ivuelekwa");
        s.enqueue(".");
        StdOut.println(s.dequeue());
        
        StdOut.println();
        for (String sp : s) {
           StdOut.println(sp); 
        }
        StdOut.println();
        StdOut.println(s.size());
        StdOut.println();
        StdOut.println(s.isEmpty());
    }

}