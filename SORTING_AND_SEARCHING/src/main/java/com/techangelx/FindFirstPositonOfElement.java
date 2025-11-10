package com.techangelx;

import java.util.List;

public class FindFirstPositonOfElement {

    public static int firstOcc(List<Integer> nums, int target) {
        int left = 0, right = nums.size() - 1;
        int firstIndexFound = -1; // default return not found
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) == target) {
                firstIndexFound = mid;  // store the index
                right = mid - 1;        // Continue searching left
            } else if (nums.get(mid) < target) {// target higher than position
                left = mid + 1; // Search right half
            } else {
                right = mid - 1;
            }    }
        return firstIndexFound; }

}
