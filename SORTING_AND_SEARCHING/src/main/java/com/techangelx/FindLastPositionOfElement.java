package com.techangelx;

import java.util.List;

public class FindLastPositionOfElement {
    public static int lastOcc(List<Integer> nums, int target) {
        int left = 0, right = nums.size() - 1;
        int lastIndexFound = -1; // default return not found
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums.get(mid) == target) {
                lastIndexFound = mid;  // store the index
                left = mid + 1;        // Continue searching right
                
            } else if (nums.get(mid) < target) {// target higher than position
                left = mid + 1; // Search right half
                
            } else {
                right = mid - 1;
            }   
        }
        return lastIndexFound; }

}
