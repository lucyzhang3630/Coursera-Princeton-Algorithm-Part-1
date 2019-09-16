/* *****************************************************************************
 *  Name: Yanan Zhang
 *  Date: 09/15/2019
 *  Description: solve the percolation problem using union find algorithom
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[] openStatus;
    private WeightedQuickUnionUF wuf;
    private int size;
    private int openCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new java.lang.IllegalArgumentException();
        openStatus = new int[n * n + 2]; // open status for the grid &virtual top& virtual bottom
        wuf = new WeightedQuickUnionUF(n * n + 2); // virtual top & virtual bottom included
        this.size = n;
        this.openCount = 0;
    }

    private int getIndex(int row, int col) {
        return (row - 1) * this.size + col;
    }

    private boolean checkIndex(int row, int col) {
        return (row >= 1 && row <= this.size && col >= 1 && col <= this.size);

    }

    // opens the site (row, col) if it is not open already, if it is open,
    // check if there is any sites surrounding is also open, if there are, connect them.
    // if it is the top site, connect it to the virtual top, if it the bottom site, connect it to virtual bottom.
    public void open(int row, int col) {
        boolean indexInBoundary = checkIndex(row, col);
        if (!indexInBoundary)
            throw new java.lang.IllegalArgumentException();
        int index = getIndex(row, col);
        if (!isOpen(row, col)) {
            openStatus[index] = 1;
            this.openCount++;
        }

        // connect open sites in top row to virtual top
        if (row == 1 && isOpen(row, col))
            wuf.union(index, 0);
        // connect open sites in bottom row to virtual bottom
        if (row == this.size && isOpen(row, col))
            wuf.union(index, this.size * this.size + 1);
        // check if there is any open sites surrounding the newly opened site,
        // if there is any, connect them.
        if (col - 1 > 0 && isOpen(row, col - 1))
            wuf.union(index, index - 1);
        if (col < this.size && isOpen(row, col + 1))
            wuf.union(index, index + 1);
        if (row - 1 > 0 && isOpen(row - 1, col))
            wuf.union(index, index - this.size);
        if (row < this.size && isOpen(row + 1, col))
            wuf.union(index, index + this.size);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        boolean indexInBoundary = checkIndex(row, col);
        if (!indexInBoundary)
            throw new java.lang.IllegalArgumentException();
        int index = getIndex(row, col);
        return openStatus[index] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        boolean indexInBoundary = checkIndex(row, col);
        if (!indexInBoundary)
            throw new java.lang.IllegalArgumentException();
        int index = getIndex(row, col);
        return wuf.connected(0, index);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openCount;
    }

    // does the system percolate?
    // if the system percolate, the virtual top and virtual bottom should be connected
    public boolean percolates() {
        return wuf.connected(0, this.size * this.size + 1);
    }

    // test client (optional)
    // public static void main(String[] args) {
    //     int n = Integer.parseInt(args[0]);
    //     Percolation percolation = new Percolation(n);
    //     percolation.open(1, 1);
    //     percolation.open(2, 2);
    //     percolation.open(1, 2);
    //     System.out.println(percolation.percolates());
    //
    // }
}
