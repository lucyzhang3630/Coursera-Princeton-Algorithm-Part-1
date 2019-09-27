/* *****************************************************************************
 *  Name: Yanan Zhang
 *  Date: 09/26/2019
 *  Description: Write a client program Permutation.java that takes an integer k
 *  as a command-line argument; reads a sequence of strings from standard input
 *  using StdIn.readString(); and prints exactly k of them, uniformly at random.
 *  Print each item from the sequence at most once.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        String s = StdIn.readString();
        RandomizedQueue<String> rdmQueue = new RandomizedQueue<String>();

        rdmQueue.enqueue(s);
        while (!StdIn.isEmpty()) {
            s = StdIn.readString();
            rdmQueue.enqueue(s);
        }

        for (int j = 0; j < n; j++) {
            System.out.println(rdmQueue.dequeue());
        }

    }
}
