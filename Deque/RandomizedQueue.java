/* *****************************************************************************
 *  Name: Yanan Zhang
 *  Date: 09/25/2019
 *  Description: Solution for problem randomizedQueue, used array to achieve
 *  constant amortized time
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int n;

    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[0];
        n = 0;
    }

    @Override
    public String toString() {
        return "RandomizedQueue{" +
                "n=" + n +
                ", items=" + Arrays.toString(items) +
                '}';
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        // if empty, add the item,
        if (isEmpty()) {
            resize(2);
        }
        else if (n == items.length) {
            resize(2 * n);
        }
        items[n] = item;
        n++;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(n);
        Item result = items[index];
        // remove the selected element
        items[index] = items[n - 1];
        items[n - 1] = null;
        n--;
        if (n > 0 && n == items.length / 4)
            resize(items.length / 2);

        return result;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(n);
        Item result = items[index];
        return result;

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = n;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return items[--i];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rdmQueue = new RandomizedQueue<String>();
        rdmQueue.enqueue("1");
        rdmQueue.enqueue("2");
        System.out.println("enqueue " + rdmQueue.toString());
        System.out.println("sample " + rdmQueue.sample());
        System.out.println("deque " + rdmQueue.dequeue());
        System.out.println("after deque " + rdmQueue.toString());

    }

}
