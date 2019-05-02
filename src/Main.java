/**
 * Kenia Rioja-Naranjo
 * CSC 401
 * Assignment 3 - Problem 4/4
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] array = createArray(getNumber());
        System.out.print("Array: ");
        printArray(array);
        MaxSubarray max = findMaxSubarray(array, 0, array.length - 1);
        max.print();
        System.out.print("Total number of inversions: " + sortAndCount(array) + "\nSorted array: ");
        printArray(array);


        //
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

        System.out.print("Enter a positive integer: ");
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