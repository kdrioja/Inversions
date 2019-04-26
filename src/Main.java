/**
 * Kenia Rioja-Naranjo
 * CSC 401
 * Assignment 3 - Problem 4/4
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        int[] left = {10, 15, 22, 80, 90};
        int[] right = {5, 8, 11, 15, 70};
        int[] merged = new int[left.length + right.length];
        System.out.println(mergeAndCount(left, right, merged));
        printArray(merged);
        */

        /*
        int[] orig = {1, 5, 4, 8, 10, 2, 6, 9, 12, 11, 3, 7};
        System.out.println("# of inversion: " + sortAndCount(orig));
        printArray(orig);
        */

        /*
        int[] maxSub = {3, -1, 5, 2, 1, -6};
        MaxSubarray m = findMaxSubarray(maxSub, 0, 5);
        m.print();

        int[] test = {-2, -4, 3, -1, 5, 7, -7, -1};
        MaxSubarray t = findMaxSubarray(test, 0, test.length - 1);
        t.print();

        int[] reck = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        MaxSubarray r = findMaxSubarray(reck, 0, reck.length - 1);
        r.print();
        */
    }


    public static MaxSubarray findMaxSubarray(int[] array, int low, int high) {
        if (low == high)
            return new MaxSubarray(low, high, array[low]);

        else {
            int middle = (low + high) / 2;

            MaxSubarray left = findMaxSubarray(array, low, middle);
            MaxSubarray right = findMaxSubarray(array, middle + 1, high);
            MaxSubarray cross = findMaxCrossing(array, low, middle, high);

            if (left.getMaxSum() > right.getMaxSum() && left.getMaxSum() > cross.getMaxSum())
                return left;
            else if (right.getMaxSum() > left.getMaxSum() && right.getMaxSum() > cross.getMaxSum())
                return right;
            else
                return cross;
        }
    }


    public static MaxSubarray findMaxCrossing(int[] array, int low, int middle, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int leftIndex = -1;

        for (int i = middle; i >= low; i--) {
            sum += array[i];

            if (sum > leftSum) {
                leftSum = sum;
                leftIndex = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int rightIndex = -1;

        for (int i = middle + 1; i <= high; i++) {
            sum += array[i];

            if (sum > rightSum) {
                rightSum = sum;
                rightIndex = i;
            }
        }

        return new MaxSubarray(leftIndex, rightIndex, leftSum + rightSum);
    }


    public static int getNumber() {
        int num;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        num = scanner.nextInt();

        while (num < 1) {
            System.out.println("Number must be greater than 0");
            System.out.print("Enter an integer: ");
            num = scanner.nextInt();
        }

        return num;
    }


    public static int[] createArray(int n) {
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int newNum = (int) (Math.random() * (2 * n + 2) + Math.negateExact(n + 1));
            result[i] = newNum;
        }
        return result;
    }


    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.print(array[array.length - 1] + "]\n");
    }


    public static int mergeAndCount(int[] left, int[] right, int[] merged) {
        int count = 0;
        int leftPointer = 0, rightPointer = 0, mergedPointer = 0;

        while (leftPointer < left.length && rightPointer < right.length) {
            if (left[leftPointer] <= right[rightPointer]) {
                merged[mergedPointer] = left[leftPointer];
                leftPointer++;
                mergedPointer++;
            }
            else {
                merged[mergedPointer] = right[rightPointer];
                count += left.length - leftPointer;
                rightPointer++;
                mergedPointer++;
            }
        }

        if (leftPointer == left.length) {
            while (rightPointer < right.length) {
                merged[mergedPointer] = right[rightPointer];
                rightPointer++;
                mergedPointer++;
            }
        }
        else {
            while (leftPointer < left.length) {
                merged[mergedPointer] = left[leftPointer];
                leftPointer++;
                mergedPointer++;
            }
        }

        return count;
    }


    public static int sortAndCount(int[] array) {
        int numOfInversion = 0;

        if (array.length == 1)
            return 0;
        else {
            int middle = array.length / 2;

            int[] left = new int[middle];
            int[] right = new int[array.length - middle];

            System.arraycopy(array, 0, left, 0, middle);
            System.arraycopy(array, middle, right, 0, array.length - middle);

            numOfInversion += sortAndCount(left);
            numOfInversion += sortAndCount(right);
            numOfInversion += mergeAndCount(left, right, array);

            return numOfInversion;
        }
    }
}