package com.techangelx;

import java.util.List;

public class FindFirstPositonOfElement {

    public static int firstOcc(List<Integer> nums, int target) {
        int left = 0, right = nums.size() - 1, indexPosition = -1; 

        while (left <= right) { // Prevent Integer overflow for int mid = (left + right) / 2;
            int mid = left + (right - left) / 2; 

            if (nums.get(mid) == target) {
                indexPosition = mid;
                right = mid - 1; // Continue searching in the left half
            } else if (nums.get(mid) < target) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }

        return indexPosition;
    }
}
