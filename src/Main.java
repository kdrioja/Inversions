import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            ArrayList<Integer> test = createArray(8);
            printArray(test);
        }

        ArrayList<Integer> test = createArray(15);
        printArray(test);

        test = createArray(getNumber());
        printArray(test);
    }

    public static ArrayList createArray(int n) {
        ArrayList result = new ArrayList<Integer>(n);
        //int min = Math.negateExact(n);
        //Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int newNum = (int) (Math.random() * (2 * n + 2) + Math.negateExact(n + 1));
            result.add(newNum);
            //result.add(rand.nextInt(((n - min) + 1) + min));
            //rand.ints(Math.negateExact(n), n+1);
            //System.out.println(rand.nextInt());
            //int randX = (int) (Math.random () * (totalRange + 1) + startPoint)
            //System.out.println((int) (Math.random() * (n + 1) + Math.negateExact(n)));
        }

        return result;
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

    public static void printArray(ArrayList<Integer> array) {
        //System.out.println(array.size());
        System.out.print("[");
        for (int i = 0; i < array.size() - 1; i++) {
            System.out.print(array.get(i) + ", ");
        }
        System.out.print(array.get(array.size() - 1) + "]\n");
    }
}