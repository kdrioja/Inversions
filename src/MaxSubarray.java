/**
 * Kenia Rioja-Naranjo
 * CSC 401
 * Assignment 3 - Problem 4/4
 */

public class MaxSubarray {
    private int low;
    private int high;
    private int maxSum;


    public MaxSubarray(int low, int high, int maxSum) {
        this.low = low;
        this.high = high;
        this.maxSum = maxSum;
    }


    public int getMaxSum() {
        return maxSum;
    }


    public void print() {
        System.out.println("Max sum: " + this.maxSum + ", with index range [" + this.low + ", " + this.high + "]");
    }
}