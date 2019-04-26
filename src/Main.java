import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
        for (int i = 0; i < 50; i++) {
            ArrayList<Integer> test = createArray(8);
            printArray(test);
        }

        ArrayList<Integer> test = createArray(15);
        printArray(test);

        test = createArray(getNumber());
        printArray(test);
         */
        /**
        ArrayList<Integer> a = new ArrayList<>();
        a.add(10);
        a.add(15);
        a.add(22);
        a.add(80);
        a.add(90);
        printArray(a);

        ArrayList<Integer> b = new ArrayList<>();
        b.add(5);
        b.add(8);
        b.add(11);
        b.add(15);
        b.add(70);
        printArray(b);

        ArrayList<Integer> c = new ArrayList<>();
        System.out.println(mergeAndCount(a, b, c));
        printArray(c);
        */
        /**
         ArrayList<Integer> example = new ArrayList<>();
         example.add(1);
         example.add(5);
         example.add(4);
         example.add(8);
         example.add(10);
         example.add(2);
         printArray(example);
         System.out.println("# inversions: " + sortAndCount(example));
         */

        int[] left = {10, 15, 22, 80, 90};
        int[] right = {5, 8, 11, 15, 70};
        int[] merged = new int[left.length + right.length];
        System.out.println(mergeAndCount(left, right, merged));
        printArray(merged);

        int[] orig = {1, 5, 4, 8, 10, 2, 6, 9, 12, 11, 3, 7};
        printArray(sortAndCount(orig));
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
        //int min = Math.negateExact(n);
        //Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int newNum = (int) (Math.random() * (2 * n + 2) + Math.negateExact(n + 1));
            result[i] = newNum;
            //result.add(rand.nextInt(((n - min) + 1) + min));
            //rand.ints(Math.negateExact(n), n+1);
            //System.out.println(rand.nextInt());
            //int randX = (int) (Math.random () * (totalRange + 1) + startPoint)
            //System.out.println((int) (Math.random() * (n + 1) + Math.negateExact(n)));
        }
        return result;
    }


    public static void printArray(int[] array) {
        //System.out.println(array.size());
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.print(array[array.length - 1] + "]\n");
    }

    public static int[] sortAndCount(int[] array) {
        if (array.length == 1)
            return array;

        else {
            int middle = array.length / 2;
            int[] left = new int[middle];
            System.arraycopy(array, 0, left, 0, middle);
            printArray(left);
            int[] right = new int[array.length - middle];
            System.arraycopy(array, middle + 1, right, 0, middle);
            printArray(right);

            sortAndCount(left);
            sortAndCount(right);
            int[] merged = new int[left.length + right.length];
            System.out.println(mergeAndCount(left, right, merged));
            return merged;


            /*
            int leftInversions = sortAndCount(array, low, middle);
            int rightInversions = sortAndCount(array, middle + 1, high);
            int[] merged = new int[high - low];
            int mergedInversions = mergeAndCount()
            return leftInversions + rightInversions + mergedInversions;
            */
        }
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

    /*
    public static int mergeAndCount(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int count = 0;
        int pointerA = 0, pointerB = 0;

        while (pointerA < A.size() && pointerB < B.size()) {
            if (A.get(pointerA) <= B.get(pointerB)) {
                C.add(A.get(pointerA));
                pointerA++;
            }
            else {
                C.add(B.get(pointerB));
                count += A.size() - pointerA;
                pointerB++;
            }
        }

        if (pointerA == A.size()) {
            while (pointerB < B.size()) {
                C.add(B.get(pointerB));
                pointerB++;
            }
        }
        else {
            while (pointerA < A.size()) {
                C.add(A.get(pointerA));
                pointerA++;
            }
        }

        return count;
    }
    */

    /*
    public static int sortAndCount(ArrayList<Integer> array) {
        if (array.size() <= 1)
            return 0;
        else {
            int mid = array.size() / 2;
            System.out.println("mid val: " + mid);
            System.out.println("end: " + (array.size() - 1) + "\n");
            ArrayList<Integer> a = new ArrayList<>(array.subList(0, mid));
            //printArray(a);
            ArrayList<Integer> b = new ArrayList<>(array.subList(mid + 1, array.size()));
            //printArray(b);
            ArrayList<Integer> c = new ArrayList<>();
            int countA = sortAndCount(a);
            int countB = sortAndCount(b);
            int countC = mergeAndCount(a, b, c);
            printArray(c);
            return countA + countB + countC;
        }
    }
    */

    /*
    public static void printArray(ArrayList<Integer> array) {
        //System.out.println(array.size());
        System.out.print("[");
        for (int i = 0; i < array.size() - 1; i++) {
            System.out.print(array.get(i) + ", ");
        }
        System.out.print(array.get(array.size() - 1) + "]\n");
    }
    */
}