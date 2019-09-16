/* *****************************************************************************
 *  Name: Yanan Zhang
 *  Date: 09/15/2019
 *  Description: Use Monte Carlo simulation to get the percolution threshold
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private StdRandom stdRandom;
    private StdStats stdStats;
    private int testsCount;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        double count;
        double threshold;
        int row;
        int col;

        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException();

        thresholds = new double[trials];
        this.testsCount = trials;
        for (int i = 0; i < trials; i++) {
            row = StdRandom.uniform(n) + 1;
            col = StdRandom.uniform(n) + 1;
            Percolation percolation = new Percolation(n);
            percolation.open(row, col);
            // if lattice percolates, get the threshold
            while (!percolation.percolates()) {
                row = StdRandom.uniform(n) + 1;
                col = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(row, col))
                    percolation.open(row, col);

            }

            count = percolation.numberOfOpenSites();
            threshold = count / (n * n);
            thresholds[i] = threshold;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double meanP = mean();
        double stddevP = stddev();
        double result = meanP - 1.96 * stddevP / Math.sqrt(this.testsCount);
        return result;

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double meanP = mean();
        double stddevP = stddev();
        double result = meanP + 1.96 * stddevP / Math.sqrt(this.testsCount);
        return result;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                                   + percolationStats.confidenceHi() + "]");

    }
}
