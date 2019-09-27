/* *****************************************************************************
 *  Name: Yanan Zhang
 *  Date: 09/25/2019
 *  Description: Solution for problem deque, used linkedlist to achieve constant
 *  worst-case time.
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int n;


    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    @Override
    public String toString() {
        if (!isEmpty())
            return "Deque{" +
                    "first=" + first.item +
                    ", last=" + last.item +
                    ", n=" + n +
                    '}';
        else
            return "Deque{" +
                    "first=" + null +
                    ", last=" + null +
                    ", n=" + n +
                    '}';
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        // create a new node, handle the assignment of item and prev
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        // assign the value of next based on it is an empty list
        if (isEmpty()) {
            last = first;
            first.next = null;
        }
        else {
            // previously, oldfirst doesn't have prev node, so we need to assign it here.
            oldfirst.prev = first;
            first.next = oldfirst;
        }
        n++;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
            last.prev = null;
        }
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }

        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (n == 0)
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        else {
            first.prev = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (n == 0)
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        n--;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        else {
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        // implement iterator
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
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
        Deque<String> deque = new Deque<String>();
        System.out.println("initialize " + deque.toString());
        deque.addFirst("1");
        deque.addFirst("2");
        System.out.println("add first " + deque.toString());
        deque.addLast("1");
        deque.addLast("2");
        System.out.println("add last " + deque.toString());

        System.out.println("remove first " + deque.removeFirst());

        System.out.println("remove last " + deque.removeLast());
        for (String str : deque) {
            System.out.println("The next value with Iterator is: " + str);
        }

    }

}
