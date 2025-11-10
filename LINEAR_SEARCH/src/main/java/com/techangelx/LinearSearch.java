package com.techangelx;

import java.util.List;

public class LinearSearch {
    
    public static List<Integer> linearSearch(List<Integer> nums, int target) {
        int n = nums.size(), position = -1;
        
        for (int i=0; i < n; i++) {
            if (nums.get(i) == target) {
                position = i;
                return List.of(position, nums.get(i));
                
            }
        } return List.of(-1);
                
    }
        
}
