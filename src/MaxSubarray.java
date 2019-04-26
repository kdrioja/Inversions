public class MaxSubarray {
    private int low;
    private int high;
    private int maxSum;

    public MaxSubarray() {
        this.low = -1;
        this.high = -1;
        this.maxSum = -1;
    }

    public MaxSubarray(int low, int high, int maxSum) {
        this.low = low;
        this.high = high;
        this.maxSum = maxSum;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    public void print() {
        System.out.println("Max sum: " + this.maxSum + ", with index range [" + this.low + ", " + this.high + "]");
    }
}