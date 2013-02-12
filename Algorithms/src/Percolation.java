
public class Percolation {
	private int n;
	private boolean[] openSites;
	private WeightedQuickUnionUF wf;
	private WeightedQuickUnionUF wf2;
	
	public Percolation(int N) {
		// create N-by-N grid, with all sites blocked
		n = N;
		// virtual top and bottom will always be open
		openSites = new boolean[n * n + 2];
		// n*n will be virtual top
		// n*n+1 will be virtual bottom
		wf = new WeightedQuickUnionUF(n * n + 2);
		//one more WQUF having only virtual top
		//n*n will be virtual top
		wf2 = new WeightedQuickUnionUF(n * n + 1);
		
		openSites[n * n] = true;
		openSites[n * n + 1] = true;

	}
	private void validate(int i, int j) {
		if (i <= 0 || j <= 0 || i >= n + 1 || j >= n + 1) {
			throw new java.lang.IndexOutOfBoundsException();
		}
	}
	private int toOneDimension(int i, int j) {
		// using row major order
		validate(i, j);
		return (i - 1) * n + (j - 1);
	}

	public void open(int i, int j) {
		// open site (row i, column j) if it is not already
		validate(i, j);
		int index = toOneDimension(i, j);
		int connectIndex = -1;
		openSites[index] = true;
		// connect to left
		if (i > 1 && isOpen(i - 1, j)) {
			connectIndex = toOneDimension(i - 1, j);
			wf.union(index, connectIndex);
			wf2.union(index, connectIndex);
		}
		// connect to right
		if ((i + 1) <= n && isOpen(i + 1, j)) {
			connectIndex = toOneDimension(i + 1, j);
			wf.union(index, connectIndex);
			wf2.union(index, connectIndex);
		}
		// connect to top
		if (j > 1 && isOpen(i, j - 1)) {
			connectIndex = toOneDimension(i, j - 1);
			wf.union(index, connectIndex);
			wf2.union(index, connectIndex);
		}
		// connect to bottom
		if ((j + 1) <= n && isOpen(i, j + 1)) {
			connectIndex = toOneDimension(i, j + 1);
			wf.union(index, connectIndex);
			wf2.union(index, connectIndex);
		}
		
			if (i == 1) {
				wf.union(index, n * n);
				wf2.union(index, n * n);
			}
	//	if(!percolates()){
			if (i == n) {
				wf.union(index, n * n + 1);
			}
		//}
	}

	public boolean isOpen(int i, int j) {
		// is site (row i, column j) open?
		validate(i, j);
		int index = toOneDimension(i, j);
		return openSites[index];

	}

	public boolean isFull(int i, int j) {
		// is site (row i, column j) full?
		validate(i, j);
		int virtualTop = n * n;
		int siteIndex = toOneDimension(i, j);
		return wf2.connected(virtualTop, siteIndex);
	}

	public boolean percolates() {
		// does the system percolate?
		int virtualTop = n * n;
		int virtualBottom = n * n + 1;
		return wf.connected(virtualTop, virtualBottom);
	}

	public static void main(String[] args) {
		Percolation perc = new Percolation(3);
		perc.open(1, 1);
		System.out.println("1: "+perc.isOpen(1, 1));
		System.out.println("1: "+perc.isFull(1, 1));
		perc.open(2, 1);
		System.out.println("2: "+perc.isFull(1, 1));
		System.out.println("2: "+perc.percolates());
		perc.open(3, 1);
		System.out.println("3: "+perc.isFull(1, 1));
		System.out.println("3: "+perc.percolates());
	}
}
