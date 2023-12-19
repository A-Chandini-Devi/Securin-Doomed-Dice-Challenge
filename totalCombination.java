import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class totalCombination {
    public static void main(String args[]) {
        // self assigning the die Faces numerically as an array
        int[] a = { 1, 2, 3, 4, 5, 6 };
        int[] b = { 1, 2, 3, 4, 5, 6 };

        System.out.println();

        // Total Combinations
        // FORMULA Combinations = no. of Faces^No. of outcomes needed = 6^1 * 6^1
        System.out.println("Toatal number of Combinations: " + a.length * b.length);
        System.out.println();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                System.out.print("<" + a[i] + "," + b[j] + ">" + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Sum of the all 36 possible outcomes of the two dice are:");

        // To find the probability lets have a bucket array which keeps track of
        // Frequency
        int[] bucket = new int[13];
        for (int i = 0; i < 13; i++)
            bucket[i] = 0;
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {

                // List<List<Integer>> ol = new ArrayList<>();
                List<Integer> il = new ArrayList<>();
                il.add(i + 1);
                il.add(j + 1);
                if ((!map.containsKey(i + 1 + j + 1))) {
                    List<List<Integer>> ol = new ArrayList<>();
                    ol.add(il);
                    map.put(i + 1 + j + 1, ol);
                } else {
                    map.get(i + 1 + j + 1).add(il);
                }

                System.out.print(a[i] + b[j] + "  ");
                bucket[i + j + 2]++;

                if ((j == 4 || j == 5) && (i != 4) && (i != 5))
                    System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println("\nProbablity of all the unique sums are:\n");
        for (int i = 2; i < 13; i++) {
            System.out.println("Sum considered is: " + i);
            System.out.println(map.get(i));
            System.out.println("Probability of sum " + i + " occurence is: " + (float) bucket[i] / 36);
            System.out.println();
        }
    }
}