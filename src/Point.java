/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;


public class Point implements Comparable<Point> {

    // compare points by slope
   public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        
        
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
    	double numerator = (this.y - that.y);
    	double denominator = (this.x - that.x);
    	
    	if(numerator == 0 && denominator == 0) {
        	//DEGENERATE LINE SEGMENT
        	return Double.NEGATIVE_INFINITY;
        }
    	else if (denominator == 0){
    		return Double.POSITIVE_INFINITY;
    	}
    	else if (numerator == 0 ) {
    		return +0;
    	}
     	
        double slope = numerator/denominator;
        
        return slope;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if((this.y < that.y) ){
        	return -1;
        }
        else if ((this.y == that.y)) {
        	 if(this.x < that.x) {
        		 return -1;
        	 }
        	 else if (this.x == that.x) {
        		 return 0;
        	 }
        	 else {
        		 return 1;
        	 }
        }
        else {
        	return 1;
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
    private class SlopeOrder implements Comparator<Point>{

    	@Override
    	public int compare(Point p2, Point p3) {
    		Point p1 = Point.this;
    		double slopep1p2 = p1.slopeTo(p2);
    		double slopep1p3 = p1.slopeTo(p3);
    		if (slopep1p2 < slopep1p3) {
    			return -1;
    		}
    		else if (slopep1p2 == slopep1p3) {
    			return 0;
    		}
    		else {
    			return 1;
    		}
    	}	
    }
}