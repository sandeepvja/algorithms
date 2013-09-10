public class PercolationStats {
    private int N;
    private int T;
    private Percolation perc;
    private double[] results;

    public PercolationStats(int N, int T) {
        // perform T independent computational experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        this.T = T;

        results = new double[T];
        for (int i = 0; i < T; i++) {
            perc = new Percolation(N);
            results[i] = performExperiment();
        }
    }

    private double performExperiment() {

        long count = 0;

        int i, j;
        StdRandom.setSeed(System.currentTimeMillis());

        while (!perc.percolates()) {
            i = StdRandom.uniform(N) + 1;
            j = StdRandom.uniform(N) + 1;
            if (!perc.isOpen(i, j)) {
                perc.open(i, j);
                count++;
            }
        }
        // System.out.println("Count: "+count);
        double ratio = (double) count / (N * N);
        // System.out.println("Ratio: "+ratio);
        return ratio;
    }

    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
        // sample standard deviation of percolation threshold
    }

    public double confidenceLo() {
        double mean;
        double variance;
        double sqrtT = Math.sqrt(T);
        double stddev;
        mean = mean();
        stddev = stddev();
        variance = Math.sqrt(stddev);
        return mean - 1.96 * variance / sqrtT;
    }

    public double confidenceHi() {
        double mean;
        double variance;
        double sqrtT = Math.sqrt(T);
        double stddev;
        mean = mean();
        stddev = stddev();
        variance = Math.sqrt(stddev);
        return mean + 1.96 * variance / sqrtT;
    }

    public static void main(String[] args) {
        // test client, described below
        int N, T;
        double confidenceMin, confidenceMax;
        double mean, stddev;

        N = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        mean = stats.mean();
        stddev = stats.stddev();

        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        confidenceMin = stats.confidenceLo();
        confidenceMax = stats.confidenceHi();
        System.out.println("95% confidence interval = " + "" + confidenceMin + ", "
                + confidenceMax);
    }
}