package com.techangelx;

import java.util.*;

public class MinimumLengthSubarray {

    public static int findMinimumLengthSubarray(List<Integer> arr, int k) {

        if (arr == null || arr.isEmpty() || k <= 0) return -1;

        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int distinct = 0;

        for (int right = 0; right < arr.size(); right++) {
            int val = arr.get(right);

            // add freq
            freq.put(val, freq.getOrDefault(val, 0) + 1);
            if (freq.get(val) == 1) { // new distinct
                distinct++;
            }

            // shrink window while valid
            while (distinct >= k) {
                minLength = Math.min(minLength, right - left + 1);

                int remove = arr.get(left);
                freq.put(remove, freq.get(remove) - 1);
                if (freq.get(remove) == 0) {
                    distinct--;
                }
                left++;
            }
        }

        return (minLength == Integer.MAX_VALUE) ? -1 : minLength;
    }
}
