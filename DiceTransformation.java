package Part_B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiceTransformation {
    private static List<List<Integer>> diceA = new ArrayList<>();
    private static List<List<Integer>> diceB = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> Die_A = new ArrayList<>();
        Die_A.add(1);
        Die_A.add(2);
        Die_A.add(3);
        Die_A.add(4);
        Die_A.add(5);
        Die_A.add(6);

        List<Integer> Die_B = new ArrayList<>();
        Die_B.add(1);
        Die_B.add(2);
        Die_B.add(3);
        Die_B.add(4);
        Die_B.add(5);
        Die_B.add(6);

        System.out.println("Original Dice A = " + Die_A);
        System.out.println("Original Dice B = " + Die_B);

        int total_combinations = Die_A.size() * Die_B.size();

        Map<Integer, Integer> original_sums = new HashMap<>();
        for (int i = 0; i < Die_A.size(); i++) {
            for (int j = 0; j < Die_B.size(); j++) {
                int temp = Die_A.get(i) + Die_B.get(j);
                int val = original_sums.getOrDefault(temp, 0) + 1;
                original_sums.put(temp, val);
            }
        }

        Map<Integer, Double> original_probabilities = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : original_sums.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            original_probabilities.put(key, (double) val / total_combinations);
        }

        System.out.println("\nProbabilities in original form: ");
        for (Map.Entry<Integer, Double> entry : original_probabilities.entrySet()) {
            int key = entry.getKey();
            double val = entry.getValue();
            System.out.println("Sum considered = " + key + "\nNo. of times the sum occurred = " + original_sums.get(key)
                    + "\nProbability of the sum occurred = " + val);
        }

        Map<Integer, Integer> new_dic = original_sums;

        find_diceA_possibility(1, new ArrayList<>());
        find_diceB_possibility(1, new ArrayList<>());

        System.out.println("Undooming Tool in process for Dice A and Dice B...!");
        for (List<Integer> i : diceA) {
            for (List<Integer> j : diceB) {
                Map<Integer, Integer> tdic = new HashMap<>();
                for (int k = 0; k < i.size(); k++) {
                    for (int l = 0; l < j.size(); l++) {
                        int val = tdic.getOrDefault(i.get(k) + j.get(l), 0) + 1;
                        tdic.put(i.get(k) + j.get(l), val);
                    }
                }

                boolean isValid = true;
                for (Map.Entry<Integer, Integer> entry : tdic.entrySet()) {
                    int key = entry.getKey();
                    int val = entry.getValue();
                    if (val != new_dic.getOrDefault(key, -1)) {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    System.out.println("New transformed undoomed Dice A -> " + i);
                    System.out.println("New transformed undoomed Dice B -> " + j);

                    System.out.println("\nProbability of Dice After the process of undooming is:");
                    for (Map.Entry<Integer, Integer> entry : tdic.entrySet()) {
                        int key = entry.getKey();
                        int val = entry.getValue();
                        System.out.println("Sum considered = " + key + "\nNo. of times the sum occurred = " + val
                                + "\nProbability of the sum occurred = " + (double) val / total_combinations);
                    }
                    return;
                }
            }
        }
    }

    public static void find_diceA_possibility(int number, List<Integer> temp) {
        if (number > 4) {
            return;
        }
        if (temp.size() > 6) {
            return;
        }
        if (temp.size() == 6) {
            if (!diceA.contains(temp)) {
                diceA.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = number; i < 5; i++) {
            List<Integer> newTemp = new ArrayList<>(temp);
            newTemp.add(i);
            find_diceA_possibility(i, newTemp);
        }
    }

    public static void find_diceB_possibility(int number, List<Integer> temp) {
        if (number > 11) {
            return;
        }
        if (temp.size() > 6) {
            return;
        }
        if (temp.size() == 6) {
            if (!diceB.contains(temp)) {
                diceB.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = number + 1; i < 13; i++) {
            List<Integer> newTemp = new ArrayList<>(temp);
            newTemp.add(i);
            find_diceB_possibility(i, newTemp);
        }
    }
}
