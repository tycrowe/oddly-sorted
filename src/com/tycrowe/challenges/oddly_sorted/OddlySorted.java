package com.tycrowe.challenges.oddly_sorted;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class OddlySorted {
    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public OddlySorted() {
        int[] example1 = new int[]{3, 1, 4, 1, 5, 9};
        int[] example2 = new int[]{10, -1, -5, 7, 2};
        int[] example3 = new int[]{0, 0, 0};
        int[] example4 = new int[]{};
        int[] example5 = new int[]{42};

        System.out.println(Arrays.toString(zigZag(example1)));
        System.out.println(Arrays.toString(zigZag(example2)));
        System.out.println(Arrays.toString(zigZag(example3)));
        System.out.println(Arrays.toString(zigZag(example4)));
        System.out.println(Arrays.toString(zigZag(example5)));
    }

    /**
     * Sorts an array of numbers in an altering pattern.
     * First number is the largest, second is smallest, third is second largest, and so on.
     * @param nums  - An array of numbers, of n size.
     * @return      - A new array that has been sorted.
     */
    public int[] zigZag(int[] nums) {
        try {
            if (nums.length == 0) throw new Exception("Integer is empty.");
            if (nums.length == 1) return nums;
            List<Integer> sortedArray = new ArrayList<>();
            List<Integer> numsRemaining = new ArrayList<>(Arrays.stream(nums).boxed().toList());

            for (int i = 0; i < nums.length; i++) {
                if(i % 2 == 0) {
                    int indexOfLargestRemainingNumber = IntStream
                            .range(0, numsRemaining.size())
                            .boxed()
                            .max(Comparator.comparingInt(numsRemaining::get))
                            .orElse(-1);

                    sortedArray.add(numsRemaining.get(indexOfLargestRemainingNumber));
                    numsRemaining.remove(indexOfLargestRemainingNumber);
                } else {
                    int indexOfSmallestRemainingNumber = IntStream
                            .range(0, numsRemaining.size())
                            .boxed()
                            .min(Comparator.comparingInt(numsRemaining::get))
                            .orElse(-1);

                    sortedArray.add(numsRemaining.get(indexOfSmallestRemainingNumber));
                    numsRemaining.remove(indexOfSmallestRemainingNumber);
                }
            }

            return sortedArray.stream().mapToInt(i->i).toArray();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        new OddlySorted();
    }

}

