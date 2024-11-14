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

        System.out.println(Arrays.toString(zigZagDumb(example1)));
        System.out.println(Arrays.toString(zigZagDumb(example2)));
        System.out.println(Arrays.toString(zigZagDumb(example3)));
        System.out.println(Arrays.toString(zigZagDumb(example4)));
        System.out.println(Arrays.toString(zigZagDumb(example5)));

        System.out.println(Arrays.toString(zipZagSmart(example1)));
        System.out.println(Arrays.toString(zipZagSmart(example2)));
        System.out.println(Arrays.toString(zipZagSmart(example3)));
        System.out.println(Arrays.toString(zipZagSmart(example4)));
        System.out.println(Arrays.toString(zipZagSmart(example5)));
    }

    /**
     * Sorts an array of numbers in an altering pattern.
     * First number is the largest, second is smallest, third is second largest, and so on.
     * I want to demonstrate usage of streams here.
     * @param nums  - An array of numbers, of n size.
     * @return      - A new array that has been sorted in zigzag fashion.
     */
    public int[] zigZagDumb(int[] nums) {
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

    /**
     * We can improve the efficiency of the program by sorting the list first, then alternating reading from both
     * ends of the sorted array.
     * @param nums  - An array of numbers, of n size.
     * @return      - A new array that has been sorted in zigzag fashion.
     */
    public int[] zipZagSmart(int[] nums) {
        if (nums.length <= 1) return nums;

        Arrays.sort(nums);
        int[] result = new int[nums.length];
        int leftIndex = 0, rightIndex = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0)
                result[i] = nums[rightIndex--];
            else
                result[i] = nums[leftIndex++];
        }

        return result;
    }

    public static void main(String[] args) {
        new OddlySorted();
    }

}

